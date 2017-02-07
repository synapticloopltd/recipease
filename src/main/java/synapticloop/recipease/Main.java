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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import synapticloop.recipease.bean.ConfigBean;
import synapticloop.recipease.function.FunctionStartsWith;
import synapticloop.recipease.model.Recipease;
import synapticloop.templar.Parser;
import synapticloop.templar.exception.FunctionException;
import synapticloop.templar.exception.ParseException;
import synapticloop.templar.exception.RenderException;
import synapticloop.templar.utils.TemplarContext;
import synapticloop.util.SimpleUsage;
import synapticloop.util.simplelogger.Logger;

public class Main {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Main.class);

	private static final String RECIPEASE_TEMPLAR_XML = "src/main/resources/recipease.templar.xml";
	private static final String OUTPUT_DIRECTORY = "build/docs";

	//	<fo:simple-page-master margin-right="1.5cm" 
	//			margin-left="1.5cm" 
	//			margin-bottom="1.5cm" 
	//			margin-top="1.5cm" 
	//			page-width="21cm" 
	//			page-height="29.7cm" master-name="both">
	//
	//		<fo:region-body margin-top="0.5cm" margin-bottom="1.7cm"/>
	//		<fo:region-before extent="0.5cm"/>
	//		<fo:region-after extent="1.5cm"/>

	private static Map<String, ConfigBean> OUTPUT_MAP = new HashMap<String, ConfigBean>();
	static {
		OUTPUT_MAP.put("./build/docs/a4.pdf", 
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
	}


	private static void renderPDF(String outputFile, String contents) throws FileNotFoundException, FOPException, TransformerException {
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outputFile)));

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
		File file = new File(OUTPUT_DIRECTORY);
		file.mkdirs();

		if(args.length != 1) {
			SimpleUsage.usageAndExit();
		}

		String jsonFile = args[0];

		// now we need to go through and get all of the import statements
		File recipeaseFile = new File(jsonFile);
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(getFileContents(recipeaseFile));

			// now that we have the JSONObject - go through each of the sections and 
			// find the import files
			File baseDirectory = recipeaseFile.getParentFile().getAbsoluteFile();

			JSONArray sectionsArray = jsonObject.getJSONArray("sections");
			for (Object section : sectionsArray) {
				JSONObject sectionObject = (JSONObject)section;
				LOGGER.info(String.format("Retrieving recipes for '%s'", sectionObject.optString("title")));

				int recipeOffset = 0;
				JSONArray recipesArray = sectionObject.getJSONArray("recipes");
				for (Object recipe : recipesArray) {
					JSONObject recipeObject = (JSONObject)recipe;
					String importLocation = recipeObject.optString("import", null);
					if(null != importLocation) {
						JSONObject parseRecipe = parseRecipe(baseDirectory.getPath(), importLocation);
						if(null != parseRecipe) {
							// go through and add the object to the current position in the array
							LOGGER.info(String.format("Successfully parsed recipe '%s', from '%s'", parseRecipe.optString("title"), importLocation));
						} else {
							parseRecipe = new JSONObject();
							parseRecipe.put("title", String.format("Could not read recipe from '%s'", importLocation));
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

		Iterator<String> iterator = OUTPUT_MAP.keySet().iterator();
		while (iterator.hasNext()) {
			String outputFile = (String) iterator.next();

			try {
				TemplarContext templarContext = new TemplarContext();
				templarContext.addFunction("startsWith", new FunctionStartsWith());
				templarContext.add("recipease", parseResponse(jsonObject.toString()));
				templarContext.add("config", OUTPUT_MAP.get(outputFile));

				Parser parser = new Parser(new File(RECIPEASE_TEMPLAR_XML));

				renderPDF(outputFile, parser.render(templarContext));
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
