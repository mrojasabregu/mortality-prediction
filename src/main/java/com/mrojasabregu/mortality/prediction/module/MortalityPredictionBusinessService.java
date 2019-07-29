package com.mrojasabregu.mortality.prediction.module;

import org.springframework.data.domain.Pageable;

public interface MortalityPredictionBusinessService<T extends MortalityPredictionResult, S> {

    T getModuleData(Pageable pageable);

    T createModuleData(S s);
}
