package com.ynov.wordle.dictionaryLoader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynov.wordle.randomGenerator.IRandomGenerator;

import java.io.File;
import java.io.IOException;

public class JsonDictionaryLoader implements IDictionaryLoader {
	
    private final IRandomGenerator  random;
    private final ObjectMapper objectMapper;
    
    public JsonDictionaryLoader(IRandomGenerator random, ObjectMapper objectMapper) {
        this.random = random;
        this.objectMapper = objectMapper;
    }
	
    @Override
    public String loadRandomWord() {
    	
        try {
            Dictionary dictionary = objectMapper.readValue(new File("dictionaries/french_dictionary.json"), Dictionary.class);
            return dictionary.words.get(random.nextInt(dictionary.words.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

