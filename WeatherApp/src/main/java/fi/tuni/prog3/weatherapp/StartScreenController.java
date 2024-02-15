package fi.tuni.prog3.weatherapp;

import java.io.IOException;
import javafx.fxml.FXML;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The `StartScreenController` class is the controller for the start screen of the weather application.
 * 
 * @author Nguyen Quang Duc
 * @version 3.0
 * @since 2023-12-06
 */
public class StartScreenController {

    private static String apiKey = "c37bc522ccf0e106cd0d1be7e1ed9655";
    
    private static String localFileName = "local_file";
    
    /**
     *
     */
    public static Place chosenPlace;
    
    /**
     *
     */
    public static WeatherAppLocalFile localFile;
    
    // FXML GUI element in the My place section
    @FXML
    private Label greetingText;

    @FXML
    private Label chosenPlaceName;
    
    @FXML
    private Label chosenCountryName;
    
    @FXML
    private ImageView chosenPlaceWeatherIcon;
    
    @FXML
    private Label chosenPlaceCurrentTemp;
    
    @FXML
    private Label chosenPlacePosition;
    
    @FXML
    private TextField searchPlaceTextField;
    
    @FXML
    private Button addFavouritePlaceButton;
    
    // FXML GUI element in the Suggestion section

    /**
     *
     */
    public static int MAX_SUGGESTION_NUMBER = 3;
    
    @FXML
    private Button suggestionButton1;
    
    @FXML
    private Button suggestionButton2;
    
    @FXML
    private Button suggestionButton3;
    
    private Button[] suggestionButtons;
    
    private GeoCodingResponse[] searchPlaceResponses;
    
    // FXML GUI element in the History search section

    /**
     *
     */
    public static int MAX_HISTORY_NUMBER = 5;
    
    @FXML
    private Button historyButton1;
    
    @FXML
    private Button historyButton2;
    
    @FXML
    private Button historyButton3;
    
    @FXML
    private Button historyButton4;
    
    @FXML
    private Button historyButton5;
    
    private Button[] historyButtons;
    
    // FXML GUI element in the Favourite place section
    @FXML
    private Button favouriteButton1;
    
    @FXML
    private Button favouriteButton2;
    
    @FXML
    private Button favouriteButton3;
    
    private Button[] favouriteButtons;
    
    @FXML
    private ImageView FavouriteIconImage;
    
    @FXML
    private ImageView weatherBackground;
    
    // FXML GUI element in the Preference section
    @FXML
    private TextField userNameTextField;
    
    @FXML
    private RadioButton metricRadioButton;
    
    @FXML
    private RadioButton imperialRadioButton;
    
    @FXML
    private RadioButton historicalRadioButton;
    
    @FXML
    private DatePicker historicalDatePicker;
    
    @FXML
    private TextField apiKeyTextField;
    
    // Additional FXML GUI element
    @FXML
    private Label emptyFavouriteText;
    
    @FXML
    private Label emptyHistoryText;
    
    @FXML
    private Label emptySuggestionText;
    
    @FXML
    private Pane apiKeyErrorPane;
    
    /**
     * The `initialize` method initializes the button arrays and updates the user interface.
     */
    @FXML
    public void initialize() 
    {
        // Initialize button arrays
        suggestionButtons = new Button[MAX_SUGGESTION_NUMBER];
        favouriteButtons = new Button[MAX_SUGGESTION_NUMBER];
        historyButtons = new Button[MAX_HISTORY_NUMBER];
        
        try {
            for (int i = 1; i <= MAX_SUGGESTION_NUMBER; i++) {
                // Suggestion buttons
                Field fieldSuggestionButton = getClass().getDeclaredField("suggestionButton" + i);
                fieldSuggestionButton.setAccessible(true);
                Button suggestionButton = (Button) fieldSuggestionButton.get(this);
                suggestionButtons[i - 1] = suggestionButton;
                
                // Favourite buttons
                Field fieldFavouriteButton = getClass().getDeclaredField("favouriteButton" + i);
                fieldFavouriteButton.setAccessible(true);
                Button favouriteButton = (Button) fieldFavouriteButton.get(this);
                favouriteButtons[i - 1] = favouriteButton;
            }
            
            for (int i = 1; i <= MAX_HISTORY_NUMBER; i++) {
                // History buttons
                Field fieldHistoryButton = getClass().getDeclaredField("historyButton" + i);
                fieldHistoryButton.setAccessible(true);
                Button historyButton = (Button) fieldHistoryButton.get(this);
                historyButtons[i - 1] = historyButton;
            }
            
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        
        // Retrieve local file or write default file
        retrieveLocalFile();
        
        // Update greeting mesage
        UpdateGreetingMessage();
        
        // Update user preference
        updateUserPreferenceGUIOnStartUp(localFile);
                
        // Update chosen place data
        chosenPlace = localFile.defaultPlace;
        updateChosenPlaceGUI(chosenPlace);
        
        // Update history data
        updateHistorySearchList();
        
        // Update favourite data
        updateFavouritePlaceList();
    }
    
    /**
     * The `switchToWeatherScreen` method switches the user interface to the weather screen.
     * 
     * @throws Exception If an error occurs while switching to the weather screen.
     */
    @FXML
    private void switchToWeatherScreen() throws Exception {
        App.setRoot("WeatherScreen");
    }
    
    /**
     * The `resetUserPreference` method resets the user preference to default values and updates the user interface.
     */
    @FXML
    private void resetUserPreference()
    {
        // Create default user preference
        localFile = new WeatherAppLocalFile();
        
        // Update chosen place
        chosenPlace = localFile.defaultPlace;
        
        // Update API key
        updateAllAPIKeyVariable(localFile.getAppId());
        
        // Update user preference
        updateUserPreferenceGUIOnStartUp(localFile);
        
        // Save local File
        saveLocalFile();
        
        // update user greeting
        UpdateGreetingMessage();
        
        // Update weather GUI
        updateChosenPlaceGUI(chosenPlace);
        
        // Update search result, favourite and history list
        updateHistorySearchList();
        updateFavouritePlaceList();
        updateSearchResult(new GeoCodingResponse[0]);
        emptySuggestionText.setVisible(false);
    }
    
    /**
     * The `searchPlace` method searches for a place based on the user input and updates the search result on the user interface.
     */
    @FXML
    private void searchPlace()
    {
        // Initialize request if it is null
        if (GeoCodingAPICall.searchPlaceRequest == null )
        {
            GeoCodingAPICall.searchPlaceRequest = new GeoCodingRequest();
        }
        
        // Check if the search field are empty or null
        if (searchPlaceTextField.getText() == null
                || searchPlaceTextField.getText().length() == 0)
            return;
        
        // Customize and send the request
        GeoCodingAPICall.searchPlaceRequest.setCityName(searchPlaceTextField.getText());
        
        searchPlaceResponses = GeoCodingAPICall.RequestCurrentWeatherData();
        
        // Update GUI based on the response
        updateSearchResult(searchPlaceResponses);
    }
    
    /**
     * The `selectSuggestionButton1` method chooses the first suggestion button and updates the user interface.
     */
    @FXML
    private void selectSuggestionButton1()
    {
        chooseSearchResult(searchPlaceResponses[0], true);
    }
    
    /**
     * The `selectSuggestionButton2` method chooses the second suggestion button and updates the user interface.
     */
    @FXML
    private void selectSuggestionButton2()
    {
        chooseSearchResult(searchPlaceResponses[1], true);
    }
    
    /**
     * The `selectSuggestionButton3` method chooses the third suggestion button and updates the user interface.
     */
    @FXML
    private void selectSuggestionButton3()
    {
        chooseSearchResult(searchPlaceResponses[2], true);
    }
    
    /**
     * The `addFavouritePlace` method adds or removes the chosen place to/from the favourite list and updates the user interface.
     */
    @FXML
    private void addFavouritePlace()
    {
        boolean isFavourite = localFile.isFavouritePlace(chosenPlace);
        
        if (!isFavourite)
        {
            // Add if place is not favourite
            localFile.addFavouritePlace(chosenPlace);
        }
        else
        {
            // Remove if place is favourite
            localFile.removeFavouritePlace(chosenPlace);
        }
        
        // Save local file
        saveLocalFile();
        
        // refresh favourite list
        updateFavouritePlaceList();
        
        // Update favourite status
        updateFavouriteButtonImage(!isFavourite);
    }
    
    /**
     * The `selectFavouriteButton1` method chooses the first favourite button and updates the user interface.
     */
    @FXML
    private void selectFavouriteButton1()
    {
        selectFavouriteButton(0);
    }
    
    /**
     * The `selectFavouriteButton2` method chooses the second favourite button and updates the user interface.
     */
    @FXML
    private void selectFavouriteButton2()
    {
        selectFavouriteButton(1);
    }
    
    /**
     * The `selectFavouriteButton3` method chooses the third favourite button and updates the user interface.
     */
    @FXML
    private void selectFavouriteButton3()
    {
        selectFavouriteButton(2);
    }
    
    /**
     * The `selectHistoryButton1` method chooses the first history button and updates the user interface.
     */
    @FXML
    private void selectHistoryButton1()
    {
        selectHistoryButton(0);
    }
    
    /**
     * The `selectHistoryButton2` method chooses the second history button and updates the user interface.
     */
    @FXML
    private void selectHistoryButton2()
    {
        selectHistoryButton(1);
    }

    /**
     * The `selectHistoryButton3` method chooses the third history button and updates the user interface.
     */
    @FXML
    private void selectHistoryButton3()
    {
        selectHistoryButton(2);
    }
    
    /**
     * The `selectHistoryButton4` method chooses the fourth history button and updates the user interface.
     */
    @FXML
    private void selectHistoryButton4()
    {
        selectHistoryButton(3);
    }
    
    /**
     * The `selectHistoryButton5` method chooses the fifth history button and updates the user interface.
     */
    @FXML
    private void selectHistoryButton5()
    {
        selectHistoryButton(4);
    }
    
    /**
     * The `saveUserPreference` method saves the user preference and updates the user interface.
     */
    @FXML
    private void saveUserPreference()
    {
        // change user name
        localFile.name = userNameTextField.getText();
        
        // change API key
        localFile.apiKey = apiKeyTextField.getText();
        
        // Update all API Key
        updateAllAPIKeyVariable(localFile.apiKey);
        
        // change default unit        
        String oldUnit = WeatherAPICall.chosenUnit;
        
        boolean isMetricSelected = metricRadioButton.isSelected();
        
        localFile.isUnitSI = isMetricSelected;
        
        if (isMetricSelected)
            WeatherAPICall.chosenUnit = WeatherAPICall.metricUnit;
        else
            WeatherAPICall.chosenUnit = WeatherAPICall.imperialUnit;
        
        boolean isUnitChanged = !oldUnit.equals(WeatherAPICall.chosenUnit);
        
        // Change historical or current weather
        localFile.isCurrentWeather = !historicalRadioButton.isSelected();
        
        // Update historical date
        if (historicalDatePicker.getValue() != null)
            localFile.chosenUnixTime = historicalDatePicker.getValue()
                    .atStartOfDay(ZoneOffset.UTC).toEpochSecond();
        
        // save local file
        saveLocalFile();
        
        // update user greeting
        UpdateGreetingMessage();
        
        // Update weather GUI
        if (isUnitChanged)
        {
            // update chosen place data on GUI
            updateChosenPlaceGUI(chosenPlace);
        }
    }
    
    /**
     * The `selectHistoryButton` method selects a place from the search history list and updates the user interface.
     *
     * @param index The index of the selected history button.
     */
    private void selectHistoryButton(int index)
    {
        chosenPlace = localFile.searchHistories.get(index);
        
        // Set chosenPlace into default place
        localFile.setDefaultPlace(chosenPlace);
        saveLocalFile();
        
        // update chosen place data on GUI
        updateChosenPlaceGUI(chosenPlace);
    }
    
    /**
     * The `selectFavouriteButton` method selects a place from the favourite list and updates the user interface.
     *
     * @param index The index of the selected favourite button.
     */
    private void selectFavouriteButton(int index)
    {
        chosenPlace = localFile.favouritePlaces.get(index);
        
        // Set chosenPlace into default place
        localFile.setDefaultPlace(chosenPlace);
        saveLocalFile();
        
        // update chosen place data on GUI
        updateChosenPlaceGUI(chosenPlace);
    }

    /**
     * The `chooseSearchResult` method chooses a search result and updates the user interface.
     *
     * @param response The `GeoCodingResponse` object representing the chosen search result.
     * @param isHistorySaved A boolean indicating whether the search result should be saved to the search history.
     */
    private void chooseSearchResult(GeoCodingResponse response, boolean isHistorySaved)
    {
        // Create new place object based on response
        chosenPlace = new Place(response.name, response.country,
                response.lat, response.lon);
        
        // Set chosenPlace into default place
        localFile.setDefaultPlace(chosenPlace);
        
        // Save to history
        if (isHistorySaved)
        {
            localFile.addSearchHistory(chosenPlace);
            
            // refresh history list
            updateHistorySearchList();
        }
        
        // Save local file
        saveLocalFile();
        
        // update chosen place data on GUI
        updateChosenPlaceGUI(chosenPlace);
    }
    
    /**
     * The `updateChosenPlaceGUI` method updates the user interface with the chosen place data and the current weather data.
     *
     * @param place The `Place` object representing the chosen place.
     */
    private void updateChosenPlaceGUI(Place place)
    {
        // Update place name and country
        chosenPlaceName.setText(place.getName());
        
        chosenCountryName.setText(place.getCountry());
        
        // Update place position
        String latString = String.format("%.2f", place.getLatitude());
        String lonString = String.format("%.2f", place.getLongitude());
        String position = "Lat: " + latString + " | Long: " + lonString;
        chosenPlacePosition.setText(position);
        
        // Request simple current weather
        CurrentWeatherRequest request = new CurrentWeatherRequest();
        request.setLatitude(place.getLatitude());
        request.setLongitude(place.getLongitude());
        
        WeatherAPICall.currentWeatherRequest = request;
        
        CurrentWeatherResponse response = WeatherAPICall.RequestCurrentWeatherData();
        
        // update simple current weather chosen place
        updateWeatherCurrentPlace(response, place);
    }
    
    /**
     * The `updateWeatherCurrentPlace` method updates the current weather data and the favourite status of the chosen place on the user interface based on the response from the weather API.
     *
     * @param response The `CurrentWeatherResponse` object representing the current weather data.
     * @param place The `Place` object representing the chosen place.
     */
    private void updateWeatherCurrentPlace(CurrentWeatherResponse response, Place place)
    {
        // Update favourite status
        updateFavouriteButtonImage(localFile.isFavouritePlace(place));
        
        // Update current weather data based on response
        int weatherCode;
        
        if (response != null)
        {
            chosenPlaceCurrentTemp.setText(WeatherScreenController.TempToStringConverter(response.main.temp));
            weatherCode = response.weather[0].id;
            setVisibibleAPIKeyErrorPane(false);
        }
        else
        {
            chosenPlaceCurrentTemp.setText("--");
            weatherCode = 800;
            setVisibibleAPIKeyErrorPane(true);
        }

        Image weatherIcon = new Image(getClass().getResourceAsStream(WeatherAPICall.WeatherIconImagePath(weatherCode)));
        chosenPlaceWeatherIcon.setImage(weatherIcon);

        Image weatherBackgroundImage = new Image(getClass().getResourceAsStream(WeatherAPICall.WeatherBackgroundImagePath(weatherCode)));        
        weatherBackground.setImage(weatherBackgroundImage);
    }
    
    /**
     * The `updateSearchResult` method updates the search result on the user interface based on the response from the geocoding API.
     *
     * @param responses An array of `GeoCodingResponse` objects representing the search results.
     */
    public void updateSearchResult(GeoCodingResponse[] responses)
    {
        int suggestionNumber;
        
        if (responses.length >= MAX_SUGGESTION_NUMBER)
        {
            suggestionNumber = MAX_SUGGESTION_NUMBER;
        }
        else
        {
            suggestionNumber = responses.length;
        }
        
        // update text of each suggestion button
        for (int i = 0; i < MAX_SUGGESTION_NUMBER; i++)
        {
            if (i < suggestionNumber)
            {
                suggestionButtons[i].visibleProperty().set(true);
                
                String buttonText = responses[i].name + ", " + responses[i].country;
                suggestionButtons[i].setText(buttonText);
            }
            else
            {
                suggestionButtons[i].visibleProperty().set(false);
            }
        }
        
        // check if there is no suggestion
        emptySuggestionText.setVisible(suggestionNumber == 0);
    }
    

    /**
     * The `updateHistorySearchList` method updates the search history list on the user interface.
     */
    private void updateHistorySearchList()
    {
        int historyNumber;
        
        if (localFile.searchHistories.size() >= MAX_HISTORY_NUMBER)
        {
            historyNumber = MAX_HISTORY_NUMBER;
        }
        else
        {
            historyNumber = localFile.searchHistories.size();
        }
        
        // update text of each suggestion button
        for (int i = 0; i < MAX_HISTORY_NUMBER; i++)
        {            
            if (i < historyNumber)
            {
                Place historyPlace = localFile.searchHistories.get(i);

                historyButtons[i].visibleProperty().set(true);
                
                String buttonText = historyPlace.name + ", " + historyPlace.country;
                historyButtons[i].setText(buttonText);
            }
            else
            {
                historyButtons[i].visibleProperty().set(false);
            }
        }
        
        // check if there is no saved history place
        emptyHistoryText.setVisible(historyNumber == 0);
    }
    
    /**
     * The `updateFavouritePlaceList` method updates the favourite list on the user interface.
     */
    private void updateFavouritePlaceList()
    {
        int favouriteNumber;
        
        if (localFile.favouritePlaces.size() >= MAX_SUGGESTION_NUMBER)
        {
            favouriteNumber = MAX_SUGGESTION_NUMBER;
        }
        else
        {
            favouriteNumber = localFile.favouritePlaces.size();
        }
        
        // update text of each suggestion button
        for (int i = 0; i < MAX_SUGGESTION_NUMBER; i++)
        {            
            if (i < favouriteNumber)
            {
                Place historyPlace = localFile.favouritePlaces.get(i);

                favouriteButtons[i].visibleProperty().set(true);
                
                String buttonText = historyPlace.name + ", " + historyPlace.country;
                favouriteButtons[i].setText(buttonText);
            }
            else
            {
                favouriteButtons[i].visibleProperty().set(false);
            }
        }
        
        // check if there is no favourite place
        emptyFavouriteText.setVisible(favouriteNumber == 0);

    }
    
    /**
     * The `retrieveLocalFile` method retrieves the local file or writes a default file if it does not exist.
     */
    private void retrieveLocalFile()
    {
        if (WeatherFileHandler.doesFileExist(localFileName))
        {            
            localFile = WeatherFileHandler.readFromFile(localFileName);
        }
        else
        {
            localFile = new WeatherAppLocalFile();
            saveLocalFile();
        }
    }
    
    /**
     * The `saveLocalFile` method saves the local file.
     */
    public static void saveLocalFile()
    {
        WeatherFileHandler.writeToFile(localFile, localFileName);
    }

    /**
     * The `updateFavouriteButtonImage` method updates the favourite button image on the user interface based on whether the chosen place is a favourite place.
     *
     * @param isFavouritePlace A boolean indicating whether the chosen place is a favourite place.
     */
    private void updateFavouriteButtonImage(boolean isFavouritePlace)
    {
        Image buttonIcon = new Image(getClass().getResourceAsStream(getFavouriteIconPath(isFavouritePlace)));
        FavouriteIconImage.setImage(buttonIcon);    
    }
    
    /**
     * The `getFavouriteIconPath` method returns the path of the favourite icon image based on whether the chosen place is a favourite place.
     *
     * @param isFavourite A boolean indicating whether the chosen place is a favourite place.
     * @return A string representing the path of the favourite icon image.
     */
    public static String getFavouriteIconPath(boolean isFavourite)
    {
        if (!isFavourite)
        {
            return "sprite/favourite_unfilled.png";
        }
        
        return "sprite/favourite_filled.png";
    }
    
    /**
     * The `UpdateGreetingMessage` method updates the greeting message displayed on the user interface.
     */
    private void UpdateGreetingMessage()
    {
        String greetingString = "Hi";
        
        if (!"".equals(localFile.name))
            greetingString += ", " + localFile.name + "!";
        else
            greetingString += "!";
        
        greetingText.setText(greetingString);
    }
    
    /**
     * The `getApiKey` method returns the API key.
     *
     * @return A string representing the API key.
     */
    public static String getApiKey()
    {
        return apiKey;
    }
    
    /**
     * The `updateUserPreferenceGUIOnStartUp` method updates the user interface with the user preference data when the application starts up.
     *
     * @param file The `WeatherAppLocalFile` object representing the user preference data.
     */
    private void updateUserPreferenceGUIOnStartUp(WeatherAppLocalFile file)
    {
        // Update user name
        userNameTextField.setText(file.name);
        
        // Update API key
        apiKeyTextField.setText(file.apiKey);
        
        // Change historical or current weather
        historicalRadioButton.setSelected(!file.isCurrentWeather);
        
        // Update historical date
        historicalDatePicker.setValue(FromUnixToLocalDate(file.getChosenDate()));
        
        // Update preferred unit
        if (file.isUnitSI)
            WeatherAPICall.chosenUnit = WeatherAPICall.metricUnit;
        else
            WeatherAPICall.chosenUnit = WeatherAPICall.imperialUnit;
        
        // Update unit radio buttons GUI
        metricRadioButton.setSelected(file.isUnitSI);
        imperialRadioButton.setSelected(!file.isUnitSI);
    }

    /**
     * The `FromUnixToLocalDate` method converts a Unix timestamp to a `LocalDate` object.
     *
     * @param unixTime The Unix timestamp to be converted.
     * @return A `LocalDate` object representing the converted Unix timestamp.
     */
    public static LocalDate FromUnixToLocalDate(long unixTime)
    {
        Instant instant = Instant.ofEpochSecond(unixTime);
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * The `updateAllAPIKeyVariable` method updates all API key variables with the new API key.
     *
     * @param newAPIKey A string representing the new API key.
     */
    private void updateAllAPIKeyVariable(String newAPIKey)
    {
        apiKey = newAPIKey;
        if (WeatherAPICall.currentWeatherRequest != null)
            WeatherAPICall.currentWeatherRequest.setAppId(newAPIKey);
        
        if (WeatherAPICall.historicalWeatherRequest != null)
            WeatherAPICall.historicalWeatherRequest.setAppId(newAPIKey);
        
        if (WeatherAPICall.forecastWeatherRequest != null)
            WeatherAPICall.forecastWeatherRequest.setAppId(newAPIKey);
        
        if (GeoCodingAPICall.searchPlaceRequest != null)
            GeoCodingAPICall.searchPlaceRequest.setAppId(newAPIKey);
    }

    /**
     * The `setVisibibleAPIKeyErrorPane` method sets the visibility of the API key error pane on the user interface.
     *
     * @param isVisible A Boolean indicating whether the API key error pane should be visible.
     */
    public void setVisibibleAPIKeyErrorPane(boolean isVisible)
    {
        apiKeyErrorPane.setVisible(isVisible);
    }
}
