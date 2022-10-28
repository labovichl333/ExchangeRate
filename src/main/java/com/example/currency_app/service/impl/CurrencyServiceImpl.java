package com.example.currency_app.service.impl;

import com.example.currency_app.dto.CurrencyInfoDTO;
import com.example.currency_app.dto.DataDTO;
import com.example.currency_app.model.CurrencyInfo;
import com.example.currency_app.repository.impl.NationalBankRepositoryImpl;
import com.example.currency_app.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {


    private final NationalBankRepositoryImpl bankRepository;

    @Override
    public List<CurrencyInfoDTO> getAll() {
        return bankRepository.getAll().stream()
                .filter(currencyInfo -> currencyInfo.getId() == currencyInfo.getParrentId())
                .sorted(Comparator.comparing(CurrencyInfo::getName))
                .map(currencyInfo -> new CurrencyInfoDTO(currencyInfo.getId(), currencyInfo.getName()))
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public DataDTO findByIdAndDateRange(int id, LocalDate startDate, LocalDate endDate) {

        List<Double> values = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();

        Optional<CurrencyInfo> currencyInfoCheack = bankRepository.findById(id);
        CurrencyInfo currencyInfo;

        if (currencyInfoCheack.isPresent()) {
            currencyInfo = currencyInfoCheack.get();
        } else {
            return new DataDTO(values, dates);
        }

        if (currencyInfo.getStartDate().isAfter(startDate)) {
            startDate = currencyInfo.getStartDate();
        }

        if (endDate.isAfter(LocalDate.now())) {
            endDate = LocalDate.now();
        }


        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate = endDate;
        findCuttencyInfo(currencyInfo.getName()).forEach(currentCurrencyInfo -> {
            LocalDate start = finalStartDate.isAfter(currentCurrencyInfo.getStartDate()) ? finalStartDate : currentCurrencyInfo.getStartDate();
            LocalDate end = finalEndDate.isBefore(currentCurrencyInfo.getEndDate()) ? finalEndDate : currentCurrencyInfo.getEndDate();
            addDataFromSinglePeriod(values, dates, currentCurrencyInfo, start, end);
        });

        return new DataDTO(values, dates);
    }


    private List<CurrencyInfo> findCuttencyInfo(String name) {
        return bankRepository.getAll().stream()
                .filter(currencyInfo -> name.equals(currencyInfo.getName()))
                .collect(Collectors.toList());
    }

    private void addDataFromSinglePeriod(List<Double> values, List<LocalDate> dates,
                                         CurrencyInfo currencyInfo, LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = startDate;
        int daysToAdd = 364;
        while (currentDate.isBefore(endDate)) {
            if (currentDate.plusDays(daysToAdd).isAfter(endDate)) {
                daysToAdd = (int) (endDate.toEpochDay() - currentDate.toEpochDay());
            }
            bankRepository.findByIdAndDateRange(currencyInfo.getId(), currentDate, currentDate.plusDays(daysToAdd)).
                    forEach(currencyValue -> {
                        values.add(currencyValue.getRateValue() / currencyInfo.getScale());
                        dates.add(currencyValue.getDate());
                    });
            currentDate = currentDate.plusDays(daysToAdd);
        }

    }

}
