package com.mrojasabregu.mortality.prediction.module;

import com.mrojasabregu.mortality.prediction.exception.BadRequestException;
import com.mrojasabregu.mortality.prediction.model.Mortality;
import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.repository.PersonRepository;
import com.mrojasabregu.mortality.prediction.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

import static com.mrojasabregu.mortality.prediction.module.AgeCalculator.calculateAge;

@Component
public class MortalityPredictionBusinessServiceImpl implements MortalityPredictionBusinessService<MortalityPredictionResult, Person> {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PredictionService predictionService;

    @Transactional(readOnly = true)
    @Override
    public MortalityPredictionResult getModuleData(Pageable pageable) {

        final Page<Person> all = personRepository.findAll(pageable);

        Double avg = -1.0D;

        List<Person> people1 = Optional.ofNullable(all.getContent()).orElse(new ArrayList<>());

        if (!people1.isEmpty()) {
            OptionalDouble average = people1.stream()
                    .filter(person -> person.getAge() != null)
                    .mapToInt(Person::getAge)
                    .average();
            avg = average.isPresent() ? average.getAsDouble() : -1.0D;
        }


        return MortalityPredictionResult.builder().avg(avg).people(all).build();
    }

    @Transactional
    @Override
    public MortalityPredictionResult createModuleData(Person person) {
        preconditionValidator(person);
        person.setMortality(mortalityPrediction(person));
        person.setDeathDate(Calendar.getInstance().getTime());
        personRepository.save(person);
        return new MortalityPredictionResult();
    }

    private Mortality mortalityPrediction(Person person) {
        return predictionService.findMortality(person.getAge());
    }

    private void preconditionValidator(Person person) {
        if (person.getAge() == null
                || person.getBirthDate() == null
                || person.getFirstName() == null
                || person.getLastName() == null) {
            throw new BadRequestException();
        }
        Age age = calculateAge(person.getBirthDate());
        if (age.getYears() != person.getAge()) {
            throw new BadRequestException();
        }
    }
}
