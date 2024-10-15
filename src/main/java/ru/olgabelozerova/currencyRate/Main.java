package ru.olgabelozerova.currencyRate;

import java.util.InputMismatchException;
import java.util.Map;

import static ru.olgabelozerova.currencyRate.CurrencyRate.*;

public class Main {
    public static void main(String[] args) {
        float amount = 0;

        CurrencyEnum fromCurrency = chooseCurrency();

        amount = inputAmount(fromCurrency);

        Map<CurrencyEnum, Float> rates = countRate(fromCurrency, amount);

        System.out.println("Конвертация из " + fromCurrency + ":");
        for (Map.Entry<CurrencyEnum, Float> entry : rates.entrySet()) {
            if(!entry.getKey().toString().equals(fromCurrency.toString())) {
                System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
            }
        }
    }
}