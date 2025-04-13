package com.ynov.wordle;

import com.ynov.wordle.inputReader.IInputReader;
import com.ynov.wordle.inputReader.ScannerInputReader;
import com.ynov.wordle.statistics.IGameStatistics;
import com.ynov.wordle.statistics.GameData;
import com.ynov.wordle.dictionaryLoader.IDictionaryLoader;
import com.ynov.wordle.dictionaryLoader.JsonDictionaryLoader;


public class WordleApplication {

	public static void main(String[] args) {
		
        IInputReader inputReader = new ScannerInputReader();
        IGameStatistics data = new GameData();
        IDictionaryLoader loader = new JsonDictionaryLoader();
        
		StartGame start = new StartGame(inputReader, data, loader);
		start.loadGame();
	}
}