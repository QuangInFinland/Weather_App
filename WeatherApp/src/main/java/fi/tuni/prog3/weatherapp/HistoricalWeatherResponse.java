package fi.tuni.prog3.weatherapp;

import static fi.tuni.prog3.weatherapp.CurrentAndForecastWeatherResponse.Current;

/**
 * The `HistoricalWeatherResponse` class represents a response containing historical weather data.
 * It contains the following fields:
 * - `lat`: A double representing the latitude of the location for which the weather data is requested.
 * - `lon`: A double representing the longitude of the location for which the weather data is requested.
 * - `timezone`: A string representing the timezone of the location for which the weather data is requested.
 * - `timezone_offset`: An integer representing the timezone offset in seconds.
 * - `data`: An array of `Current` objects representing the past weather data.
 *
 * This class provides no constructors.
 *
 * This class also provides no methods.
 * 
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class HistoricalWeatherResponse {
    // The latitude of the location

    /**
     *
     */
    public double lat;
    // The longitude of the location

    /**
     *
     */
    public double lon;
    // The timezone of the location

    /**
     *
     */
    public String timezone;
    // The timezone offset in seconds

    /**
     *
     */
    public int timezone_offset;
    // The past weather data

    /**
     *
     */
    public Current[] data;
}
