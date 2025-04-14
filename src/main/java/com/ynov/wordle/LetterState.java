package com.ynov.wordle;

public enum LetterState {

    GREEN("\u001B[32m", 2),
    YELLOW("\u001B[33m", 1),
    GRAY("\u001B[37m", 0),
    RESET("\u001B[0m", 0);

    private final String ansiCode;
    private final int score;

    LetterState(String ansiCode, int score) {
        this.ansiCode = ansiCode;
        this.score = score;
    }

    public String getAnsiCode() {
        return ansiCode;
    }

    public int getScore() {
        return score;
    }
}
