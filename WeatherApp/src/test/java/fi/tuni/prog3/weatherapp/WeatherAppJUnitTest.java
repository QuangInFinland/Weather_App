package fi.tuni.prog3.weatherapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * This class contains JUnit test cases for the WeatherAppLocalFile class.
 * It covers different functionalities of the WeatherAppLocalFile class, testing constructors,
 * getter and setter methods, and specific functionality like adding a favorite place.
 * The tests aim to ensure the proper initialization and functionality of the WeatherAppLocalFile class.
 * 
 * @author Quang Nguyen, Nguyen Quang Duc
 * @version 3.0
 * @since 2023-12-06
 */
public class WeatherAppJUnitTest {
    
    /**
     * Test the default constructor of WeatherAppLocalFile.
     * Verifies the default initialization of various fields in the class.
     */
    @Test
    public void testDefaultConstructor() {
        // Create a new WeatherAppLocalFile object
        WeatherAppLocalFile walf = new WeatherAppLocalFile();
        // Test that the name field is empty
        assertEquals("", walf.getName());
        // Test that the apiKey field is the same as the one in StartScreenController
        assertEquals("c37bc522ccf0e106cd0d1be7e1ed9655", walf.getAppId());
        // Test that the isCurrentWeather field is true
        assertTrue(walf.isCurrentWeather());
        // Test that the isUnitSI field is true
        assertTrue(walf.isUnitSI());
        // Test that the defaultPlace field is a Place object with empty fields
        assertEquals(new Place(), walf.getDefaultPlace());
        // Test that the favouritePlaces field is an empty ArrayList
        assertEquals(new ArrayList<Place>(), walf.getFavouritePlaces());
        // Test that the searchHistories field is an empty ArrayList
        assertEquals(new ArrayList<Place>(), walf.getSearchHistories());
        // Test that the chosenUnixTime field is 1701718384
        assertEquals(979225200, walf.getChosenDate());
    }

    /**
     * Test the getter and setter methods of WeatherAppLocalFile.
     * Validates the correctness of the getter and setter functionality for the class fields.
     */
    @Test
    public void testGettersAndSetters() {
        // Create a new WeatherAppLocalFile object
        WeatherAppLocalFile walf = new WeatherAppLocalFile();
        
        // Initialize test variables
        String testName = "Sample name";
        String testAPIKey = "123456789";
        Place testPlace = new Place("Hanoi", "Vietnam", 21.028511, 105.804817);
        long testUnixTime = 1701718384;
        
        // Set the name field to "Nguyen"
        walf.setName(testName);
        // Test that the name field is "Nguyen"
        assertEquals(testName, walf.getName());
        // Set the apiKey field to "123456789"
        walf.setAppId(testAPIKey);
        // Test that the apiKey field is "123456789"
        assertEquals(testAPIKey, walf.getAppId());
        // Set the isCurrentWeather field to false
        walf.setCurrentWeather(false);
        // Test that the isCurrentWeather field is false
        assertFalse(walf.isCurrentWeather());
        // Set the isUnitSI field to false
        walf.setUnitSI(false);
        // Test that the isUnitSI field is false
        assertFalse(walf.isUnitSI());
        // Set the defaultPlace field to a Place object 
        walf.setDefaultPlace(testPlace);
        // Test that the defaultPlace field is a Place object
        assertEquals(testPlace, walf.getDefaultPlace());
        // Set the favouritePlaces field to an empty ArrayList
        walf.setFavouritePlaces(new ArrayList<Place>());
        // Test that the favouritePlaces field is an empty ArrayList
        assertEquals(new ArrayList<Place>(), walf.getFavouritePlaces());
        // Set the searchHistories field to an empty ArrayList
        walf.setSearchHistories(new ArrayList<Place>());
        // Test that the searchHistories field is an empty ArrayList
        assertEquals(new ArrayList<Place>(), walf.getSearchHistories());
        // Set the chosenUnixTime field to 1701718384
        walf.setChosenDate(testUnixTime);
        // Test that the chosenUnixTime field is 1701718384
        assertEquals(testUnixTime, walf.getChosenDate());
    }

    /**
     * Test the addFavouritePlace method of WeatherAppLocalFile.
     * Validates the functionality of adding a favorite place to the WeatherAppLocalFile instance.
     */
    @Test
    public void testAddFavouritePlace() {
        // Create a new WeatherAppLocalFile object
        WeatherAppLocalFile walf = new WeatherAppLocalFile();
        // Create a new Place object with fields "Hanoi", "Vietnam", 21.028511, 105.804817
        Place defaultPlace = new Place("Hanoi", "Vietnam", 21.028511, 105.804817);
        // Add the place object to the favouritePlaces field
        walf.addFavouritePlace(defaultPlace);
        // Test that the favouritePlaces field is an ArrayList with one element, which is the place object
        assertEquals(new ArrayList<Place>(List.of(defaultPlace)), walf.getFavouritePlaces());
    }
    
    /**
     * Test the constructor of WeatherAppLocalFile with parameters.
     * Verifies the initialization of fields when using a parameterized constructor.
     */
    @Test
    public void testConstructorWithParameters() {
        Place defaultPlace = new Place("Hanoi", "Vietnam", 21.028511, 105.804817);
        
        WeatherAppLocalFile walf = new WeatherAppLocalFile("Nguyen", "123456789", false, 1701718384, false, defaultPlace);

        // Test assertions
        assertEquals("Nguyen", walf.getName());
        assertEquals("123456789", walf.getAppId());
        assertFalse(walf.isCurrentWeather());
        assertFalse(walf.isUnitSI());
        assertEquals(defaultPlace, walf.getDefaultPlace());
        assertEquals(new ArrayList<>(), walf.getFavouritePlaces());
        assertEquals(new ArrayList<>(), walf.getSearchHistories());
        assertEquals(1701718384, walf.getChosenDate());
    }
    
    /**
     * Tests the parameterized constructor of WeatherAppLocalFile using different sets of input parameters.
     * 
     * @param name             The name value used for the WeatherAppLocalFile object construction.
     * @param apiKey           The API key value used for the WeatherAppLocalFile object construction.
     * @param isCurrentWeather Boolean flag indicating the current weather status used for the WeatherAppLocalFile object construction.
     * @param chosenUnixTime   The chosen Unix time value used for the WeatherAppLocalFile object construction.
     * @param isUnitSI         Boolean flag indicating the unit system used for the WeatherAppLocalFile object construction.
     * @param defaultPlace     The default Place object used for the WeatherAppLocalFile object construction.
     */
    @ParameterizedTest
    @MethodSource("ParametersProviderForConstructor")
    public void testConstructorWithParameters(String name, String apiKey, boolean isCurrentWeather,
                                              long chosenUnixTime, boolean isUnitSI, Place defaultPlace) {
        WeatherAppLocalFile walf = new WeatherAppLocalFile(name, apiKey, isCurrentWeather,
                chosenUnixTime, isUnitSI, defaultPlace);

        // Test assertions
        assertEquals(name, walf.getName());
        assertEquals(apiKey, walf.getAppId());
        assertEquals(isCurrentWeather, walf.isCurrentWeather());
        assertEquals(isUnitSI, walf.isUnitSI());
        assertEquals(defaultPlace, walf.getDefaultPlace());
        assertEquals(new ArrayList<Place>(), walf.getFavouritePlaces());
        assertEquals(new ArrayList<Place>(), walf.getSearchHistories());
        assertEquals(chosenUnixTime, walf.getChosenDate());
    }
    
    /**
     * Provides test arguments for parameterized constructor testing in WeatherAppLocalFile.
     * Returns a Stream of Arguments to test various parameter combinations for the constructor.
     * Each argument set consists of name, API key, current weather flag, chosen Unix time, SI unit flag,
     * and a Place object representing a location.
     * 
     * @return Stream of Arguments containing different parameter sets for testing the constructor.
     */
    static Stream<Arguments> ParametersProviderForConstructor() {
        return Stream.of(
            arguments("Name1", "APIKey1", true, 123456789L, false, new Place("Hanoi", "Vietnam", 21.028511, 105.804817)),
            arguments("Name2", "APIKey2", false, 987654321L, true, new Place("London", "UK", 51.5074, 0.1278))
        );
    }
}