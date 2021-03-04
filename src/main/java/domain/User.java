package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String username;
	private String password;
	private String email;
	private boolean admin;
	
	public User(String username, String password, String email) {
		this.username=username;
		this.password=password;
		this.email=email;
		this.admin=false;
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
