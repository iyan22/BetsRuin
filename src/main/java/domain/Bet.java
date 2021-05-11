package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet implements Serializable{
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private Integer id;
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
	
	public void setAmount(float a) {
		amount=a;
	}

	public float getAmount() {
		return amount;
	}

	public String getUsername() {
		return username;
	}
	
	public Prediction getPrediction() {
		return prediction;
	}
	
	public void setPrediction(Prediction p) {
		prediction=p;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(Integer i) {
		id=i;
	}
}
