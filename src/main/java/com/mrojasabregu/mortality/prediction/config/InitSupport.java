package com.mrojasabregu.mortality.prediction.config;

import com.mrojasabregu.mortality.prediction.service.PredictionClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


@Configuration
@EnableConfigurationProperties
public class InitSupport {

    @Bean
    public PredictionClient predictionClient(@Value("${mortality.prediction.service.url}") String predictionURL) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(predictionURL + "/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(PredictionClient.class);

    }
}
