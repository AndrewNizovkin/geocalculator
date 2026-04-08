package ru.taheoport.geocalculator_service.repository;

import org.springframework.stereotype.Repository;
import ru.taheoport.geocalculator_service.model.PolygonStation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for managing a collection of
 *  * polygon stations
 */
@Repository
public class PolygonRepositoryImpl implements PolygonRepository{

    private final List<PolygonStation> polygonStations;

    public PolygonRepositoryImpl() {
        this.polygonStations = new ArrayList<>();
    }

    /**
     * Clears repository
     */
    @Override
    public void clearAll() {
        polygonStations.clear();
    }

    /**
     * Gets number of stations in the repository
     * @return int
     */
    @Override
    public int size() {
        return polygonStations.size();
    }

    /**
     * Adds new polygon station to repository
     *
     * @return empty instance of PolygonStation
     */
    @Override
    public PolygonStation addNewStation() {
        PolygonStation polygonStation = new PolygonStation();
        polygonStations.add(polygonStation);
        return polygonStation;
    }

    /**
     * Gets polygon station by specified index
     *
     * @param stationIndex int index
     * @return instance of PolygonStation
     */
    @Override
    public PolygonStation getStationById(int stationIndex) {
        if (!polygonStations.isEmpty() && stationIndex < polygonStations.size()) {
            return polygonStations.get(stationIndex);
        } else {
            return null;
        }
    }

}
