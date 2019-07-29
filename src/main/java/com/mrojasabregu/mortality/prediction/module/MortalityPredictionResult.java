package com.mrojasabregu.mortality.prediction.module;

import com.mrojasabregu.mortality.prediction.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortalityPredictionResult {

    private Double avg;
    private Page<Person> people;
}
