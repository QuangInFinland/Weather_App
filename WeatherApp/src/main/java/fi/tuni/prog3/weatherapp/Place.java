package fi.tuni.prog3.weatherapp;

import java.util.Objects;

/**
 * The `Place` class represents a location on Earth.
 * It contains the following fields:
 * - `name`: A string representing the name of the place.
 * - `country`: A string representing the country in which the place is located.
 * - `latitude`: A double representing the latitude of the place.
 * - `longitude`: A double representing the longitude of the place.
 *
 * This class provides two constructors:
 * - A default constructor that initializes the fields with default values.
 * - A constructor that takes the name, country, latitude, and longitude as parameters to initialize the fields.
 *
 * This class also provides getters and setters for each field.
 * 
 * @author Quang Nguyen, Nguyen Quang Duc
 * @version 2.0
 * @since 2023-12-06
 */
public class Place {

    /**
     *
     */
    public String name;

    /**
     *
     */
    public String country;

    /**
     *
     */
    public Double latitude;

    /**
     *
     */
    public Double longitude;

    /**
     * Initializes the `Place` object with default values.
     */
    public Place() {
        this.name = "Đà Nẵng";
        this.country = "VN";
        this.latitude = 20.9147215;
        this.longitude = 106.6735556;
    }

    /**
     * Initializes the `Place` object with the specified name, country, latitude, and longitude.
     *
     * @param name The name of the place.
     * @param country The country in which the place is located.
     * @param latitude The latitude of the place.
     * @param longitude The longitude of the place.
     */
    public Place(String name, String country, Double latitude, Double longitude) {
        this.name = internalNameFilter(name, country);
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the name of the place.
     *
     * @return A string representing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the place.
     *
     * @param name A string representing the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the country in which the place is located.
     *
     * @return A string representing the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country in which the place is located.
     *
     * @param country A string representing the country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the latitude of the place.
     *
     * @return A double representing the latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the place.
     *
     * @param latitude A double representing the latitude.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude of the place.
     *
     * @return A double representing the longitude.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the place.
     *
     * @param longitude A double representing the longitude.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Changes the name, latitude, and longitude of the place.
     *
     * @param name The new name of the place.
     * @param latitude The new latitude of the place.
     * @param longitude The new longitude of the place.
     */
    public void changeProperties(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns a string representation of the `Place` object.
     *
     * @return A string representing the `Place` object.
     */
    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
    
    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object with which to compare.
     * @return `true` if this object is the same as the `obj` argument; `false` otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Place place = (Place) obj;
        return Objects.equals(latitude, place.latitude) &&
                Objects.equals(longitude, place.longitude);
    }
    
    /**
     * The `internalNameFilter` method filters the original name of a place based on its country.
     *
     * @param originalName The original name of the place.
     * @param originalCountry The country in which the place is located.
     * @return A string representing the filtered name of the place.
     */
    private String internalNameFilter(String originalName,String originalCountry)
    {
        if ("Hanoi".equals(originalName) && "VN".equals(originalCountry))
            return "Hà Lội";
        
        if ("Ho Chi Minh City".equals(originalName) && "VN".equals(originalCountry))
            return "Sài Thành";
        
        if ("Hue City".equals(originalName) && "VN".equals(originalCountry))
            return "Huệ";
        
        if (("Đà Nẵng".equals(originalName) || "Da Nang".equals(originalName)) 
                && "VN".equals(originalCountry))
            return "Đà Thành";
        
        return originalName;
    }
}
