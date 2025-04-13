package com.ynov.wordle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Verify the validity of the word guessed by the player")
public class WordValidatorTest {

	private WordValidator wordValidator;
	
	@BeforeEach
	public void set() {
		wordValidator = new WordValidator();
	}
	
	@Test
	@DisplayName("The word 'tasse' is valid.")
	public void validateWordTestWithValidWord() {
		
		String word = new String("tasse");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, true);
	}
	
	@Test
	@DisplayName("The word 'chat' is invalid because it has less than 5 letters.")
	public void validateWordTestWithWordShorterThanFiveChar() {
		
		String word = new String("chat");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("The word 'croissant' is invalid because it has more than 5 letters.")
	public void validateWordTestWithWordLongerThanFiveChars() {
		
		String word = new String("croissant");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("The word '15car' is invalid because it contains digits.")
	public void validateWordTestWithDigits() {
		
		String word = new String("15car");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("The word 'bon jour' is invalid because it contains a space.")
	public void validateWordTestWithBlankSpaces() {
		
		String word = new String("bon jour");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("The word 'bon@jour' is invalid because it contains a special character.")
	public void validateWordTestWithSpecialChars() {
		
		String word = new String("bon@jour");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
}