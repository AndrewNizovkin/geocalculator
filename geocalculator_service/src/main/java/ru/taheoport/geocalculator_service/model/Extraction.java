package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the data extracted
 * from the geodetic survey to build the polygon.
 */
@Getter
@Setter
@NoArgsConstructor
public class Extraction {

    /**
     * Name of polygon survey station
     */
    private String stationName = "noname";

    /**
     * Height of total station over point
     */
    private long stationHeight = 0;

    /**
     * Geodetic measurement collection
     */
    private List<Measurement> measurements = new ArrayList<>();

}
