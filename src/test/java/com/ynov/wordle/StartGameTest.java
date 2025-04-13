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
}

