package com.mrojasabregu.mortality.prediction.service;


import java.util.Optional;

public interface GenericEntityService<T> {

    Iterable<T> findAll();

    T save(T t);

    Optional<T> findByID(String id);

    void remove(String id);
}
