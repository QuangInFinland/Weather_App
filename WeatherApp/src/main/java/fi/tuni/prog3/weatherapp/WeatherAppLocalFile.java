package fi.tuni.prog3.weatherapp;

import java.util.ArrayList;
import java.util.List;

/**
 * The WeatherAppLocalFile class represents the local configuration file for the Weather App.
 * It stores user preferences such as name, API key, units, default place, favorite places, search history, etc.
 * This class provides methods to initialize default settings and manage user-specific configurations.
 *
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class WeatherAppLocalFile {

    /**
     *
     */
    public String name;

    /**
     *
     */
    public String apiKey;

    /**
     *
     */
    public boolean isCurrentWeather;

    /**
     *
     */
    public long chosenUnixTime;

    /**
     *
     */
    public boolean isUnitSI;

    /**
     *
     */
    public Place defaultPlace;

    /**
     *
     */
    public List<Place> favouritePlaces;

    /**
     *
     */
    public List<Place> searchHistories;
    
    /**
     * Constructs a WeatherAppLocalFile object with customized user settings.
     *
     * @param name              The user's name or identifier.
     * @param apiKey            The API key used for weather data retrieval.
     * @param isCurrentWeather  Flag indicating whether to retrieve current weather data.
     * @param chosenUnixTime    The chosen Unix time for historical weather data (if applicable).
     * @param isUnitSI          Flag indicating whether to use SI units (true) or Imperial units (false).
     * @param defaultPlace      The default location/place for weather data retrieval.
     */
    public WeatherAppLocalFile(String name, String apiKey, boolean isCurrentWeather,
        long chosenUnixTime, boolean isUnitSI, Place defaultPlace) {
        this.name = name;
        this.apiKey = apiKey;
        this.isCurrentWeather = isCurrentWeather;
        this.isUnitSI = isUnitSI;
        this.defaultPlace = defaultPlace;
        this.favouritePlaces = new ArrayList<>();
        this.searchHistories = new ArrayList<>();
        this.chosenUnixTime = chosenUnixTime;
    }
    
    /**
     * Constructs a WeatherAppLocalFile object with default settings.
     * The default values include an empty name, default API key, and other preset configurations.
     */
    public WeatherAppLocalFile() {
        this.name = "";
        this.apiKey = "c37bc522ccf0e106cd0d1be7e1ed9655";
        this.isCurrentWeather = true;
        this.isUnitSI = true;
        this.defaultPlace = new Place();
        this.favouritePlaces = new ArrayList<>();
        this.searchHistories = new ArrayList<>();
        this.chosenUnixTime = 979225200;
    }

    /**
     * The method retrieves the name associated with the Weather App's local configuration.
     *
     * @return The name stored in the Weather App's local configuration.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the Weather App's local configuration.
     *
     * @param name The new name to be set in the Weather App's configuration.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the API key stored in the Weather App's local configuration.
     *
     * @return The API key used for accessing weather data.
     */
    public String getAppId() {
        return apiKey;
    }

    /**
     * Sets the API key for accessing weather data in the Weather App's local configuration.
     *
     * @param apiKey The new API key to be set in the Weather App's configuration.
     */
    public void setAppId(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Checks whether the Weather App is set to retrieve current weather data.
     *
     * @return true if the Weather App is configured to fetch current weather data; otherwise, false.
     */
    public boolean isCurrentWeather() {
        return isCurrentWeather;
    }

    /**
     * Sets the configuration for the Weather App to retrieve current weather data.
     *
     * @param currentWeather The flag indicating whether to fetch current weather data (true) or not (false).
     */
    public void setCurrentWeather(boolean currentWeather) {
        isCurrentWeather = currentWeather;
    }

    /**
     * Retrieves the chosen date or Unix time stored in the Weather App's local configuration.
     *
     * @return The chosen date or Unix time for historical weather data retrieval.
     */
    public long getChosenDate() {
        return chosenUnixTime;
    }

    /**
     * Sets the chosen date or Unix time for historical weather data retrieval in the Weather App's configuration.
     *
     * @param chosenUnixTime The new chosen date or Unix time to be set in the Weather App's configuration.
     */
    public void setChosenDate(long chosenUnixTime) {
        this.chosenUnixTime = chosenUnixTime;
    }

    /**
     * Checks whether the Weather App is configured to use SI units.
     *
     * @return true if the Weather App is set to use SI units; otherwise, false for Imperial units.
     */
    public boolean isUnitSI() {
        return isUnitSI;
    }

    /**
     * Sets the unit system configuration for the Weather App to use SI or Imperial units.
     *
     * @param unitSI The flag indicating whether to use SI units (true) or Imperial units (false).
     */
    public void setUnitSI(boolean unitSI) {
        isUnitSI = unitSI;
    }

    /**
     * Retrieves the default place/location stored in the Weather App's local configuration.
     *
     * @return The default place/location for weather data retrieval.
     */
    public Place getDefaultPlace() {
        return defaultPlace;
    }

    /**
     * Sets the default place/location for weather data retrieval in the Weather App's configuration.
     *
     * @param defaultPlace The new default place/location to be set in the Weather App's configuration.
     */
    public void setDefaultPlace(Place defaultPlace) {
        this.defaultPlace = defaultPlace;
    }

    /**
     * Retrieves the list of search histories stored in the Weather App's local configuration.
     *
     * @return The list of search histories maintained by the Weather App.
     */
    public List<Place> getSearchHistories() {
        return searchHistories;
    }

    /**
     * Sets the list of search histories for the Weather App's local configuration.
     *
     * @param searchHistories The new list of search histories to be set in the Weather App's configuration.
     */
    public void setSearchHistories(List<Place> searchHistories) {
        this.searchHistories = searchHistories;
    }

    /**
     * Adds a new search history to the list of search histories.
     * Limits the number of search histories to the specified maximum number.
     *
     * @param newSearch The new Place object representing the search to be added.
     */
    public void addSearchHistory(Place newSearch) {
        this.searchHistories = optimizedAddPlace(this.searchHistories, newSearch, StartScreenController.MAX_HISTORY_NUMBER);
    }
    
    /**
     * Retrieves the list of favorite places stored in the Weather App's local configuration.
     *
     * @return The list of favorite places maintained by the Weather App.
     */
    public List<Place> getFavouritePlaces() {
        return favouritePlaces;
    }

    /**
     * Sets the list of favorite places for the Weather App's local configuration.
     * This method allows updating the list of favorite places with a new set of places.
     *
     * @param favouritePlaces The new list of favorite places to be set in the Weather App's configuration.
     */
    public void setFavouritePlaces(List<Place> favouritePlaces) {
        this.favouritePlaces = favouritePlaces;
    }

    /**
     * Adds a new favorite place to the list of favorite places.
     * Limits the number of favorite places to the specified maximum number.
     *
     * @param newPlace The new Place object representing the favorite place to be added.
     */
    public void addFavouritePlace(Place newPlace) { 
        this.favouritePlaces = optimizedAddPlace(this.favouritePlaces, newPlace, StartScreenController.MAX_SUGGESTION_NUMBER);
    }

    /**
     * Removes a favorite place from the list of favorite places if it exists.
     *
     * @param removedPlace The Place object to be removed from the list of favorite places.
     */
    public void removeFavouritePlace(Place removedPlace) 
    { 
        int existingIndex = -1;

        // Check if there's a similar place in the list
        for (int i = 0; i < favouritePlaces.size(); i++) {
            if (favouritePlaces.get(i).equals(removedPlace)) {
                existingIndex = i;
                break;
            }
        }

        // If a similar place exists, remove it
        if (existingIndex != -1) {
            favouritePlaces.remove(existingIndex);
        }
    }
    
    /**
     * Checks if a place is present in the list of favorite places.
     *
     * @param currentPlace The Place object to check if it exists in the list of favorite places.
     * @return true if the place is found in the list, otherwise false.
     */
    public boolean isFavouritePlace(Place currentPlace)
    {
        // Check if there's a similar place in the list
        for (int i = 0; i < favouritePlaces.size(); i++) {
            if (favouritePlaces.get(i).equals(currentPlace)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Adds a Place to the list of places while optimizing the list to a specified maximum size.
     * If a similar place exists in the list, it is removed before adding the new place.
     * The list is then trimmed to the specified maximum size.
     *
     * @param placeList   The list of Place objects to which the new Place will be added.
     * @param addedPlace  The new Place object to be added to the list.
     * @param limit       The maximum size to which the list should be optimized.
     * @return The updated list of Place objects with the new Place added and optimized to the specified limit.
     */
    private List<Place> optimizedAddPlace(List<Place> placeList, Place addedPlace, int limit) {        
        int existingIndex = -1;

        // Check if there's a similar place in the list
        for (int i = 0; i < placeList.size(); i++) {
            if (placeList.get(i).equals(addedPlace)) {
                existingIndex = i;
                break;
            }
        }

        // If a similar place exists, remove it
        if (existingIndex != -1) {
            placeList.remove(existingIndex);
        }

        // Add the new place at the beginning of the list
        placeList.add(0, addedPlace);

        // Trim the list to a maximum size of limit number
        if (placeList.size() > limit) {
            placeList = placeList.subList(0, limit);
        }
        
        return placeList;
    }
}
