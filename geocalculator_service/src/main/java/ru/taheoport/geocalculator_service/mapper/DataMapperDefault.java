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
}
