package com.ynov.wordle.randomGenerator;

import java.util.Random;

public class JavaRandomGenerator implements IRandomGenerator {
	
	    private final Random random = new Random();

	    @Override
	    public int nextInt(int bound) {
	        return random.nextInt(bound);
	    }
}
