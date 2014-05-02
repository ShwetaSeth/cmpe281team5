package Entity;

import java.io.Serializable;

public class User implements Serializable {
	String username;
	String password;
	String fname;
	String lname;
	String bgcolor;
	String topscoreChecked ;
	String favgame;
	
	public String isTopscoreChecked() {
		return topscoreChecked;
	}
	public void setTopscoreChecked(String topscoreChecked) {
		this.topscoreChecked = topscoreChecked;
	}
	public String getFavgame() {
		return favgame;
	}
	public void setFavgame(String favgame) {
		this.favgame = favgame;
	}
	public String getTopscoreChecked() {
		return topscoreChecked;
	}
	int game1_highscore;
	int game2_highscore;
	int game3_highscore;
	int game4_highscore;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	
	public int getGame1_highscore() {
		return game1_highscore;
	}
	public void setGame1_highscore(int game1_highscore) {
		this.game1_highscore = game1_highscore;
	}
	
	public int getGame2_highscore() {
		return game2_highscore;
	}
	public void setGame2_highscore(int game2_highscore) {
		this.game2_highscore = game2_highscore;
	}
	
	public int getGame3_highscore() {
		return game3_highscore;
	}
	public void setGame3_highscore(int game3_highscore) {
		this.game3_highscore = game3_highscore;
	}
	
	public int getGame4_highscore() {
		return game4_highscore;
	}
	public void setGame4_highscore(int game4_highscore) {
		this.game4_highscore = game4_highscore;
	}
	
}
