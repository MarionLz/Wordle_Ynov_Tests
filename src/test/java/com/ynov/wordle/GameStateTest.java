package com.ynov.wordle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.ynov.wordle.inputReader.IInputReader;
import com.ynov.wordle.statistics.GameData;
import com.ynov.wordle.statistics.IGameStatistics;

@DisplayName("Track the game state: remaining attempts, victory, or defeat")
public class GameStateTest {
	
	private IGameStatistics data;
	
	@Mock
	private IInputReader mockInputReader;
	
    @Mock
    private WordValidator mockValidator;

    @Mock
    private GameLogic mockGameLogic;
    
	private GameState gameState;
	
	@BeforeEach
    public void setUp() {
		data = spy(new GameData());
        MockitoAnnotations.openMocks(this);
        gameState = spy(new GameState(mockInputReader, mockValidator, data, mockGameLogic));
    }

    @Test
    @DisplayName("Should decrement remaining attempts on valid guess")
    public void testNbRemainingAttemptsDecrementsOnValidGuess() {

    	when(mockInputReader.getInput()).thenReturn("fruit");
        when(mockValidator.validateWord("fruit")).thenReturn(true);
        when(mockGameLogic.checkGuess()).thenReturn("fruit");
        doReturn(0).when(gameState).calculateFinalScore();
        
        gameState.makeGuess();
        
        assertEquals(5, data.getNbRemainingAttempts());
    }
    
    @Test
    @DisplayName("Should end game when no attempts are left")
    public void testGameEndsWhenAttemptsRunOut() {

    	data.setNbRemainingAttempts(1);
    	when(mockInputReader.getInput()).thenReturn("fruit");
        when(mockValidator.validateWord("fruit")).thenReturn(true);
        when(mockGameLogic.checkGuess()).thenReturn("fruit");
        doReturn(false).when(data).getCorrectAttempt();
        doReturn(0).when(gameState).calculateFinalScore();

        //redirect the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        gameState.makeGuess();

        verify(data, times(1)).setNbRemainingAttempts(0);
        verify(data, times(1)).setStreaks(0);
        verify(data, times(1)).setTotalGamesPlayed(1);
        
        String expectedMessage = "Game over, no more attempts left.";
        String actualOutput = outContent.toString().trim();

        assert actualOutput.contains(expectedMessage);
    }
    
    @Test
    @DisplayName("Should end game with success message on correct guess")
    public void testGameEndsWhenCorrectAnswerIsGuessed() {
    	
    	when(mockInputReader.getInput()).thenReturn("fruit");
        when(mockValidator.validateWord("fruit")).thenReturn(true);
        when(mockGameLogic.checkGuess()).thenReturn("fruit");
        doReturn(0).when(gameState).calculateFinalScore();
        
        //redirect the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        gameState.makeGuess();
        
        verify(data, times(1)).setNbWins(1);
        verify(data, times(1)).setStreaks(1);
        verify(data, times(1)).setTotalGamesPlayed(1);
        
        String expectedMessage = "Well done ! You found the answer";
        String actualOutput = outContent.toString().trim();

        assert actualOutput.contains(expectedMessage);
    }

    @Test
    @DisplayName("Should prompt to try again on incorrect guess with remaining attempts")
    public void testTryAgainIfIncorrectGuessAndAttemptsLeft() {

    	when(mockInputReader.getInput()).thenReturn("fruit");
        when(mockValidator.validateWord("fruit")).thenReturn(true);
        when(mockGameLogic.checkGuess()).thenReturn("fruit");
        doReturn(false).when(data).getCorrectAttempt();
        doReturn(0).when(gameState).calculateFinalScore();
        
        //redirect the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        gameState.makeGuess();
        
        String expectedMessage = "Try again !";
        String actualOutput = outContent.toString().trim();

        assert actualOutput.contains(expectedMessage);
    }

    @Test
    @DisplayName("Should not decrease attempts on invalid word")
    public void testInvalidWordDoesNotDecreaseAttempts() {
    	
    	when(mockInputReader.getInput()).thenReturn("chat");
        when(mockValidator.validateWord("chat")).thenReturn(false);
        doReturn(0).when(gameState).calculateFinalScore();

        gameState.makeGuess();

        assertEquals(6, data.getNbRemainingAttempts());
    }
    
    @Test
    @DisplayName("Should end game after two invalid word entries")
    public void testGameEndsWhenTwoInvalidWordsAreEntered() {
    	
    	when(mockInputReader.getInput()).thenReturn("chat");
        when(mockValidator.validateWord("chat")).thenReturn(false);
        doReturn(0).when(gameState).calculateFinalScore();
        
        //redirect the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        gameState.makeGuess();
        
        verify(data, times(1)).setStreaks(0);
        verify(data, times(1)).setTotalGamesPlayed(1);
        
        String expectedMessage = "Game over, you entered 2 invalid words.";
        String actualOutput = outContent.toString().trim();

        assert actualOutput.contains(expectedMessage);
    }
    
    @Test
    void testCalculateFinalScore_Win_PerfectGuess() {
        data.setScore(0);
        data.setNbRemainingAttempts(5);
        data.setGuess("angle");
        data.setTarget("angle");

        int score = gameState.calculateFinalScore();
        // baseScore = 50 + 4*10 = 90
        // bonus: all 5 letters correct = 5*3 = 15
        assertEquals(115, score);
    }

    @Test
    void testCalculateFinalScore__Loss_SomeMatches() {
    	data.setCorrectAttempt(false);
        data.setGuess("apple");
        data.setTarget("angle");
        data.setNbRemainingAttempts(0);

        // baseScore = 10
        // bonus = a l e placed correctly = 3+3+3 = 9
        assertEquals(19, gameState.calculateFinalScore());
    }
    
    @Test
    void testCalculateFinalScore_AllMisplacedLetters() {
        data.setCorrectAttempt(false);
        data.setGuess("glean");
        data.setTarget("angle");
        data.setNbRemainingAttempts(0);

        // base = 10
        // bonus = all 5 letters present but misplaced = 5
        assertEquals(15, gameState.calculateFinalScore());
    }
    
}
