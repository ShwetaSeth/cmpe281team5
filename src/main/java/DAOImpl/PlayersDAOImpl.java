package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import Entity.Players;
import DAO.PlayersDAO;

public class PlayersDAOImpl implements PlayersDAO{
	
	public DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int addPlayer(Players player) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		Connection connection = null;
		int game_id = 0;
		try{
			connection = dataSource.getConnection();
			String query= "INSERT INTO players (player, total_score) VALUES (?,?)";
			pstmt = connection.prepareStatement(query, new String[] {"game_id"});
			pstmt.setString(1, player.getPlayer());
			pstmt.setInt(2, player.getTotal_score());
			pstmt.executeUpdate();
			
			rslt = pstmt.getGeneratedKeys();
			if(rslt != null && rslt.next()){
				game_id = rslt.getInt(1);
			}
		}
		finally{
			pstmt.close();
			connection.close();
		}
		return game_id;
	}
	
	@Override
	public void updateScore(Players player) throws SQLException{
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;		
		Statement stmt = null;
		ResultSet rslt = null;
		Connection connection = null;
		try{
			connection = dataSource.getConnection();
			String query= "UPDATE players SET total_score = ? WHERE game_id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, player.getTotal_score());
			pstmt.setInt(2, player.getGame_id());
			pstmt.executeUpdate();			
			
			query = "SELECT game2_highscore from users where username = '" + player.getPlayer() + "'"; 
			stmt = connection.createStatement();
			rslt = stmt.executeQuery(query);
			if(rslt.next()){			
				int current_highscore = rslt.getInt("game2_highscore");
				if(current_highscore < player.getTotal_score()){
					query = "UPDATE users SET game2_highscore = ? WHERE username = ?";
					pstmt1 = connection.prepareStatement(query);
					pstmt1.setInt(1, player.getTotal_score());
					pstmt1.setString(2, player.getPlayer());
					pstmt1.executeUpdate();						
				}
			}
		}
		finally{
			pstmt.close();
			stmt.close();
			connection.close();
		}
	}	
	
	@Override
	public int[] getScores(Players player) throws SQLException{
		int[] scores = new int[10];
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		try{
			connection = dataSource.getConnection();
			String query = "SELECT total_score FROM players WHERE player = ? ORDER BY game_id DESC limit 10";
			pstmt = connection.prepareStatement(query);
			rslt = pstmt.executeQuery();
			for(int i = 0; i < scores.length; i++){
				rslt.next();
				scores[i] = rslt.getInt("total_score");
			}
		}
		finally{
			pstmt.close();
			rslt.close();
			connection.close();
		}
		return scores;
	}
}
