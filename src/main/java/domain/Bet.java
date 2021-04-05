package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bet {
	@Id
	private int id;
	
	private Question question;
	
	private String username;
	private float amount;
	private String result;

	public Bet(int id, String result, float amount, Question question){
		this.id = id;
		this.question = question;
		this.amount = amount;
		this.result = result;
	}
	
	public void setUser(User user) {
		this.username = user.getUsername();
	}

	public Question getQuestion() {
		return this.question;
	}

	public String getResult() {
		return result;
	}
	
	public float getAmount() {
		return amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}
}
