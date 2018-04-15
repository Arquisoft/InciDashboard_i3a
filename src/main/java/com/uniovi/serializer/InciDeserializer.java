package com.uniovi.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entitites.Incident;

public class InciDeserializer implements Deserializer<Incident> {

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
	}

	@Override
	public Incident deserialize(String arg0, byte[] serializedInci) {
		ObjectMapper mapper = new ObjectMapper();
		Incident deserializedInci = null;
		try {
			deserializedInci = mapper.readValue(serializedInci, Incident.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return deserializedInci;
	}

	@Override
	public void close() {
	}

}
