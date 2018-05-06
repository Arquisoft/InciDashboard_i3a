package io.github.asw.i3a.operatorsWebClient.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import io.github.asw.i3a.operatorsWebClient.client.IncidentService;
import io.github.asw.i3a.operatorsWebClient.client.OperatorService;
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;
import io.github.asw.i3a.operatorsWebClient.entitites.UserInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OperatorController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginAux() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String setLogin(@ModelAttribute("UserInfo") UserInfo values, HttpServletResponse response) {
		HttpResponse<JsonNode> authenticationResponse = OperatorService.authenticate(values.getLogin(),
				values.getPassword());
		log.info("Login attemp with :" + values + " and result : " + authenticationResponse.getStatusText());
		if (authenticationResponse.getStatus() == org.springframework.http.HttpStatus.OK.value()) {
			try {
				Cookie operatorId = new Cookie("operatorId",
						(String) authenticationResponse.getBody().getObject().get("operatorId"));
				operatorId.setMaxAge(1000);
				response.addCookie(operatorId);
				log.info("Cookie added to the response");
			} catch (JSONException e) {
				log.info("Failure creating the cookie in the login process");
				e.printStackTrace();
			}
			log.info("Login successful, redirecting to indicents...");
			return "redirect:/operator/listMyIncidents";
		}
		log.info("Login failure, redirecting to login...");
		return "redirect:/login";
	}

	@RequestMapping("/operator/listMyIncidents")
	public String getMyIncidentsList(Model model, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		
		List<Incident> incidents = IncidentService.getInProcessIncidentsOfOperator(opId);
		
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
		
		model.addAttribute("incidents", incidents);
		log.info("Redirecting to the incidents of the operator");
		return "operator/incidents";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@Nullable @CookieValue("operatorId") String opId, HttpServletResponse response) {
		if (opId == null)
			return "redirect:/login";
		Cookie agentC = new Cookie("operatorId", opId);
		agentC.setMaxAge(0);
		response.addCookie(agentC);
		log.info("Logout and redirecting to the login page");
		return "redirect:/";
	}
}
