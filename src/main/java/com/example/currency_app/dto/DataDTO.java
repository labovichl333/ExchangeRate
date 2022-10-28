package com.example.currency_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DataDTO {

   private List<Double> values;

   private List<LocalDate> dates;

}
