package com.ynov.wordle;

import com.ynov.wordle.dictionaryLoader.IDictionaryLoader;
import com.ynov.wordle.inputReader.IInputReader;
import com.ynov.wordle.statistics.GameData;
import com.ynov.wordle.statistics.IGameStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

public class StartGameTest {

    @Mock
    private IInputReader inputReader;

    @Mock
    private IDictionaryLoader dictionaryLoader;

    @Mock
    private IGameStatistics gameStatistics;
    
    @Mock
    private GameState gameState;

    @InjectMocks
    private StartGame startGame;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startGame = spy(new StartGame(inputReader, gameStatistics, gameState, dictionaryLoader));
    }

    @Test
    public void testReadSelection_ValidInput() {
    	
        when(inputReader.getInput()).thenReturn("2");

        int result = startGame.readSelection();
        
        assertEquals(2, result);
    }

    @Test
    public void testReadSelection_InvalidInput() throws Exception {
    	
        when(inputReader.getInput()).thenReturn("invalid");

        int result = startGame.readSelection();

        assertEquals(-1, result);
    }

    @Test
    public void testStartNewGame() {
    	
        GameData newData = new GameData();
        when(dictionaryLoader.loadRandomWord()).thenReturn("blanc");
        when(gameState.makeGuess()).thenReturn(newData);

        startGame.startNewGame();

        verify(dictionaryLoader).loadRandomWord();
        verify(gameState).makeGuess();

    }

    @Test
    public void testDisplayGameStatistics() throws Exception {
    	
        when(gameStatistics.getNbWins()).thenReturn(5);
        when(gameStatistics.getStreaks()).thenReturn(3);
        when(gameStatistics.getTotalGamesPlayed()).thenReturn(2);
        when(gameStatistics.getTotalAttempts()).thenReturn(6);

        startGame.displayGameStatistics();

        verify(gameStatistics).getNbWins();
        verify(gameStatistics).getStreaks();
    }
    
    @Test
    public void testDisplayGameStatistics_EmptyScore() throws Exception {
    	
        when(gameStatistics.getNbWins()).thenReturn(5);
        when(gameStatistics.getStreaks()).thenReturn(3);
        when(gameStatistics.getTotalGamesPlayed()).thenReturn(2);
        when(gameStatistics.getTotalAttempts()).thenReturn(6);
        when(gameStatistics.getScoreHistory()).thenReturn(Collections.emptyList());

        //redirect the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        startGame.displayGameStatistics();

        String expectedMessage = "No scores recorded yet.";
        String actualOutput = outContent.toString().trim();

        assert actualOutput.contains(expectedMessage);
    }
    
    @Test
    public void testDisplayGameStatistics_WithScores() throws Exception {

    	when(gameStatistics.getNbWins()).thenReturn(5);
        when(gameStatistics.getStreaks()).thenReturn(3);
        when(gameStatistics.getTotalGamesPlayed()).thenReturn(3);
        when(gameStatistics.getTotalAttempts()).thenReturn(9);
        when(gameStatistics.getScoreHistory()).thenReturn(Arrays.asList(10, 20, 30));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        startGame.displayGameStatistics();

        String actualOutput = outContent.toString().trim();

        assertTrue(actualOutput.contains("Score history"));
        assertTrue(actualOutput.contains("Game 1: 10 points"));
        assertTrue(actualOutput.contains("Game 2: 20 points"));
        assertTrue(actualOutput.contains("Game 3: 30 points"));
        assertTrue(actualOutput.contains("Average score"));
    }
    
    @Test
    public void testLoadGame_menuOption2And3() {

    	when(inputReader.getInput())
            .thenReturn("2")
            .thenReturn("3");
        when(gameStatistics.getTotalGamesPlayed()).thenReturn(1);
        when(gameStatistics.getTotalAttempts()).thenReturn(3);
        when(gameStatistics.getNbWins()).thenReturn(1);
        when(gameStatistics.getStreaks()).thenReturn(1);

        startGame.loadGame();

        verify(startGame, times(2)).loadMenu();
        verify(startGame).displayGameStatistics();
        verify(startGame, never()).startNewGame();
    }

    @Test
    public void testLoadGame_menuOption1And3() {
    	
        GameData data = new GameData();

        when(inputReader.getInput())
            .thenReturn("1")
            .thenReturn("3");
        when(dictionaryLoader.loadRandomWord()).thenReturn("apple");
        when(gameState.makeGuess()).thenReturn(data);

        startGame.loadGame();

        verify(startGame).startNewGame();
        verify(startGame, times(2)).loadMenu();
        verify(startGame, never()).displayGameStatistics();
    }
    
    @Test
    public void testLoadGame_WrongMenuOption() {
    	
        when(inputReader.getInput()).thenReturn("4").thenReturn("3");

        //redirect the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        startGame.loadGame();

        String expectedMessage = "Unsupported option. Please enter a number corresponding to the provided menu";
        String actualOutput = outContent.toString().trim();

        assert actualOutput.contains(expectedMessage);
    }
}

