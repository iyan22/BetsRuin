package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String username;
	private String name;
	private String surname;
	private String password;
	private String email;
	private boolean admin;
	
	//addFunds
	private boolean bankdata=false;
	private int[] card; // 0,1,2,3-CARD NUMBER 4-MM 5-YYYY 6-CVV
	private float funds=0;
	
	public User(String username, String name,String surname, String password, String email) {
		this.username=username;
		this.password=password;
		this.name=name;
		this.surname=surname;
		this.email=email;
		this.admin=false;
	}
	public String getMail() {
		return email;
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
	
	public String email() {
		return email;
	}
	
}
