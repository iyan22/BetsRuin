package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
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
import domain.Prediction;
import domain.Question;
import domain.User;
import exceptions.NoReferralCodeFound;
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

			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17),"Futbol");
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17),"Futbol");
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17),"Futbol");
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17),"Futbol");
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17),"Futbol");
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17),"Futbol");
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17),"Futbol");
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17),"Futbol");
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17),"Futbol");
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17),"Futbol");

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1),"Futbol");
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1),"Futbol");
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1),"Futbol");
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1),"Futbol");
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1),"Futbol");
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1),"Futbol");


			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28),"Futbol");
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28),"Futbol");
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28),"Futbol");
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28),"Futbol");

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

			User admin = new User("admin", "admin","admin","admin", "admin", "", "");
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
		if (ev.doesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
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
	 * @throw NoReferralCodeFound if the referral code was not found
	 */
	public User createUser(String username,String name, String surname, String password, String email, String referredBy) throws UserAlreadyExists, NoReferralCodeFound {
		User usr = db.find(User.class, username);
		if (usr != null) {
			throw new UserAlreadyExists(ResourceBundle.getBundle("Etiquetas").getString("ErrorUserAlreadyExist"));
		}
		if (!referredBy.equals("")) {
			User refU = getRefUser(referredBy);
			if(refU == null) {
				throw new NoReferralCodeFound();
			}
			db.getTransaction().begin();;
			refU.addRef();
			db.getTransaction().commit();
		}
		System.out.println(">> DataAccess: createUser=> username= "+username+" email= "+email);
		String ref = refGen();
		db.getTransaction().begin();
		db.persist(new User(username,name,surname, password, email, ref, referredBy));
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
	public Event createEvent(String description, Date date, String type) {
		TypedQuery<Event> query= db.createQuery("SELECT e FROM Event e", Event.class);
		ArrayList<Event> resv= (ArrayList<Event>) query.getResultList();
		int res=resv.size();
		Event event = new Event(res+1, description, date, type);
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
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		return new Vector<Event>(query.getResultList());
	}

	/**
	 * This method gets all the bets from a questions, not used for first iteration.
	 * @param q question to get the bets.
	 * @return a vector with all the bets related to that questions.
	 */
	public Vector<Bet> getBets(Prediction p) {
		System.out.println(">> DataAccess: getBets");
		TypedQuery<Bet> query = db.createQuery("SELECT b FROM Bet b WHERE b.prediction=?1", Bet.class);
		query.setParameter(1, p);
		return new Vector<Bet>(query.getResultList());
	}

	
	public Vector<Prediction> getPredictions(Question q) {
		TypedQuery<Prediction> query = db.createQuery("SELECT p FROM Prediction p WHERE p.question=?1", Prediction.class);
		query.setParameter(1, q);
		return new Vector<Prediction>(query.getResultList());
	}

	/**
	 * Adds a bet to a question
	 * @param amount to bet
	 * @param first team winning bet
	 * @param tie bet
	 * @param second team winning bet
	 * @param question to add the bet to
	 * @return the created bet
	 */
	public Bet addBet(User user, float amount, Prediction pred) {
		System.out.println(">> DataAccess: addBet");
		int i = countBets();
		Bet bet = new Bet(i, amount, user, pred);
		bet.setUser(user);
		pred.addBet(bet);
		System.out.println("Bet added!");
		db.getTransaction().begin();
		db.persist(bet);
		db.getTransaction().commit();
		betMade(user, amount);
		return bet;
	}

	/**
	 * This method adds a prediction to a selected question.
	 * @param q: the question
	 * @param answer: the prediction answer
	 * @param share: how much is the winning multiplier
	 * @return a prediction
	 */
	public Prediction addPrediction(Question q, String answer, float share) {
		int i = countPredictions();
		Prediction pred = new Prediction(i, q, answer, share);
		q.addPrediction(pred);
		db.getTransaction().begin();
		db.persist(pred);
		db.getTransaction().commit();
		return pred;
	}
	
	/**
	 * Method that calculates how many predictions are in the data base
	 * @return number of predictions
	 */
	public int countPredictions() {
		TypedQuery<Prediction> query= db.createQuery("SELECT p FROM Object p WHERE p instanceof Prediction", Prediction.class);
		ArrayList<Prediction> resv= (ArrayList<Prediction>) query.getResultList();
		int res=resv.size();
		res++;
		return res;

	}
	
	/**
	 * Method that calculates how many bets are in the data base
	 * @return number of bets.
	 */
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
		return ev.doesQuestionExists(question);

	}

	/**
	 * This method checks if the user is stored at the data base as admin.
	 * @param username to search.
	 * @return true if the user is admin, false otherwise.
	 */
	public boolean isAdmin(String username) {
		User us = db.find(User.class, username);
		boolean res = false;
		if(us != null) {
			res = us.isAdmin();
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
		TypedQuery<Bet> query = db.createQuery("SELECT b FROM Bet b WHERE b.username=?1",Bet.class);
		query.setParameter(1, username);
		return  query.getResultList();
	}

	/**
	 * Method used to add funds to user's account
	 * @param user
	 * @return boolean if successful or not
	 */
	public boolean addFunds(User user, float amount) {
		User u = db.find(User.class, user.getUsername());
		addFundsRef(user.getReferredBy(), amount);
		if (u != null) {
			db.getTransaction().begin();
			u.addFunds(amount);
			db.getTransaction().commit();
			return true;
		}
		return false;
	}
	/**
	 * Method used to assign a credit card to a user
	 * @param user
	 * @param card
	 * @return true if success, false if error
	 */
	public boolean addCard(User user, int[] card) {
		User u = db.find(User.class, user.getUsername());
		if (u != null) {
			db.getTransaction().begin();
			u.setCard(card);
			db.getTransaction().commit();
			return true;
		}
		return false;
	}
	/**
	 * Method used to subtract the amount bet
	 * @param user
	 * @param amount
	 */
	public void betMade(User user, float amount) {
		User u = db.find(User.class, user.getUsername());
		db.getTransaction().begin();
		u.betMade(amount);
		db.getTransaction().commit();
	}
	
	/**
	 * Method to get a user from the database.
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.username=?1", User.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
	
	/**
	 * This method closes an event and returns the money to the users that made a bet in one of the questions.
	 * @param e
	 */
	public void closeEvent(Event e) {
		Event ef = db.find(Event.class, e.getEventNumber());
		Question qf;
		Prediction pf;
		Bet bf;
		TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q WHERE q.event=?1", Question.class);
		query.setParameter(1, e);
		ArrayList<Question> qlist = (ArrayList<Question>) query.getResultList();
		db.getTransaction().begin();
		// Iterate all over the questions
		for (Question q: qlist) {
			// And all over the bets of each
			qf=db.find(Question.class, q.getQuestionNumber());
			TypedQuery<Prediction> queryP = db.createQuery("SELECT p FROM Prediction p WHERE p.question=?2", Prediction.class);
			queryP.setParameter(2, q);
			ArrayList<Prediction> plist = (ArrayList<Prediction>) queryP.getResultList();
			for (Prediction p: plist) {
				pf=db.find(Prediction.class, p.getPredictionId());
				TypedQuery<Bet> queryB = db.createQuery("SELECT b FROM Bet b WHERE b.prediction=?3", Bet.class);
				queryB.setParameter(3, p);
				ArrayList<Bet> blist = (ArrayList<Bet>) queryB.getResultList();
				if (pf.isWinner()) {
					// And now over the winner bets
					for (Bet b: blist) {
						bf=db.find(Bet.class, b.getId());
						getUser(bf.getUsername()).addFunds((float) (bf.getAmount()*pf.getShare()));
						db.remove(bf);
					}
				}
				db.remove(pf);
			}
			db.remove(qf);
		}
		db.remove(ef);
		db.getTransaction().commit();
	}
	
	/**
	 * This method set a prediction as winner.
	 * @param winner: the prediction
	 */
	public void setWinner(Prediction winner) {
		Prediction pf = db.find(Prediction.class, winner.getPredictionId());
		db.getTransaction().begin();
		pf.setWinner();
		db.getTransaction().commit();
	}
	
	/**
	 * This method closes a question
	 * @param q: the question
	 */
	public void closeQuestion(Question q) {
		Question qf = db.find(Question.class, q.getQuestionNumber());
		db.getTransaction().begin();
		qf.close();
		db.getTransaction().commit();
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getOpenEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2 AND ev.isOpen()",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}
	
	/**
	 * Method use to generate a referralCode
	 * @return referralCode
	 */
	public String refGen() {
		boolean done=false;
		int leftLimit=48;
		int rightLimit=90;
		int targetStringLength=6;
		Random random=new Random();
		String generatedString="";
		while(!done) {
			generatedString=random.ints(leftLimit, rightLimit+1)
					.filter(i->(i<=57 || i>=65))
					.limit(targetStringLength)
					.collect(StringBuilder::new,StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			if(checkRef(generatedString)) done=true;
		}
		return generatedString;
	}
	/**
	 * Method used to check if the referral code exists in the database
	 * @param ref, referral code
	 * @return boolean
	 */
	private boolean checkRef(String ref) {
		TypedQuery<User> query=db.createQuery("SELECT u FROM User u WHERE u.referralCode=?1", User.class);
		query.setParameter(1, ref);
		List<User> l=query.getResultList();
		if(l.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Method used to get the Referred User
	 * @param ref, referral Code
	 * @return user
	 */
	private User getRefUser(String ref) {
		TypedQuery<User> query=db.createQuery("SELECT u FROM User u WHERE u.referralCode=?1", User.class);
		query.setParameter(1, ref);
		List<User> l= query.getResultList();
		if(l.isEmpty()) {
			return null;
		}else {
			return l.get(0);
		}
	}
	/**
	 * Method used to add 10% of the added funds to the referred user
	 * @param ref, referral code
	 * @param fund, total amount
	 */
	private void addFundsRef(String ref, float fund) {
		TypedQuery<User> query=db.createQuery("SELECT u FROM User u WHERE u.referralCode=?1", User.class);
		query.setParameter(1, ref);
		User u=query.getSingleResult();
		db.getTransaction().begin();
		u.addFunds((float)(fund*0.1));
		db.getTransaction().commit();
	}


}