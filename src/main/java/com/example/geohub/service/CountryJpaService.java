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

import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service

public class CountryJpaService implements CountryRepository {

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<Country> getCountries() {
        return (ArrayList<Country>) countryJpaRepository.findAll();
    }

    @Override
    public Country getCountryById(int countryId) {
        try {
            return countryJpaRepository.findById(countryId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country addCountry(Country country) {
        countryJpaRepository.save(country);
        return country;
    }

    @Override
    public Country updateCountry(int countryId, Country country) {
        try {
            Country newcountry = countryJpaRepository.findById(countryId).get();
            if (country.getCountryName() != null) {
                newcountry.setCountryName(country.getCountryName());
            }
            if (country.getCurrency() != null) {
                newcountry.setCurrency(country.getCurrency());
            }
            if (country.getPopulation() != 0) {
                newcountry.setPopulation(country.getPopulation());
            }
            if (country.getLatitude() != null) {
                newcountry.setLatitude(country.getLatitude());
            }
            if (country.getLongitude() != null) {
                newcountry.setLongitude(country.getLongitude());
            }
            countryJpaRepository.save(newcountry);
            return newcountry;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCountry(int countryId) {
        try {
            countryJpaRepository.deleteById(countryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

}