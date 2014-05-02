package Entity;

import java.io.Serializable;

public class Hints implements Serializable{
	
	int hints_id;
	String answer;
	String hint1;
	String hint2;
	String hint3;
	String difficulty;
	
	public int getHints_id() {return hints_id;}
	public void setHints_id(int hints_id) {this.hints_id = hints_id;}
	
	public String getAnswer() {return answer;}
	public void setAnswer(String answer) {this.answer = answer;}
	
	public String getHint1() {return hint1;}
	public void setHint1(String hint1) {this.hint1 = hint1;}
	
	public String getHint2() {return hint2;}
	public void setHint2(String hint2) { this.hint2 = hint2;}

	public String getHint3() {return hint3;}
	public void setHint3(String hint3) {this.hint3 = hint3;}
	
	public String getDifficulty() {return difficulty;}
	public void setDifficulty(String difficulty) {this.difficulty = difficulty;}
	
}
