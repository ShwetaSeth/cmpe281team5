package Entity;

import java.io.Serializable;

public class Memory implements Serializable {
	private int Game_id;
	private String Player_id;
	private int Score;
	private int PicId;
	public enum Outcome{W,L};
	
	
	public int getGameId() {
		return Game_id;
	}
	public void setGameId(int Game_id) {
		this.Game_id = Game_id;
	}
	
	public String getPlayerId() {
		return Player_id;
	}
	public void setPlayerId(String Player_id) {
		this.Player_id = Player_id;
	}
	
	public int getScore() {
		return Score;
	}
	public void setScore(int Score) {
		this.Score = Score;
	}
	public int getPicId() {
		return PicId;
	}
	public void setPicId(int PicId) {
		this.PicId = PicId;
	}
}
