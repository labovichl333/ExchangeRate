package com.example.currency_app.service;

import com.example.currency_app.dto.CurrencyInfoDTO;
import com.example.currency_app.dto.DataDTO;

import java.time.LocalDate;
import java.util.List;


public interface CurrencyService {

    List<CurrencyInfoDTO> getAll();

    DataDTO findByIdAndDateRange(int id, LocalDate startDate, LocalDate endDate);

}
