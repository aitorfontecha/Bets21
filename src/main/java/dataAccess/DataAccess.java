package dataAccess;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Administratzaile;
import domain.Bezero;
import domain.Event;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import domain.Apostua;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
		+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			// admin sortu
			Administratzaile admin1 = new Administratzaile("admin", "admin");

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

			// admina datubasera gehitu
			db.persist(admin1);

			db.getTransaction().commit();
			System.out.println("Db initialized");

			this.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
		// property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * Datu basean dauden erabiltzaile guztiak inprimitzen ditu
	 */
	public void getAllUsers() {
		TypedQuery<Pertsona> query = db.createQuery("SELECT p FROM Pertsona p", Pertsona.class);
		List<Pertsona> pertsonak = query.getResultList();
		System.out.println("Datu basearen edukia");
		for (Pertsona p : pertsonak) {
			System.out.println(p.getErabiltzailea() + "   " + p.getPasahitza());
		}
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
		+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	/**
	 * Gertaera bat data berdinean existitzen den ala ez aztertu
	 * 
	 * @param date,     aztertu nahi den eguna
	 * @param gertaera, aztertu nahi den gertaera
	 * @return
	 */
	public boolean existEvent(Date date, Event gertaera) {
		List<Event> gertaerak = this.getEvents(date);
		for (Event ev : gertaerak) {
			if (ev.getDescription().equalsIgnoreCase(gertaera.getDescription())) {
				return true;
			}
		}
		return false;
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	/**
	 * Pertsona baten logina egiten du, datuak ziurtatuz
	 * 
	 * @param username, erabiltzaile izena
	 * @param password, pasahitza
	 * @return pertsona datuak zuzenak badira, null bestela
	 */
	public Pertsona isLogin(String username, String password) {
		System.out.println(db.isOpen());
		Pertsona p = db.find(Pertsona.class, username); // datu basean pertsona bilatu
		if (p != null) {
			if (p.getPasahitza().equals(password)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Bezero bat erregistratzen du
	 * 
	 * @param username,        bezeroaren erabiltzaile-izena
	 * @param pass1,           bezeroaren pasahitza
	 * @param pass2,           bezeroaren pasahitz errepikatua
	 * @param jaiotzeData,     bezeroaren jaiotze data
	 * @param kredituTxartela, bezeroaren kreditu txartel zenbakia (16 digitu)
	 * @return true ondo erregistratu bada, false bestela
	 */
	public boolean storeGuest(String username, String pass1, String pass2, Date jaiotzeData, String kredituTxartela) {
		Bezero bezero1 = new Bezero(username, pass1, kredituTxartela, jaiotzeData);
		boolean aurkitua = false;
		if (pass1.equals(pass2)) { // Pasahitza ondo errepikatu behar da.
			Pertsona p = db.find(Pertsona.class, username);
			if (p == null) { // Erregistratu nahi den erabiltzailea oraindik ez da existitzen.
				Date gaurkoa = new Date();
				String formato = "yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
				int unekoUrtea = Integer.parseInt(dateFormat.format(gaurkoa));
				int jaiotzeUrtea = Integer.parseInt(dateFormat.format(jaiotzeData));
				if (unekoUrtea - jaiotzeUrtea >= 18 && kredituTxartela.length() == 16) { // 18 urte baino gehiago eta
					// txartela zebakia egokiak
					// izan behar dira.
					db.getTransaction().begin();
					db.persist(bezero1);
					db.getTransaction().commit();
					System.out.println("Gordeta " + username);
					aurkitua = true;
				} else if (unekoUrtea - jaiotzeUrtea < 18) {
					System.out.println("Ez dituzu 18 urte");
				} else if (kredituTxartela.length() != 16) {
					System.out.println("Kreditu txartelaren zenbakia ez da zuzena.");
				}
			} else {
				System.out.println("Dagoeneko erabiltzailea existitzen da.");
			}
		} else {
			System.out.println("Idatzitako bi pasahitzak ezberdinak");
		}

		return aurkitua;
	}

	/**
	 * Gertaera bat sortzen du
	 * 
	 * @param gertaera, txertatu nahi den gertaera
	 */
	public boolean storeEvent(Event gertaera) {

		db.getTransaction().begin();

		if (!db.contains(gertaera)) {
			db.persist(gertaera);
			System.out.println("Gordeta " + gertaera);
		} else {
			System.out.println("Dagoeneko gertaera gordeta dago! ");
		}

		db.getTransaction().commit();
		return true;
	}

	public Pronostikoa pronostikoaIpini(Question galdera, Pronostikoa p) {
		int code = galdera.getQuestionNumber();
		db.getTransaction().begin();
		Question galdera1 = db.find(Question.class, code);
		galdera1.pronostikoaGehitu(p);
		db.getTransaction().commit();
		galdera1.inprimatuPronostikoak();
		return p;
	}

	public boolean emaitzaIpini(Question galdera, Pronostikoa pr) {
		db.getTransaction().begin();
		int code = galdera.getQuestionNumber();
		Question galdera1 = db.find(Question.class, code);
		Pronostikoa pronostikoa1= db.find(Pronostikoa.class, pr.getIdPronostikoa());
		galdera1.emaitzaIpini(pr);
		for (Apostua apostua: pronostikoa1.getApostuak() ){
			if(!apostua.getKopiatuta()) {
			apostua.setEmaitzak(true);
			Vector<Boolean> emaitzak=apostua.getEmaitzak();
			double kuota=0;
			if (!emaitzak.contains(false)){
				Vector<Pronostikoa> p = apostua.getPronostikoa();
				for (Pronostikoa pi : p){
					kuota += pi.getKuota();
				}
				double irabaz=0;
				irabaz= apostua.getApustuDiru() * kuota;
				Bezero b= apostua.getBezeroa();
				String code2= b.getErabiltzailea();
				Bezero bezero = db.find(Bezero.class, code2);
				bezero.diruaSartu(irabaz);
				Vector<Bezero> kopioiak = bezero.getKopioiak();
				if(!kopioiak.isEmpty()) {
					for(Bezero bk: kopioiak) {
						double irabazDeskontu = irabaz*0.1;
						bk.diruaSartu(irabaz-irabazDeskontu);
						b.diruaSartu(irabazDeskontu);
					}
					
				}
			}
			
			
			}

		}

		db.getTransaction().commit();
		galdera1.inprimatuEmaitza();
		return true;
	}


	public Apostua apustuaEgin(Pronostikoa pronostikoa, double diruKop, String username) {	
		db.getTransaction().begin();
		Bezero bezero = db.find(Bezero.class, username);
		if (bezero.getDiruKop()>=diruKop) {
			Apostua apostu = new Apostua(diruKop, bezero);
			int id = pronostikoa.getIdPronostikoa();
			Pronostikoa pronostikoa1 = db.find(Pronostikoa.class, id);
			pronostikoa1.addApostua(apostu);
			bezero.addApostua(apostu);
			bezero.addMugimendua(-diruKop);
			apostu.addPronostikoa(pronostikoa1);
			bezero.setDiruKop(bezero.getDiruKop()-diruKop);
			
			Vector<Bezero> kopioiak= bezero.getKopioiak();
			if(kopioiak != null) {
				for(Bezero b : kopioiak) {
					if (b.getDiruKop()>=diruKop) {
					Apostua apostu2 = new Apostua(diruKop, b);
					apostu2.setKopiatuta(bezero);
					apostu2.addPronostikoa(pronostikoa1);
					pronostikoa1.addApostua(apostu2);
					b.addApostua(apostu2);
					b.addMugimendua(-diruKop);
					b.setDiruKop(b.getDiruKop()-diruKop);
					}
				}
				
			}
			db.getTransaction().commit();
			pronostikoa1.inprimatuApostuak();
			return apostu;
		}
		else {
			System.out.println("Ez daukazu dirurik");
			return null;
		}
		
	}


	public boolean diruaSartu (double diruKop, String username){

		if (diruKop>=1) {
			db.getTransaction().begin();
			Bezero bezero = db.find(Bezero.class, username); 
			bezero.diruaSartu(diruKop);
			db.getTransaction().commit();
			return true;
		} else {
			System.out.println("Ez da heltzen diru kopuru minimora");
			return false;
		}

	}

	
	
	public Event deleteEvent(Event gertaera) {	
		db.getTransaction().begin();
		db.remove(db.contains(gertaera) ? gertaera : db.merge(gertaera));
		db.getTransaction().commit();
		return gertaera;
	}
	
	public Question deleteQuestion(Question galdera) {
    		db.getTransaction().begin();
    		db.remove(db.contains(galdera) ? galdera : db.merge(galdera));
    		db.getTransaction().commit();
    		return galdera;
    	}
	
	public List<Pronostikoa> getPronostikoak (Question galdera){
		int code = galdera.getQuestionNumber();
		db.getTransaction().begin();
		Question q= db.find(Question.class, code);
		db.getTransaction().commit();
		List<Pronostikoa> pronostikoak = q.getPronostikoak();
		return pronostikoak;
	}
		
	public Bezero getPertsona(String username) {
		db.getTransaction().begin();
		Bezero p= db.find(Bezero.class, username);
		db.getTransaction().commit();
		return p;  
	}


	public Apostua apostuAnitzaEgin(List<Pronostikoa> pronostikoak, double diruKop, String username) {	
		db.getTransaction().begin();
		Bezero bezero = db.find(Bezero.class, username);
		if (bezero.getDiruKop()>=diruKop) {
			Apostua apostuAnitza = new Apostua(diruKop, bezero);
			for (int i=0; i< pronostikoak.size(); i++) {
				int  unekoa = pronostikoak.get(i).getIdPronostikoa();
				Pronostikoa unekoPronostikoa = db.find(Pronostikoa.class, unekoa);
				unekoPronostikoa.addApostua(apostuAnitza);
				apostuAnitza.addPronostikoa(unekoPronostikoa);
			}
			bezero.addApostua(apostuAnitza);
			bezero.addMugimendua(-diruKop);
			bezero.setDiruKop(bezero.getDiruKop()-diruKop);
			db.persist(apostuAnitza);
			Vector<Bezero> kopioiak= bezero.getKopioiak();
			if(kopioiak != null) {
				for(Bezero b : kopioiak) {
					if (b.getDiruKop()>=diruKop) {
						Apostua apostuAnitza2 = new Apostua(diruKop, bezero);
						b.addMugimendua(-diruKop);
						for (int i=0; i< pronostikoak.size(); i++) {
							int  unekoa2 = pronostikoak.get(i).getIdPronostikoa();
							Pronostikoa unekoPronostikoa2 = db.find(Pronostikoa.class, unekoa2);
							unekoPronostikoa2.addApostua(apostuAnitza2);
							apostuAnitza2.addPronostikoa(unekoPronostikoa2);
						}
				}
				
			}
			}
			db.getTransaction().commit();
			return apostuAnitza;
		}
		else {
			System.out.println("Ez daukazu dirurik");
			return null;
		}

		
	}

	public List<Pertsona> getBezeroak(){
		db.getTransaction().begin();
		TypedQuery<Pertsona> query = db.createQuery("SELECT p FROM Pertsona p", Pertsona.class);
		List<Pertsona> pertsonak = query.getResultList();
		return  pertsonak;
	}
	
	public void kopiatu(Bezero kopiatzekoPertsona, Bezero kopioia){
		db.getTransaction().begin();
		Bezero kopioiaDB= db.find(Bezero.class, kopioia.getErabiltzailea());
		Bezero kopiatzekoaDB= db.find(Bezero.class, kopiatzekoPertsona.getErabiltzailea());
		
		kopiatzekoaDB.addKopioia(kopioiaDB);
		db.getTransaction().commit();
	}
	
	public List<Pronostikoa> getPronostikoGuztiak(){
		TypedQuery<Pronostikoa> query = db.createQuery("SELECT a FROM Pronostikoa a", Pronostikoa.class);
		List<Pronostikoa> pronostikoak= query.getResultList();
		return pronostikoak;
	}

	public List<Double> getMugimenduak(String user){
		db.getTransaction().begin();
		Bezero b = db.find(Bezero.class,user);
		List<Double> mugi=b.getMugimenduak();
		return mugi;
	}
}
