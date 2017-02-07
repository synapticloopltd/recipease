package synapticloop.recipease.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import synapticloop.util.simplelogger.Logger;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Section {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Section.class);

	@JsonProperty("title") private String title;
	@JsonProperty("image") private String image;
	@JsonProperty("recipes") private List<Recipe> recipes;

	public String getTitle() { return this.title; }
	public String getImage() { return this.image; }
	public List<Recipe> getRecipes() { return this.recipes; }

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		LOGGER.error("No native setter for key '" + name + "' with value '" + value + "'");
	}
}
