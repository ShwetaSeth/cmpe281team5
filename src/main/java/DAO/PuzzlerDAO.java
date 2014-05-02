package DAO;

import Entity.Puzzler;


public interface PuzzlerDAO {
	
	public int getGameScore(Puzzler puzzler);
	public void setGameScore(Puzzler puzzler);
	public void updateHighestScore(Puzzler puzzler);
	
	
}
