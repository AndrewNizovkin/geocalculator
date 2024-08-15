package ru.taheoport.geocalculator_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "geo-points")
public class GeoPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Coordinate X in millimeters
     */
    @Column(name = "x")
    private long xPoint;

    /**
     * Coordinate Y in millimeters
     */

    @Column(name = "y")
    private long yPoint;

    /**
     * Coordinate Z in millimeters
     */
    @Column(name = "z")
    private long zPoint;

    /**
     * Constructor with three parameters
     * @param xPoint coordinate X
     * @param yPoint coordinate Y
     * @param zPoint coordinate Z
     */
    public GeoPoint(long xPoint, long yPoint, long zPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.zPoint = zPoint;
    }
}
