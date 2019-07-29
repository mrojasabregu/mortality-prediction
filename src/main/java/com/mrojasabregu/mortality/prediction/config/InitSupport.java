package com.mrojasabregu.mortality.prediction.config;

import com.expedia.flex.monitoring.http.OkHttp3ClientFactory;
import com.mrojasabregu.mortality.prediction.service.PredictionClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableConfigurationProperties
public class InitSupport {

    private final OkHttp3ClientFactory okHttp3ClientFactory;

    @Autowired
    public InitSupport(OkHttp3ClientFactory okHttp3ClientFactory) {
        this.okHttp3ClientFactory = okHttp3ClientFactory;
    }


    @Bean
    public PredictionClient fxrsClient(@Value("${mortality.prediction.service.url}") String answerUrl, @Value("${mortality.prediction.service..timeout}") int timeout) {
        return createAdapter(answerUrl, timeout, PredictionClient.class);
    }

    private <T> T createAdapter(String url, long timeout, Class<T> clientClass) {
        final OkHttpClient okHttpClient = okHttp3ClientFactory.builder()
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();

        return createRetrofitAdapter(url, okHttpClient, clientClass);
    }

    private <T> T createRetrofitAdapter(String url, OkHttpClient okHttpClient, Class<T> clientClass) {
        return new Retrofit.Builder()
                .baseUrl(url + "/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(clientClass);
    }
}
