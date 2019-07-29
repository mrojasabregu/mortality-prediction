package com.mrojasabregu.mortality.prediction.module;

import com.mrojasabregu.mortality.prediction.exception.BadRequestException;
import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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

    @Transactional(readOnly = true)
    @Override
    public MortalityPredictionResult getModuleData(Pageable pageable) {

        final Page<Person> all = personRepository.findAll(pageable);

        Double avg = 1.0D;

        List<Person> people1 = Optional.ofNullable(all.getContent()).orElse(new ArrayList<>());

        if (!people1.isEmpty()) {
            OptionalDouble average = people1.stream()
                    .filter(Objects::nonNull)
                    .mapToInt(Person::getAge)
                    .average();
            avg = average.isPresent() ? average.getAsDouble() : -1.0D;
        }


        return MortalityPredictionResult.builder().avg(avg).persons(all).build();
    }

    @Transactional
    @Override
    public MortalityPredictionResult createModuleData(Person person) {
        preconditionValidator(person);
        Date deathDate = mortalityPrediction(person);
        person.setDeathDate(deathDate);
        personRepository.save(person);
        return new MortalityPredictionResult();
    }

    private Date mortalityPrediction(Person person) {
        return null;
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
