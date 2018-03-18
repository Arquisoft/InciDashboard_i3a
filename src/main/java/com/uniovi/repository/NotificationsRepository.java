package com.uniovi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entitites.Notification;

public interface NotificationsRepository extends CrudRepository<Notification, Long> {

	public List<Notification> findAll();
}
