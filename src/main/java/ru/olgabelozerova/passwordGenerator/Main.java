package ru.olgabelozerova.passwordGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int length = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите желаемую длину пароля (от 8 до 12 символов):");
                length = scanner.nextInt();
                PasswordGenerator generator = new PasswordGenerator(length);
                String password = generator.generate();
                System.out.println("Сгенерированный пароль длиной " + length + " символов: " + password);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введено не целое число. Пожалуйста, введите целое число");
                scanner.next();
            }

        }
    }
}
