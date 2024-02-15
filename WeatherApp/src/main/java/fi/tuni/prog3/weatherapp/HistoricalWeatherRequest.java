package fi.tuni.prog3.weatherapp;

import static fi.tuni.prog3.weatherapp.StartScreenController.getApiKey;

/**
 * The `HistoricalWeatherRequest` class represents a request to retrieve historical weather data.
 * It contains the following fields:
 * - `appid`: A string representing the API key used to access the weather data.
 * - `lat`: A double representing the latitude of the location for which the weather data is requested.
 * - `lon`: A double representing the longitude of the location for which the weather data is requested.
 * - `unixTime`: A long representing the Unix timestamp for which the weather data is requested.
 *
 * This class provides two constructors:
 * - A default constructor that initializes the fields with default values.
 * - A constructor that takes the latitude, longitude, and Unix timestamp as parameters to initialize the fields.
 *
 * This class also provides getters and setters for each field.
 * 
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class HistoricalWeatherRequest {
    private String appid;
    private double lat;
    private double lon;
    private long unixTime;
    
    /**
     * Initializes the `HistoricalWeatherRequest` object with default values.
     */
    public HistoricalWeatherRequest() {
        this.appid = getApiKey();
        this.lat = 20.9147215;
        this.lon = 106.6735556;
        this.unixTime = 979225200;
    }

    /**
     * Initializes the `HistoricalWeatherRequest` object with the specified latitude, longitude, and Unix timestamp.
     *
     * @param lat The latitude of the location for which the weather data is requested.
     * @param lon The longitude of the location for which the weather data is requested.
     * @param unixTime The Unix timestamp for which the weather data is requested.
     */
    public HistoricalWeatherRequest(double lat, double lon, long unixTime) {
        this.appid = getApiKey();
        this.lat = lat;
        this.lon = lon;
        this.unixTime = unixTime;
    }

    /**
     * Returns the API key used to access the weather data.
     *
     * @return A string representing the API key.
     */
    public String getAppId() {
        return appid;
    }

    /**
     * Sets the API key used to access the weather data.
     *
     * @param appid A string representing the API key.
     */
    public void setAppId(String appid) {
        this.appid = appid;
    }

    /**
     * Returns the latitude of the location for which the weather data is requested.
     *
     * @return A double representing the latitude.
     */
    public double getLatitude() {
        return lat;
    }

    /**
     * Sets the latitude of the location for which the weather data is requested.
     *
     * @param lat A double representing the latitude.
     */
    public void setLatitude(double lat) {
        this.lat = lat;
    }

    /**
     * Returns the longitude of the location for which the weather data is requested.
     *
     * @return A double representing the longitude.
     */
    public double getLongitude() {
        return lon;
    }

    /**
     * Sets the longitude of the location for which the weather data is requested.
     *
     * @param lon A double representing the longitude.
     */
    public void setLongitude(double lon) {
        this.lon = lon;
    }
    
    /**
     * Returns the Unix timestamp for which the weather data is requested.
     *
     * @return A long representing the Unix timestamp.
     */
    public long getUnixTime() {
        return unixTime;
    }

    /**
     * Sets the Unix timestamp for which the weather data is requested.
     *
     * @param unixTime A long representing the Unix timestamp.
     */
    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }
}
