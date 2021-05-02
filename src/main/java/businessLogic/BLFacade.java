package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Vector;

//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Bet;
import domain.Event;
import domain.Prediction;
import exceptions.AlreadyFollowed;
import exceptions.EventFinished;
import exceptions.NoReferralCodeFound;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	/**
	 * This method creates an user
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return the created user, or exception
	 * @throws UserAlreadyExists if the same user already exists
	 * @throws NoReferralCodeFound if the referralCode is not assigned to any user
	 */
	@WebMethod User createUser(String username,String name, String surname, String password, String email, String referredBy) throws UserAlreadyExists, NoReferralCodeFound;
	
	/**
	 * This method checks if the login is correct
	 * 
	 * @param username to check
	 * @param password to check
	 * @return user if the username is correct, null if username or password are not correct
	 */
	@WebMethod User login(String username, String password);
	
	/**
	 * This method creates and event
	 * 
	 * @param description of the event
	 * @param date of the event
	 * @return the created event
	 */
	@WebMethod Event createEvent(String description, Date date) ;
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * Adds a bet to a question
	 * @param amount to bet
	 * @param win if the 'first'/'second' team wins or there is a 'tie'
	 * @param question to add the bet to
	 * @return the created bet
	 */
	@WebMethod
	public Bet addBet(User user, float amount, Prediction pred);
	
	@WebMethod
	public Prediction addPrediction(Question q, String answer, float mult);
	
	/**
	 * This method find a question in the data base.
	 * @param questionNumber id of the question.
	 * @return question if is in the data base.
	 */
	public Question findQuestion(int questionNumber);
	
	/**
	 * This method checks if the user is administrator.
	 * @param username to check
	 * @return true if the user is admin, false otherwise.
	 */
	public boolean isAdmin(String username);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	/**
	 * Method used to obtain all the bets a user has made
	 * @param username
	 * @return list of bets
	 */
	public List<Bet> getBets(String username);
	
	/**
	 * Method used to obtain all the predictions that have been made for a question
	 * @param q 
	 * @return list of predictions
	 */
	public List<Prediction> getPredictions(Question q);
	
	/**
	 * Method used to add funds to user's account
	 * @param user
	 * @return boolean if successful or not
	 */
	public boolean addFunds(User user, float amount);
	/**
	 * Method used to assign a credit card to a user
	 * @param user
	 * @param card
	 * @return true if success, false if error
	 */
	public boolean addCard(User user, int[] card);
	/**
	 * Method used to subtract the amount betted
	 * @param user
	 * @param amount
	 */
	public void betMade(User user, float amount);
	/**
	 * Method used to close an event, all questions must be closed
	 * @param e
	 */
	public void closeEvent(Event e);
	/**
	 * Method used to set a prediction as winner
	 * @param p
	 */
	public void setPredictionToWinner(Prediction p);
	/**
	 * Method used to close a question
	 * @param q
	 */
	public void closeQuestion(Question q);
	
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getOpenEventsMonth(Date date);
	
	/**
	 * Method used to add to the user's follow list a new team/player
	 * 
	 * @param user
	 * @param team
	 */
	@WebMethod
	public void follow(User user, String team) throws AlreadyFollowed;
	
	/**
	 * Method used to obtain all the active events that include the teams that the user follow
	 * 
	 * @param user
	 * @return collection of events
	 */
	public Vector<Event> activeFollowedEvents(User user);
	
	/**
	 * Method used to obtain all the questions created for an event
	 * @param e
	 * @return collection of questions
	 */
	public Vector<Question> getQuestions(Event e);
	
	public Vector<String> getFollowedTeams(User user);
}
