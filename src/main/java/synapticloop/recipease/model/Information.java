package synapticloop.recipease.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Information {
	@JsonIgnore private Map<String, Object> information = new LinkedHashMap<String, Object>();

	public Map<String, Object> getInformation() { return this.information; }

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		information.put(name, value);
	}

}
