package com.example.currency_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrencyValue {

    @JsonProperty("Cur_ID")
    private int id;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Cur_OfficialRate")
    private double rateValue;

}
