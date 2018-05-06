package io.github.asw.i3a.operatorsWebClient.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.asw.i3a.operatorsWebClient.client.IncidentService;
import io.github.asw.i3a.operatorsWebClient.entitites.Comment;
import io.github.asw.i3a.operatorsWebClient.entitites.InciInfo;
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@EnableScheduling
public class IncidentController {

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String getListOpen( Model model, @Nullable @CookieValue("operatorId") String opId ) {
		if (opId == null)
			return "redirect:/login";
		List<Incident> incidents = Arrays.asList( IncidentService.getAllOpenIncidents() );

		SimpleDateFormat sdf = new SimpleDateFormat( "EEE MMM dd HH:mm:ss Z yyyy",
				new Locale( "us" ) );
		Collections.sort( incidents, Collections.reverseOrder( ( i1, i2 ) -> {
			try {
				return sdf.parse( i1.getDate() ).compareTo( sdf.parse( i2.getDate() ) );
			} catch (ParseException e) {
				log.error( e.getMessage() );
				return 0;
			}
		} ) );

		model.addAttribute( "incidents", incidents );
		log.info( "Incidents sent to the model: " + incidents.size() );
		return "incident/list";
	}

	@RequestMapping("/incident/details/{id}")
	public String getDetail( Model model, @PathVariable String id,
			@Nullable @CookieValue("operatorId") String opId ) {
		if (opId == null)
			return "redirect:/login";
		Incident inci = IncidentService.getIncident( id );
		model.addAttribute( "incident", inci );
		try {
			if (!inci.getLocation().isEmpty() && inci.getLocation().contains( ", " ))
				model.addAttribute( "lat",
						Double.parseDouble( inci.getLocation().split( ", " )[0] ) );
			model.addAttribute( "lng", Double.parseDouble( inci.getLocation().split( ", " )[1] ) );
		} catch (Exception e) {
			log.error( "Error parsin the location of the incident no map will be displayed." );
		}
		log.info( "Seeing incent: " + id + " details" );
		return "incident/details";
	}

	@RequestMapping(value = "/incident/edit/{id}", method = RequestMethod.GET)
	public String getEdit( Model model, @Nullable @CookieValue("operatorId") String opId,
			@PathVariable String id ) {
		if (opId == null)
			return "redirect:/login";
		Incident incident = IncidentService.getIncident( id );
		model.addAttribute( "incident", incident );
		log.info( "Page for editing incident: " + id );
		return "incident/edit";
	}

	@RequestMapping(value = "/incident/edit/{id}", method = RequestMethod.POST)
	public String setEdit( Model model, @ModelAttribute("inciInfo") InciInfo newInciData,
			@Nullable @CookieValue("operatorId") String opId, @PathVariable String id ) {
		if (opId == null)
			return "redirect:/login";
		Incident incident = IncidentService.getIncident( id );
		giveNewDataToIncident( newInciData, incident, opId );
		assignOp( opId, incident );
		incident.setIncidentId( id );
		log.info( "Operator assigned to the incident" );

		IncidentService.saveIncident( incident );
		log.info( "Incident updated in the database" );
		return "redirect:/operator/listMyIncidents";
	}

	private void giveNewDataToIncident( InciInfo newInciData, Incident incident, String opId ) {
		log.info( "The old status of the incident is: " + incident.getStatus() );
		log.info( "The NEW status: " + newInciData.getStatus() );
		incident.setStatus( newInciData.getStatus() );
		log.info( "There are " + incident.getComments().size() + " comments in the incident" );
		log.info( "Content of the new comment: " + newInciData.getComment() );
		incident.getComments().add( new Comment( newInciData.getComment(), opId ) );
		log.info( "UPDATE  " + incident.getComments().size() + " comments in the incident" );
	}

	private void assignOp( String opId, Incident incident ) {
		switch (incident.getStatus()) {
			case "OPEN":
				incident.setOperatorId( "" );
				log.info( "The operator has been desassigned" );
				break;
			default:
				incident.setOperatorId( opId );
				log.info( "The new operator " + opId + " has been assigned" );
				break;
		}
	}

}
