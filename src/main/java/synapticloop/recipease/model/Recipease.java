package synapticloop.recipease.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import synapticloop.util.simplelogger.Logger;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Recipease {
	private static Logger LOGGER = Logger.getLoggerSimpleName(Recipease.class);

	@JsonProperty("title") private String title;
	@JsonProperty("image") private String image;
	@JsonProperty("subTitle") private String subTitle;
	@JsonProperty("tocTitle") private String tocTitle;
	@JsonProperty("sections") private List<Section> sections;

	public String getTitle() { return this.title; }
	public String getImage() { return this.image; }
	public String getSubTitle() { return this.subTitle; }
	public String getTocTitle() { return this.tocTitle; }
	public List<Section> getSections() { return this.sections; }

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		LOGGER.error("No native setter for key '" + name + "' with value '" + value + "'");
	}
}
