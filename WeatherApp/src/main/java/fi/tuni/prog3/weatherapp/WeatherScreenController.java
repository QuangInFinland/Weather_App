package fi.tuni.prog3.weatherapp;

import static fi.tuni.prog3.weatherapp.StartScreenController.chosenPlace;
import static fi.tuni.prog3.weatherapp.StartScreenController.localFile;
import static fi.tuni.prog3.weatherapp.StartScreenController.getFavouriteIconPath;
import static fi.tuni.prog3.weatherapp.StartScreenController.saveLocalFile;
import static fi.tuni.prog3.weatherapp.StartScreenController.FromUnixToLocalDate;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import java.lang.reflect.Field;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * Controller class responsible for managing the Weather Screen GUI functionality.
 * This class handles the display and interaction with weather-related data and forecast details.
 * It manages the retrieval and presentation of current and historical weather information,
 * along with updating the graphical user interface components accordingly.
 * Provides methods for handling weather data updates, user interactions, and GUI modifications.
 */
public class WeatherScreenController {
    
    // FXML GUI element representing weather data
    @FXML
    private ImageView weatherBackground;
    
    @FXML
    private ImageView FavouriteIconImage;
    
    @FXML
    private Label mainCurrentTemp;
    
    @FXML
    private Label mainLowTemp;
    
    @FXML
    private Label mainHighTemp;
    
    @FXML
    private Label mainPlaceName;
    
    @FXML
    private Label generalWeatherText;
    
    @FXML
    private Label currentUVindex;
    
    @FXML
    private Label currentUVText;
    
    @FXML
    private Slider currentUVISlider;
    
    @FXML
    private Label sunriseTime;
    
    @FXML
    private Label sunsetTime;
    
    @FXML
    private Label currentVisibility;
    
    @FXML
    private Label visibilityText;
    
    @FXML
    private Label feelLikeTemp;
    
    @FXML
    private Label feelLikeText;
    
    @FXML
    private Label windSpeed;
    
    @FXML
    private ImageView windArrow;
    
    @FXML
    private ImageView rainIcon;
    
    @FXML
    private Label rainAmount1h;
    
    @FXML
    private Label rainForecastText;
    
    @FXML
    private Label currentPressure;
    
    @FXML
    private ImageView pressureArrow;
    
    @FXML
    private Label currentHumidity;
    
    @FXML
    private Slider humiditySlider;
    
    @FXML
    private Label alertTitleText;
    
    @FXML
    private Label senderAlertText;
    
    @FXML
    private Label moonPhaseText;
    
    @FXML
    private ImageView moonPhaseImage;
    
    // FXML GUI element representing hourly forecast weather data
    private static final int NUMBER_OF_HOURLY_FORECAST = 24;
    
    @FXML
    private Label hourlyForecastTime1;
    @FXML
    private Label hourlyForecastTime2;
    @FXML
    private Label hourlyForecastTime3;
    @FXML
    private Label hourlyForecastTime4;
    @FXML
    private Label hourlyForecastTime5;
    @FXML
    private Label hourlyForecastTime6;
    @FXML
    private Label hourlyForecastTime7;
    @FXML
    private Label hourlyForecastTime8;
    @FXML
    private Label hourlyForecastTime9;
    @FXML
    private Label hourlyForecastTime10;
    @FXML
    private Label hourlyForecastTime11;
    @FXML
    private Label hourlyForecastTime12;
    @FXML
    private Label hourlyForecastTime13;
    @FXML
    private Label hourlyForecastTime14;
    @FXML
    private Label hourlyForecastTime15;
    @FXML
    private Label hourlyForecastTime16;
    @FXML
    private Label hourlyForecastTime17;
    @FXML
    private Label hourlyForecastTime18;
    @FXML
    private Label hourlyForecastTime19;
    @FXML
    private Label hourlyForecastTime20;
    @FXML
    private Label hourlyForecastTime21;
    @FXML
    private Label hourlyForecastTime22;
    @FXML
    private Label hourlyForecastTime23;
    @FXML
    private Label hourlyForecastTime24;
    
    private Label[] hourlyForecastTimes;
    
    @FXML
    private Label hourlyForecastTemp1;
    @FXML
    private Label hourlyForecastTemp2;
    @FXML
    private Label hourlyForecastTemp3;
    @FXML
    private Label hourlyForecastTemp4;
    @FXML
    private Label hourlyForecastTemp5;
    @FXML
    private Label hourlyForecastTemp6;
    @FXML
    private Label hourlyForecastTemp7;
    @FXML
    private Label hourlyForecastTemp8;
    @FXML
    private Label hourlyForecastTemp9;
    @FXML
    private Label hourlyForecastTemp10;
    @FXML
    private Label hourlyForecastTemp11;
    @FXML
    private Label hourlyForecastTemp12;
    @FXML
    private Label hourlyForecastTemp13;
    @FXML
    private Label hourlyForecastTemp14;
    @FXML
    private Label hourlyForecastTemp15;
    @FXML
    private Label hourlyForecastTemp16;
    @FXML
    private Label hourlyForecastTemp17;
    @FXML
    private Label hourlyForecastTemp18;
    @FXML
    private Label hourlyForecastTemp19;
    @FXML
    private Label hourlyForecastTemp20;
    @FXML
    private Label hourlyForecastTemp21;
    @FXML
    private Label hourlyForecastTemp22;
    @FXML
    private Label hourlyForecastTemp23;
    @FXML
    private Label hourlyForecastTemp24;
    
    private Label[] hourlyForecastTemps;
    
    @FXML
    private ImageView hourlyForecastIcon1;
    @FXML
    private ImageView hourlyForecastIcon2;
    @FXML
    private ImageView hourlyForecastIcon3;
    @FXML
    private ImageView hourlyForecastIcon4;
    @FXML
    private ImageView hourlyForecastIcon5;
    @FXML
    private ImageView hourlyForecastIcon6;
    @FXML
    private ImageView hourlyForecastIcon7;
    @FXML
    private ImageView hourlyForecastIcon8;
    @FXML
    private ImageView hourlyForecastIcon9;
    @FXML
    private ImageView hourlyForecastIcon10;
    @FXML
    private ImageView hourlyForecastIcon11;
    @FXML
    private ImageView hourlyForecastIcon12;
    @FXML
    private ImageView hourlyForecastIcon13;
    @FXML
    private ImageView hourlyForecastIcon14;
    @FXML
    private ImageView hourlyForecastIcon15;
    @FXML
    private ImageView hourlyForecastIcon16;
    @FXML
    private ImageView hourlyForecastIcon17;
    @FXML
    private ImageView hourlyForecastIcon18;
    @FXML
    private ImageView hourlyForecastIcon19;
    @FXML
    private ImageView hourlyForecastIcon20;
    @FXML
    private ImageView hourlyForecastIcon21;
    @FXML
    private ImageView hourlyForecastIcon22;
    @FXML
    private ImageView hourlyForecastIcon23;
    @FXML
    private ImageView hourlyForecastIcon24;
    
    private ImageView[] hourlyForecastIcons;
    
    // FXML GUI element representing daily forecast weather data
    private static final int NUMBER_OF_DAILY_FORECAST = 8;

    @FXML
    private Label dailyForecastTime1;
    @FXML
    private Label dailyForecastTime2;
    @FXML
    private Label dailyForecastTime3;
    @FXML
    private Label dailyForecastTime4;
    @FXML
    private Label dailyForecastTime5;
    @FXML
    private Label dailyForecastTime6;
    @FXML
    private Label dailyForecastTime7;
    @FXML
    private Label dailyForecastTime8;
    @FXML
    private Label dailyForecastTime9;
    @FXML
    private Label dailyForecastTime10;
    @FXML
    private Label dailyForecastTime11;
    @FXML
    private Label dailyForecastTime12;
    @FXML
    private Label dailyForecastTime13;
    @FXML
    private Label dailyForecastTime14;
    
    private Label[] dailyForecastTimes;
    
    @FXML
    private Label dailyForecastTempMin1;
    @FXML
    private Label dailyForecastTempMin2;
    @FXML
    private Label dailyForecastTempMin3;
    @FXML
    private Label dailyForecastTempMin4;
    @FXML
    private Label dailyForecastTempMin5;
    @FXML
    private Label dailyForecastTempMin6;
    @FXML
    private Label dailyForecastTempMin7;
    @FXML
    private Label dailyForecastTempMin8;
    @FXML
    private Label dailyForecastTempMin9;
    @FXML
    private Label dailyForecastTempMin10;
    @FXML
    private Label dailyForecastTempMin11;
    @FXML
    private Label dailyForecastTempMin12;
    @FXML
    private Label dailyForecastTempMin13;
    @FXML
    private Label dailyForecastTempMin14;
    
    private Label[] dailyForecastTempMins;
    
    @FXML
    private Label dailyForecastTempMax1;
    @FXML
    private Label dailyForecastTempMax2;
    @FXML
    private Label dailyForecastTempMax3;
    @FXML
    private Label dailyForecastTempMax4;
    @FXML
    private Label dailyForecastTempMax5;
    @FXML
    private Label dailyForecastTempMax6;
    @FXML
    private Label dailyForecastTempMax7;
    @FXML
    private Label dailyForecastTempMax8;
    @FXML
    private Label dailyForecastTempMax9;
    @FXML
    private Label dailyForecastTempMax10;
    @FXML
    private Label dailyForecastTempMax11;
    @FXML
    private Label dailyForecastTempMax12;
    @FXML
    private Label dailyForecastTempMax13;
    @FXML
    private Label dailyForecastTempMax14;
    
    private Label[] dailyForecastTempMaxs;
    
    @FXML
    private ImageView dailyForecastIcon1;
    @FXML
    private ImageView dailyForecastIcon2;
    @FXML
    private ImageView dailyForecastIcon3;
    @FXML
    private ImageView dailyForecastIcon4;
    @FXML
    private ImageView dailyForecastIcon5;
    @FXML
    private ImageView dailyForecastIcon6;
    @FXML
    private ImageView dailyForecastIcon7;
    @FXML
    private ImageView dailyForecastIcon8;
    @FXML
    private ImageView dailyForecastIcon9;
    @FXML
    private ImageView dailyForecastIcon10;
    @FXML
    private ImageView dailyForecastIcon11;
    @FXML
    private ImageView dailyForecastIcon12;
    @FXML
    private ImageView dailyForecastIcon13;
    @FXML
    private ImageView dailyForecastIcon14;
    
    private ImageView[] dailyForecastIcons;
    
    // Additional FXML GUI element
    @FXML
    private ScrollPane hourlyForecastScrollPane;
    
    @FXML
    private ScrollPane dailyForecastScrollPane;
    
    @FXML
    private Label historicalDateText;
    
    @FXML
    private Label hourlyForecastEmptyText;
    
    @FXML
    private Label dailyForecastEmptyText;
      
    @FXML
    private Pane apiKeyErrorPane;
    
    @FXML
    private Label futureWeatherNotification;
    
    /**
     * Initializes the UI components related to hourly and daily weather forecasts.
     * Sets up labels and image views to display weather information.
     * This method is automatically called when the corresponding FXML controller is initialized.
     * It populates arrays of labels and image views for hourly and daily forecasts based on their respective FXML IDs.
     * Additionally, it attempts to update the weather data upon startup.
     */
    @FXML
    public void initialize() 
    {
        hourlyForecastTimes = new Label[NUMBER_OF_HOURLY_FORECAST];
        hourlyForecastTemps = new Label[NUMBER_OF_HOURLY_FORECAST];        
        hourlyForecastIcons = new ImageView[NUMBER_OF_HOURLY_FORECAST];
        
        dailyForecastTimes = new Label[NUMBER_OF_DAILY_FORECAST];
        dailyForecastTempMins = new Label[NUMBER_OF_DAILY_FORECAST]; 
        dailyForecastTempMaxs = new Label[NUMBER_OF_DAILY_FORECAST]; 
        dailyForecastIcons = new ImageView[NUMBER_OF_DAILY_FORECAST];
        
        try {
            for (int i = 1; i <= NUMBER_OF_HOURLY_FORECAST; i++) {
                // Hour title
                Field fieldHourTitle = getClass().getDeclaredField("hourlyForecastTime" + i);
                fieldHourTitle.setAccessible(true);
                Label labelHour = (Label) fieldHourTitle.get(this);
                hourlyForecastTimes[i - 1] = labelHour;
                
                // Hour temp
                Field fieldHourTemp = getClass().getDeclaredField("hourlyForecastTemp" + i);
                fieldHourTemp.setAccessible(true);
                Label labelTemp = (Label) fieldHourTemp.get(this);
                hourlyForecastTemps[i - 1] = labelTemp;
                
                // Weather Icon
                Field fieldHourWeatherIcon = getClass().getDeclaredField("hourlyForecastIcon" + i);
                fieldHourWeatherIcon.setAccessible(true);
                ImageView weatherIcon = (ImageView) fieldHourWeatherIcon.get(this);
                hourlyForecastIcons[i - 1] = weatherIcon;
            }
            
            for (int i = 1; i <= NUMBER_OF_DAILY_FORECAST; i++) {
                // Daily title
                Field fieldDailyTitle = getClass().getDeclaredField("dailyForecastTime" + i);
                fieldDailyTitle.setAccessible(true);
                Label labelDaily = (Label) fieldDailyTitle.get(this);
                dailyForecastTimes[i - 1] = labelDaily;
                
                // Daily temp min
                Field fieldDailyTempMin = getClass().getDeclaredField("dailyForecastTempMin" + i);
                fieldDailyTempMin.setAccessible(true);
                Label labelTempMin = (Label) fieldDailyTempMin.get(this);
                dailyForecastTempMins[i - 1] = labelTempMin;
                
                // Daily temp max
                Field fieldDailyTempMax = getClass().getDeclaredField("dailyForecastTempMax" + i);
                fieldDailyTempMax.setAccessible(true);
                Label labelTempMax = (Label) fieldDailyTempMax.get(this);
                dailyForecastTempMaxs[i - 1] = labelTempMax;
                
                // Weather Icon
                Field fieldDailyWeatherIcon = getClass().getDeclaredField("dailyForecastIcon" + i);
                fieldDailyWeatherIcon.setAccessible(true);
                ImageView weatherIcon = (ImageView) fieldDailyWeatherIcon.get(this);
                dailyForecastIcons[i - 1] = weatherIcon;
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        
        // Update weather data on startup
        updateCustomizedWeatherOnStartup();
    }

    /**
    * Switches the current screen to the StartScreen.
    * Invoked when the corresponding FXML button is pressed.
    *
    * @throws IOException Signals an exception in case of issues navigating to the StartScreen.
    */
    @FXML
    private void switchToStartScreen() throws IOException 
    {
        App.setRoot("StartScreen");
    }
    
    /**
     * Refreshes the weather data displayed on the UI.
     * Invoked when the corresponding FXML button for refresh is pressed.
     * It updates customized weather data based on the current settings.
     */
    @FXML
    private void refreshWeatherData() 
    {
        // update customized weather data
        updateCustomizedWeatherOnStartup();
    }
    
    /**
     * Adds or removes the chosen place from the list of favorite places.
     * Invoked when the corresponding FXML button for adding/removing favorite is pressed.
     * It checks if the chosen place is already a favorite and performs the respective action accordingly.
     * After the action, it saves the local file and updates the favorite button's status.
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
        
        // save local file
        saveLocalFile();
        
        // Update favourite status
        updateFavouriteButtonImage(!isFavourite);
    }
    
    /**
     * Updates the weather data based on the chosen settings upon application startup.
     */
    private void updateCustomizedWeatherOnStartup()
    {
        // Update current weather data
        if (localFile.isCurrentWeather)
        {
            updateCurrentWeatherData();
            
            return;
        }
        
        // Update historical weather data
        updateHistoricalWeatherData();
    }

    /**
     * Updates the displayed icon on the favorite button based on the provided status.
     *
     * @param isFavouritePlace Boolean indicating the status of the place as favorite or not.
     */
    private void updateFavouriteButtonImage(boolean isFavouritePlace)
    {
        Image buttonIcon = new Image(getClass().getResourceAsStream(getFavouriteIconPath(isFavouritePlace)));
        FavouriteIconImage.setImage(buttonIcon);    
    }

    /**
     * Updates the historical weather data for the chosen place.
     */
    private void updateHistoricalWeatherData()
    {
        Place currentPlace = chosenPlace;
        
        // Update basic GUI for historical weather data
        updateBasicGUI(true);
        
        // Check if chosen date is in the past
        boolean isFutureDate = !isHistoricalDateInThePast(localFile.getChosenDate());
        futureWeatherNotification.setVisible(isFutureDate);
        mainCurrentTemp.setVisible(!isFutureDate);
        
        if (isFutureDate)
        {
            setVisibibleAPIKeyErrorPane(false);
            return;
        }
        
        WeatherAPICall.historicalWeatherRequest = new HistoricalWeatherRequest(currentPlace.getLatitude(), currentPlace.getLongitude(), localFile.getChosenDate());
        
        HistoricalWeatherResponse response = WeatherAPICall.RequestHistocialWeatherData();
        
        // Set visible error pane
        setVisibibleAPIKeyErrorPane(response == null);
        
        // Check if response is null
        if (response == null)
            return;
        
        updateHistoricalWeatherGUI(response);
    }
    
    /**
     * Updates the current weather data for the chosen place.
     */
    private void updateCurrentWeatherData()
    {
        Place currentPlace = chosenPlace;
        
        // Update basic GUI for current weather data
        updateBasicGUI(false);
        
        WeatherAPICall.forecastWeatherRequest = new CurrentAndForecastWeatherRequest(currentPlace.getLatitude(), currentPlace.getLongitude());
        
        CurrentAndForecastWeatherResponse response = WeatherAPICall.RequestCurrentAndForecastWeatherData();
        
        // Set visible error pane
        setVisibibleAPIKeyErrorPane(response == null);
        
        // Check if response is null        
        if (response == null)
            return;
        
        updateCurrentWeatherGUI(response);
    }
    
    /**
     * Prepares the GUI layout based on whether it's historical weather data or not.
     *
     * @param isHistoricalWeather Boolean indicating whether the data is historical or not.
     */
    private void preUpdateWeatherGUI(boolean isHistoricalData)
    {
        hourlyForecastScrollPane.setVisible(!isHistoricalData);
        dailyForecastScrollPane.setVisible(!isHistoricalData);
        hourlyForecastEmptyText.setVisible(isHistoricalData);
        dailyForecastEmptyText.setVisible(isHistoricalData);
        
        mainHighTemp.setVisible(!isHistoricalData);
        mainLowTemp.setVisible(!isHistoricalData);
        historicalDateText.setVisible(isHistoricalData);
        
        if (!isHistoricalData)
        {
            mainCurrentTemp.setVisible(!isHistoricalData);
            futureWeatherNotification.setVisible(isHistoricalData);
        }
    }
    
    /**
     * Updates the basic GUI elements based on the provided weather type.
     *
     * @param isHistoricalWeather Boolean indicating whether the data is historical or not.
     */
    private void updateBasicGUI(boolean isHistoricalWeather)
    {
        // Prepare GUI for historical weather GUI
        preUpdateWeatherGUI(isHistoricalWeather);
        
        // Update place name
        mainPlaceName.setText(chosenPlace.getName());
        
        // Update favourite place status
        updateFavouriteButtonImage(localFile.isFavouritePlace(chosenPlace));
        
        // Update historical date
        if (isHistoricalWeather)
            historicalDateText.setText(DateUnixTimeConverter(localFile.getChosenDate(), true));
    }
    
    /**
     * Checks if a historical date is in the past based on the provided UNIX timestamp.
     *
     * @param unixHistoricalTime UNIX timestamp indicating a historical date.
     * @return True if the date is in the past; otherwise, false.
     */
    private boolean isHistoricalDateInThePast(long unixHistoricalTime)
    {
        // Check if the date is in the future
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
        
        return !currentDate.isBefore(FromUnixToLocalDate(unixHistoricalTime));
    }
    
    /**
     * Updates the displayed GUI elements with historical weather response data.
     *
     * @param response HistoricalWeatherResponse containing the weather data.
     */
    private void updateHistoricalWeatherGUI(HistoricalWeatherResponse response)
    {
        // Update weather background
        Image weatherBackgroundImage = new Image(getClass().getResourceAsStream(WeatherAPICall.WeatherBackgroundImagePath(response.data[0].weather[0].id)));        
        weatherBackground.setImage(weatherBackgroundImage);

        // Update current temp
        mainCurrentTemp.setText(TempToStringConverter(response.data[0].temp));
        
        // Update general weather description
        generalWeatherText.setText(response.data[0].weather[0].main);
        
        // Update UV index
        int UVIndex = (int)Math.round(response.data[0].uvi);
        currentUVindex.setText(String.valueOf(UVIndex));
        currentUVText.setText(WeatherAPICall.UVITextGenerator(UVIndex));
        
        if (UVIndex <= 11)
            currentUVISlider.setValue(UVIndex);
        else
            currentUVISlider.setValue(11);
        
        // Update Sunrise and Sunset time
        sunriseTime.setText(UnixTimeConverter(response.data[0].sunrise + response.timezone_offset, false));
        sunsetTime.setText(UnixTimeConverter(response.data[0].sunset + response.timezone_offset, true));
        
        // Update visibility index and text
        int visibleDistance = (int)Math.round((double)response.data[0].visibility / 1000);
        currentVisibility.setText(String.valueOf(visibleDistance) + " km");
        
        visibilityText.setText(WeatherAPICall.VisibilityTextGenerator(visibleDistance));
        
        // Update feel like temp and text
        feelLikeTemp.setText(TempToStringConverter(response.data[0].feels_like));
        
        String weatherDescription = response.data[0].weather[0].description;
        weatherDescription = weatherDescription.replaceFirst(weatherDescription.substring(0, 1), weatherDescription.substring(0, 1).toUpperCase());
        feelLikeText.setText(weatherDescription);
        
        // Update wind data
        windSpeed.setText(DecimalToStringConverter(response.data[0].wind_speed));
        SetWindArrowRotation(response.data[0].wind_deg);
        
        // Update rain data
        UpdateHistoricalRainWeatherData(response);

        // Update pressure data
        int pressureValue = response.data[0].pressure;
        
        currentPressure.setText(String.valueOf(pressureValue));
        SetPressureArrowRotation(mapValueToAngle(pressureValue));
        
        // Update humidity data
        int humidityValue = response.data[0].humidity;
        currentHumidity.setText(String.valueOf(humidityValue));
        humiditySlider.setValue(humidityValue);
    }
    
    /**
     * Updates the displayed GUI elements with current weather response data.
     *
     * @param response CurrentAndForecastWeatherResponse containing the weather data.
     */
    private void updateCurrentWeatherGUI(CurrentAndForecastWeatherResponse response)
    {
        // Update weather background
        Image weatherBackgroundImage = new Image(getClass().getResourceAsStream(WeatherAPICall.WeatherBackgroundImagePath(response.current.weather[0].id)));        
        weatherBackground.setImage(weatherBackgroundImage);

        // Update current temp
        mainCurrentTemp.setText(TempToStringConverter(response.current.temp));
        
        // Update current high and low temp        
        mainLowTemp.setText("L: " + TempToStringConverter(response.daily[0].temp.min));
        mainHighTemp.setText("H: " + TempToStringConverter(response.daily[0].temp.max));
        
        // Update general weather description
        generalWeatherText.setText(response.current.weather[0].main);

        // Update place name
        mainPlaceName.setText(chosenPlace.getName());
        
        // Update UV index
        int UVIndex = (int)Math.round(response.current.uvi);
        currentUVindex.setText(String.valueOf(UVIndex));
        currentUVText.setText(WeatherAPICall.UVITextGenerator(UVIndex));
        
        if (UVIndex <= 11)
            currentUVISlider.setValue(UVIndex);
        else
            currentUVISlider.setValue(11);
        
        // Update Sunrise and Sunset time
        sunriseTime.setText(UnixTimeConverter(response.current.sunrise + response.timezone_offset, false));
        sunsetTime.setText(UnixTimeConverter(response.current.sunset + response.timezone_offset, true));
        
        // Update visibility index and text
        int visibleDistance = (int)Math.round((double)response.current.visibility / 1000);
        currentVisibility.setText(String.valueOf(visibleDistance) + " km");
        
        visibilityText.setText(WeatherAPICall.VisibilityTextGenerator(visibleDistance));
        
        // Update feel like temp and text
        feelLikeTemp.setText(TempToStringConverter(response.current.feels_like));
        
        String weatherDescription = response.current.weather[0].description;
        weatherDescription = weatherDescription.replaceFirst(weatherDescription.substring(0, 1), weatherDescription.substring(0, 1).toUpperCase());
        feelLikeText.setText(weatherDescription);
        
        // Update wind data
        windSpeed.setText(DecimalToStringConverter(response.current.wind_speed));
        SetWindArrowRotation(response.current.wind_deg);
        
        // Update rain data
        UpdateCurrentRainWeatherData(response);

        // Update pressure data
        int pressureValue = response.current.pressure;
        
        currentPressure.setText(String.valueOf(pressureValue));
        SetPressureArrowRotation(mapValueToAngle(pressureValue));
        
        // Update humidity data
        int humidityValue = response.current.humidity;
        currentHumidity.setText(String.valueOf(humidityValue));
        humiditySlider.setValue(humidityValue);
        
        // Update alert data
        if (response.alerts == null || response.alerts.length == 0)
        {
            alertTitleText.setText("--");
            senderAlertText.setText("No important alert");
        }
        else
        {
            CurrentAndForecastWeatherResponse.Alert currentAlert = response.alerts[0];
            alertTitleText.setText(currentAlert.event);
            senderAlertText.setText(currentAlert.sender_name);
        }
        
        // Update moon phase data
        double moonPhase = response.daily[0].moon_phase;
        moonPhaseText.setText(WeatherAPICall.MoonPhaseTextGenerator(moonPhase));
        
        // Load the image using the classloader from the resources folder
        Image newImage = new Image(getClass().getResourceAsStream(WeatherAPICall.MoonPhaseImagePath(moonPhase)));

        // Set the new image to the ImageView
        moonPhaseImage.setImage(newImage);
        
        // Update hourly forecast
        UpdateHourlyWeatherForecast(response);
        
        // Update daily forecast
        UpdateDailyWeatherForecast(response);
    }
    
    /**
     * Updates the hourly weather forecast based on the provided response data.
     *
     * @param response CurrentAndForecastWeatherResponse containing the weather forecast data.
     */
    public void UpdateHourlyWeatherForecast(CurrentAndForecastWeatherResponse response)
    {
        for (int i = 0; i < NUMBER_OF_HOURLY_FORECAST; i++) {
            
            CurrentAndForecastWeatherResponse.Hourly hourlyForecast = response.hourly[i];
            // Update time data
            hourlyForecastTimes[i].setText(OnlyHourUnixTimeConverter(hourlyForecast.dt + response.timezone_offset));
            
            // Update temp data
            hourlyForecastTemps[i].setText(TempToStringConverter(hourlyForecast.temp));
            
            // Update weather icon
            Image weatherIcon = new Image(getClass().getResourceAsStream(WeatherAPICall.WeatherIconImagePath(hourlyForecast.weather[0].id)));
            hourlyForecastIcons[i].setImage(weatherIcon);
        }
    }
    
    /**
     * Updates the daily weather forecast based on the provided response data.
     *
     * @param response CurrentAndForecastWeatherResponse containing the weather forecast data.
     */
    public void UpdateDailyWeatherForecast(CurrentAndForecastWeatherResponse response)
    {
        for (int i = 0; i < NUMBER_OF_DAILY_FORECAST; i++) {
            
            CurrentAndForecastWeatherResponse.Daily dailyForecast = response.daily[i];
            // Update time data
            dailyForecastTimes[i].setText(DateUnixTimeConverter(dailyForecast.dt + response.timezone_offset, false));
            
            // Update temp data
            dailyForecastTempMins[i].setText(TempToStringConverter(dailyForecast.temp.min));
            dailyForecastTempMaxs[i].setText(TempToStringConverter(dailyForecast.temp.max));
            
            // Update weather icon
            Image weatherIcon = new Image(getClass().getResourceAsStream(WeatherAPICall.WeatherIconImagePath(dailyForecast.weather[0].id)));
            dailyForecastIcons[i].setImage(weatherIcon);
        }
    }
    
    /**
     * Maps the provided value to an angle within a specified range.
     *
     * @param value Value to be mapped to an angle.
     * @return Angle mapped based on the provided value.
     */
    public static double mapValueToAngle(int value) 
    {
        // Calculate the range of values and range of angles
        int lowerLimit = 970;
        int higherLimit = 1030;
        double valueRange = higherLimit - lowerLimit;
        double angleRange = 180; // Angle range from 0 to 180 degrees

        // Map the value to an angle within the angle range
        double mappedAngle = ((value - lowerLimit) / valueRange) * angleRange;

        return mappedAngle;
    }
    
    /**
     * Updates the rain weather data based on the current weather response.
     *
     * @param response CurrentAndForecastWeatherResponse containing the current weather data.
     */
    private void UpdateCurrentRainWeatherData(CurrentAndForecastWeatherResponse response)
    {
        Image icon;
        
        if (response.current.rain != null)
        {            
            icon = new Image(getClass().getResourceAsStream("sprite/rain.png"));          
            rainAmount1h.setText(RainAmountStringConverter(response.current.rain._1h));
        }
        else if (response.current.snow != null)
        {
            icon = new Image(getClass().getResourceAsStream("sprite/snow.png"));
            rainAmount1h.setText(RainAmountStringConverter(response.current.snow._1h));
        }
        else
        {            
            icon = new Image(getClass().getResourceAsStream("sprite/rain.png"));
            rainAmount1h.setText(RainAmountStringConverter(0));
        }
        
        rainIcon.setImage(icon);
        
        UpdateForecastRainWeatherData(response);
    }
    
    /**
     * Updates the historical rain weather data based on the historical weather response.
     *
     * @param response HistoricalWeatherResponse containing the historical weather data.
     */
    private void UpdateHistoricalRainWeatherData(HistoricalWeatherResponse response)
    {
        Image icon;
        
        if (response.data[0].rain != null)
        {            
            icon = new Image(getClass().getResourceAsStream("sprite/rain.png"));          
            rainAmount1h.setText(RainAmountStringConverter(response.data[0].rain._1h));
        }
        else if (response.data[0].snow != null)
        {
            icon = new Image(getClass().getResourceAsStream("sprite/snow.png"));
            rainAmount1h.setText(RainAmountStringConverter(response.data[0].snow._1h));
        }
        else
        {            
            icon = new Image(getClass().getResourceAsStream("sprite/rain.png"));
            rainAmount1h.setText(RainAmountStringConverter(0));
        }
        
        rainIcon.setImage(icon);
        
        rainForecastText.setText("");
    }
    
    /**
     * Updates the forecast rain weather data based on the forecast response.
     *
     * @param response CurrentAndForecastWeatherResponse containing the forecast weather data.
     */
    private void UpdateForecastRainWeatherData(CurrentAndForecastWeatherResponse response)
    {
        if (response.daily[1].rain != 0)
        {                
            rainForecastText.setText(RainForecastStringConverter(response.daily[1].rain));
        }
        else if (response.daily[1].snow != 0)
        {  
            rainForecastText.setText(RainForecastStringConverter(response.daily[1].snow));
        }
        else
        {              
            rainForecastText.setText(RainForecastStringConverter(0));
        }
    }
    
    /**
     * Converts the provided rain amount value to a formatted string.
     *
     * @param value Rain amount value to be converted.
     * @return Formatted string representing the rain amount.
     */
    private String RainAmountStringConverter(double value)
    {        
        return String.valueOf((int)Math.round(value)) + " mm";
    }
    
    /**
     * Converts the provided rain forecast value to a formatted string.
     *
     * @param value Rain forecast value to be converted.
     * @return Formatted string representing the rain forecast.
     */
    private String RainForecastStringConverter(double value)
    {        
        return String.valueOf((int)Math.round(value)) + " mm expected in next 24h.";
    }
    
    /**
     * Sets the rotation angle for the pressure arrow based on the specified angle.
     *
     * @param angle Angle value for rotation.
     */
    private void SetPressureArrowRotation(double angle) 
    {
        // Remove existing Rotate transformations
        pressureArrow.getTransforms().clear();

        // Get image view dimensions
        double imageViewWidth = pressureArrow.getBoundsInLocal().getWidth();
        double imageViewHeight = pressureArrow.getBoundsInLocal().getHeight();

        // Calculate pivot point (center of the image view)
        double pivotX = imageViewWidth / 2;
        double pivotY = imageViewHeight / 2;

        // Create a new Rotate transformation with the specified angle and pivot point
        Rotate rotate = new Rotate(angle, pivotX, pivotY);

        // Apply the new rotation to the ImageView
        pressureArrow.getTransforms().add(rotate);
    }
    
    /**
     * Sets the rotation angle for the wind arrow based on the specified angle.
     *
     * @param angle Angle value for rotation.
     */
    private void SetWindArrowRotation(double angle) 
    {
        // Remove existing Rotate transformations
        windArrow.getTransforms().clear();

        // Get image view dimensions
        double imageViewWidth = windArrow.getBoundsInLocal().getWidth();
        double imageViewHeight = windArrow.getBoundsInLocal().getHeight();

        // Calculate pivot point (center of the image view)
        double pivotX = imageViewWidth / 2;
        double pivotY = imageViewHeight / 2;

        // Create a new Rotate transformation with the specified angle and pivot point
        Rotate rotate = new Rotate(angle, pivotX, pivotY);

        // Apply the new rotation to the ImageView
        windArrow.getTransforms().add(rotate);
    }
    
    /**
     * Converts the provided decimal value to a string representation.
     *
     * @param value Decimal value to be converted.
     * @return String representation of the decimal value.
     */
    private String DecimalToStringConverter(double value)
    {
        DecimalFormat df = new DecimalFormat("#.#");
        double formattedValue = Double.parseDouble(df.format(value));
        
        return String.valueOf(formattedValue);
    }
    
    /**
     * Converts the temperature value to a string with appropriate units (°C or °F).
     *
     * @param temp Temperature value to be converted.
     * @return String representation of the temperature value with units.
     */
    public static String TempToStringConverter(double temp)
    {
        String TempText = String.valueOf((int)Math.round(temp));
        
        if (WeatherAPICall.chosenUnit == null ? WeatherAPICall.metricUnit == null : WeatherAPICall.chosenUnit.equals(WeatherAPICall.metricUnit))
            TempText += "°C";
        else
            TempText += "°F";
        
        return TempText;
    }
    
    /**
     * Converts the provided Unix timestamp to a formatted time string.
     *
     * @param unixTimestamp Unix timestamp to be converted.
     * @param isTextExtended Flag indicating whether extended text is needed.
     * @return Formatted time string based on the provided Unix timestamp.
     */
    private String UnixTimeConverter(long unixTimestamp, boolean isTextExtended)
    {
        // Convert Unix timestamp to Instant
        Instant timeInstant = Instant.ofEpochSecond(unixTimestamp);

        // Convert to local time (modify ZoneId according to the desired timezone)
        ZoneId zoneId = ZoneId.of("UTC"); // Change the timezone if needed
        //Instant localInstant = sunriseInstant.atZone(zoneId).toInstant();
        ZonedDateTime zonedDateTime = timeInstant.atZone(zoneId);
        
        // Format the local time into "HH:MM A.M./P.M." format
        String formatText;
        
        if (isTextExtended)
            formatText = "hh:mm a";
        else
            formatText = "hh:mm";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatText);        
        String formattedTimeText = formatter.format(zonedDateTime).toLowerCase().replace("am", "a.m.").replace("pm", "p.m.");
        
        return formattedTimeText;
    }
    
    /**
     * Converts the provided Unix timestamp to a string representation of only the hour.
     *
     * @param unixTimestamp Unix timestamp to be converted.
     * @return String representation of the hour from the Unix timestamp.
     */
    private String OnlyHourUnixTimeConverter(long unixTimestamp)
    {
        // Convert Unix timestamp to Instant
        Instant timeInstant = Instant.ofEpochSecond(unixTimestamp);

        // Convert to local time (modify ZoneId according to the desired timezone)
        ZoneId zoneId = ZoneId.of("UTC"); // Change the timezone if needed
        //Instant localInstant = sunriseInstant.atZone(zoneId).toInstant();
        ZonedDateTime zonedDateTime = timeInstant.atZone(zoneId);
        
        // Format the local time into "HH" format
        String formatText = "hh";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatText);        
        String formattedTimeText = formatter.format(zonedDateTime);
        
        return formattedTimeText;
    }
    
    /**
     * Converts the provided Unix timestamp to a formatted date string.
     *
     * @param unixTimestamp Unix timestamp to be converted.
     * @param isFullDate Flag indicating whether a full date or abbreviated date is needed.
     * @return Formatted date string based on the provided Unix timestamp.
     */
    private String DateUnixTimeConverter(long unixTimestamp, boolean isFullDate)
    {
        // Convert Unix timestamp to Instant
        Instant timeInstant = Instant.ofEpochSecond(unixTimestamp);

        // Convert to local time (modify ZoneId according to the desired timezone)
        ZoneId zoneId = ZoneId.of("UTC"); // Change the timezone if needed
        //Instant localInstant = sunriseInstant.atZone(zoneId).toInstant();
        ZonedDateTime zonedDateTime = timeInstant.atZone(zoneId);
        
        // Format the local time into "DD.MM" format
        String formatText;
        if (!isFullDate)
            formatText = "dd.MM";
        else
            formatText = "dd/MM/YYYY";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatText);        
        
        String formattedTimeText = formatter.format(zonedDateTime);
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date using the formatter
        String CurrentDateText = currentDate.format(formatter);
        
        if (formattedTimeText == null ? CurrentDateText == null : formattedTimeText.equals(CurrentDateText))
        {
            formattedTimeText = "Today";
        }
        
        return formattedTimeText;
    }
    
    /**
     * Sets the visibility of the API key error pane based on the specified visibility flag.
     *
     * @param isVisible Flag indicating the visibility of the API key error pane.
     */
    public void setVisibibleAPIKeyErrorPane(boolean isVisible)
    {
        apiKeyErrorPane.setVisible(isVisible);
    }
}