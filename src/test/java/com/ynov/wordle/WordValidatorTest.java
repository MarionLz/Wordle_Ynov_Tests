package com.ynov.wordle;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Vérifier la validité du mot proposé par le joueur")
public class WordValidatorTest {

	private WordValidator wordValidator;
	
	@BeforeEach
	public void set() {
		wordValidator = new WordValidator();
	}
	
	@Test
	@DisplayName("Le mot 'tasse' est valide.")
	public void validateWordTestWithValidWord() {
		
		String word = new String("tasse");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, true);
	}
	
	@Test
	@DisplayName("Le mot 'chat' n'est pas valide car il contient moins de 5 lettres.")
	public void validateWordTestWithWordShorterThanFiveChar() {
		
		String word = new String("chat");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("Le mot 'croissant' n'est pas valide car il contient plus de 5 lettres.")
	public void validateWordTestWithWordLongerThanFiveChars() {
		
		String word = new String("croissant");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("Le mot '15car' n'est pas valide car il contient des chiffres.")
	public void validateWordTestWithDigits() {
		
		String word = new String("15car");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("Le mot 'bon jour' n'est pas valide car il contient un espace.")
	public void validateWordTestWithBlankSpaces() {
		
		String word = new String("bon jour");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
	
	@Test
	@DisplayName("Le mot 'bon@jour' n'est pas valide car il contient un caractère spécial.")
	public void validateWordTestWithSpecialChars() {
		
		String word = new String("bon@jour");
		
		boolean result = wordValidator.validateWord(word);
		
		assertEquals(result, false);
	}
}
