package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.social.facebook.api.ads.TargetingLocation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sebastian Górecki
 */
public class TargetingLocationSerializer extends JsonSerializer<TargetingLocation> {
	@Override
	public void serialize(TargetingLocation location, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		if (location.getCountries() != null) {
			jgen.writeObjectField("countries", location.getCountries());
		}
		if (location.getRegions() != null) {
			serializeLocationValueEntries("regions", location.getRegions(), jgen);
		}
		if (location.getCities() != null) {
			jgen.writeObjectField("cities", location.getCities());
		}
		if (location.getZips() != null) {
			serializeLocationValueEntries("zips", location.getZips(), jgen);
		}
		if (location.getLocationTypes() != null) {
			jgen.writeObjectField("location_types", location.getLocationTypes());
		}
		jgen.writeEndObject();
	}

	private void serializeLocationValueEntries(String entryName, List<String> entries, JsonGenerator jgen) throws IOException {
		jgen.writeFieldName(entryName);
		jgen.writeStartArray();
		for (String entry : entries) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", entry);
			jgen.writeObject(map);
		}
		jgen.writeEndArray();
	}
}
