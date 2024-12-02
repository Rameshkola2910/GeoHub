/*
 *
 * You can use the following import statements
 * 
 * java.util.ArrayList;
 * 
 */
package com.example.geohub.repository;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;

import java.util.*;

public interface CityRepository {

    ArrayList<City> getCities();

    City getCityById(int cityId);

    City addCity(City city);

    City updateCity(int cityId, City city);

    void deleteCity(int cityId);

    Country getCountryCity(int cityId);
}