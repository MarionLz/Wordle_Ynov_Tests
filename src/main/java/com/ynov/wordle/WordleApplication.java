package com.ynov.wordle;

import com.ynov.wordle.inputReader.IInputReader;
import com.ynov.wordle.inputReader.ScannerInputReader;
import com.ynov.wordle.statistics.IGameStatistics;
import com.ynov.wordle.statistics.GameData;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynov.wordle.dictionaryLoader.IDictionaryLoader;
import com.ynov.wordle.dictionaryLoader.JsonDictionaryLoader;
import com.ynov.wordle.randomGenerator.IRandomGenerator;
import com.ynov.wordle.randomGenerator.JavaRandomGenerator;


public class WordleApplication {

	public static void main(String[] args) {
		
        IInputReader inputReader = new ScannerInputReader();
        IGameStatistics data = new GameData();
        GameState gameState = new GameState();
        IRandomGenerator random = new JavaRandomGenerator();
        ObjectMapper objectMapper = new ObjectMapper();
        IDictionaryLoader loader = new JsonDictionaryLoader(random, objectMapper);
        
		StartGame start = new StartGame(inputReader, data, gameState, loader);
		start.loadGame();
	}
}