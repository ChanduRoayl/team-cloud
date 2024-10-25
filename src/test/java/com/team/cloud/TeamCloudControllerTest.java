package com.team.cloud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TeamCloudControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TeamCloudController teamCloudController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCapital_Success() {
        String country = "India";
        String capital = "New Delhi";
        Country countryObj = new Country();
        countryObj.setCapital(capital);
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setData(Collections.singletonList(countryObj));

        when(restTemplate.getForEntity("https://jsonmock.hackerrank.com/api/countries?name=" + country, CountryResponse.class))
                .thenReturn(new ResponseEntity<>(countryResponse, HttpStatus.OK));

        String result = teamCloudController.getCapital(country);
        assertEquals(capital, result);
    }

    @Test
    public void testGetCapital_CountryNotFound() {
        String country = "Unknown";
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setData(Collections.emptyList());

        when(restTemplate.getForEntity("https://jsonmock.hackerrank.com/api/countries?name=" + country, CountryResponse.class))
                .thenReturn(new ResponseEntity<>(countryResponse, HttpStatus.OK));

        assertThrows(ResponseStatusException.class, () -> teamCloudController.getCapital(country));
    }
}