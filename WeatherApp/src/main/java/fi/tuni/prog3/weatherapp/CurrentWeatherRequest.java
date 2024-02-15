package fi.tuni.prog3.weatherapp;

import static fi.tuni.prog3.weatherapp.StartScreenController.getApiKey;

/**
 * The `CurrentWeatherRequest` class represents a request for current weather data.
 * It contains the API key, latitude, and longitude for the request.
 *
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class CurrentWeatherRequest {
    private String appid;
    private double lat;
    private double lon;

    /**
     * Constructs a new `CurrentWeatherRequest` object with default values.
     * The default values are:
     * - `appid`: the API key obtained from `StartScreenController.getApiKey()`.
     * - `lat`: 20.9147215.
     * - `lon`: 106.6735556.
     */
    public CurrentWeatherRequest() {
        this.appid = getApiKey();
        this.lat = 20.9147215;
        this.lon = 106.6735556;
    }

    /**
     * Constructs a new `CurrentWeatherRequest` object with the specified latitude and longitude.
     * The default value for `appid` is the API key obtained from `StartScreenController.getApiKey()`.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     */
    public CurrentWeatherRequest(double lat, double lon) {
        this.appid = getApiKey();
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Returns the API key used for the request.
     *
     * @return The API key used for the request.
     */
    public String getAppId() {
        return appid;
    }

    /**
     * Sets the API key to be used for the request.
     *
     * @param appid The API key to be used for the request.
     */
    public void setAppId(String appid) {
        this.appid = appid;
    }

    /**
     * Returns the latitude of the location.
     *
     * @return The latitude of the location.
     */
    public double getLatitude() {
        return lat;
    }

    /**
     * Sets the latitude of the location.
     *
     * @param lat The latitude of the location.
     */
    public void setLatitude(double lat) {
        this.lat = lat;
    }

    /**
     * Returns the longitude of the location.
     *
     * @return The longitude of the location.
     */
    public double getLongitude() {
        return lon;
    }

    /**
     * Sets the longitude of the location.
     *
     * @param lon The longitude of the location.
     */
    public void setLongitude(double lon) {
        this.lon = lon;
    }
}
