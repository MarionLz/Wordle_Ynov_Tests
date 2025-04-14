package com.ynov.wordle;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynov.wordle.dictionaryLoader.Dictionary;
import com.ynov.wordle.dictionaryLoader.JsonDictionaryLoader;
import com.ynov.wordle.randomGenerator.IRandomGenerator;

public class JsonDictionaryLoaderTest {
	
	@Mock
    private ObjectMapper objectMapper;

    @Mock
    private IRandomGenerator random;

    private JsonDictionaryLoader dictionaryLoader;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dictionaryLoader = new JsonDictionaryLoader(random, objectMapper);
    }
    
    @Test
    void testLoadRandomWord_Success() throws IOException {

    	List<String> words = Arrays.asList("pomme", "banane", "cerise");
        Dictionary mockDictionary = new Dictionary(words);

        when(objectMapper.readValue(any(File.class), eq(Dictionary.class))).thenReturn(mockDictionary);
        when(random.nextInt(words.size())).thenReturn(1);

        String result = dictionaryLoader.loadRandomWord();

        assertEquals("banane", result);
        verify(objectMapper).readValue(any(File.class), eq(Dictionary.class));
    }
    
    @Test
    void testLoadRandomWord_IOException() throws IOException {
    	
        when(objectMapper.readValue(any(File.class), eq(Dictionary.class)))
            .thenThrow(new IOException("Fichier non trouvé"));

        String result = dictionaryLoader.loadRandomWord();

        assertEquals("", result);
    }
    
    
}
