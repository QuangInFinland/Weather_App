package fi.tuni.prog3.weatherapp;

import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;

/**
 * The `WeatherFileHandler` class provides methods to read from and write to a local file containing user preference data.
 * It contains the following methods:
 * - `writeToFile`: Writes the user preference data to a local file.
 * - `readFromFile`: Reads the user preference data from a local file.
 * - `doesFileExist`: Checks whether the local file exists.
 * 
 * @author Quang Nguyen, Nguyen Quang Duc
 * @version 2.0
 * @since 2023-12-06
 */
public class WeatherFileHandler {

    /**
     * The `writeToFile` method writes the user preference data to a local file.
     *
     * @param data The `WeatherAppLocalFile` object representing the user preference data.
     * @param filePath A string representing the path of the local file.
     * @return A boolean indicating whether the write operation was successful.
     */
    public static boolean writeToFile(WeatherAppLocalFile data, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * The `readFromFile` method reads the user preference data from a local file.
     *
     * @param filePath A string representing the path of the local file.
     * @return The `WeatherAppLocalFile` object representing the user preference data.
     */
    public static WeatherAppLocalFile readFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, WeatherAppLocalFile.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The `doesFileExist` method checks whether the local file exists.
     *
     * @param filePath A string representing the path of the local file.
     * @return A boolean indicating whether the local file exists.
     */
    public static boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
