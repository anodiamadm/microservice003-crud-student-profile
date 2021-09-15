package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.db.repository.address.CountryRepository;
import com.anodiam.CRUDStudentProfile.db.repository.address.StateRepository;
import com.anodiam.CRUDStudentProfile.db.repository.address.SuburbRepository;
import com.anodiam.CRUDStudentProfile.db.repository.address.TownRepository;
import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.model.address.State;
import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import com.anodiam.CRUDStudentProfile.model.address.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("api/address")
@CrossOrigin
public class ReadAddressController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private SuburbRepository suburbRepository;

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
    @GetMapping("states/{country_id}")
    @ResponseBody
    public List<State> getSatesOfCountry(@PathVariable("country_id")BigInteger country_id) throws Exception {
        try {
            Country country = countryRepository.getById(country_id);
            if(country!=null){
                List<State> stateList = stateRepository.findByCountry(country);
                return stateList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//  @GetMapping("towns") :: List all towns of given state - to populate dropdowns in the frontend
    @GetMapping("towns/{state_id}")
    @ResponseBody
    public List<Town> getTownsOfState(@PathVariable("state_id")BigInteger state_id) throws Exception {
        try {
            State state = stateRepository.getById(state_id);
            if(state!=null){
                List<Town> townList = townRepository.findByState(state);
                return townList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//  @GetMapping("suburbs") :: List all suburbs of given town - to populate dropdowns in the frontend
    @GetMapping("suburbs/{town_id}")
    @ResponseBody
    public List<Suburb> getSuburbsOfTown(@PathVariable("town_id")BigInteger town_id) throws Exception {
        try {
            Town town = townRepository.getById(town_id);
            if(town!=null){
                List<Suburb> suburbList = suburbRepository.findByTown(town);
                return suburbList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
