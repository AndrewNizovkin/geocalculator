package ru.taheoport.geocalculator_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "direct-task")
public class DirectTask {

    long id;

    long baseX;

    long baseY;

    long baseZ;

    long orientX;

    long orientY;

    long orientZ;

    long horAngle;

    long tiltAngle;

    long inclinedDistance;

}
