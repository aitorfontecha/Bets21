package businessLogic;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;
	private static String user;

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
	public ExtendedIterator<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		ExtendedIteratorImplementation<Event> it = new ExtendedIteratorImplementation<Event>(events);
		dbManager.close();
		return it;
	}
	
	@WebMethod
	public List<Question> getQuestions(Event gertaera) {
		List<Question> questions = gertaera.getQuestions();
		return questions;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public List<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		List<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	@WebMethod
	public void close() {
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
	@WebMethod
	public Pertsona isLogin(String username, String pasahitza) {
		dbManager.open(false);
		Pertsona p;
		p = dbManager.isLogin(username, pasahitza);
		dbManager.close();
		return p;
	}
	
	@WebMethod
	public boolean storeGuest(String username, String pass1, String pass2, Date jaiotzeData, String kredituTxartela) {
		dbManager.open(false);
		boolean em = dbManager.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
		dbManager.close();
		return em;
	}
	
	@WebMethod
	public boolean existEvent(Date data, Event gertaera) {
		dbManager.open(false);
		boolean em= dbManager.existEvent(data, gertaera);
		dbManager.close();
		return em;
	}
	
	@WebMethod
	public boolean storeEvent(Event gertaera) {
		dbManager.open(false);
		boolean em = dbManager.storeEvent(gertaera);
		dbManager.close();
		return em;
	}
	
	@WebMethod
	public Pronostikoa pronostikoaIpini(Question galdera, Pronostikoa pronostikoa){
		dbManager.open(false);
		Pronostikoa res= dbManager.pronostikoaIpini(galdera, pronostikoa);
		dbManager.close();
		return res;
	}
	
	@WebMethod
	public boolean emaitzaIpini(Question galdera, Pronostikoa pronostikoa) {
		dbManager.open(false);
		boolean em= dbManager.emaitzaIpini(galdera, pronostikoa);
		dbManager.close();
		return em;
	}
	
	@WebMethod
	public Event deleteEvent(Event gertaera) {
		dbManager.open(false);
		Event e= dbManager.deleteEvent(gertaera);
		dbManager.close();
		return e;
	}
	
	@WebMethod
	public Boolean apustuaEgin(Pronostikoa pronostikoa, double diruKop, String username) {
		dbManager.open(false);
		Boolean apo= dbManager.apustuaEgin(pronostikoa, diruKop, username);
		dbManager.close();
		return apo;
	}
	
	@WebMethod
	public boolean diruaSartu(double diruKop, String username) {
		dbManager.open(false);
		boolean em= dbManager.diruaSartu(diruKop, username);
		dbManager.close();
		return em;
	}

	@WebMethod
	public String getUser() {
		return user;
   	}
   
	@WebMethod
    public void setUser(String user) {
    	dbManager.open(false);
    	BLFacadeImplementation.user = user;
    	dbManager.close();
    }
	
	@WebMethod
	public List<Pronostikoa> getPronostikoak (Question galdera){
		dbManager.open(false);
		List<Pronostikoa> pronostikoak= dbManager.getPronostikoak(galdera);
		dbManager.close();
		return pronostikoak;
	}
	
	@WebMethod
	public Bezero getPertsona(String username) {
		dbManager.open(false);
		Bezero b= dbManager.getPertsona(username);
		dbManager.close();
		return b;
	}
	@WebMethod
	public Question deleteQuestion(Question galdera){
	    		dbManager.open(false);
        		Question q= dbManager.deleteQuestion(galdera);
        		dbManager.close();
        		return q;
	}
	
	@WebMethod
	public Boolean apostuAnitzaEgin(List<Pronostikoa> pronostikoak, double diruKop, String username) {
		dbManager.open(false);
		Apostua apo= dbManager.apostuAnitzaEgin(pronostikoak, diruKop, username);
		dbManager.close();
		return true;
	}

	@WebMethod
	public List<Pertsona> getBezeroak(){
		dbManager.open(false);
		List<Pertsona> bezeroak = dbManager.getBezeroak();
		dbManager.close();
		return bezeroak;
	}
	
	@WebMethod
	public void kopiatu(Bezero b1, Bezero b2){
		dbManager.open(false);
		dbManager.kopiatu(b1, b2);
		dbManager.close();
	}
	
	@WebMethod
	public List<Pronostikoa> getPronostikoGuztiak(){
		dbManager.open(false);
		List<Pronostikoa> pronostikoak= dbManager.getPronostikoGuztiak();
		dbManager.close();
		return pronostikoak;
	}

	@WebMethod
	public List<Double> getMugimenduak(String user){
		dbManager.open(false);
		List<Double> mugimenduak = dbManager.getMugimenduak(user);
		dbManager.close();
		return mugimenduak;
	}
}
