package ru.taheoport.geocalculator_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class implements 3D coordinates base and target
 */
@Entity
@Data
//@Getter
@NoArgsConstructor
@Table(name = "inverse-task")
public class InverseTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Coordinate X base point
     */
    @Column(name = "baseX")
    private long baseX;

    /**
     * Coordinate Y base point
     */
    @Column(name = "baseY")
    private long baseY;

    /**
     * Coordinate Z base point
     */
    @Column(name = "baseZ")
    private long baseZ;

    /**
     * Coordinate X target point
     */
    @Column(name = "targetX")
    private long targetX;

    /**
     * Coordinate Y target point
     */
    @Column(name = "targetY")
    private long targetY;

    /**
     * Coordinate Z target point
     */
    @Column(name = "targetZ")
    private long targetZ;

}
