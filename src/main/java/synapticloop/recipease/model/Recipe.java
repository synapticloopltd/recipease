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

public class Recipe {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Recipe.class);

	@JsonProperty("title") private String title = "I never knew what to call this...";
	@JsonProperty("ingredients") private List<String> ingredients;
	@JsonProperty("directions") private List<String> directions;
	@JsonProperty("information") private List<Information> information;
	@JsonProperty("notes") private List<String> notes;

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		LOGGER.error("No native setter for key '" + name + "' with value '" + value + "'");
	}

	public String getTitle() { return this.title; }
	public List<String> getIngredients() { return this.ingredients; }
	public List<String> getDirections() { return this.directions; }
	public List<Information> getInformation() { return this.information; }
	public List<String> getNotes() { return this.notes; }

}
