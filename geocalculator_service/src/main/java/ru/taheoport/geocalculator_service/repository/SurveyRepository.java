package ru.taheoport.geocalculator_service.repository;

import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;

/**
 * Provides methods for the survey station repository
 */
public interface SurveyRepository {

    /**
     * Clears survey station repository
     */
    void clearAll();

    /**
     * Gets number of survey stations
     * @return int
     */
    int size();

    /**
     * Gets number of measurements on specified station index
     * @param stationIndex int station index
     * @return int number of measurements
     */
    int measurementSize(int stationIndex);

    /**
     * Adds new survey station to repository
     * @return SurveyStation The instance that was added
     */
    SurveyStation addNewStation();

    /**
     * Adds new measurement to station with specified index
     * @param stationIndex int
     * @return instance of Measurement that was added
     */
    Measurement addNewMeasurement(int stationIndex);

    /**
     * Gets name of station with specified index
     * @param stationIndex int
     * @return string
     */
    String getStationName(int stationIndex);

    /**
     * Saves name of station with specified index
     * @param stationIndex int
     * @param stationName string
     */
    void saveStationName(int stationIndex, String stationName);

    /**
     * Gets coordinate X in meters of station with specified index
     * @param stationIndex int
     * @return double coordinate X
     */
    long getStationX(int stationIndex);

    /**
     * Saves coordinate X in meters to station with specified index
     * @param stationIndex int
     * @param stationX double coordinate X
     */
    void saveStationX(int stationIndex, long stationX);

    /**
     * Gets coordinate Y in meters of station with specified index
     * @param stationIndex int
     * @return long coordinate Y
     */
    long getStationY(int stationIndex);

    /**
     * Saves coordinate Y in meters to station with specified index
     * @param stationIndex int
     * @param stationY long coordinate Y
     */
    void saveStationY(int stationIndex, long stationY);

    /**
     * Gets coordinate Z in meters of station with specified index
     * @param stationIndex int
     * @return long coordinate Z
     */
    long getStationZ(int stationIndex);

    /**
     * Saves coordinate Z in meters to station with specified index
     * @param stationIndex int
     * @param stationZ long coordinate Z
     */
    void saveStationZ(int stationIndex, long stationZ);

    /**
     * Gets height of the tool over the point in meters of
     * station with specified index
     * @param stationIndex int
     * @return long
     */
    long getStationHeight(int stationIndex);

    /**
     * Saves height of the tool over the point in meters to
     * station with specified index
     * @param stationIndex int
     * @param stationHeight long
     */
    void saveStationHeight(int stationIndex, long stationHeight);

    /**
     * Gets direction to back point (landmark) in radians of
     * station with specified index
     * @param stationIndex int
     * @return long
     */
    long getOrDirection(int stationIndex);

    /**
     * Saves direction to back point (landmark) in radians to
     * station with specified index
     * @param stationIndex int
     * @param orDirection long
     */
    void saveOrDirection(int stationIndex, long orDirection);

    /**
     * Gets name of the station with specified index
     * @param stationIndex int
     * @return String
     */
    String getOrName(int stationIndex);

    /**
     * Saves name to station with specified index
     * @param stationIndex int
     * @param orName String
     */
    void saveOrName(int stationIndex, String orName);

    /**
     * Gets coordinate X in meters of back point (landmark)
     * of station with specified index
     * @param stationIndex int
     * @return long coordinate X of landmark
     */
    long getOrX(int stationIndex);

    /**
     * Saves coordinate X in meters of back point (landmark)
     * to station with specified index
     * @param stationIndex int
     * @param  orX long
     */
    void saveOrX(int stationIndex, long orX);

    /**
     * Gets coordinate Y in meters of back point (landmark)
     * of station with specified index
     * @param stationIndex int
     * @return long coordinate Y of landmark
     */
    long getOrY(int stationIndex);

    /**
     * Saves coordinate Y in meters of back point (landmark)
     * to station with specified index
     * @param stationIndex int
     * @param  orY long
     */
    void saveOrY(int stationIndex, long orY);

    /**
     * Gets direction angle of baseline in radians of
     * station with specified index
     * @param stationIndex int
     * @return long
     */
    long getBaseDirectionAngle(int stationIndex);

    /**
     * Saves direction angle of baseline in radians to
     * station with specified index
     * @param stationIndex int
     * @param baseDirectionAngle long
     */
    void saveBaseDirectionAngle(int stationIndex, long baseDirectionAngle);

    /**
     * Gets name of target with specified index in measurements collection
     * of survey station with specified index
     * @param stationIndex int
     * @param targetIndex int
     * @return String
     */
    String getTargetName(int stationIndex, int targetIndex);

    /**
     * Saves name of target with specified index in measurements collection
     * of survey station with specified index
     * @param stationIndex int
     * @param targetIndex int
     * @param targetName String
     */
    void saveTargetName(int stationIndex, int targetIndex, String targetName);

    /**
     * Gets direction from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetDirection(int stationIndex, int targetIndex);

    /**
     * Saves direction from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDirection long
     */
    void saveTargetDirection(int stationIndex, int targetIndex, long targetDirection);

    /**
     * Gets inclined distance from station to this target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetInclinedDistance(int stationIndex, int targetIndex);

    /**
     * Saves inclined distance from station to this target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetInclinedDistance long
     */
    void saveTargetInclinedDistance(int stationIndex, int targetIndex, long targetInclinedDistance);

    /**
     * Gets tilt angle from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetTiltAngle(int stationIndex, int targetIndex);

    /**
     * Saves tilt angle from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetTiltAngle long
     */
    void saveTargetTiltAngle(int stationIndex, int targetIndex, long targetTiltAngle);

    /**
     * Gets height of the reflector over the target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetHeight(int stationIndex, int targetIndex);

    /**
     * Saves height of the reflector over the target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetHeight long
     */
    void saveTargetHeight(int stationIndex, int targetIndex, long targetHeight);

    /**
     * Gets horizontal distance from station to target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetHorizontalDistance(int stationIndex, int targetIndex);

    /**
     * Saves horizontal distance from station to target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetHorizontalDistance long
     */
    void saveTargetHorizontalDistance(int stationIndex, int targetIndex, long targetHorizontalDistance);

    /**
     * Gets direction angle from station to target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetDirectionAngle(int stationIndex, int targetIndex);

    /**
     * Saves direction angle from station to target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDirectionAngle long
     */
    void saveTargetDirectionAngle(int stationIndex, int targetIndex, long targetDirectionAngle);

    /**
     * Gets the X-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetDeltaX(int stationIndex, int targetIndex);

    /**
     * Saves the X-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDeltaX long
     */
    void saveTargetDeltaX(int stationIndex, int targetIndex, long targetDeltaX);

    /**
     * Gets the Y-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetDeltaY(int stationIndex, int targetIndex);

    /**
     * Saves the Y-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDeltaY long
     */
    void saveTargetDeltaY(int stationIndex, int targetIndex, long targetDeltaY);

    /**
     * Gets the Z-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetDeltaZ(int stationIndex, int targetIndex);

    /**
     * Saves the Z-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDeltaZ long
     */
    void saveTargetDeltaZ(int stationIndex, int targetIndex, long targetDeltaZ);

    /**
     * Gets coordinate X of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetX(int stationIndex, int targetIndex);

    /**
     * Saves coordinate X of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetX long
     */
    void saveTargetX(int stationIndex, int targetIndex, long targetX);

    /**
     * Gets coordinate Y of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetY(int stationIndex, int targetIndex);

    /**
     * Saves coordinate Y of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetY long
     */
    void saveTargetY(int stationIndex, int targetIndex, long targetY);

    /**
     * Gets coordinate Z of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return long
     */
    long getTargetZ(int stationIndex, int targetIndex);

    /**
     * Saves coordinate Z of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetZ long
     */
    void saveTargetZ(int stationIndex, int targetIndex, long targetZ);



}
