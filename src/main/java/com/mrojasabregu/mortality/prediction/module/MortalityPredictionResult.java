package com.mrojasabregu.mortality.prediction.module;

import com.mrojasabregu.mortality.prediction.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortalityPredictionResult{

    private float avg;

    private List<Person> persons;

}
