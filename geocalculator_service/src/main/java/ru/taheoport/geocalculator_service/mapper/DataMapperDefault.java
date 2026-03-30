package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;

@Component
public class DataMapperDefault implements DataMapper {

    /**
     * Converts angle value in seconds to D.MS format
     * @param seconds long
     * @return String
     */
    @Override
    public String secondsToDms(long seconds) {
        int secondsValue = (int) seconds;
        StringBuilder dmsValue = new StringBuilder();
        int deg;
        int min;
        int sec;
        switch ((int) Math.signum(secondsValue)) {
            case -1 -> dmsValue.append("-");
            case 0 -> {
                return "0.0000";
            }
        }
        secondsValue = Math.abs(secondsValue);

        if (secondsValue < 3600) {
            dmsValue.append("0.");
        } else {
            deg = secondsValue / 3600;
            secondsValue = secondsValue - 3600 * deg;
            dmsValue.append(deg).append(".");
        }

        min = secondsValue / 60;
        sec = secondsValue - 60 * min;

        if (min < 10) {
            dmsValue.append("0").append(min);
        } else {
            dmsValue.append(min);
        }

        if (sec < 10) {
            dmsValue.append("0").append(sec);
        } else {
            dmsValue.append(sec);
        }

        return dmsValue.toString();
    }

    /**
     * Converts angle value in D.MS format to seconds
     * @param angleValueAsDms String
     * @return long
     */
    @Override
    public long dmsToSeconds(String angleValueAsDms) {
        int deg;
        int min;
        int sec;
        int sepPos = angleValueAsDms.indexOf('.');
        int sign = (int) Math.signum(Double.parseDouble(angleValueAsDms));
        deg = Math.abs(Integer.parseInt(angleValueAsDms.substring(0, sepPos)));
        min = Integer.parseInt(angleValueAsDms.substring(sepPos + 1, sepPos + 3));
        sec = Integer.parseInt(angleValueAsDms.substring(sepPos + 3, sepPos + 5));
        return (deg * 3600L + min * 60L + sec) * sign;
    }

    /**
     * Converts line value from millimeters to meters
     * @param millimeters long value in millimeters
     * @return String value in meters
     */
    @Override
    public String millimeterToMeter(long millimeters) {
        double meter = (double) millimeters / 1000;

        return commaToPoint(String.format("%.3f", meter));
    }

    /**
     * Converts line value from meters to millimeters
     * @param meters String value in meters
     * @return long value in millimeters
     */
    @Override
    public long meterToMillimeter(String meters) {
        double millimeter;
        try {
            millimeter = Double.parseDouble(meters) * 1000;
        } catch (NumberFormatException e) {
            return 0;
        }
        return (long) millimeter;
    }

    /**
     * Changes decimal separator from ',' to '.'
     *
     * @param value String
     * @return String
     */
    @Override
    public String commaToPoint(String value) {
        return value.replaceAll(",", ".");
    }

    /**
     * Converts angle value from d.mmss format to radians
     *
     * @param value string
     * @return double
     */
    @Override
    public double dmsToRadians(String value) {
        double sign = Math.signum(Double.parseDouble(value));
        String[] parts = value.split("\\.");
        double degree = Math.abs(Double.parseDouble(parts[0]));
//        degree = Math.abs(degree);

        double minutes = Double.parseDouble(parts[1].substring(0, 2));

        double seconds = Double.parseDouble(parts[1].substring(2, 4));

        return sign * (degree + minutes / 60 + seconds / 3600) * Math.PI / 180;
    }

    /**
     * Converts angle value from radians to d.mmss format
     *
     * @param value double
     * @return String
     */
    @Override
    public String radiansToDms(double value) {
        double valueDegree = Math.abs(Math.toDegrees(value));
        int sign = (int) Math.signum(value);
        if (sign == 0) {
            return "0.0000";
        }
        long degree = Math.round(valueDegree);

        double minDouble = (valueDegree - degree) * 60;
        long minutes = Math.round(minDouble);

        long seconds = Math.round((minDouble - minutes) * 60);

        long valueSeconds = sign * (degree * 3600 + minutes * 60 + seconds);

        return secondsToDms(valueSeconds);
    }

    /**
     * Removes insignificant zeros at the beginning of the string
     *
     * @param value String
     * @return String
     */
    @Override
    public String removeFirstZero(String value) {
        if (value == null || value.isEmpty()) return value;

        int firstNotZeroIndex = 0;
        while (firstNotZeroIndex < value.length() && value.charAt(firstNotZeroIndex) == '0') {
            firstNotZeroIndex++;
        }

        if (firstNotZeroIndex == value.length()) return "0";

        return value.substring(firstNotZeroIndex);
    }

    /**
     * Converts string value from gis16 to millimeters
     *
     * @param value String
     * @return long line value in millimeters
     */
    @Override
    public long leicaToMillimeter(String value) {
        return Long.parseLong(value);
    }

    /**
     * Converts string value of horizontal angle from gis16 to seconds
     * @param value String
     * @param rightShift int number of chars for right shift int rightShift
     * @return long angle value in seconds
     */
    @Override
    public long leicaToDirection(String value, int rightShift) {
        for (int i = 0; i < rightShift; i++) {
            value = value.substring(0, value.length() - 1);
        }

        long degrees = Long.parseLong(value.substring(0, value.length() - 4));
        long minutes = Long.parseLong(value.substring(value.length() - 4, value.length() - 2));
        long seconds = Long.parseLong(value.substring(value.length() - 2));

        return degrees * 3600 + minutes * 60 + seconds;
    }

    /**
     * Converts string value of tilt angle from gis16 to seconds
     *
     * @param value String
     * @param rightShift int number of chars for right shift int rightShift
     * @return long angle in seconds
     */
    @Override
    public long leicaToTiltAngle(String value, int rightShift ) {

        long angle = leicaToDirection(value, rightShift);

        if (angle == 0) return 0;

        return -1 * (leicaToDirection(value, rightShift) - 324000);
    }

    /**
     * Replaces special characters with spaces
     *
     * @param line String
     * @return String
     */
    @Override
    public String specialCharsToSpaces(String line) {

        return line.replaceAll("[+?*_(),dm\\n]", " ");
//        P100_ ?+00014487m0874204+0480358d+00014475***+00+00000_*_,1.595
    }
}
