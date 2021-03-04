package domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bet {

	@OneToMany
	private Question question;
	@ManyToOne
	private User user;
	private float amount;
	private boolean first;
	private boolean tie;
	private boolean second;

	public Bet(String win, float amount, Question question){
		this.question=question;
		this.amount=amount;
		if(win.equalsIgnoreCase("first")) {
			tie=false;
			second=false;
			first=true;
		}
		else if(win.equalsIgnoreCase("tie")) {
			first=false;
			second=false;
			tie=true;
		}
		else {
			first=false;
			tie=false;
			second=true;
		}
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return this.question;
	}

	public boolean getFirst() {
		return first;
	}

	public boolean getTie() {
		return tie;
	}

	public boolean getSecond() {
		return second;
	}

	public float getAmount() {
		return amount;
	}
}
