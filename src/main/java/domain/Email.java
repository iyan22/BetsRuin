package domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Email {
	
	private String sender;
	private String subject;
	private String message;
	private Date date; 
	private LinkedList<User> sentTo;
	
	public Email(String sender, String subject, String message) {
		this.sender=sender;
		this.subject=subject;
		this.message=message;
		this.date= new Date();
		this.sentTo= new LinkedList<User>();
	}
	
	public Email() {}
	
	public String getSender() {
		return sender;
	}
	public String getSubject() {
		return subject;
	}
	public String getMessage() {
		return message;
	}
	public Date getDate() {
		return date;
	}
	public List<User> getSentToList(){
		return sentTo;
	}
	public void addUser(User u) {
		sentTo.add(u);
	}
}
