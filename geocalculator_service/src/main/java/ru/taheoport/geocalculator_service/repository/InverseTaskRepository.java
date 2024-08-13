package ru.taheoport.geocalculator_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taheoport.geocalculator_service.model.InverseTask;

@Repository
public interface InverseTaskRepository extends JpaRepository<InverseTask, Long> {
}
