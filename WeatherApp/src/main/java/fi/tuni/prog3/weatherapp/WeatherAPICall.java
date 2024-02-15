package fi.tuni.prog3.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

/**
 * The WeatherAPICall class contains methods to interact with the OpenWeatherMap API
 * and retrieve various weather-related data such as current weather, historical weather,
 * forecast, moon phase information, and weather-related images.
 * It provides functionality to generate descriptive texts and retrieve image paths
 * based on specific weather parameters and indices.
 *
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class WeatherAPICall {
    
    /**
     *
     */
    public static HistoricalWeatherRequest historicalWeatherRequest;
    
    /**
     *
     */
    public static CurrentWeatherRequest currentWeatherRequest;
    
    /**
     *
     */
    public static CurrentAndForecastWeatherRequest forecastWeatherRequest;
    
    /**
     *
     */
    public static String chosenUnit = "metric";

    /**
     *
     */
    public static String metricUnit = "metric";

    /**
     *
     */
    public static String imperialUnit = "imperial";
    
    /**
     * Makes a request to retrieve historical weather data from the OpenWeatherMap API.
     * Uses latitude, longitude, API key, chosen units, and a specific date/time to fetch historical weather information.
     *
     * @return HistoricalWeatherResponse containing the requested historical weather data.
     *         Returns null if an error occurs during the retrieval process.
     */
    public static HistoricalWeatherResponse RequestHistocialWeatherData() 
    {
        try {
            
            if (historicalWeatherRequest == null)
            {
                InitializeHistoricalWeatherRequest();
            }
            
            String latitudeString = String.valueOf(historicalWeatherRequest.getLatitude());
            String longitudeString = String.valueOf(historicalWeatherRequest.getLongitude());
            
            String apiUrl = "https://api.openweathermap.org/data/3.0/onecall/timemachine?lat=" + latitudeString + 
                    "&lon=" + longitudeString + "&appid=" + historicalWeatherRequest.getAppId() + 
                    "&units=" + chosenUnit + "&dt=" + historicalWeatherRequest.getUnixTime();

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // JSON response
            String jsonResponse = response.toString();
            
            // Parse the JSON using Gson
            Gson gson = new Gson();
            HistoricalWeatherResponse weatherResponse = gson.fromJson(jsonResponse, HistoricalWeatherResponse.class);

            connection.disconnect();
            
            return weatherResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
    }
    
    /**
     * Initializes the historical weather request object if it's currently null.
     * Creates a new instance of HistoricalWeatherRequest and assigns it to the global variable.
     */
    public static void InitializeHistoricalWeatherRequest()
    {                
        historicalWeatherRequest = new HistoricalWeatherRequest();
    }
    
    /**
     * Makes a request to retrieve current weather data from the OpenWeatherMap API.
     * Uses latitude, longitude, and API key to fetch current weather information.
     *
     * @return CurrentWeatherResponse containing the requested current weather data.
     *         Returns null if an error occurs during the retrieval process.
     */
    public static CurrentWeatherResponse RequestCurrentWeatherData() 
    {
        try {
            
            if (currentWeatherRequest == null)
            {
                InitializeCurrentWeatherRequest();
            }
            
            String latitudeString = String.valueOf(currentWeatherRequest.getLatitude());
            String longitudeString = String.valueOf(currentWeatherRequest.getLongitude());
            
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitudeString + 
                    "&lon=" + longitudeString + "&appid=" + currentWeatherRequest.getAppId() + "&units=" + chosenUnit;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // JSON response
            String jsonResponse = response.toString();
            
            // Parse the JSON using Gson
            Gson gson = new Gson();
            CurrentWeatherResponse weatherResponse = gson.fromJson(jsonResponse, CurrentWeatherResponse.class);

            connection.disconnect();
            
            return weatherResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
    }
    
    /**
     * Initializes the current weather request object if it's currently null.
     * Creates a new instance of CurrentWeatherRequest and assigns it to the global variable.
     */
    public static void InitializeCurrentWeatherRequest()
    {                
        currentWeatherRequest = new CurrentWeatherRequest();
    }
    
    /**
     * Makes a request to retrieve both current and forecast weather data from the OpenWeatherMap API.
     * Uses latitude, longitude, API key, chosen units, and exclusion parameters to fetch weather information.
     *
     * @return CurrentAndForecastWeatherResponse containing the requested current and forecast weather data.
     *         Returns null if an error occurs during the retrieval process.
     */
    public static CurrentAndForecastWeatherResponse RequestCurrentAndForecastWeatherData() 
    {
        try {
            
            if (forecastWeatherRequest == null)
            {
                InitializeForecastWeatherRequest();
            }
            
            String latitudeString = String.valueOf(forecastWeatherRequest.getLatitude());
            String longitudeString = String.valueOf(forecastWeatherRequest.getLongitude());
            
            String apiUrl = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitudeString + 
                    "&lon=" + longitudeString + "&appid=" + forecastWeatherRequest.getAppId() + 
                    "&units=" + chosenUnit +
                    "&exclude=" + forecastWeatherRequest.getExclude();

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // JSON response
            String jsonResponse = response.toString();

            // Parse the JSON using Gson
            Gson gson = new Gson();
            CurrentAndForecastWeatherResponse weatherResponse = gson.fromJson(jsonResponse, CurrentAndForecastWeatherResponse.class);

            connection.disconnect();
            
            return weatherResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
    }
    
    /**
     * Initializes the forecast weather request object if it's currently null.
     * Creates a new instance of CurrentAndForecastWeatherRequest and assigns it to the global variable.
     */
    public static void InitializeForecastWeatherRequest()
    {        
        forecastWeatherRequest = new CurrentAndForecastWeatherRequest();
    }
    
    /**
     * Generates text based on the given visibility index.
     *
     * @param visibilityIndex The index representing visibility conditions.
     * @return String indicating the visibility status based on the index.
     */
    public static String VisibilityTextGenerator(int visibilityIndex)
    {
        String visibilityText;
        
        if (visibilityIndex == 10)
        {
            visibilityText = "Excellent visibility with long range";
        }
        else if (visibilityIndex >= 8)
        {
            visibilityText = "Good and comfortable visibility";
        }
        else if (visibilityIndex >= 4)
        {
            visibilityText = "Reasonable range of vision";
        }
        else if (visibilityIndex >= 1)
        {
            visibilityText = "Outdoor activities may be impacted";
        }
        else
        {
            visibilityText = "Challenging condition for vision";
        }
        
        return visibilityText;
    }
    
    /**
     * Generates text based on the given UV index.
     *
     * @param UVIndex The UV index representing UV radiation levels.
     * @return String indicating the UV index status based on the given value.
     */
    public static String UVITextGenerator(int UVIndex)
    {
        String UVIText;
        
        if (UVIndex >= 11)
        {
            UVIText = "extreme";
        }
        else if (UVIndex >= 8)
        {
            UVIText = "very high";
        }
        else if (UVIndex >= 6)
        {
            UVIText = "high";
        }
        else if (UVIndex >= 3)
        {
            UVIText = "moderate";
        }
        else
        {
            UVIText = "low";
        }
        
        return UVIText;
    }
    
    /**
     * Generates text describing the moon phase based on the given value.
     *
     * @param value The value representing the phase of the moon.
     * @return String describing the moon phase based on the given value.
     */
    public static String MoonPhaseTextGenerator(double value)
    {
        String moonPhaseText = "";
        
        if (value == 0 || value == 1)
        {
            moonPhaseText = "New moon";
        }
        else if (value == 0.25)
        {
            moonPhaseText = "First quarter";
        }
        else if (value == 0.5)
        {
            moonPhaseText = "Full moon";
        }
        else if (value == 0.75)
        {
            moonPhaseText = "Last quarter";
        }
        else if (value > 0 && value < 0.25)
        {
            moonPhaseText = "Waxing crescent";
        }
        else if (value > 0.25 && value < 0.5)
        {
            moonPhaseText = "Waxing gibbous";
        }
        else if (value > 0.5 && value < 0.75)
        {
            moonPhaseText = "Waning gibbous";
        }
        else if (value > 0.75 && value < 1)
        {
            moonPhaseText = "Waning crescent";
        }
        
        return moonPhaseText;
    }
    
    /**
     * Retrieves the image path for the moon phase based on the given value.
     *
     * @param value The value representing the phase of the moon.
     * @return String indicating the file path of the moon phase image based on the given value.
     */
    public static String MoonPhaseImagePath(double value)
    {
        String moonPhaseImagePath = "";
        
        if (value == 0 || value == 1)
        {
            moonPhaseImagePath = "moon_phase/new_moon.png";
        }
        else if (value == 0.25)
        {
            moonPhaseImagePath = "moon_phase/first_quarter.png";
        }
        else if (value == 0.5)
        {
            moonPhaseImagePath = "moon_phase/full_moon.png";
        }
        else if (value == 0.75)
        {
            moonPhaseImagePath = "moon_phase/third_quarter.png";
        }
        else if (value > 0 && value < 0.25)
        {
            moonPhaseImagePath = "moon_phase/waxing_crescent.png";
        }
        else if (value > 0.25 && value < 0.5)
        {
            moonPhaseImagePath = "moon_phase/waxing_gibbous.png";
        }
        else if (value > 0.5 && value < 0.75)
        {
            moonPhaseImagePath = "moon_phase/waning_gibbous.png";
        }
        else if (value > 0.75 && value < 1)
        {
            moonPhaseImagePath = "moon_phase/waning_crescent.png";
        }
        
        return moonPhaseImagePath;
    }

    /**
     * Retrieves the image path for the weather icon based on the given value.
     *
     * @param value The value representing different weather conditions.
     * @return String indicating the file path of the weather icon image based on the given value.
     */
    public static String WeatherIconImagePath(int value)
    {
        String weatherIconImagePath = "";
        
        if (value == 211 || value == 212 || value == 221)
        {
            weatherIconImagePath = "weather_icon/thunderstorm.png";
        }
        else if (value >= 200 && value < 300)
        {
            weatherIconImagePath = "weather_icon/thunderstorm_with_rain.png";
        }
        else if (value >= 300 && value < 400)
        {
            weatherIconImagePath = "weather_icon/drizzle.png";
        }
        else if (value == 511)
        {
            weatherIconImagePath = "weather_icon/freezing_rain.png";
        }
        else if (value == 500 || value == 501 || value == 520 || value == 531)
        {
            weatherIconImagePath = "weather_icon/rain.png";
        }
        else if (value > 501 && value < 600)
        {
            weatherIconImagePath = "weather_icon/heavy_rain.png";
        }
        else if (value >= 600 && value < 700)
        {
            weatherIconImagePath = "weather_icon/snow.png";
        }
        else if (value >= 700 && value < 800)
        {
            weatherIconImagePath = "weather_icon/haze.png";
        }
        else if (value == 800)
        {
            weatherIconImagePath = "weather_icon/clear.png";
        }
        else if (value == 801)
        {
            weatherIconImagePath = "weather_icon/few_cloud.png";
        }
        else if (value > 801)
        {
            weatherIconImagePath = "weather_icon/overcast_cloud.png";
        }
        
        return weatherIconImagePath;
    }
    
    /**
     * Retrieves the image path for the weather background based on the given value.
     *
     * @param value The value representing different weather conditions.
     * @return String indicating the file path of the weather background image based on the given value.
     */
    public static String WeatherBackgroundImagePath(int value)
    {
        String weatherBackgroundImagePath = "";
        
        if (value == 211 || value == 212 || value == 221)
        {
            weatherBackgroundImagePath = "weather_background/thunderstorm.png";
        }
        else if (value >= 200 && value < 300)
        {
            weatherBackgroundImagePath = "weather_background/thunderstorm_with_rain.png";
        }
        else if (value >= 300 && value < 400)
        {
            weatherBackgroundImagePath = "weather_background/drizzle.png";
        }
        else if (value == 511)
        {
            weatherBackgroundImagePath = "weather_background/freezing_rain.png";
        }
        else if (value == 500 || value == 501 || value == 520 || value == 531)
        {
            weatherBackgroundImagePath = "weather_background/rain.png";
        }
        else if (value > 501 && value < 600)
        {
            weatherBackgroundImagePath = "weather_background/heavy_rain.png";
        }
        else if (value >= 600 && value < 700)
        {
            weatherBackgroundImagePath = "weather_background/snow.png";
        }
        else if (value >= 700 && value < 800)
        {
            weatherBackgroundImagePath = "weather_background/haze.png";
        }
        else if (value == 800)
        {
            weatherBackgroundImagePath = "weather_background/clear.png";
        }
        else if (value == 801)
        {
            weatherBackgroundImagePath = "weather_background/few_cloud.png";
        }
        else if (value > 801)
        {
            weatherBackgroundImagePath = "weather_background/overcast_cloud.png";
        }
        
        return weatherBackgroundImagePath;
    }
}
