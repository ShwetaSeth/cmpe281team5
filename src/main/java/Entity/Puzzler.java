package Entity;

public class Puzzler {
//need to set my getters n setters
	private String username;
	private int Score;
	private int time;
	private int Moves;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int Moves,int time) {
		Score = Moves+time;
	}
		
	public int getTime() {
			return time;
		}
	public void setTime(int time) {
			this.time = time;
	}

	public int getMoves() {
		return Moves;
				}
	
	public void setMoves(int Moves) {
		this.Moves = Moves;		
			
}
}

