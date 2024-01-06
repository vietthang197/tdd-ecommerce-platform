package com.tdd.notifyservices.repository;

import com.tdd.notifyservices.entity.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {
}
