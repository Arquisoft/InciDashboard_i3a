package io.github.asw.i3a.operatorsWebClient.listeners;

import javax.annotation.ManagedBean;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.mashape.unirest.http.JsonNode;

import io.github.asw.i3a.operatorsWebClient.client.IncidentService;
import io.github.asw.i3a.operatorsWebClient.client.OperatorService;
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ManagedBean
public class Receiver {

	@Autowired
	private SimpMessagingTemplate template;

	@KafkaListener(topics = "${kafka.topic}")
	public void listen( String kafkaInput ) {
		JsonNode kafkaPayload = new JsonNode( kafkaInput );
		String incidentId = "";
		
		try {
			incidentId = kafkaPayload.getObject().getString( "incidentId" );
			log.info( "Incident received on kafka with id: " + incidentId );
		} catch (JSONException e) {
			log.error( e.getMessage() );
		}
		
		Incident in = assignOperator(
				IncidentService.getIncident( incidentId ) );
		
		this.template.convertAndSend( "/topic/incidents", in );
	}

	private Incident assignOperator( Incident incident ) {
		if (incident.getOperatorId() == "") {
			incident.setOperatorId( OperatorService.getOperatorToAssign() );
		}
		incident.setStatus( "IN_PROCESS" );
		IncidentService.saveIncident( incident );
		return incident;
	}
}
