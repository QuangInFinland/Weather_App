package fi.tuni.prog3.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * The `App` class is the main class of the JavaFX application.
 * It extends the `Application` class and overrides the `start()` method.
 * The `start()` method loads the `StartScreen.fxml` file and sets the scene for the stage.
 * The `setRoot()` method sets the root of the scene to the specified FXML file.
 * The `loadFXML()` method loads the specified FXML file and returns the root node.
 * The `main()` method launches the JavaFX application.
 *
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class App extends Application {

    private static Scene scene;

    /**
     * The `App` class is the main class of the JavaFX application.
     * It extends the `Application` class and overrides the `start()` method.
     * The `start()` method loads the `StartScreen.fxml` file and sets the scene for the stage.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("StartScreen"));
        stage.setScene(scene);
        // Set stage name and icon
        stage.setTitle("Weather App");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sprite/app_icon.png")));
        stage.show();
    }

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml The name of the FXML file to be loaded.
     * @throws IOException If the FXML file cannot be loaded.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the specified FXML file and returns the root node.
     *
     * @param fxml The name of the FXML file to be loaded.
     * @return The root node of the FXML file.
     * @throws IOException If the FXML file cannot be loaded.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

}