package com.uniovi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.uniovi.entitites.Incident;

public class InciSerializer extends JsonSerializer<Incident> {

	@Override
	public void serialize(Incident incident, JsonGenerator generator, SerializerProvider sp)
			throws IOException, JsonProcessingException {

		generator.writeStartObject();

		// agent:
		generator.writeObjectFieldStart("agent");
		generator.writeStringField("username", incident.getAgent().getUsername());
		generator.writeNumberField("kind", incident.getAgent().getKind());
		generator.writeEndObject();

		// location:
		generator.writeObjectFieldStart("location");
		generator.writeStringField("location", incident.getLocation());
		// generator.writeNumberField("latitude", incident.getLocation().getLatitude());
		// generator.writeNumberField("longitude",
		// incident.getLocation().getLongitude());
		generator.writeEndObject();

		// tags
		generator.writeArrayFieldStart("tags");
		for (String tag : incident.getTags()) {
			generator.writeString(tag);
		}
		generator.writeEndArray();

		generator.writeObjectFieldStart("property_values");
		for (String key : incident.getProperty_value().keySet()) {
			generator.writeObjectField(key, incident.getProperty_value().get(key));
		}
		generator.writeEndObject();

		generator.writeEndObject();

	}

}
