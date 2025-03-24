package com.ynov.wordle;

public enum LetterState {

	GREEN("\u001B[32m"),  // Vert pour une lettre bien placée
    YELLOW("\u001B[33m"), // Jaune pour une lettre mal placée
    GRAY("\u001B[37m"),   // Gris pour une lettre absente
    RESET("\u001B[0m");   // Réinitialisation de la couleur

    private final String ansiCode;

    LetterState(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return ansiCode;
    }
}
