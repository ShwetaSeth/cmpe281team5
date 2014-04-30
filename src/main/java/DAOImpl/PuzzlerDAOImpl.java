package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import DAO.PuzzlerDAO;
import Entity.Puzzler;

public class PuzzlerDAOImpl implements PuzzlerDAO {
	
	public DataSource dataSource;
	PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rslt = null;
    Connection conn;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public void setGameScore(Puzzler puzzler) {
		String username = puzzler.getUsername();
		username = "abc";
		Connection connection = null;
		
		//int score = puzzler.getScore();
          int score = puzzler.getMoves();
          
		try{
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			
			
			String query = "INSERT INTO puzzler ( username,Score, Time, Moves ) VALUES (?,?,?,?)";// is this right?
			pstmt= connection.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setInt(2, 3);
			pstmt.setInt(3, 2);
			pstmt.setInt(4, puzzler.getMoves());
			pstmt.executeUpdate();
			
		
		}
		catch(Exception e){
			e.printStackTrace();
		
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getGameScore(Puzzler puzzler) {
		String username = puzzler.getUsername();
	
		username = "abc";
		Connection connection;
		
		//int score = puzzler.getScore();
          int score = puzzler.getMoves();
          
		try{
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			
			String query = "Select Score from puzzler where username = '" + username +"'";
			System.out.println(query);
			rslt = stmt.executeQuery(query);
			
			//System.out.println("1."+ rslt.getInt(0));
			while (rslt.next()) {//not sure if i need this
			System.out.println("Score is"+ rslt.getInt("Score"));
	
			connection.close();
			if(rslt.getInt("Score") > 0){
			
			  return rslt.getInt("Score");
				
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
		
		}
		
		return score;
		
	}
	
}
