package com.uniovi.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Notification;

@Repository
public interface NotificationsRepository extends MongoRepository<Notification, ObjectId> {

	public List<Notification> findAll();

	@Query("{ 'operator.email': ?0 }'")
	public List<Notification> findIncidentsOf(String email);
}
