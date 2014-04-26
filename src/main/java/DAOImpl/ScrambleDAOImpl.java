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

	
    	
	public void setGame()
	{
		Connection connection;
		try{
			connection = dataSource.getConnection();
			String query ="UPDATE scramble SET currScore=0";
			pstmt = connection.prepareStatement(query);
			//System.out.println("query is"+ query);
			pstmt.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public int enterWord(Scramble scramble) {
		String lastWord = scramble.getLastWord().toLowerCase();
		String username = scramble.getUsername();
		username = "abc";
	
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
	
		username = "abc";
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

	

}
