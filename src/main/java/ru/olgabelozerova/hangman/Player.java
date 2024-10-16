package ru.olgabelozerova.hangman;

import static ru.olgabelozerova.hangman.HangmanGame.MAX_LIVES;

public class Player {
    private int numOfLives = MAX_LIVES;


    public Player() {
    }

    public Player(int numOfLives) {
        if (numOfLives >= 1) {
            this.numOfLives = numOfLives;
        } else throw new IllegalArgumentException("Ошибка: количество жизней игрока не может быть меньше 1");
    }

    public int getNumOfLives() {
        return numOfLives;
    }

    public void setNumOfLives(int numOfLives) {
        this.numOfLives = numOfLives;
    }

    public void win(String word) {
        System.out.println("Вы победили! Загаданное слово: " + word);
    }

    public void lose(String word) {
        System.out.println("Увы, вы проиграли... Попробуйте еще раз!\n Загаданное слово: " + word);

    }

}
