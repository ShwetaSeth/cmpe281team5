package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import DAO.UserDAO;
import Entity.User;

public class UserDAOImpl implements UserDAO {

	private Connection connection;
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rslt = null;
    	
	@Override
	public String register(User user) throws SQLException {
		int i=0;
		String result = "";
		String user_name = user.getUsername();
		String username = user_name.toLowerCase();		
		
		try{
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			String query = "Select username from users";
			rslt = stmt.executeQuery(query);
			while (rslt.next()) {
				if(rslt.getString("username").equals(username)){
					i++;
				}
			}
			if(i==0){
				query = "INSERT INTO users (username,password,fname,lname,bgcolor,top_score_wanted,favgame) values(?,?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getFname());
				pstmt.setString(4, user.getLname());
				pstmt.setString(5, user.getBgcolor());
				pstmt.setString(6, user.getTopscoreChecked());
				pstmt.setString(7, user.getFavgame());
				pstmt.executeUpdate();
				connection.close();
				result= "Success:User Created with username '" + username + "'";
			}
			else{
				result="Error:::Username '" + username + "' already taken.";
			}
		}catch(Exception e){
			e.printStackTrace();
		
		}
		finally{
			connection.close();
		}
		return result;
	}

	
	
	@Override
	public String logIn(User user) throws SQLException{
		String result = "";
    	String username = (user.getUsername()).toLowerCase();
    	String password = user.getPassword();
    	Boolean b = false;
		try{
			connection = dataSource.getConnection();
			String query = "Select count(*) from users";
			pstmt = connection.prepareStatement(query);
			rslt = pstmt.executeQuery();
			rslt.next();
			int count = rslt.getInt(1);
			
			if(count == 0){
				result = "Error:::User does not exist";
			}
			else{
				query = "Select username from users";
				pstmt = connection.prepareStatement(query);
				rslt = pstmt.executeQuery();
				whileloop: while(rslt.next()){
					
					if(username.equals(rslt.getString("username"))){
						b=true;
						break whileloop;
					}
					else{
						b=false;
					}
				}
				
				if(b){
						query = "Select password from users where username = ?";
						pstmt = connection.prepareStatement(query);
						pstmt.setString(1, username);
						rslt = pstmt.executeQuery();
						rslt.next();
						String pass = rslt.getString("password");
						
						if(password.equals(pass)){
							result = "Success:Logged in successfully";
						}
						else{
							result = "Error:::Incorrect password";
						}					
				}
				else{
					result = "Error:::User does not exist";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
    	return result;
	}

	@Override
	public User getUser(String username) throws SQLException{
		User user = new User();
		try {
			connection = dataSource.getConnection();		
			String query  = "SELECT * FROM users WHERE username = '" + username + "'" ;
			pstmt = connection.prepareStatement(query);
			ResultSet rslt = pstmt.executeQuery();
			while(rslt.next()){
				user.setUsername(username);
				user.setPassword(rslt.getString("password"));
				user.setFname(rslt.getString("fname"));
				user.setLname(rslt.getString("lname"));
				user.setBgcolor(rslt.getString("bgcolor"));
				user.setTopscoreChecked(rslt.getString("top_score_wanted"));
				user.setFavgame(rslt.getString("favgame"));
				user.setGame1_highscore(rslt.getInt("game1_highscore"));
				user.setGame2_highscore(rslt.getInt("game2_highscore"));
				user.setGame3_highscore(rslt.getInt("game3_highscore"));
				user.setGame4_highscore(rslt.getInt("game4_highscore"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return user;
	}
	
	
	public int getHighestScore(User user,String game)
	{
		String result = "";
    	String username = (user.getUsername()).toLowerCase();
    	int highScore = -1;

		try{
			connection = dataSource.getConnection();
			String query = "Select game1_highscore, game2_highscore,game3_highscore,game4_highscore from users where username = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, username);
			rslt = pstmt.executeQuery();
			rslt.next();
			
			if(game.contains("scramble"))
				highScore = rslt.getInt("game1_highscore");
			
			if(game.contains("WhatsYourTech"))
				highScore = rslt.getInt("game2_highscore");
			
			if(game.contains("memory"))
				highScore = rslt.getInt("game3_highscore");
			
			if(game.contains("Puzzler"))
				highScore = rslt.getInt("game4_highscore");
			
		
			return highScore;
			}catch(Exception e){
				e.printStackTrace();
			
			}
	
			return -1;
		}
}
