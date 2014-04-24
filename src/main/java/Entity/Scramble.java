package Entity;

public class Scramble {

	private String username;
	private int prevScore;
	private int currScore;
	private String lastWord;
	
	public String getLastWord() {
		return lastWord;
	}
	public void setLastWord(String lastWord) {
		this.lastWord = lastWord;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPrevScore() {
		return prevScore;
	}
	public void setPrevScore(int prevScore) {
		this.prevScore = prevScore;
	}
	public int getCurrScore() {
		return currScore;
	}
	public void setCurrScore(int currScore) {
		this.currScore = currScore;
	}
	
	
	
}
