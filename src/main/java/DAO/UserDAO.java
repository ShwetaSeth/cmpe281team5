package DAO;

import java.sql.SQLException;

import Entity.User;

public interface UserDAO {

	public String register(User user) throws SQLException;
	public String logIn(User user) throws SQLException;
	public User getUser(String username) throws SQLException;
	
}
