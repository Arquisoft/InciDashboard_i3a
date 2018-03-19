package com.uniovi.clasification;

import java.util.List;

import com.uniovi.entitites.Notification;

public class NotificationManager {

	private static NotificationManager notificationManager;
	
	private List<Notification> notifications;
	
	private NotificationManager() {	}
	
	public static NotificationManager getInstance() {
		if (notificationManager == null) {
			notificationManager = new NotificationManager();
		}
		return notificationManager;
	}
	
	public void addNotification(Notification notification) {
		notifications.add(notification);
	}
}
