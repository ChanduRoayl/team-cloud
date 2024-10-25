package com.team.cloud;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TeamCloudController {

    @Autowired
    private RestTemplate restTemplate;

    @Operation(summary = "Get a greeting message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping("/capital")
    public String getCapital(@RequestParam String country) {
        String url = "https://jsonmock.hackerrank.com/api/countries?name=" + country;
        ResponseEntity<CountryResponse> response = restTemplate.getForEntity(url, CountryResponse.class);
        if (response.getBody() == null || response.getBody().getData().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }
        Country countryObj = response.getBody().getData().get(0);

        return countryObj.getCapital();
    }

    //write method get the data from database

}
