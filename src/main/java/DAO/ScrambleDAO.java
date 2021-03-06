package DAO;

import Entity.Scramble;

public interface ScrambleDAO {
	
	public int enterWord(Scramble scramble);

	public int getCurrentScore(Scramble scramble);

	public void setGame(String username);

	public int getResult(Scramble scramble);
}
