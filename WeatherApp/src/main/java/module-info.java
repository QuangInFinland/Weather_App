/**
 * Defines a Java module for the WeatherApp application.
 * Specifies required dependencies and access rules for the modules.
 * This module integrates JavaFX for GUI functionality, uses FXML for defining the UI,
 * and requires the com.google.gson library for JSON processing.
 * It opens the package fi.tuni.prog3.weatherapp to allow access to the FXML components.
 * The module exports fi.tuni.prog3.weatherapp to make it accessible to other modules.
 */
module fi.tuni.prog3.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens fi.tuni.prog3.weatherapp to javafx.fxml;
    
    exports fi.tuni.prog3.weatherapp;
}