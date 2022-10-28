package com.example.currency_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrencyInfo {

    @JsonProperty("Cur_ID")
    private int id;

    @JsonProperty("Cur_ParentID")
    private int parrentId;

    @JsonProperty("Cur_Name")
    private String name;

    @JsonProperty("Cur_Scale")
    private int scale;

    @JsonProperty("Cur_DateStart")
    private LocalDate startDate;

    @JsonProperty("Cur_DateEnd")
    private LocalDate endDate;

}