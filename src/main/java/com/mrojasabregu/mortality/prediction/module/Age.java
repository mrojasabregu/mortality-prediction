package com.mrojasabregu.mortality.prediction.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Age {
    private int days;
    private int months;
    private int years;
}
