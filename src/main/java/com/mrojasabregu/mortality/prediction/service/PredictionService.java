package com.mrojasabregu.mortality.prediction.service;

import com.mrojasabregu.mortality.prediction.exception.BadRequestException;
import com.mrojasabregu.mortality.prediction.model.Mortality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    @Autowired
    private PredictionClient predictionClient;

    public Mortality findMortality(long age) {
        Mortality mortality = null;
        try {
            mortality = predictionClient.findMortality(age);
        } catch (Exception ex) {
            throw new BadRequestException(ex);
        }

        return mortality;
    }
}
