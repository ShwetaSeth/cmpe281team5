package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import DAO.ScrambleDAO;
import Entity.Scramble;
import Entity.User;

public class ScrambleDAOImpl implements ScrambleDAO{
	public DataSource dataSource;
	PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rslt = null;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
    	
	public void setGame(String username)
	{
		Connection connection;
		try{
			connection = dataSource.getConnection();
			String query ="INSERT IGNORE INTO scramble(username) VALUES (?)";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			
			query ="UPDATE scramble SET currScore=0";
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
			query ="UPDATE scramblewords SET guess=0";
			pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
			
			
			connection.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public int enterWord(Scramble scramble) {
		String lastWord = scramble.getLastWord().toLowerCase();
		String username = scramble.getUsername();
	
		System.out.println(lastWord);
		Connection connection;
		int score = scramble.getCurrScore();
		try{
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			
			String query = "Select count(*) AS count from scramblewords where word = '" + lastWord +"' AND guess!=1 ";
			System.out.println(query);
			rslt = stmt.executeQuery(query);
			
			//System.out.println("1."+ rslt.getInt(0));
			while (rslt.next()) {
				System.out.println("count is"+ rslt.getInt("count"));
		
				if(rslt.getInt("count") > 0){
					query = "UPDATE scramblewords SET guess = 1 where word = '" + lastWord +"' ";
					pstmt = connection.prepareStatement(query);
					pstmt.executeUpdate();
					
					score = score +1;
					System.out.println("new score is "+ score);
	
					query = "UPDATE scramble SET currScore= "+score+" where username = '"+ username + "'";
				
					pstmt = connection.prepareStatement(query);
					//pstmt.setInt(1,score);
					System.out.println("query is"+ query);
					pstmt.executeUpdate();
				}
				connection.close();
				return score;
			}
		}
		catch(Exception e){
			e.printStackTrace();		
		}
		return score;
	}
	
	public int getCurrentScore(Scramble scramble) {
		String username = scramble.getUsername();
	
		
		Connection connection;
		int score = scramble.getCurrScore();
		try{
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			
			String query = "Select currScore from scramble where username = '" + username +"'";
			System.out.println(query);
			rslt = stmt.executeQuery(query);
			
			//System.out.println("1."+ rslt.getInt(0));
			while (rslt.next()) {
			System.out.println("currScore is"+ rslt.getInt("currScore"));
	
			connection.close();
			if(rslt.getInt("currScore") > 0){
			
			  return rslt.getInt("currScore");
				
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		
		}
		
		return score;
		
	}
	
	public int getResult(Scramble scramble)
	{
		String username = scramble.getUsername();

		Connection connection;
		int currScore = scramble.getCurrScore();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			String query = "Select prevScore from scramble where username = '"
					+ username + "'";
			rslt = stmt.executeQuery(query);

			while (rslt.next()) {
				int prevScore = rslt.getInt("prevScore");
				System.out.println("prevScore is" + rslt.getInt("prevScore"));
				
				
				query = "UPDATE scramble SET prevScore= "+currScore+" where username = '"+ username + "'";
				pstmt = connection.prepareStatement(query);
				pstmt.executeUpdate();
				
				
				query = "SELECT game1_highscore FROM users WHERE username = '"+ username + "'";
				pstmt = connection.prepareStatement(query);
				pstmt.executeUpdate();
				rslt.next();
				
				int highScore = rslt.getInt("game1_highscore");
				
				if(currScore>highScore)
				{
					query = "UPDATE users SET game1_highscore = "+currScore+" where username = '"+ username + "'";
					pstmt = connection.prepareStatement(query);
					pstmt.executeUpdate();
					
				}
				connection.close();
				return prevScore;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return -1;
	}
	
	
	
	
}
