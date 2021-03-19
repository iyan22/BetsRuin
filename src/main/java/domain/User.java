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
	private int[] card; // 1-CARD NUMBER 2-MM 3-YYYY 4-CVV
	private int funds=0;
	
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
	public int getFunds() {
		return funds;
	}
	public void addFunds(int amount) {
		funds=funds+amount;
	}
	public void setBank() {
		bankdata=true;
	}
	public boolean getBank() {
		return bankdata;
	}
	public void setCard(int[] card) {
		this.card=card;
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
