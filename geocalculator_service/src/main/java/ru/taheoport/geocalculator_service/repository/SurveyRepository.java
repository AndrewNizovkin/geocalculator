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
    double getStationX(int stationIndex);

    /**
     * Saves coordinate X in meters to station with specified index
     * @param stationIndex int
     * @param stationX double coordinate X
     */
    void saveStationX(int stationIndex, double stationX);

    /**
     * Gets coordinate Y in meters of station with specified index
     * @param stationIndex int
     * @return double coordinate Y
     */
    double getStationY(int stationIndex);

    /**
     * Saves coordinate Y in meters to station with specified index
     * @param stationIndex int
     * @param stationY double coordinate Y
     */
    void saveStationY(int stationIndex, double stationY);

    /**
     * Gets coordinate Z in meters of station with specified index
     * @param stationIndex int
     * @return double coordinate Z
     */
    double getStationZ(int stationIndex);

    /**
     * Saves coordinate Z in meters to station with specified index
     * @param stationIndex int
     * @param stationZ double coordinate Z
     */
    void saveStationZ(int stationIndex, double stationZ);

    /**
     * Gets height of the tool over the point in meters of
     * station with specified index
     * @param stationIndex int
     * @return double
     */
    double getStationHeight(int stationIndex);

    /**
     * Saves height of the tool over the point in meters to
     * station with specified index
     * @param stationIndex int
     * @param stationHeight double
     */
    void saveStationHeight(int stationIndex, double stationHeight);

    /**
     * Gets direction to back point (landmark) in radians of
     * station with specified index
     * @param stationIndex int
     * @return double
     */
    double getOrDirection(int stationIndex);

    /**
     * Saves direction to back point (landmark) in radians to
     * station with specified index
     * @param stationIndex int
     * @param orDirection double
     */
    void saveOrDirection(int stationIndex, double orDirection);

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
     * @return double coordinate X of landmark
     */
    double getOrX(int stationIndex);

    /**
     * Saves coordinate X in meters of back point (landmark)
     * to station with specified index
     * @param stationIndex int
     * @param  orX double
     */
    void saveOrX(int stationIndex, double orX);

    /**
     * Gets coordinate Y in meters of back point (landmark)
     * of station with specified index
     * @param stationIndex int
     * @return double coordinate Y of landmark
     */
    double getOrY(int stationIndex);

    /**
     * Saves coordinate Y in meters of back point (landmark)
     * to station with specified index
     * @param stationIndex int
     * @param  orY double
     */
    void saveOrY(int stationIndex, double orY);

    /**
     * Gets direction angle of baseline in radians of
     * station with specified index
     * @param stationIndex int
     * @return double
     */
    double getBaseDirectionAngle(int stationIndex);

    /**
     * Saves direction angle of baseline in radians to
     * station with specified index
     * @param stationIndex int
     * @param baseDirectionAngle double
     */
    void saveBaseDirectionAngle(int stationIndex, double baseDirectionAngle);

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
     * @return String
     */
    double getTargetDirection(int stationIndex, int targetIndex);

    /**
     * Saves direction from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDirection double
     */
    void saveTargetDirection(int stationIndex, int targetIndex, double targetDirection);

    /**
     * Gets inclined distance from station to this target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetInclinedDistance(int stationIndex, int targetIndex);

    /**
     * Saves inclined distance from station to this target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetInclinedDistance double
     */
    void saveTargetInclinedDistance(int stationIndex, int targetIndex, double targetInclinedDistance);

    /**
     * Gets tilt angle from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetTiltAngle(int stationIndex, int targetIndex);

    /**
     * Saves tilt angle from station to this target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetTiltAngle double
     */
    void saveTargetTiltAngle(int stationIndex, int targetIndex, double targetTiltAngle);

    /**
     * Gets height of the reflector over the target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetHeight(int stationIndex, int targetIndex);

    /**
     * Saves height of the reflector over the target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetHeight double
     */
    void saveTargetHeight(int stationIndex, int targetIndex, double targetHeight);

    /**
     * Gets horizontal distance from station to target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetHorizontalDistance(int stationIndex, int targetIndex);

    /**
     * Saves horizontal distance from station to target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetHorizontalDistance double
     */
    void saveTargetHorizontalDistance(int stationIndex, int targetIndex, double targetHorizontalDistance);

    /**
     * Gets direction angle from station to target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetDirectionAngle(int stationIndex, int targetIndex);

    /**
     * Saves direction angle from station to target in radians
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDirectionAngle double
     */
    void saveTargetDirectionAngle(int stationIndex, int targetIndex, double targetDirectionAngle);

    /**
     * Gets the X-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetDeltaX(int stationIndex, int targetIndex);

    /**
     * Saves the X-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDeltaX double
     */
    void saveTargetDeltaX(int stationIndex, int targetIndex, double targetDeltaX);

    /**
     * Gets the Y-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetDeltaY(int stationIndex, int targetIndex);

    /**
     * Saves the Y-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDeltaY double
     */
    void saveTargetDeltaY(int stationIndex, int targetIndex, double targetDeltaY);

    /**
     * Gets the Z-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetDeltaZ(int stationIndex, int targetIndex);

    /**
     * Saves the Z-axis coordinate increment between the station and target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetDeltaZ double
     */
    void saveTargetDeltaZ(int stationIndex, int targetIndex, double targetDeltaZ);

    /**
     * Gets coordinate X of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetX(int stationIndex, int targetIndex);

    /**
     * Saves coordinate X of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetX double
     */
    void saveTargetX(int stationIndex, int targetIndex, double targetX);

    /**
     * Gets coordinate Y of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetY(int stationIndex, int targetIndex);

    /**
     * Saves coordinate Y of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetY double
     */
    void saveTargetY(int stationIndex, int targetIndex, double targetY);

    /**
     * Gets coordinate Z of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @return double
     */
    double getTargetZ(int stationIndex, int targetIndex);

    /**
     * Saves coordinate Z of target in meters
     * with specified indexes of station and target
     * @param stationIndex int
     * @param targetIndex int
     * @param targetZ double
     */
    void saveTargetZ(int stationIndex, int targetIndex, double targetZ);



}
