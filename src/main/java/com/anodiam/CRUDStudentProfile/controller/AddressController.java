package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.db.repository.address.CountryRepository;
import com.anodiam.CRUDStudentProfile.model.address.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private CountryRepository countryRepository;

//  REVIEW COMMENT::  TRY - CATCH needs to narrow down to specific exception instead of just EXCEPTION
//  @GetMapping("countries") :: List all countries - to populate dropdowns in the frontend
    @GetMapping("countries")
    @ResponseBody
    public List<Country> getAllCountries() throws Exception {
        try {
            List<Country> countryList = countryRepository.findAll();
            return countryList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//  @GetMapping("states") :: List all states of given country - to populate dropdowns in the frontend
//    @GetMapping("states/{country_id}")
//    @ResponseBody
//    public List<Country> getAllCountries() throws Exception {
//        try {
//            List<Country> countryList = countryRepository.findAll();
//            return countryList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//  @GetMapping("towns") :: List all towns of given state - to populate dropdowns in the frontend
//  @GetMapping("suburbs") :: List all suburbs of given town - to populate dropdowns in the frontend

}
