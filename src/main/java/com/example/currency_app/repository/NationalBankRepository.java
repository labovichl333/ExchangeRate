package com.example.currency_app.repository;

import com.example.currency_app.model.CurrencyInfo;
import com.example.currency_app.model.CurrencyValue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NationalBankRepository {

    List<CurrencyInfo> getAll();

    List<CurrencyValue> findByIdAndDateRange(int id, LocalDate startDate, LocalDate endDate);

    Optional<CurrencyInfo> findById(int id);

}
