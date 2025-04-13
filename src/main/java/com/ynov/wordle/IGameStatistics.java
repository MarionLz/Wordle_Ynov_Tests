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
	    
	    int getNbWins();
	    void setNbWins(int nbWins);
	    
	    int getStreaks();
	    void setStreaks(int streaks);
	    
		int getTotalAttempts();
		void setTotalAttempts(int totalAttempts);
		
		int getTotalGamesPlayed();
		void setTotalGamesPlayed(int totalGamesPlayed);
	    
	    int getAverageAttempts();
	    void setAverageAttempts(int averageAttempts);
}
