package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.User;
import domain.Bet;
import domain.Event;
import domain.Prediction;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	public BLFacadeImplementation(DataAccess da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dbManager.createQuestion(event,question,betMinimum);		

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
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	@WebMethod
	public User createUser(String username,String name, String surname, String password, String email) throws UserAlreadyExists{

		//The minimum bet must be greater than 0
		dbManager.open(false);

		User usr=dbManager.createUser(username,name,surname,password,email);		

		dbManager.close();

		return usr;
	};
	
	/**
	 * This method checks if the user is administrator.
	 * @param username to check
	 * @return true if the user is admin, false otherwise.
	 */
	public boolean isAdmin(String username) {
		dbManager.open(false);
		boolean adm = dbManager.isAdmin(username);
		dbManager.close();
		return adm;
	}
	
	/**
	 * This method find a question in the data base.
	 * @param questionNumber id of the question.
	 * @return question if is in the data base.
	 */
	public Question findQuestion(int questionNumber) {
		dbManager.open(false);
		
		Question q = dbManager.findQuestion(questionNumber);
		
		dbManager.close();
		
		return q;
	}
	
	/**
	 * This method creates and event
	 * 
	 * @param description of the event
	 * @param date of the event
	 * @return the created event
	 */
	public Event createEvent(String description, Date date) {
		dbManager.open(false);
		
		Event event = dbManager.createEvent(description, date);
		
		dbManager.close();
		
		return event;
	}
	
	/**
	 * This method checks if the login is correct
	 * 
	 * @param username to check
	 * @param password to check
	 * @return user if the username is correct, null if username or password are not correct
	 */
	public User login(String username, String password) {
		dbManager.open(false);
		
		User usr = dbManager.login(username, password);
		dbManager.close();
		
		return usr;
	}
	
	public Prediction addPrediction(Question q, String answer, float share) {
		dbManager.open(false);
		Prediction pred = dbManager.addPrediction(q, answer, share);
		dbManager.close();
		return pred;
	}
	
	/**
	 * Adds a bet to a prediction
	 * @param amount to bet
	 * @param prediction to add the bet to
	 * @return the created bet
	 */
	public Bet addBet(User user, float amount, Prediction pred){
		dbManager.open(false);
		Bet bet = dbManager.addBet(user, amount, pred);
		dbManager.close();
		return bet;
	}

	/**
	 * Method to close the data base.
	 */
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod	
	public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
	/**
	 * Method used to obtain all the bets a user has made
	 * @param username
	 * @return list of bets
	 */
	public List<Bet> getBets(String username){
		dbManager.open(false);
		List<Bet> l=dbManager.getBets(username);
		dbManager.close();
		return l;
	}
	
	public List<Prediction> getPredictions(Question q){
		dbManager.open(false);
		List<Prediction> list = dbManager.getPredictions(q);
		dbManager.close();
		return list;
	}
	/**
	 * Method used to add funds to user's account
	 * @param user
	 * @return boolean if successful or not
	 */
	public boolean addFunds(User user, float amount) {
		dbManager.open(false);
		boolean res=dbManager.addFunds(user, amount);
		dbManager.close();
		return res;
	}
	/**
	 * Method used to assign a credit card to a user
	 * @param user
	 * @param card
	 * @return true if success, false if error
	 */
	public boolean addCard(User user, int[] card) {
		dbManager.open(false);
		boolean res=dbManager.addCard(user, card);
		dbManager.close();
		return res;
	}
	/**
	 * Method used to subtract the amount betted
	 * @param user
	 * @param amount
	 */
	public void betMade(User user, float amount) {
		dbManager.open(false);
		dbManager.betMade(user, amount);
		dbManager.close();
	}
	
	
	/**
	 * Method used to close an event, all questions must be closed
	 * @param e
	 */
	public void closeEvent(Event e) {
		dbManager.open(false);
		dbManager.closeEvent(e);
		dbManager.close();
	}


}

