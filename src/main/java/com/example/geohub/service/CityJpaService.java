/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */
package com.example.geohub.service;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CityRepository;
import com.example.geohub.repository.CountryJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class CityJpaService implements CityRepository {

   @Autowired
   private CityJpaRepository cityJpaRepository;

   @Autowired
   private CountryJpaRepository countryJpaRepository;

   @Override
   public ArrayList<City> getCities() {
      return (ArrayList<City>) cityJpaRepository.findAll();
   }

   @Override
   public City getCityById(int cityId) {
      try {
         return cityJpaRepository.findById(cityId).get();
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public City addCity(City city) {
      int countryId = city.getCountry().getCountryId();
      try {
         Country country = countryJpaRepository.findById(countryId).get();
         city.setCountry(country);
         cityJpaRepository.save(city);
         return city;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public City updateCity(int cityId, City city) {
      try {
         City newcity = cityJpaRepository.findById(cityId).get();
         if (city.getCityName() != null) {
            newcity.setCityName(city.getCityName());
         }
         if (city.getPopulation() != 0) {
            newcity.setPopulation(city.getPopulation());
         }
         if (city.getLatitude() != null) {
            newcity.setLatitude(city.getLatitude());
         }
         if (city.getLongitude() != null) {
            newcity.setLongitude(city.getLongitude());
         }
         if (city.getCountry() != null) {
            int countryId = city.getCountry().getCountryId();
            Country country = countryJpaRepository.findById(countryId).get();
            newcity.setCountry(country);
         }
         cityJpaRepository.save(newcity);
         return newcity;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public void deleteCity(int cityId) {
      try {
         cityJpaRepository.deleteById(cityId);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      throw new ResponseStatusException(HttpStatus.NO_CONTENT);
   }

   @Override
   public Country getCountryCity(int cityId) {
      try {
         City city = cityJpaRepository.findById(cityId).get();
         return city.getCountry();
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

}