package ru.olgabelozerova.hangman;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    static final int MAX_LIVES = 6;
    private static final String LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String[] WORDS = {"программист", "компьютер", "игра", "слово"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Player player = new Player(MAX_LIVES);
        StringBuilder wrongLetters = new StringBuilder(); // Список неправильно угаданных букв

        String word = WORDS[random.nextInt(WORDS.length)];
        char[] guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '_');
        char guess;


        while (player.getNumOfLives() > 0 && !isWordGuessed(guessedWord)) {
            displayGameState(guessedWord, player.getNumOfLives(), wrongLetters.toString());

            try {
                guess = inputLetter(scanner);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (word.indexOf(guess) >= 0) {
                revealLetters(word, guessedWord, guess);
            } else {
                handleWrongGuess(wrongLetters, guess, player);
            }
        }

        displayGameState(guessedWord, player.getNumOfLives(), wrongLetters.toString());
        endGame(player, word);

        scanner.close();
    }

    private static void displayGameState(char[] guessedWord, int lives, String wrongLetters) {
        System.out.println("Загаданное слово: " + String.valueOf(guessedWord));
        System.out.println("Оставшиеся жизни: " + lives);
        System.out.println("Неправильные буквы: " + wrongLetters);
        drawHangman(MAX_LIVES - lives);
    }

    private static void revealLetters(String word, char[] guessedWord, char guess) {
        // Открываем все вхождения угаданной буквы
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord[i] = guess;
            }
        }
    }

    private static void handleWrongGuess(StringBuilder wrongLetters, char guess, Player player) {
        if (wrongLetters.indexOf(String.valueOf(guess)) == -1) {
            wrongLetters.append(guess).append(" ");
            player.setNumOfLives(player.getNumOfLives() - 1);
        } else {
            System.out.println("Эта буква уже была угадана неправильно.");
        }
    }

    private static void endGame(Player player, String word) {
        if (player.getNumOfLives() == 0) {
            player.lose(word);
        } else {
            player.win(word);
        }
    }

    public static boolean isWordGuessed(char[] guessedWord) {
        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private static void drawHangman(int step) {
        switch (step) {
            case 0 -> System.out.println("""
                    _______
                    |     |
                    |
                    |
                    |
                    |
                    _|_
                    """);
            case 1 -> System.out.println("""
                    _______
                    |     |
                    |     \uD83E\uDD20
                    |
                    |
                    |
                    _|_
                    """);
            case 2 -> System.out.println("""
                    _______
                    |     |
                    |     \uD83E\uDD7A
                    |     |
                    |
                    |
                    _|_
                    """);
            case 3 -> System.out.println("""
                    _______
                    |     |
                    |     \uD83D\uDE33
                    |    /|
                    |
                    |
                    _|_
                    """);
            case 4 -> System.out.println("""
                    _______
                    |     |
                    |     \uD83D\uDE30
                    |    /|\\
                    |
                    |
                    _|_
                    """);
            case 5 -> System.out.println("""
                    _______
                    |     |
                    |     \uD83E\uDD75
                    |    /|\\
                    |    /
                    |
                    _|_
                    """);
            case 6 -> System.out.println("""
                    _______
                    |     |
                    |     ☠\uFE0F
                    |    /|\\
                    |    / \\
                    |
                    _|_
                    """);
        }
    }

    public static char inputLetter(Scanner scanner) {
        System.out.print("Введите букву: ");
        String guess = scanner.next().toLowerCase();
        if (guess.length() != 1) throw new IllegalArgumentException("Ошибка: введите только одну букву.");

        if (!LETTERS.contains(guess)) {
            throw new IllegalArgumentException("Ошибка: введите одну из допустимых букв.");
        }

        return guess.charAt(0);
    }
}
