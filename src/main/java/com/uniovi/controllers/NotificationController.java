package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uniovi.services.NotificationService;

@Controller
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
}
