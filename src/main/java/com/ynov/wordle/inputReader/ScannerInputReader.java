package com.ynov.wordle.inputReader;

import java.util.Scanner;

public class ScannerInputReader implements IInputReader {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
