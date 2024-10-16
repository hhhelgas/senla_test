package ru.olgabelozerova.passwordGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        List<Character> password = new ArrayList<>();

        // Гарантируем наличие хотя бы одного символа из каждой категории
        password.add(UPPER_CASE_LETTERS.charAt(random.nextInt(UPPER_CASE_LETTERS.length())));
        password.add(LOWER_CASE_LETTERS.charAt(random.nextInt(LOWER_CASE_LETTERS.length())));
        password.add(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        password.add(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Заполняем оставшиеся символы случайным образом из всех категорий
        for (int i = 4; i < length; i++) {
            password.add(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Перемешиваем символы в пароле, чтобы они были в случайном порядке
        Collections.shuffle(password);

        // Преобразуем список символов в строку
        StringBuilder passwordBuilder = new StringBuilder();
        for (Character ch : password) {
            passwordBuilder.append(ch);
        }

        return passwordBuilder.toString();

    }
}
