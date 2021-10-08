package com.anodiam.CRUDStudentProfile;

import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
class JwtAuthApplicationTests {

	@Autowired
	private CountryService countryService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testString() throws Exception {
		String expectedValue="HELLOWORLD";
		String actualValue="HELLOWORLD";
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testCountryName() throws Exception {
		List<Country> actualValue=countryService.findAll();
		List<Country> expectedValue=new ArrayList<Country>();
		BigInteger big_integer = BigInteger.valueOf(1);
		expectedValue.add(new Country(big_integer,"AU","Australia"));
		assertEquals(expectedValue.get(0).getCountry_name(), actualValue.get(0).getCountry_name());
	}
}
