package ru.olgabelozerova.passwordGenerator;

import java.util.Random;

public class PasswordGenerator {
    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
    private static final String ALL_CHARACTERS = UPPER_CASE_LETTERS + LOWER_CASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;

    private int length;

    public int getLength() {
        return length;
    }

    public PasswordGenerator(int length) {
        if (length < 8 || length > 12) {
            throw new IllegalArgumentException("Недопустимая длина пароля. Пароль должен содержать от 8 до 12 символов");
        }


        this.length = length;
    }

    public String generate() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(index));
        }
        return password.toString();
    }
}
