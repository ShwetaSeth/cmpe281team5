package DAO;

import java.sql.SQLException;

import Entity.Players;

public interface PlayersDAO {
	
	public int addPlayer(Players player) throws SQLException;
	public void updateScore(Players player) throws SQLException;
	public int[] getScores(Players player) throws SQLException;

	
}
