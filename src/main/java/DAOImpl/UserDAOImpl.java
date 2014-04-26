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

	public DataSource dataSource;

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
	public String register(User user) {
		int i=0;
		String result = "";
		String user_name = user.getUsername();
		String username = user_name.toLowerCase();
		Connection connection;
		
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
				query = "INSERT INTO users (username,password,fname,lname) values(?,?,?,?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getFname());
				pstmt.setString(4, user.getLname());
				pstmt.executeUpdate();
				connection.close();
				result= "Success:User Created with username '" + username + "'";
			}
			else{
				result="Error:::Username '" + username + "' already taken.";
			}
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return result;
	}

	@Override
	public String logIn(User user) {
		Connection connection;
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
							
						}
						else{
							result = "Error:::Incorrect password";
						}					
				}
				else{
					result = "Error:::User does not exist";
				}
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    	return result;
	}

	@Override
	public User getUser(String username) {
		User user = new User();
		Connection connection;
		try {
			connection = dataSource.getConnection();		
			String query  = "SELCT * FROM users WHERE username = '" + username + "'" ;
			pstmt = connection.prepareStatement(query);
			ResultSet rslt = pstmt.executeQuery();
			while(rslt.next()){
				user.setUsername(username);
				user.setPassword(rslt.getString("password"));
				user.setFname(rslt.getString("fname"));
				user.setLname(rslt.getString("lname"));
				user.setGame1_highscore(rslt.getInt("game1_highscore"));
				user.setGame2_highscore(rslt.getInt("game2_highscore"));
				user.setGame3_highscore(rslt.getInt("game3_highscore"));
				user.setGame4_highscore(rslt.getInt("game4_highscore"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
