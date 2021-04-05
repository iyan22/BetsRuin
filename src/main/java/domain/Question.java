package domain;

import java.io.*;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private double betShare;
	private String result; 
	private boolean open;
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> bets = new Vector<Bet>();

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, double betShare, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum = betMinimum;
		this.betShare = betShare;
		this.event = event;
		this.open = true;
	}
	
	public Question(String query, float betMinimum, double betShare, Event event) {
		super();
		this.question = query;
		this.betMinimum = betMinimum;
		this.betShare = betShare;
		this.event = event;
		this.open = true;
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be set
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be set
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * Get the minimum amount of the bet
	 * 
	 * @return the minimum bet amount
	 */	
	public float getBetMinimum() {
		return betMinimum;
	}
	/**
	 * Get the minimum amount of the bet
	 * 
	 * @param  betMinimum minimum bet amount to be set
	 */
	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}
	/**
	 * Get the minimum amount of the bet
	 * 
	 * @return the minimum bet amount
	 */
	public double getBetShare() {
		return betShare;
	}
	/**
	 * Get the minimum amount of the bet
	 * 
	 * @param  betMinimum minimum bet amount to be set
	 */
	public void setBetShare(double betShare) {
		this.betShare = betShare;
	}
	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	/**
	 * Get the list of bets associated to the question
	 * 
	 * @return the associated event
	 */
	public Vector<Bet> getBetList() {
		return bets;
	}
	
	public void addBet(Bet bet) {
		bets.add(bet);
	}

	public boolean isOpen() {
		return open;
	}	

	public void close() {
		this.open = false;
	}

	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}




	
}