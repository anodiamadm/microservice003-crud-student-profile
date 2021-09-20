package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.serviceRepository.address.State.StateService;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryService;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Suburb.SuburbService;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Town.TownService;
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
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @Autowired
    private TownService townService;

    @Autowired
    private SuburbService suburbService;

//  @GetMapping("countries") :: List all countries - to populate dropdowns in the frontend
    @GetMapping("countries")
    @ResponseBody
    public List<Country> getAllCountries() throws Exception {
        try {
            return countryService.findAll();
        } catch (Exception except) {
            except.printStackTrace();
            return null;
        }
    }

//  @GetMapping("states") :: List all states of given country - to populate dropdowns in the frontend
    @GetMapping("states/{country_id}")
    @ResponseBody
    public List<State> getSatesOfCountry(@PathVariable("country_id")BigInteger country_id) throws Exception {
        try {
            return stateService.findByCountryId(country_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//  @GetMapping("towns") :: List all towns of given state - to populate dropdowns in the frontend
    @GetMapping("towns/{state_id}")
    @ResponseBody
    public List<Town> getTownsOfState(@PathVariable("state_id")BigInteger state_id) throws Exception {
        try {
            return townService.findByStateId(state_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//  @GetMapping("suburbs") :: List all suburbs of given town - to populate dropdowns in the frontend
    @GetMapping("suburbs/{town_id}")
    @ResponseBody
    public List<Suburb> getSuburbsOfTown(@PathVariable("town_id")BigInteger town_id) throws Exception {
        try {
            return suburbService.findByTownId(town_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
