package synapticloop.recipease;

/*
 * Copyright (c) 2017 Synapticloop.
 * 
 * All rights reserved.
 * 
 * This code may contain contributions from other parties which, where 
 * applicable, will be listed in the default build file for the project 
 * ~and/or~ in a file named CONTRIBUTORS.txt in the root of the project.
 * 
 * This source code and any derived binaries are covered by the terms and 
 * conditions of the Licence agreement ("the Licence").  You may not use this 
 * source code or any derived binaries except in compliance with the Licence.  
 * A copy of the Licence is available in the file named LICENSE.txt shipped with 
 * this source code or binaries.
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopConfParser;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import synapticloop.recipease.bean.ConfigBean;
import synapticloop.recipease.function.FunctionStartsWith;
import synapticloop.recipease.model.Recipe;
import synapticloop.recipease.model.Recipease;
import synapticloop.recipease.model.Section;
import synapticloop.templar.Parser;
import synapticloop.templar.exception.FunctionException;
import synapticloop.templar.exception.ParseException;
import synapticloop.templar.exception.RenderException;
import synapticloop.templar.utils.TemplarContext;
import synapticloop.util.SimpleUsage;
import synapticloop.util.simplelogger.Logger;

public class Main {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Main.class);

	private static final String JSON_KEY_SECTIONS = "sections";
	private static final String JSON_KEY_TITLE = "title";
	private static final String JSON_KEY_IMPORT = "import";
	private static final String JSON_KEY_RECIPES = "recipes";
	private static final String JSON_KEY_IMAGE = "image";

	private static final String RECIPEASE_TEMPLAR_XML = "src/main/resources/recipease.templar.xml";

	private static Map<String, ConfigBean> OUTPUT_MAP = new HashMap<String, ConfigBean>();
	static {
		OUTPUT_MAP.put("./a4.pdf", 
				new ConfigBean("1.5cm", 
						"1.5cm", 
						"1.5cm", 
						"1.5cm", 
						"21cm", 
						"29.7cm", 
						"0.5cm", 
						"1.7cm", 
						"0.5cm", 
						"1.5cm", 
						"18cm"));
		OUTPUT_MAP.put("./mobile.pdf", 
				new ConfigBean("0.5cm", 
						"0.5cm", 
						"2.2cm", 
						"0.5cm", 
						"12cm", 
						"18cm", 
						"0.5cm", 
						"0.3cm", 
						"0.5cm", 
						"0.5cm", 
						"11cm"));
	}


	private static void renderPDF(File baseDirectory, String outputFile, String contents) throws FileNotFoundException, FOPException, TransformerException {
		URI uri = new File(".").toURI();
		LOGGER.info("Generating from '" + uri.toString() + "'.");
		URI uriBaseDirectory = baseDirectory.toURI();
		LOGGER.info("Setting base directory URI to '" + uriBaseDirectory + "'.");
		FopFactory fopFactory = FopFactory.newInstance(uriBaseDirectory);

		File file = new File(outputFile);
		LOGGER.info("Outputting file to '" + file.getAbsolutePath() + "'.");
		OutputStream out = new BufferedOutputStream(new FileOutputStream(file));

		try {
			// Step 3: Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(); // identity transformer

			Source src = new StreamSource(new StringReader(contents));

			Result res = new SAXResult(fop.getDefaultHandler());

			transformer.transform(src, res);
		} finally {
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	private static String getFileContents(File file) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		String line = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
		} catch (IOException ex) {
			throw(ex);
		} finally {
			if(null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException ignored) {
				}
			}
		}
		return (stringBuilder.toString());
	}

	private static ObjectMapper initializeObjectMapperJson() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
		return mapper;
	}

	private static Recipease parseResponse(String contents) throws IOException {
		return initializeObjectMapperJson().readValue(contents, Recipease.class);
	}

	/**
	 * The main entry point for the programme
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			SimpleUsage.usageAndExit();
		}

		String jsonFile = args[0];

		// now we need to go through and get all of the import statements
		File recipeaseFile = new File(jsonFile);
		JSONObject jsonObject = null;
		File baseDirectory= null;
		try {
			jsonObject = new JSONObject(getFileContents(recipeaseFile));

			// now that we have the JSONObject - go through each of the sections and 
			// find the import files
			baseDirectory = recipeaseFile.getParentFile().getAbsoluteFile();

			JSONArray sectionsArray = jsonObject.getJSONArray(JSON_KEY_SECTIONS);
			for (Object section : sectionsArray) {
				JSONObject sectionObject = (JSONObject)section;
				LOGGER.info(String.format("Retrieving recipes for '%s'", sectionObject.optString(JSON_KEY_TITLE)));
				String imageValue = sectionObject.getString(JSON_KEY_IMAGE);
				if(!imageValue.startsWith("/")) {
					//					sectionObject.put(JSON_KEY_IMAGE, "file:" + baseDirectory.getAbsolutePath() + "/" + imageValue);
					//					sectionObject.put(JSON_KEY_IMAGE, "file:./" + imageValue);
					sectionObject.put(JSON_KEY_IMAGE, baseDirectory.getAbsolutePath() + "/" + imageValue);
				}

				int recipeOffset = 0;
				JSONArray recipesArray = sectionObject.getJSONArray(JSON_KEY_RECIPES);
				for (Object recipe : recipesArray) {
					JSONObject recipeObject = (JSONObject)recipe;
					String importLocation = recipeObject.optString(JSON_KEY_IMPORT, null);
					if(null != importLocation) {
						JSONObject parseRecipe = parseRecipe(baseDirectory.getPath(), importLocation);
						if(null != parseRecipe) {
							// go through and add the object to the current position in the array
							LOGGER.info(String.format("Successfully parsed recipe '%s', from '%s'", parseRecipe.optString(JSON_KEY_TITLE), importLocation));
						} else {
							parseRecipe = new JSONObject();
							parseRecipe.put(JSON_KEY_TITLE, String.format("Could not read recipe from '%s'", importLocation));
							LOGGER.fatal(String.format("Could not parse recipe from '%s'", importLocation));
						}
						recipesArray.put(recipeOffset, parseRecipe);
					}
					recipeOffset++;
				}
			}

		} catch (JSONException | IOException ex) {
			LOGGER.fatal(String.format("Could not read or parse the recipease JSON file, message was: %s", ex.getMessage()));
			System.exit(-1);
		}

		// Get a list of all of the essentials, and puth them in order
		Recipease recipease = null;
		HashMap<String, List<Recipe>> categoryLookup = new LinkedHashMap<>();
		Set<String> categorySet = new HashSet<>();

		try {
			recipease = parseResponse(jsonObject.toString());

			// now we need to go through all of the categories
			List<Section> sections = recipease.getSections();
			for (Section section : sections) {
				List<Recipe> recipes = section.getRecipes();
				for (Recipe recipe : recipes) {
					List<String> categories = recipe.getCategories();
					if(null != categories) {
						categorySet.addAll(categories);
					}
				}
			}

			List<String> categoryList = new ArrayList<>();
			Iterator<String> iterator = categorySet.iterator();
			while (iterator.hasNext()) {
				categoryList.add(iterator.next());
			}

			Collections.sort(categoryList);
			for (String category : categoryList) {
				categoryLookup.put(category, new ArrayList<>());
			}

			for (Section section : sections) {
				List<Recipe> recipes = section.getRecipes();
				for (Recipe recipe : recipes) {
					List<String> categories = recipe.getCategories();
					if(null != categories) {
						for (String category : categories) {
							List<Recipe> list = categoryLookup.get(category);
							list.add(recipe);
						}
					}
				}
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			// todo - log something here
			System.exit(-1);
		}

		Iterator<String> iterator = OUTPUT_MAP.keySet().iterator();
		while (iterator.hasNext()) {
			String outputFile = (String) iterator.next();

			try {
				TemplarContext templarContext = new TemplarContext();
				if(!templarContext.hasFunction("startsWith")) {
					templarContext.addFunction("startsWith", new FunctionStartsWith());
				}


				templarContext.add("recipease", recipease);
				templarContext.add("config", OUTPUT_MAP.get(outputFile));
				templarContext.add("outputMap", outputFile);
				templarContext.add("categories", categoryLookup);

				Parser parser = new Parser(new File(RECIPEASE_TEMPLAR_XML));

				renderPDF(baseDirectory, outputFile, parser.render(templarContext));
			} catch (JSONException | IOException | FOPException | TransformerException | ParseException | RenderException | FunctionException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static JSONObject parseRecipe(String path, String filename) {
		try {
			JSONObject jsonObject = new JSONObject(getFileContents(new File(String.format("%s/%s", path, filename))));

			return(jsonObject);
		} catch (JSONException | IOException ex) {
			LOGGER.fatal(String.format("Could not import JSON recipe object '%s'", filename));
		}
		return(null);
	}
}
