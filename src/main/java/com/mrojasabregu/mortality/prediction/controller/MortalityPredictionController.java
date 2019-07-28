package com.mrojasabregu.mortality.prediction.controller;

import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.module.MortalityPredictionBusinessService;
import com.mrojasabregu.mortality.prediction.module.MortalityPredictionResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Mortality Prediction", description = "This api provide the prediction of mortality date")
@RequestMapping(value = "/api/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class MortalityPredictionController {


    @Autowired
    private MortalityPredictionBusinessService mortalityPredictionBusinessService;

    @ApiOperation(value = "View a list of available people", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/persons")
    public ResponseEntity<MortalityPredictionResult> getAllPerson() {

        MortalityPredictionResult moduleData = mortalityPredictionBusinessService.getModuleData();

        return new ResponseEntity<MortalityPredictionResult>(moduleData, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Add an person")
    @PostMapping("/person")
    public ResponseEntity<MortalityPredictionResult> createPerson(
            @ApiParam(value = "Person object store in database table", required = true)
            @RequestBody Person person) {
        MortalityPredictionResult build = MortalityPredictionResult.builder().build();

        mortalityPredictionBusinessService.createModuleData(person);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
