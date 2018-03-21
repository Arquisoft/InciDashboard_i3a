package com.uniovi.serializer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entitites.Agent;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Location;
public class InciDeserializer extends JsonDeserializer<Incident>{

	@SuppressWarnings("unchecked")
	@Override
	public Incident deserialize(JsonParser parser, DeserializationContext toBeDeserialized)
			throws IOException, JsonProcessingException {
		
		ObjectCodec objectCodec = parser.getCodec();
	    JsonNode jsonNode = objectCodec.readTree(parser);
	    
	    Incident incident = new Incident();
	    incident.setAgent(new Agent(jsonNode.get("agent").get("username").asText(), 
	    		jsonNode.get("agent").get("kind").asInt()));
	    incident.setName(jsonNode.get("inciName").asText());
	    
	    incident.setLocation(new Location(jsonNode.get("location").get("lat").asDouble(),
	    		jsonNode.get("location").get("lon").asDouble()));
	    
	    
	    Iterator<JsonNode> tagsIter = jsonNode.get("tags").elements();
	    while (tagsIter.hasNext()) {
	    		JsonNode tag = tagsIter.next();
	    		incident.getTags().add(tag.asText());
	    }
	    
	    Iterator<JsonNode> commentsIter = jsonNode.get("comments").elements();
	    while (commentsIter.hasNext()) {
	    		JsonNode comment = commentsIter.next();
	    		incident.getComments().add(comment.asText());
	    }
	    
	    JsonNode properties = jsonNode.get("properties");
	    ObjectMapper mapper = new ObjectMapper();
	    incident.setProperty_value(mapper.convertValue(properties, Map.class));
	    
		return incident;
	}

	
}
