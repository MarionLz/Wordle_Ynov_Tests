package com.ynov.wordle;

public interface IGameStatistics {

	    String getGuess();
	    void setGuess(String guess);
	    String getTarget();
	    void setTarget(String target);
	    int getNbRemainingAttempts();
	    void setNbRemainingAttempts(int nbRemainingAttempts);
	    boolean getCorrectAttempt();
	    void setCorrectAttempt(boolean correctAttempt);
}
