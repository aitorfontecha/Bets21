package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

//import domain.Booking;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import domain.*;


/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade {

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
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;

	/**
	 * This method retrieves the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public List<Event> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public List<Date> getEventsMonth(Date date);

	/**
	 * This method calls the data access to initialize the database with some events
	 * and questions. It is invoked only when the option "initialize" is declared in
	 * the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD();

	@WebMethod
	public Pertsona isLogin(String username, String pasahitza);

	@WebMethod
	public boolean storeGuest(String username, String pass1, String pass2, Date jaiotzeData, String kredituTxartela);

	@WebMethod
	public boolean existEvent(Date data, Event gertaera);

	@WebMethod
	public boolean storeEvent(Event gertaera);

	@WebMethod
	public List<Question> getQuestions(Event gertaera);

	@WebMethod
	public Pronostikoa pronostikoaIpini(Question galdera, Pronostikoa pronostikoa);

	@WebMethod
	public boolean emaitzaIpini(Question galdera, Pronostikoa pronostikoa);

	@WebMethod
	public Event deleteEvent(Event gertaera);

	@WebMethod
	public Apostua apustuaEgin(Pronostikoa pronostikoa, double diruKop, String username);

	@WebMethod
	public boolean diruaSartu(double diruKop, String username);

	@WebMethod
	public String getUser();

	@WebMethod
	public void setUser(String user);

	@WebMethod
	public List<Pronostikoa> getPronostikoak (Question galdera);

	@WebMethod
	public Bezero getPertsona(String username);

	@WebMethod
	public Question deleteQuestion(Question galdera);

}
