package com.intership.app_driver.service;

import com.intership.app_driver.entity.Task;
import com.intership.app_driver.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Flux<Task> getTasksByDriver(String driverId) {
        return taskRepository.findAll()
                .filter(task -> task.getDriverId().equals(driverId));
    }

    public Mono<Task> createTask(Task task) {
        return taskRepository.save(task);
    }
}

