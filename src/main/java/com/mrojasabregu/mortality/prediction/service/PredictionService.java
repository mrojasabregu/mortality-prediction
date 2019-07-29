package com.mrojasabregu.mortality.prediction.service;

import com.mrojasabregu.mortality.prediction.exception.BadRequestException;
import com.mrojasabregu.mortality.prediction.model.Mortality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class PredictionService {

    @Autowired
    private PredictionClient predictionClient;

    public Mortality findMortality(long age) {
        try {
            Response<Mortality> execute = predictionClient.findMortality(age).execute();
            return execute.body();
        } catch (Exception ex) {
            throw new BadRequestException(ex);
        }

    }
}
