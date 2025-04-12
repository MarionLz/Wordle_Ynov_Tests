package com.ynov.wordle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Identifier si les lettres du mot proposé par le joueur sont correctes, mal placées ou absentes.")
public class GameLogicTest {

	private GameData data;
	private GameLogic gameLogic;
	
	@BeforeEach
	void setUp() {
		
		data = new GameData();
	}
	
	@Test
	@DisplayName("Le mot proposé est correct, le mot renvoyé doit être vert.")
	public void checkGuessedWordTestSameWord() {
		
		data.setTarget("fruit");
		data.setGuess("fruit");
		gameLogic = new GameLogic(data);
		
		String result = gameLogic.checkGuess();
		
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "f" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "r" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "u" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode();
		
		assertEquals(data.getCorrectAttempt(), true);
		assertEquals(result, expectedOutput);
	}

	@Test
	@DisplayName("Le mot proposé contient des lettes absentes qui doivent être passées en gris.")
	public void checkGuessedWordTestAbsentLetters() {
		
		data.setTarget("fruit");
		data.setGuess("frais");
		gameLogic = new GameLogic(data);

		String result = gameLogic.checkGuess();
		
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "f" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "r" + LetterState.RESET.getAnsiCode() +
                				LetterState.GRAY.getAnsiCode() + "a" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.GRAY.getAnsiCode() + "s" + LetterState.RESET.getAnsiCode();
		
		assertEquals(data.getCorrectAttempt(), false);
		assertEquals(result, expectedOutput);
	}
	
	@Test
	@DisplayName("Le mot proposé contient des lettes mal placées qui doivent être passées en jaune.")
	public void checkGuessedWordTestLettersWithBadLocation() {
		
		data.setTarget("niche");
		data.setGuess("chien");
		gameLogic = new GameLogic(data);

		String result = gameLogic.checkGuess();
		
		String expectedOutput = LetterState.YELLOW.getAnsiCode() + "c" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "h" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.YELLOW.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode() +
				                LetterState.YELLOW.getAnsiCode() + "n" + LetterState.RESET.getAnsiCode();
		
		assertEquals(data.getCorrectAttempt(), false);
		assertEquals(result, expectedOutput);
	}
	
	@Test
	@DisplayName("Le mot proposé contient deux 'e' mais le mot à deviner n'en contient qu'un.")
	public void checkGuessedWordTest() {
		
		data.setTarget("dates");
		data.setGuess("dette");
		gameLogic = new GameLogic(data);

		String result = gameLogic.checkGuess();
		
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "d" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode() +
				                LetterState.GRAY.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode() +
				                LetterState.GRAY.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode();
		
		assertEquals(data.getCorrectAttempt(), false);
		assertEquals(result, expectedOutput);
	}
}
