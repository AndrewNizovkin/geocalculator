package ru.taheoport.geocalculator_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taheoport.geocalculator_service.model.BackTaskRequest;

public interface BackTaskRepository extends JpaRepository<BackTaskRequest, Long> {
}
