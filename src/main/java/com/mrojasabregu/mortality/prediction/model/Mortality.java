package com.mrojasabregu.mortality.prediction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mortality {
    private String cause;
    private float probability;
}
