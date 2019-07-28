package com.mrojasabregu.mortality.prediction.module;

public interface MortalityPredictionBusinessService<T extends MortalityPredictionResult, S> {

    T getModuleData();

    T createModuleData(S s);
}
