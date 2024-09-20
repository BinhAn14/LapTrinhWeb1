package com.example.demo27.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelRequest {
    private String nationality;
    private List<String> visitedCountries;
    private TravelType travelType;
}

