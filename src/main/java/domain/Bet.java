package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bet {
	@Id
	private int id;
	@OneToMany
	private Question question;
	@ManyToOne
	private String user;
	private float amount;
	private boolean first;
	private boolean tie;
	private boolean second;

	public Bet(int id,String win, float amount, Question question){
		this.id=id;
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
		this.user = user.getUsername();
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
