package synapticloop.recipease.model;

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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import synapticloop.util.simplelogger.Logger;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Recipease {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Recipease.class);

	@JsonProperty("title") private String title = "My Delicious Un-Named Recipes";
	@JsonProperty("image") private String image;
	@JsonProperty("subTitle") private String subTitle;
	@JsonProperty("tocTitle") private String tocTitle = "Table of Contents";
	@JsonProperty("ingredientsTitle") private String ingredientsTitle = "Ingredients";
	@JsonProperty("notesTitle") private String notesTitle = "Notes";
	@JsonProperty("directionsTitle") private String directionsTitle = "Directions";
	@JsonProperty("sections") private List<Section> sections;

	public String getTitle() { return this.title; }
	public String getImage() { return this.image; }
	public String getSubTitle() { return this.subTitle; }
	public String getTocTitle() { return this.tocTitle; }
	public String getIngredientsTitle() { return this.ingredientsTitle; }
	public String getNotesTitle() { return this.notesTitle; }
	public String getDirectionsTitle() { return this.directionsTitle; }
	public List<Section> getSections() { return this.sections; }

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		LOGGER.error("No native setter for key '" + name + "' with value '" + value + "'");
	}
}
