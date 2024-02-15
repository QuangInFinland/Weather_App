package fi.tuni.prog3.weatherapp;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.api.FxAssert.verifyThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.testfx.api.FxRobotException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

/**
 * This class contains TestFX-based tests for WeatherAppFx functionalities.
 * 
 * @author Quang Nguyen, Nguyen Quang Duc
 * @version 3.0
 * @since 2023-12-06
 */
public class WeatherAppFxTest extends TestFXBase {
    
    // CSS IDs for UI elements
    final String addFavouriteButtonID = "#addFavouritePlaceButton";
    final String favouriteButtonOneID = "#favouriteButton1";
    final String chosenPlaceNameID = "#chosenPlaceName";
    final String userNameTextFieldID = "#userNameTextField";
    final String placeSearchTextFieldID = "#searchPlaceTextField";
    final String suggestionButtonOneID = "#suggestionButton1";
    
    /**
     * Test clicking an unknown button to assert FxRobotException.
     */
    @Test
    @DisplayName("Testing clicking unknown button")
    public void clickOnUnknowButton() 
    {
        FxRobotException exception = assertThrows(FxRobotException.class, () -> {
            clickOn("#unknown");
        });
    }
    
    /**
     * Test functionality of adding a favorite place.
     */
    @Test
    @DisplayName("Testing favourite button")
    public void testButtonAddFavouritePlace()
    {              
        clickOn(addFavouriteButtonID);

        sleep(1000);
        
        String defaultPlaceName = StartScreenController.localFile.defaultPlace.name;
        
        verifyThat(favouriteButtonOneID, (Button button) -> {
            String buttonText = button.getText();
            return buttonText.contains(defaultPlaceName);
        });
    }
    
    /**
     * Test displaying the default place.
     */
    @Test
    @DisplayName("Testing Default Place Display")
    public void testDefaultPlaceDisplay()
    {        
        verifyThat(chosenPlaceNameID, (Label label) -> {
            String labeltext = label.getText();
            return labeltext.contains(StartScreenController.localFile.defaultPlace.name);
        });
    }

    /**
     * Test automatic saving of user preferences.
     */
    @Test
    @DisplayName("Testing User Preference Automatic Saving")
    public void testAutomaticSavedUserPreference()
    {        
        String testName = StartScreenController.localFile.name + "abc";
        clickOn(userNameTextFieldID).type(KeyCode.A, KeyCode.B, KeyCode.C);
                
        sleep(1000);
        
        assertEquals(testName, StartScreenController.localFile.name);
    }
    
    /**
     * Test searching for a new place.
     */
    @Test
    @DisplayName("Testing Search New Place")
    public void testNewPlaceSearch()
    {        
        String searchResultPlaceName = "Tampere";
        
        KeyCode[] keyCodeArray = {KeyCode.T, KeyCode.A, KeyCode.M, KeyCode.P, 
                KeyCode.E, KeyCode.R, KeyCode.E};
        clickOn(placeSearchTextFieldID).type(keyCodeArray);
               
        clickOn(suggestionButtonOneID);
        
        sleep(1000);
        
        verifyThat(suggestionButtonOneID, (Button button) -> {
            String buttonText = button.getText();
            return buttonText.contains(searchResultPlaceName);
        });
        
        assertEquals(searchResultPlaceName, StartScreenController.localFile.defaultPlace.name);
    }
}