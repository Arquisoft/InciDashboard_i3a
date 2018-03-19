package com.uniovi.serializer;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entitites.Incident;
public class InciSerializer implements Serializer<Incident>{

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
	}

	@Override
	public byte[] serialize(String arg0, Incident toBeSerialized) {
		byte[] serialized = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			serialized = objectMapper.writeValueAsString(toBeSerialized).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serialized;
	}
}
