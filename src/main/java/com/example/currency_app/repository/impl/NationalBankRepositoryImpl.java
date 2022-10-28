package com.example.currency_app.repository.impl;

import com.example.currency_app.model.CurrencyInfo;
import com.example.currency_app.model.CurrencyValue;
import com.example.currency_app.repository.NationalBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Repository
public class NationalBankRepositoryImpl implements NationalBankRepository {

    private  final String CURRENCY_VALUE_URI= "/rates/dynamics/{id}?startDate={startDate}&endDate={endDate}";

    private  final String CURRENCYS_INFO_URI = "/currencies";

    private  final String CURRENCY_INFO_URI = "/currencies/{id}";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<CurrencyInfo> getAll() {
        CurrencyInfo[] currencyInfos = restTemplate.getForObject(CURRENCYS_INFO_URI, CurrencyInfo[].class);

        if(currencyInfos==null)
            return new ArrayList<>();

        return Arrays.asList(currencyInfos);
    }

    @Override
    public List<CurrencyValue> findByIdAndDateRange(int id, LocalDate startDate, LocalDate endDate) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("startDate", String.valueOf(startDate));
        params.put("endDate", String.valueOf(endDate));
        CurrencyValue[] currencyValues = restTemplate.getForObject(CURRENCY_VALUE_URI, CurrencyValue[].class, params);

        if(currencyValues==null)
            return new ArrayList<>();

        return Arrays.asList(currencyValues);
    }

    @Override
    public Optional<CurrencyInfo> findById(int id) {

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        CurrencyInfo currencyValues = restTemplate.getForObject(CURRENCY_INFO_URI, CurrencyInfo.class, params);

        return Optional.ofNullable(currencyValues);
    }
}
