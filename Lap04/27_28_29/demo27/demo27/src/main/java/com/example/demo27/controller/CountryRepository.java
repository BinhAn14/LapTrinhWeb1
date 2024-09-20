package com.example.demo27.controller;

import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public class CountryRepository {
    
    public List<Country> getCountries() {
        return Arrays.asList(
            new Country("China", "CN"),
            new Country("Vietnam", "VN"),
            new Country("USA", "US"),
            new Country("Japan", "JP")
        );
    }
}
