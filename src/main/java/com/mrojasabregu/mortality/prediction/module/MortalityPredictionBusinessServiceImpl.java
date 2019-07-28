package com.mrojasabregu.mortality.prediction.module;

import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MortalityPredictionBusinessServiceImpl implements MortalityPredictionBusinessService<MortalityPredictionResult, Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public MortalityPredictionResult getModuleData() {
        List<Person> all = personRepository.findAll();
        float avg = 5.0f;

        return MortalityPredictionResult.builder().avg(avg).persons(all).build();
    }

    @Override
    public MortalityPredictionResult createModuleData(Person person) {


        Person save = personRepository.save(person);

        return new MortalityPredictionResult();
    }
}
