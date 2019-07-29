package com.mrojasabregu.mortality.prediction.service;

import com.mrojasabregu.mortality.prediction.model.Mortality;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PredictionClient {

    @GET("mortality/{age}")
    Mortality findMortality(@Path("age") long age);
}
