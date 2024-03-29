package com.mrojasabregu.mortality.prediction.controller;

import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.module.MortalityPredictionBusinessService;
import com.mrojasabregu.mortality.prediction.module.MortalityPredictionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Mortality Prediction", description = "This api provide the prediction of mortality date")
@RequestMapping(value = "/api", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class MortalityPredictionController {


    @Autowired
    private MortalityPredictionBusinessService mortalityPredictionBusinessService;

    @ApiOperation(value = "View a list of available people", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/people")
    public ResponseEntity<MortalityPredictionResult> getAllPerson(
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "firstName", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
                    Pageable pageable) {

        final MortalityPredictionResult moduleData = mortalityPredictionBusinessService.getModuleData(pageable);

        return new ResponseEntity<>(moduleData, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Add a person")
    @PostMapping("/person")
    public ResponseEntity<MortalityPredictionResult> createPerson(
            @ApiParam(value = "Person object store in database table", required = true)
            @RequestBody Person person) {

        mortalityPredictionBusinessService.createModuleData(person);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
