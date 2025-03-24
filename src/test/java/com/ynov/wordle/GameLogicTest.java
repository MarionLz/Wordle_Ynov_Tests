package com.ynov.wordle;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Identifier si les lettres du mot proposé par le joueur sont correctes, mal placées ou absentes.")
public class GameLogicTest {

	private GameLogic gameLogic;

	@Test
	@DisplayName("Le mot proposé est correct, le mot renvoyé doit être vert.")
	public void checkGuessedWordTestSameWord() {
		
		gameLogic = new GameLogic("fruit", "fruit");
		
		String result = gameLogic.checkGuessedWord();
		
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "f" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "r" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "u" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode();
		
		assertEquals(result, expectedOutput);
	}

	@Test
	@DisplayName("Le mot proposé contient des lettes absentes qui doivent être passées en gris.")
	public void checkGuessedWordTestAbsentLetters() {
		
		gameLogic = new GameLogic("frais", "fruit");

		String result = gameLogic.checkGuessedWord();
		
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "f" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "r" + LetterState.RESET.getAnsiCode() +
                				LetterState.GRAY.getAnsiCode() + "a" + LetterState.RESET.getAnsiCode() +
				                LetterState.GREEN.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.GRAY.getAnsiCode() + "s" + LetterState.RESET.getAnsiCode();
		
		assertEquals(result, expectedOutput);
	}
	
	@Test
	@DisplayName("Le mot proposé contient des lettes mal placées qui doivent être passées en jaune.")
	public void checkGuessedWordTestLettersWithBadLocation() {
		
		gameLogic = new GameLogic("chien", "niche");

		String result = gameLogic.checkGuessedWord();
		
		String expectedOutput = LetterState.YELLOW.getAnsiCode() + "c" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "h" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "i" + LetterState.RESET.getAnsiCode() +
				                LetterState.YELLOW.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode() +
				                LetterState.YELLOW.getAnsiCode() + "n" + LetterState.RESET.getAnsiCode();
		
		assertEquals(result, expectedOutput);
	}
	
	@Test
	@DisplayName("Le mot proposé contient deux 'e' mais le mot à deviner n'en contient qu'un.")
	public void checkGuessedWordTest() {
		
		gameLogic = new GameLogic("dette", "dates");

		String result = gameLogic.checkGuessedWord();
		
		String expectedOutput = LetterState.GREEN.getAnsiCode() + "d" + LetterState.RESET.getAnsiCode() +
                				LetterState.YELLOW.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode() +
                				LetterState.GREEN.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode() +
				                LetterState.GRAY.getAnsiCode() + "t" + LetterState.RESET.getAnsiCode() +
				                LetterState.GRAY.getAnsiCode() + "e" + LetterState.RESET.getAnsiCode();
		
		assertEquals(result, expectedOutput);
	}
}
