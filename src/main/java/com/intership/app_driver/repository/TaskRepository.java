package com.intership.app_driver.repository;

import com.intership.app_driver.entity.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface TaskRepository extends ReactiveCrudRepository<Task, UUID> {
}
