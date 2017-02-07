package synapticloop.recipease.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import synapticloop.util.simplelogger.Logger;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Recipe {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Recipe.class);

	@JsonProperty("title") private String title;
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
