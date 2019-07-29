package com.mrojasabregu.mortality.prediction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mortality {
    private String cause;
    private Float probability;
}
