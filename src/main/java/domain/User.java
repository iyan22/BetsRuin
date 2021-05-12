package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

import exceptions.AlreadyFollowed;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Serializable{
	
	@XmlID
	@Id
	private String username;
	private String name;
	private String surname;
	private String password;
	private String email;
	private boolean admin;
	private String referralCode;
	private Integer referred=0;
	private String referredBy;
	private Vector<String> followed;
	
	//addFunds
	private boolean bankdata=false;
	private int[] card; // 0,1,2,3-CARD NUMBER 4-MM 5-YYYY 6-CVV
	private float funds=0;
	
	public User(String username, String name,String surname, String password, String email, String ref, String refBy) {
		this.username=username;
		this.password=password;
		this.name=name;
		this.surname=surname;
		this.email=email;
		this.referralCode=ref;
		this.referredBy=refBy;
		this.admin=false;
		this.followed = new Vector<String>();
	}
	
	public User() {}
	
	public String getMail() {
		return email;
	}
	public void setMail(String mail) {
		this.email=mail;
	}
	public float getFunds() {
		return funds;
	}
	public void addFunds(float amount) {
		funds=funds+amount;
	}
	public void setBank() {
		bankdata=true;
	}
	public boolean getBank() {
		return bankdata;
	}
	public void setCard(int[] card) {
		setBank();
		this.card=card;
	}
	public void betMade(float amount) {
		this.funds=this.funds-amount;
	}
	public int[] getCard() {
		return card;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getUsername() {
		return username;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin() {
		this.admin=true;
	}
	
	public String getPassword() {
		return password;
	}
	public String getRefCode() {
		return referralCode;
	}
	public void setRefCode(String ref) {
		this.referralCode=ref;
	}
	public Integer getNumberRef() {
		return referred;
	}
	public void addRef() {
		this.referred++;
	}
	public String getReferredBy() {
		return referredBy;
	}
	public void addFollowed(String f) throws AlreadyFollowed {
		if(followed.contains(f)) throw new AlreadyFollowed();
		else followed.add(f);
	}
	
	public Vector<String> getFollowed(){
		return followed;
	}
	
}
