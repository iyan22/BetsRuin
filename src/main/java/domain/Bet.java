package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Bet {
	@Id
	private int id;
	
	@XmlIDREF
	private Prediction pred;
	
	private String user;
	private float amount;

	public Bet(int id, float amount, User user, Prediction pred){
		this.id=id;
		this.amount=amount;
		this.user=user.getUsername();
		this.pred=pred;
	}
	
	public void setUser(User user) {
		this.user = user.getUsername();
	}

	public Prediction getPrediction() {
		return this.pred;
	}
	public float getAmount() {
		return amount;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
