package ru.taheoport.geocalculator_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taheoport.geocalculator_service.model.BackTask;

public interface BackTaskRepository extends JpaRepository<BackTask, Long> {
}
