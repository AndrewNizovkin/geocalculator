package ru.taheoport.geocalculator_service.repository;

import org.springframework.stereotype.Repository;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the methods of the SurveyRepository interface for managing the survey point repository.
 */
@Repository
public class SurveyRepositoryImpl implements SurveyRepository {
    private final List<SurveyStation> surveyStations;

    public SurveyRepositoryImpl() {
        surveyStations = new ArrayList<>();
    }
    /**
     * Clears survey station repository
     */
    @Override
    public void clearAll() {
        surveyStations.clear();
    }

    /**
     * Gets number of survey stations
     * @return int
     */
    @Override
    public int size() {
        return surveyStations.size();
    }

    /**
     * Gets number of measurements on specified station index
     * @param stationIndex int station index
     * @return int number of measurements
     */
    @Override
    public int measurementSize(int stationIndex) {
        return surveyStations.get(stationIndex).getMeasurements().size();
    }

    /**
     * Adds new survey station to repository
     * @return SurveyStation The instance that was added
     */
    @Override
    public SurveyStation addNewStation() {
        SurveyStation surveyStation = new SurveyStation();
        surveyStations.add(surveyStation);
        return surveyStation;
    }

    /**
     * Adds new measurement to station with specified index
     * @param stationIndex int
     * @return instance of Measurement that was added
     */
    @Override
    public Measurement addNewMeasurement(int stationIndex) {
        Measurement measurement = new Measurement();
        surveyStations.get(stationIndex).getMeasurements().add(measurement);
        return measurement;
    }

    /**
     * Gets name of station with specified index
     * @param stationIndex int
     * @return string
     */
    @Override
    public String getStationName(int stationIndex) {
        return surveyStations.get(stationIndex).getStationName();
    }

    /**
     * Saves name of station with specified index
     * @param stationIndex int
     * @param stationName  string
     */
    @Override
    public void saveStationName(int stationIndex, String stationName) {
        surveyStations.get(stationIndex).setStationName(stationName);
    }

    /**
     * Gets coordinate X in meters of station with specified index
     * @param stationIndex int
     * @return long coordinate X
     */
    @Override
    public long getStationX(int stationIndex) {
        return surveyStations.get(stationIndex).getStationX();
    }

    /**
     * Saves coordinate X in meters to station with specified index
     * @param stationIndex int
     * @param stationX     long coordinate X
     */
    @Override
    public void saveStationX(int stationIndex, long stationX) {
        surveyStations.get(stationIndex).setStationX(stationX);
    }

    /**
     * Gets coordinate Y in meters of station with specified index
     * @param stationIndex int
     * @return long coordinate Y
     */
    @Override
    public long getStationY(int stationIndex) {
        return surveyStations.get(stationIndex).getStationY();
    }

    /**
     * Saves coordinate Y in meters to station with specified index
     * @param stationIndex int
     * @param stationY long coordinate Y
     */
    @Override
    public void saveStationY(int stationIndex, long stationY) {
        surveyStations.get(stationIndex).setStationY(stationY);

    }

    /**
     * Gets coordinate Z in meters of station with specified index
     * @param stationIndex int
     * @return long coordinate Z
     */
    @Override
    public long getStationZ(int stationIndex) {
        return surveyStations.get(stationIndex).getStationZ();
    }

    /**
     * Saves coordinate Z in meters to station with specified index
     * @param stationIndex int
     * @param stationZ     long coordinate Z
     */
    @Override
    public void saveStationZ(int stationIndex, long stationZ) {
        surveyStations.get(stationIndex).setStationZ(stationZ);
    }

    /**
     * Gets height of the tool over the point in meters of
     * station with specified index
     * @param stationIndex int
     * @return long
     */
    @Override
    public long getStationHeight(int stationIndex) {
        return surveyStations.get(stationIndex).getStationHeight();
    }

    /**
     * Saves height of the tool over the point in meters to
     * station with specified index
     * @param stationIndex  int
     * @param stationHeight long
     */
    @Override
    public void saveStationHeight(int stationIndex, long stationHeight) {
        surveyStations.get(stationIndex).setStationHeight(stationHeight);
    }

    /**
     * Gets direction to back point (landmark) in radians of
     * station with specified index
     * @param stationIndex int
     * @return long
     */
    @Override
    public long getOrDirection(int stationIndex) {
        return surveyStations.get(stationIndex).getOrDirection();
    }

    /**
     * Saves direction to back point (landmark) in radians to
     * station with specified index
     * @param stationIndex int
     * @param orDirection  long
     */
    @Override
    public void saveOrDirection(int stationIndex, long orDirection) {
        surveyStations.get(stationIndex).setOrDirection(orDirection);
    }

    /**
     * Gets name of the station with specified index
     * @param stationIndex int
     * @return String
     */
    @Override
    public String getOrName(int stationIndex) {
        return surveyStations.get(stationIndex).getOrName();
    }

    /**
     * Saves name to station with specified index
     *
     * @param stationIndex int
     * @param orName String
     */
    @Override
    public void saveOrName(int stationIndex, String orName) {
        surveyStations.get(stationIndex).setOrName(orName);
    }

    /**
     * Gets coordinate X in meters of back point (landmark)
     * of station with specified index
     * @param stationIndex int
     * @return long coordinate X of landmark
     */
    @Override
    public long getOrX(int stationIndex) {
        return surveyStations.get(stationIndex).getOrX();
    }

    /**
     * Saves coordinate X in meters of back point (landmark)
     * to station with specified index
     * @param stationIndex int
     * @param orX long
     */
    @Override
    public void saveOrX(int stationIndex, long orX) {
        surveyStations.get(stationIndex).setOrX(orX);
    }

    /**
     * Gets coordinate Y in meters of back point (landmark)
     * of station with specified index
     * @param stationIndex int
     * @return long coordinate Y of landmark
     */
    @Override
    public long getOrY(int stationIndex) {
        return surveyStations.get(stationIndex).getOrY();
    }

    /**
     * Saves coordinate Y in meters of back point (landmark)
     * to station with specified index
     * @param stationIndex int
     * @param orY long
     */
    @Override
    public void saveOrY(int stationIndex, long orY) {
        surveyStations.get(stationIndex).setOrY(orY);
    }

    /**
     * Gets direction angle of baseline in radians of
     * station with specified index
     * @param stationIndex int
     * @return double
     */
    @Override
    public long getBaseDirectionAngle(int stationIndex) {
        return surveyStations.get(stationIndex).getBaseDirectionAngle();
    }

    /**
     * Saves direction angle of baseline in radians to
     * station with specified index
     * @param stationIndex       int
     * @param baseDirectionAngle long
     */
    @Override
    public void saveBaseDirectionAngle(int stationIndex, long baseDirectionAngle) {
        surveyStations.get(stationIndex).setBaseDirectionAngle(baseDirectionAngle);
    }

    /**
     * Gets name of target with specified index in measurements collection
     * of survey station with specified index
     * @param stationIndex int
     * @param targetIndex  int
     * @return String
     */
    @Override
    public String getTargetName(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetName();
    }

    /**
     * Saves name of target with specified index in measurements collection
     * of survey station with specified index
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetName   String
     */
    @Override
    public void saveTargetName(int stationIndex, int targetIndex, String targetName) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetName(targetName);
    }

    /**
     * Gets direction from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetDirection(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetDirection();
    }

    /**
     * Saves direction from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex    int
     * @param targetIndex     int
     * @param targetDirection long
     */
    @Override
    public void saveTargetDirection(int stationIndex, int targetIndex, long targetDirection) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetDirection(targetDirection);
    }

    /**
     * Gets inclined distance from station to this target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetInclinedDistance(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetInclinedDistance();
    }

    /**
     * Saves inclined distance from station to this target in meters
     * with specified indexes of station and target
     * @param stationIndex           int
     * @param targetIndex            int
     * @param targetInclinedDistance long
     */
    @Override
    public void saveTargetInclinedDistance(int stationIndex, int targetIndex, long targetInclinedDistance) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetInclinedDistance(targetInclinedDistance);
    }

    /**
     * Gets tilt angle from station to this target in radians
     * with specified indexes of station and target
     *
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetTiltAngle(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetTiltAngle();
    }

    /**
     * Saves tilt angle from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex    int
     * @param targetIndex     int
     * @param targetTiltAngle long
     */
    @Override
    public void saveTargetTiltAngle(int stationIndex, int targetIndex, long targetTiltAngle) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetTiltAngle(targetTiltAngle);
    }

    /**
     * Gets height of the reflector over the target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetHeight(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetHeight();
    }

    /**
     * Saves height of the reflector over the target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetHeight long
     */
    @Override
    public void saveTargetHeight(int stationIndex, int targetIndex, long targetHeight) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetHeight(targetHeight);
    }

    /**
     * Gets horizontal distance from station to target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetHorizontalDistance(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetHorizontalDistance();
    }

    /**
     * Saves horizontal distance from station to target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetHorizontalDistance long
     */
    @Override
    public void saveTargetHorizontalDistance(int stationIndex, int targetIndex, long targetHorizontalDistance) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetHorizontalDistance(targetHorizontalDistance);
    }

    /**
     * Gets direction angle from station to target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetDirectionAngle(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetDirectionAngle();
    }

    /**
     * Saves direction angle from station to target in radians
     * with specified indexes of station and target
     * @param stationIndex         int
     * @param targetIndex          int
     * @param targetDirectionAngle long
     */
    @Override
    public void saveTargetDirectionAngle(int stationIndex, int targetIndex, long targetDirectionAngle) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetDirectionAngle(targetDirectionAngle);
    }

    /**
     * Gets the X-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetDeltaX(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetDeltaX();
    }

    /**
     * Saves the X-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetDeltaX long
     */
    @Override
    public void saveTargetDeltaX(int stationIndex, int targetIndex, long targetDeltaX) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetDeltaX(targetDeltaX);
    }

    /**
     * Gets the Y-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetDeltaY(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetDeltaY();
    }

    /**
     * Saves the Y-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetDeltaY long
     */
    @Override
    public void saveTargetDeltaY(int stationIndex, int targetIndex, long targetDeltaY) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetDeltaY(targetDeltaY);
    }

    /**
     * Gets the Z-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetDeltaZ(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetDeltaZ();
    }

    /**
     * Saves the Z-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetDeltaZ long
     */
    @Override
    public void saveTargetDeltaZ(int stationIndex, int targetIndex, long targetDeltaZ) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetDeltaZ(targetDeltaZ);
    }

    /**
     * Gets coordinate X of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetX(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetX();
    }

    /**
     * Saves coordinate X of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetX long
     */
    @Override
    public void saveTargetX(int stationIndex, int targetIndex, long targetX) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetX(targetX);

    }

    /**
     * Gets coordinate Y of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetY(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetY();
    }

    /**
     * Saves coordinate Y of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetY long
     */
    @Override
    public void saveTargetY(int stationIndex, int targetIndex, long targetY) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetY(targetY);
    }

    /**
     * Gets coordinate Z of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @return long
     */
    @Override
    public long getTargetZ(int stationIndex, int targetIndex) {
        return surveyStations.get(stationIndex).getMeasurements().get(targetIndex).getTargetZ();
    }

    /**
     * Saves coordinate Z of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex  int
     * @param targetZ long
     */
    @Override
    public void saveTargetZ(int stationIndex, int targetIndex, long targetZ) {
        surveyStations.get(stationIndex).getMeasurements().get(targetIndex).setTargetZ(targetZ);
    }
}
