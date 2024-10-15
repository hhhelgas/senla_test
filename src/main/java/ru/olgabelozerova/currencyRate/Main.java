package ru.olgabelozerova.currencyRate;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static ru.olgabelozerova.currencyRate.CurrencyRate.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float amount = 0;

        CurrencyEnum fromCurrency = chooseCurrency(scanner);

        amount = inputAmount(fromCurrency, scanner);

        Map<CurrencyEnum, Float> rates = countRate(fromCurrency, amount);

        System.out.println("Конвертация из " + fromCurrency + ":");
        for (Map.Entry<CurrencyEnum, Float> entry : rates.entrySet()) {
            if (entry.getKey() != fromCurrency) {
                System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
            }
        }

        scanner.close();

    }
}