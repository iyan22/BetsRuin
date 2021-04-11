package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Bet {
	
	@Id
	private int id;
	@XmlIDREF
	private Prediction prediction;
	private String username;
	private float amount;

	public Bet(int id, float amount, User user, Prediction prediction) {
		this.id = id;
		this.amount = amount;
		this.username = user.getUsername();
		this.prediction = prediction;
	}
	
	public void setUsername(User username) {
		this.username = username.getUsername();
	}

	public float getAmount() {
		return amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUser(User user) {
		this.username = user.getUsername();
	}
	
	public Prediction getPrediction() {
		return prediction;
	}
	
	public int getId() {
		return id;
	}
	
}
