package com.uniovi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Notification;

@Repository
public interface NotificationsRepository extends CrudRepository<Notification, Long> {

	public List<Notification> findAll();
}
