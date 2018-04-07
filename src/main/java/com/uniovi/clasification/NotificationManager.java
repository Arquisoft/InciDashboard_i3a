package com.uniovi.clasification;

import java.util.ArrayList;
import java.util.List;

import com.uniovi.entitites.Notification;

public class NotificationManager {

	private static NotificationManager notificationManager;

	private List<Notification> notifications;

	private NotificationManager() {
		notifications = new ArrayList<Notification>();
	}

	public static NotificationManager getInstance() {
		if (notificationManager == null) {
			notificationManager = new NotificationManager();
		}
		return notificationManager;
	}

	public void addNotification(Notification notification) {
		notifications.add(notification);
	}

	public List<Notification> getNotifications() {
		return new ArrayList<Notification>(notifications);
	}
}
