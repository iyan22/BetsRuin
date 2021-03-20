package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess()  {	
		new DataAccess(false);
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

			}

			User admin = new User("admin", "admin","admin","admin", "admin");
			admin.setAdmin();
			db.persist(admin);


			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 


			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			

			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
		Event ev = db.find(Event.class, event.getEventNumber());
		System.out.println(event.getDescription());
		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		Question q = ev.addQuestion(question, betMinimum);
		db.getTransaction().begin();
		
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}
	
	/**
	 * This method creates a user and stores it in the data base.
	 * @param username that the user will identify
	 * @param password of the user
	 * @param email of the new user
	 * @return the created user or exception
	 * @throws UserAlreadyExists if there is a user with the same username
	 */
	public User createUser(String username,String name, String surname, String password, String email) throws UserAlreadyExists {
		System.out.println(">> DataAccess: createUser=> username= "+username+" email= "+email);
		User usr = db.find(User.class, username);
		if(usr!=null) throw new UserAlreadyExists(ResourceBundle.getBundle("Etiquetas").getString("ErrorUserAlreadyExist"));

		db.getTransaction().begin();
		db.persist(new User(username,name,surname, password, email));
		db.getTransaction().commit();
		return usr;
	}
	
	/**
	 * This method checks if the user and the password coincide.
	 * @param username of the user.
	 * @param password of the user.
	 * @return true if the user and the password coincide, false otherwise.
	 */
	public User login(String username, String password) {
		User usr = db.find(User.class, username);
		if(usr!=null && password.contentEquals(usr.getPassword())) return usr;
		else return null;
	}
	
	/**
	 * This method creates an event and stores it in the data base.
	 * @param description of the event.
	 * @param date of the event.
	 * @return the event.
	 */
	public Event createEvent(String description, Date date) {
		TypedQuery<Event> query= db.createQuery("SELECT e FROM Event e", Event.class);
		ArrayList<Event> resv= (ArrayList<Event>) query.getResultList();
		int res=resv.size();
		Event event = new Event(res+1, description, date);
		db.getTransaction().begin();
		db.persist(event);
		db.getTransaction().commit();
		return event;
	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}
	
	/**
	 * This method gets all the bets from a questions, not used for first iteration.
	 * @param q question to get the bets.
	 * @return a vector with all the bets related to that questions.
	 */
	public Vector<Bet> getBets(Question q){
		System.out.println(">> DataAccess: getBets");
		Vector<Bet> res = new Vector<Bet>();
		TypedQuery<Bet> query = db.createQuery("SELECT b FROM Bet b WHERE b.question=q", Bet.class);
		List<Bet> bets = query.getResultList();
		for(Bet b: bets)
		{
			res.add(b);
			System.out.println(b.toString());
		}
		return res;
	}

	/**
	 * Adds a bet to a question
	 * @param amount to bet
	 * @param first team winning bet
	 * @param tie bet
	 * @param second team winning bet
	 * @param question to add the bet to
	 * @return the created bet
	 * @throws BetDenied if the probabilities of winning/tie ar over 100
	 */
	public Bet addBet(User user,String win, float amount, Question question) {
		System.out.println(">> DataAccess: addBet");
		int i=countBets();
		Bet bet = new Bet(i,win, amount, question);
		bet.setUser(user);
		question.addBet(bet);
		System.out.println("Bet added!");
		db.getTransaction().begin();
		db.persist(bet);
		db.getTransaction().commit();
		betMade(user, amount);
		return bet;
	}
	public int countBets() {
		TypedQuery<Bet> query= db.createQuery("SELECT b FROM Bet b", Bet.class);
		ArrayList<Bet> resv= (ArrayList<Bet>) query.getResultList();
		int res=resv.size();
		res++;
		return res;
		
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}
	
	/**
	 * This method  find a question in the data base with its number.
	 * @param questionNumber to search in the data base.
	 * @return
	 */
	public Question findQuestion(int questionNumber) {
		Question qst = db.find(Question.class, questionNumber);
		return qst;
	}
	
	/**
	 * This method opens the data base.
	 * @param initializeMode
	 */
	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	
	/**
	 * This method checks if the question in an event is already registered in the data base.
	 * @param event of the question.
	 * @param question to check.
	 * @return true if already exist, false otherwise.
	 */
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}
	
	/**
	 * This method checks if the user is stored at the data base as admin.
	 * @param username to search.
	 * @return true if the user is admin, false otherwise.
	 */
	public boolean isAdmin(String username) {
		User us = db.find(User.class, username);
		boolean res=false;
		if(us!=null) {
			res=us.isAdmin();
		}
		return res;
	}
	
	/**
	 * This method closes the data base.
	 */
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	/**
	 * Method used to obtain all the bets a user has made
	 * @param username
	 * @return list of bets
	 */
	public List<Bet> getBets(String username){
		TypedQuery<Bet> query= db.createQuery("SELECT b FROM Bet b WHERE b.user=?1",Bet.class);
		query.setParameter(1, username);
		return  query.getResultList();
	}
	
	/**
	 * Method used to add funds to user's account
	 * @param user
	 * @return boolean if successful or not
	 */
	public boolean addFunds(User user, float amount) {
		User u=db.find(User.class, user.getUsername());
		if(u!=null) {
			db.getTransaction().begin();
			u.addFunds(amount);
			db.getTransaction().commit();
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Method used to assign a credit card to a user
	 * @param user
	 * @param card
	 * @return true if success, false if error
	 */
	public boolean addCard(User user, int[] card) {
		User u=db.find(User.class, user.getUsername());
		if(u!=null) {
			db.getTransaction().begin();
			u.setCard(card);
			db.getTransaction().commit();
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Method used to subtract the amount betted
	 * @param user
	 * @param amount
	 */
	public void betMade(User user, float amount) {
		User u=db.find(User.class, user.getUsername());
			db.getTransaction().begin();
			u.betMade(amount);
			db.getTransaction().commit();
	}

}