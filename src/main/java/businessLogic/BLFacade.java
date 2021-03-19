package businessLogic;

import java.util.Vector;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Bet;
import domain.Event;
import exceptions.EventFinished;
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
	 */
	@WebMethod User createUser(String username,String name, String surname, String password, String email) throws UserAlreadyExists;
	
	/**
	 * This method checks if the login is correct
	 * 
	 * @param username to check
	 * @param password to check
	 * @return if the username and password are correct
	 */
	@WebMethod boolean login(String username, String password);
	
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
	public Bet addBet(String win, float amount, Question question);
	
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

	
}
