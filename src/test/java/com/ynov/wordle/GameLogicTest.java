package com.ynov.wordle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Identify whether the guessed letters are correct, misplaced, or absent.")
public class GameLogicTest {

	private GameData data;
	private GameLogic gameLogic;
	
	@BeforeEach
	void setUp() {
		
		data = new GameData();
	}
	
	@Test
	@DisplayName("The guessed word is correct, all letters should be marked green.")
	public void testCheckGuess_SameWord() {
		
		data.setTarget("fruit");
		data.setGuess("fruit");
		gameLogic = new GameLogic(data);
		
		String result = gameLogic.checkGuess();
		
		// Expected result: all letters green
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "f" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "r" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "u" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode();
		
		// Check that the guess was marked as correct
		assertEquals(data.getCorrectAttempt(), true);

		// Check that the result matches the expected output
		assertEquals(result, expectedOutput);
	}

	@Test
	@DisplayName("The guessed word contains absent letters ('a' and 's') which should be marked gray.")
	public void testCheckGuess_AbsentLetters() {
		
		data.setTarget("fruit");
		data.setGuess("frais");
		gameLogic = new GameLogic(data);

		String result = gameLogic.checkGuess();
		
		// Define the expected gray representation for 'a' and 's'
		String grayA = LetterState.GRAY.getAnsiCode() + "a" + LetterState.RESET.getAnsiCode();
	    String grayS = LetterState.GRAY.getAnsiCode() + "s" + LetterState.RESET.getAnsiCode();
		
		// Assert that both letters are marked gray in the result
	    assertTrue(result.contains(grayA), "The letter 'a' should be marked gray.");
	    assertTrue(result.contains(grayS), "The letter 's' should be marked gray.");
	}
	
	@Test
	@DisplayName("The guessed word contains only misplaced letters, all should be marked yellow.")
	public void testCheckGuess_LettersWithBadLocation() {
		
		data.setTarget("niche");
		data.setGuess("chien");
		gameLogic = new GameLogic(data);

		String result = gameLogic.checkGuess();
		
		// Expected output: all letters are yellow (misplaced)
		String expectedOutput = LetterState.YELLOW.getAnsiCode() + "c" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "h" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.YELLOW.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode() +
				                LetterState.YELLOW.getAnsiCode() + "n" + LetterState.RESET.getAnsiCode();
		
		// The guess should be incorrect
		assertEquals(data.getCorrectAttempt(), false);

		// Check that the result matches the expected yellow-marked output
		assertEquals(result, expectedOutput);
	}
	
	@Test
	@DisplayName("The guessed word contains two 'e's, but the target has only one — first 'e' should be yellow, second gray.")
	public void testCheckGuess_AttemptWithDoublesLetters() {
		
		data.setTarget("dates");
		data.setGuess("dette");
		gameLogic = new GameLogic(data);

		String result = gameLogic.checkGuess();
		
		// Define the expected outputs: first 'e' yellow, second 'e' gray
	    String yellowE = LetterState.YELLOW.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode();
	    String grayE = LetterState.GRAY.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode();

		// Use a regex to find all occurrences of colored 'e' letters
	    Pattern pattern = Pattern.compile("\u001B\\[\\d{2}m" + "e" + "\u001B\\[0m");
	    Matcher matcher = pattern.matcher(result);

	    List<String> coloredEs = new ArrayList<>();
	    while (matcher.find()) {
	        coloredEs.add(matcher.group());
	    }

		// Ensure we found exactly two 'e' letters
	    assertEquals(2, coloredEs.size(), "The guessed word should contain two 'e' letters.");

		// Check the expected colors
	    assertEquals(yellowE, coloredEs.get(0), "The first 'e' should be yellow (misplaced).");
	    assertEquals(grayE, coloredEs.get(1), "The second 'e' should be gray (absent).");
	}
}
