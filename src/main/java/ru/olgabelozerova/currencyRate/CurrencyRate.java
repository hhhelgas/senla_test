package ru.olgabelozerova.currencyRate;

import java.util.*;

public class CurrencyRate {
    // Курсы конвертации валют в USD
    private static final float USD_TO_USD = 1;
    private static final float EUR_TO_USD = 1 / 1.09F;
    private static final float RUB_TO_USD = 1 / 96.88F;
    private static final float GBP_TO_USD = 1 / 0.76F;
    private static final float NIS_TO_USD = 1 / 3.75F;

    // Курсы конвертации из USD в другие валюты
    private static final float USD_TO_EUR = 1.09F;
    private static final float USD_TO_RUB = 96.88F;
    private static final float USD_TO_GBP = 0.76F;
    private static final float USD_TO_NIS = 3.75F;

    public static float toUSD(CurrencyEnum fromCurrency, float amount) {
        return switch (fromCurrency) {
            case USD -> amount * USD_TO_USD;
            case EUR -> amount * EUR_TO_USD;
            case RUB -> amount * RUB_TO_USD;
            case GBP -> amount * GBP_TO_USD;
            case NIS -> amount * NIS_TO_USD;
            default ->
                    throw new IllegalArgumentException("Неверная валюта: " + fromCurrency + ". Выберите одну из доступных валют: " + Arrays.toString(CurrencyEnum.values()));
        };
    }

    public static float fromUSD(CurrencyEnum toCurrency, float amountInUSD) {
        return switch (toCurrency) {
            case USD -> amountInUSD * USD_TO_USD;
            case EUR -> amountInUSD * USD_TO_EUR;
            case RUB -> amountInUSD * USD_TO_RUB;
            case GBP -> amountInUSD * USD_TO_GBP;
            case NIS -> amountInUSD * USD_TO_NIS;
            default ->
                    throw new IllegalArgumentException("Неверная валюта: " + toCurrency + ". Выберите одну из доступных валют: " + Arrays.toString(CurrencyEnum.values()));
        };
    }

    public static Map<CurrencyEnum, Float> countRate(CurrencyEnum fromCurrency, float amount) {
        Map<CurrencyEnum, Float> rateMap = new HashMap<>();
        float amountInUSD = toUSD(fromCurrency, amount);
        float rate = 0;
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            rate = Math.abs(fromUSD(currency, amountInUSD));
            rateMap.put(currency, rate);
        }

        return rateMap;
    }

    public static CurrencyEnum chooseCurrency(Scanner scanner) {
        System.out.println("Введите исходную валюту " + Arrays.toString(CurrencyEnum.values()) + ": ");
        String currencyInput = scanner.nextLine().toUpperCase();

        try {
            return CurrencyEnum.valueOf(currencyInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная валюта, выберите из доступных: " + Arrays.toString(CurrencyEnum.values()));
            return chooseCurrency(scanner); // Рекурсия для повторного выбора
        }
    }

    public static float inputAmount(CurrencyEnum fromCurrency, Scanner scanner) {
        try {
            System.out.print("Введите сумму в " + fromCurrency + ": ");
            float amount = scanner.nextFloat();
            scanner.nextLine();

            if (amount < 0 || Float.compare(amount, -0.0f) == 0) {
                System.out.println("Ошибка: сумма не может быть отрицательной. Введите неотрицательное число.");
                return inputAmount(fromCurrency, scanner); // Возвращаем результат рекурсивного вызова для повторного ввода
            }

            return amount;

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введено не неотрицательное вещественное число. " +
                    "Пожалуйста, введите неотрицательное вещественное  число.");
            scanner.nextLine();
            return inputAmount(fromCurrency, scanner); // Возвращаем результат рекурсивного вызова для повторного ввода
        }
    }
}



