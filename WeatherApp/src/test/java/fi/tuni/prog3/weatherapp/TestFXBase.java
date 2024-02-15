package fi.tuni.prog3.weatherapp;

import org.assertj.core.internal.bytebuddy.dynamic.scaffold.MethodGraph.Node;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * TestFXBase is a base class for TestFX tests.
 * It extends ApplicationTest and provides methods for setting up the JavaFX application and accessing nodes.
 * 
 * @author Nguyen Quang Duc
 * @version 1.0
 * @since 2023-12-06
 */
public class TestFXBase extends ApplicationTest {

    private static Scene scene;

    /**
     * The start method initializes the JavaFX application.
     * It loads the StartScreen.fxml file and sets the scene for the stage.
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
     * Finds and retrieves the node specified by the query.
     * TestFX provides many operations to retrieve elements from the loaded GUI.
     *
     * @param query The query to find the node in the scene graph.
     * @param <T>   The type of node to be retrieved.
     * @return The node found based on the query.
     */
    public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return (T) lookup(query).query();
    }
}
