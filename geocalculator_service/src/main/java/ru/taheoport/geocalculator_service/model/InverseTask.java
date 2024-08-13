package ru.taheoport.geocalculator_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class implements 3D coordinates of basis
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

    @Column(name = "firstX")
    private long firstX;

    @Column(name = "firstY")
    private long firstY;

    @Column(name = "firstZ")
    private long firstZ;

    @Column(name = "secondX")
    private long secondX;

    @Column(name = "secondY")
    private long secondY;

    @Column(name = "secondZ")
    private long secondZ;

}
