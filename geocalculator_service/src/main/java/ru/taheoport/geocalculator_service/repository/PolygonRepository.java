package ru.taheoport.geocalculator_service.repository;

import ru.taheoport.geocalculator_service.model.PolygonStation;

/**
 * Defines methods for managing a collection of
 * polygon stations
 */
public interface PolygonRepository {

    /**
     * Clears repository
     */
    void clearAll();

    /**
     * Gets number of stations in the repository
     * @return int
     */
    int size();

    /**
     * Adds new polygon station to repository
     * @return empty instance of PolygonStation
     */
    PolygonStation addNewStation();

    /**
     * Gets polygon station by specified index
     * @param stationIndex int index
     * @return instance of PolygonStation
     */
    PolygonStation getStationById(int stationIndex);
}
