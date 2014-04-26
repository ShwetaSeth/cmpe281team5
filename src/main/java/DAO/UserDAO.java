package DAO;

import Entity.User;

public interface UserDAO {

	public String register(User user);
	public String logIn(User user);
	
	public User getUser(String username);
}
