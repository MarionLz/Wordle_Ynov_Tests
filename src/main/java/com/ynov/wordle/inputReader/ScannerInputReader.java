package com.ynov.wordle.inputReader;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerInputReader implements IInputReader {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getInput() {
    	try {
            scanner.hasNextLine();
                return scanner.nextLine();
//            } else {
//                throw new NoSuchElementException("No input provided.");
//            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: No input found. Exiting game...");
            System.exit(0);
            return null;
        }
    }
}
