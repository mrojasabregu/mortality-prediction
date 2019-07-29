package com.mrojasabregu.mortality.prediction.module;

import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

@Component
public class MortalityPredictionBusinessServiceImpl implements MortalityPredictionBusinessService<MortalityPredictionResult, Person> {

    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    @Override
    public MortalityPredictionResult getModuleData(Pageable pageable) {

        final Page<Person> all = personRepository.findAll(pageable);


        final Optional<OptionalDouble> optionalAVG = Optional.ofNullable(all)
                .map(Page::getContent)
                .map(people -> {
                    return people
                            .stream()
                            .filter(Objects::nonNull)
                            .mapToInt(Person::getAge)
                            .average();
                });

        final Double avg= optionalAVG.isPresent()?optionalAVG.map(OptionalDouble::getAsDouble).orElse(-1.0D):-1.0D;

        return MortalityPredictionResult.builder().avg(avg).persons(all).build();
    }

    @Transactional
    @Override
    public MortalityPredictionResult createModuleData(Person person) {


        Person save = personRepository.save(person);

        return new MortalityPredictionResult();
    }
}
