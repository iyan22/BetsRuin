package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.User;
import domain.Bet;
import domain.Email;
import domain.Event;
import domain.Prediction;
import exceptions.AlreadyFollowed;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.NoReferralCodeFound;
import exceptions.UserAlreadyExists;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	@WebMethod
	public User createUser(String username, String name, String surname, String password, String email,
			String referredBy) throws UserAlreadyExists, NoReferralCodeFound {

		// The minimum bet must be greater than 0
		dbManager.open(false);

		User usr = dbManager.createUser(username, name, surname, password, email, referredBy);

		dbManager.close();

		return usr;
	};

	/**
	 * This method checks if the user is administrator.
	 * 
	 * @param username to check
	 * @return true if the user is admin, false otherwise.
	 */
	@WebMethod public boolean isAdmin(String username) {
		dbManager.open(false);
		boolean adm = dbManager.isAdmin(username);
		dbManager.close();
		return adm;
	}

	/**
	 * This method find a question in the data base.
	 * 
	 * @param questionNumber id of the question.
	 * @return question if is in the data base.
	 */
	@WebMethod public Question findQuestion(int questionNumber) {
		dbManager.open(false);

		Question q = dbManager.findQuestion(questionNumber);

		dbManager.close();

		return q;
	}

	/**
	 * This method creates and event
	 * 
	 * @param description of the event
	 * @param date        of the event
	 * @return the created event
	 */
	@WebMethod public Event createEvent(String description, Date date, String type) {
		dbManager.open(false);
		Event event = dbManager.createEvent(description, date, type);
		dbManager.close();
		return event;
	}

	/**
	 * This method checks if the login is correct
	 * 
	 * @param username to check
	 * @param password to check
	 * @return user if the username is correct, null if username or password are not
	 *         correct
	 */
	@WebMethod public User login(String username, String password) {
		dbManager.open(false);

		User usr = dbManager.login(username, password);
		dbManager.close();

		return usr;
	}

	@WebMethod public Prediction addPrediction(Question q, String answer, float share) {
		dbManager.open(false);
		Prediction pred = dbManager.addPrediction(q, answer, share);
		dbManager.close();
		return pred;
	}

	/**
	 * Adds a bet to a prediction
	 * 
	 * @param amount     to bet
	 * @param prediction to add the bet to
	 * @return the created bet
	 */
	@WebMethod public Bet addBet(User user, float amount, Prediction pred) {
		dbManager.open(false);
		Bet bet = dbManager.addBet(user, amount, pred);
		dbManager.close();
		return bet;
	}

	/**
	 * Method to close the data base.
	 */
	@WebMethod public void close() {
		DataAccess dB4oManager = new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	/**
	 * Method used to obtain all the bets a user has made
	 * 
	 * @param username
	 * @return list of bets
	 */
	@WebMethod public List<Bet> getBets(String username) {
		dbManager.open(false);
		List<Bet> l = dbManager.getBets(username);
		dbManager.close();
		return l;
	}

	@WebMethod public List<Prediction> getPredictions(Question q) {
		dbManager.open(false);
		List<Prediction> list = dbManager.getPredictions(q);
		dbManager.close();
		return list;
	}

	/**
	 * Method used to add funds to user's account
	 * 
	 * @param user
	 * @return boolean if successful or not
	 */
	@WebMethod public boolean addFunds(User user, float amount) {
		dbManager.open(false);
		boolean res = dbManager.addFunds(user, amount);
		dbManager.close();
		return res;
	}

	/**
	 * Method used to assign a credit card to a user
	 * 
	 * @param user
	 * @param card
	 * @return true if success, false if error
	 */
	@WebMethod public boolean addCard(User user, int[] card) {
		dbManager.open(false);
		boolean res = dbManager.addCard(user, card);
		dbManager.close();
		return res;
	}

	/**
	 * Method used to subtract the amount bet
	 * 
	 * @param user
	 * @param amount
	 */
	@WebMethod public void betMade(User user, float amount) {
		dbManager.open(false);
		dbManager.betMade(user, amount);
		dbManager.close();
	}

	/**
	 * Method used to close an event, all questions must be closed
	 * 
	 * @param e
	 */
	@WebMethod public void closeEvent(Event e) {
		dbManager.open(false);
		dbManager.closeEvent(e);
		dbManager.close();
	}

	/**
	 * Method used to set a prediction as winner
	 * 
	 * @param p
	 */
	@WebMethod public void setPredictionToWinner(Prediction p) {
		dbManager.open(false);
		dbManager.setWinner(p);
		dbManager.close();
	}

	/**
	 * Method used to close a question
	 * 
	 * @param q
	 */
	@WebMethod public void closeQuestion(Question q) {
		dbManager.open(false);
		dbManager.closeQuestion(q);
		dbManager.close();
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getOpenEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getOpenEventsMonth(date);
		dbManager.close();
		return dates;
	}

	/**
	 * Method used to add to the user's follow list a new team/player
	 * 
	 * @param user
	 * @param team
	 * @throws AlreadyFollowed
	 */
	@WebMethod
	public void follow(User user, String team) throws AlreadyFollowed {
		dbManager.open(false);
		dbManager.follow(user, team);
		dbManager.close();
	}

	/**
	 * Method used to obtain all the active events that include the teams that the
	 * user follow
	 * 
	 * @param user
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> activeFollowedEvents(User user) {
		dbManager.open(false);
		Vector<Event> events = dbManager.activeFollowedEvents(user);
		dbManager.close();
		return events;
	}

	/**
	 * Method used to obtain all the questions created for an event
	 * 
	 * @param e
	 * @return collection of questions
	 */
	@WebMethod public Vector<Question> getQuestions(Event e) {
		dbManager.open(false);
		Vector<Question> questions = dbManager.getQuestions(e);
		dbManager.close();
		return questions;
	}

	@WebMethod public Vector<String> getFollowedTeams(User user) {
		dbManager.open(false);
		Vector<String> teams = dbManager.getFollowedTeams(user);
		dbManager.close();
		return teams;
	}
	
	/**
	 * This method invokes the data access to retrieve the events of a given date with a specific category
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEventsType(Date date,String type) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEventsType(date,type);
		dbManager.close();
		return events;
	}
	
	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events of a especific category
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getOpenEventsMonthType(Date date,String type) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getOpenEventsMonthType(date,type);
		dbManager.close();
		return dates;
	}
	/**
	 * Method used to send mail to all the users
	 */
	@WebMethod
	public void sendMailOffer(Email mail) {
		dbManager.open(false);
		ArrayList<User> users=dbManager.getAllUsers();
		dbManager.close();
		String from = mail.getSender();
		String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("inforbets66@gmail.com", "betsinfor66");

            }

        });
        boolean sent=true;
		for(int i=0; i<users.size(); i++) {
	        User u = users.get(i);
	        String to=u.getMail();
	        session.setDebug(true);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject(mail.getSubject());

	            // Now set the actual message
	            message.setText(mail.getMessage());

	            System.out.println("sending...");
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            //mex.printStackTrace(); do nothing
	        	sent=false;
	        }
	        if(sent) {
	        	mail.addUser(u);
	        }else {
	        	sent=true;
	        }
	        
		}
		dbManager.open(false);
		dbManager.saveMail(mail);
		dbManager.close();
		
	}
}
