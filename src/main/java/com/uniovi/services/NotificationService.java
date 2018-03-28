package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Notification;
import com.uniovi.repository.NotificationsRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationsRepository notificationsRepository;

	public void add(Notification notification) {
		notificationsRepository.save(notification);
	}

	public List<Notification> getAllNotifications() {
		return notificationsRepository.findAll();
	}

	public void deleteAll() {
		notificationsRepository.deleteAll();
	}
}
