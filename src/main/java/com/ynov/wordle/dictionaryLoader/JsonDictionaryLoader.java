package com.ynov.wordle.dictionaryLoader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class JsonDictionaryLoader implements IDictionaryLoader {
	
    @Override
    public String loadRandomWord() {
    	
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Dictionary dictionary = objectMapper.readValue(new File("dictionaries/french_dictionary.json"), Dictionary.class);
            Random random = new Random();
            return dictionary.words.get(random.nextInt(dictionary.words.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

