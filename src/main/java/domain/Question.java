package domain;

import java.io.*;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private boolean open = true;
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Prediction> preds = new Vector<Prediction>();

	public Question(){
		super();
	}
	
	public Question(Integer questionNumber, String question, float betMinimum, Event event) {
		super();
		this.questionNumber = questionNumber;
		this.question = question;
		this.betMinimum = betMinimum;
		this.event = event;
	}
	
	public Question(String question, float betMinimum, Event event) {
		super();
		this.question = question;
		this.betMinimum = betMinimum;
		this.event = event;
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
	
	public void addPrediction(Prediction pred) {
		preds.add(pred);
	}

	public Vector<Prediction> getPredictions(){
		return preds;
	}
	
	public void setPredictions(Vector<Prediction> preds) {
		this.preds = preds;
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