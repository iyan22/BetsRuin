package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Prediction {

	@Id
	private int id;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> bets=new Vector<Bet>();
	
	@XmlIDREF
	private Question question;
	
	private String answer;
	private float multiplier;
	private float minim;
	private boolean win = false;
	
	public Prediction(int id, Question q, String ans, float mult, float minim) {
		this.id=id;
		this.question=q;
		this.answer=ans;
		this.multiplier=mult;
		this.minim=minim;
	}
	
	public void setMinim(float minim) {
		this.minim=minim;
	}
	
	public float getMinim() {
		return minim;
	}
	
	public void setWinner() {
		this.win = true;
	}
	
	public boolean isWinner() {
		return win;
	}
	
	public void setQuestion(Question q) {
		this.question=q;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setAnswer(String answer) {
		this.answer=answer;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setMultiplier(float mult) {
		this.multiplier=mult;
	}
	
	public float getMultiplier() {
		return multiplier;
	}
	
	public void addBet(Bet bet) {
		bets.add(bet);
	}
}
