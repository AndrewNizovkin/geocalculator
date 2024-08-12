package ru.taheoport.geocalculator_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

/**
 * This class implements 3D coordinates of
 */
@Entity
@Data
@Getter
public class BackTaskRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private long firstX;

    private long firstY;

    private long firstZ;

    private long secondX;

    private long secondY;

    private long secondZ;

}
