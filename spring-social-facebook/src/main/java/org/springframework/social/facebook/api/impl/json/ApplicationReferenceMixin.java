package org.springframework.social.facebook.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class ApplicationReferenceMixin extends FacebookObjectMixin {

	@JsonCreator
	ApplicationReferenceMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("name") String name, 
			@JsonProperty("url") String url) {}
	
}
