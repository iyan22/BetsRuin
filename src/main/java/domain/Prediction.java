package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Prediction implements Serializable{
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private Integer predictionId;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> bets = new Vector<Bet>();
	@XmlIDREF
	private Question question;
	private String answer;
	private float share;
	private boolean win = false;
	
	public Prediction(int id, Question question, String answer, float share) {
		this.predictionId = id;
		this.question = question;
		this.answer = answer;
		this.share = share;
	}
	

	public int getPredictionId() {
		return predictionId;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isWinner() {
		return win;
	}
	public void setWinner() {
		this.win = true;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public float getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	public Vector<Bet> getBets() {
		return bets;
	}
	
	public void addBet(Bet bet) {
		bets.add(bet);
	}
}
