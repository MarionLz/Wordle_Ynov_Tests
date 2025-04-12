package com.ynov.wordle;

public enum LetterState {

	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
    GRAY("\u001B[37m"),
    RESET("\u001B[0m");

    private final String ansiCode;

    LetterState(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return ansiCode;
    }
}
