package fi.tuni.prog3.weatherapp;

/**
 * The `CurrentWeatherResponse` class represents the response for current weather data.
 * It contains the coordinates, weather condition, base, main weather parameters,
 * visibility, wind, rain, snow, clouds, date and time of the data, system information,
 * timezone, city ID, city name, and HTTP response code.
 * 
 * The `Coord` class represents the coordinates of the location.
 * The `Weather` class represents the weather condition.
 * The `Main` class represents the main weather parameters.
 * The `Wind` class represents the wind parameters.
 * The `Rain` class represents the rain volume.
 * The `Snow` class represents the snow volume.
 * The `Clouds` class represents the cloudiness.
 * The `Sys` class represents the system information.
 * 
 * @author Nguyen Quang Duc
 * @version 2.0
 * @since 2023-12-06
 */
public class CurrentWeatherResponse {

    /**
     * The coordinate object
     */
    public Coord coord;

    /**
     * The array of weather objects
     */
    public Weather[] weather;

    /**
     * The name of source that weather data comes from
     */
    public String base;

    /**
     * The main object
     */
    public Main main;

    /**
     * The average visibility, meters
     */
    public int visibility;

    /**
     * The wind object
     */
    public Wind wind;

    /**
     * The rain object
     */
    public Rain rain;

    /**
     * The snow object
     */
    public Snow snow;

    /**
     * The cloud object
     */
    public Clouds clouds;

    /**
     * The time of the current data, Unix, UTC
     */
    public long dt;

    /**
     * The system object
     */
    public Sys sys;

    /**
     * The timezone of the place
     */
    public int timezone;

    /**
     * The internal id
     */
    public int id;

    /**
     * The place name
     */
    public String name;

    /**
     * The internal parameter
     */
    public int cod;

    /**
     * The `Coord` class represents the coordinates of the location.
     */
    public class Coord {

        /**
         * The latitude of the location
         */
        public double lat;

        /**
         * The longitude of the location
         */
        public double lon;
    }

    /**
     * The `Weather` class represents the weather condition.
     */
    public class Weather {

        /**
         * The weather condition id
         */
        public int id;

        /**
         * The group of weather parameters
         */
        public String main;

        /**
         * The weather condition within the group
         */
        public String description;

        /**
         * The weather icon id
         */
        public String icon;
    }

    /**
     * The `Main` class represents the main weather parameters.
     */
    public class Main {

        /**
         * The current temperature
         */
        public double temp;

        /**
         * The current temperature accounting for human perception
         */
        public double feels_like;

        /**
         * The minimum temperature in the day
         */
        public double temp_min;

        /**
         * The maximum temperature in the day
         */
        public double temp_max;

        /**
         * The atmospheric pressure on the sea level, hPa
         */
        public int pressure;

        /**
         * The humidity percentage
         */
        public int humidity;

        /**
         * The atmospheric pressure on the sea level, hPa
         */
        public int sea_level;

        /**
         * The atmospheric pressure on the ground level, hPa
         */
        public int grnd_level;
    }

    /**
     * The `Wind` class represents the wind parameters.
     */
    public class Wind {

        /**
         * The wind speed
         */
        public double speed;

        /**
         * The wind direction, degrees (meteorological)
         */
        public double deg;

        /**
         * The wind gust
         */
        public double gust;
    }

    /**
     * The `Rain` class represents the rain volume.
     */
    public class Rain {

        /**
         * The precipitation volume for the last hour, mm
         */
        public double _1h;

        /**
         * The precipitation volume for the last 3 hours, mm
         */
        public double _3h;
    }
    
    /**
     * The `Snow` class represents the snow volume.
     */
    public class Snow {

        /**
         * The precipitation volume for the last hour, mm
         */
        public double _1h;

        /**
         * The precipitation volume for the last 3 hours, mm
         */
        public double _3h;
    }

    /**
     * The `Clouds` class represents the cloudiness.
     */
    public class Clouds {

        /**
         * the cloudiness percentage
         */
        public int all;
    }

    /**
     * The `Sys` class represents the system information.
     */
    public class Sys {

        /**
         * The internal type
         */
        public int type;

        /**
         * The internal id
         */
        public int id;

        /**
         * The country code of place's weather
         */
        public String country;

        /**
         * The internal message
         */
        public String message;

        /**
         * The sunrise time, Unix, UTC
         */
        public long sunrise;

        /**
         * The sunset time, Unix, UTC
         */
        public long sunset;
    }
}
