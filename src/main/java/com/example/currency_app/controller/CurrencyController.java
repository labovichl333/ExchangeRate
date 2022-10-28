package com.example.currency_app.controller;

import com.example.currency_app.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public String getCurrencies(Model model) {
        model.addAttribute("currencies", currencyService.getAll());
        return "index";
    }

    @GetMapping("/currencies/{currencyId}")
    public String getCarDetail(@PathVariable(required = false) int currencyId,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "endDate", required = false) String endDate,
                               Model model) {

        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);

        model.addAttribute("data", currencyService
                .findByIdAndDateRange(currencyId, LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter)));


        return "chart";
    }
}
