package test.dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jdo.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.*;

public class TestDataAccess {
	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public TestDataAccess() {

		System.out.println("Creating TestDataAccess instance");

		open();

	}

	public void open() {

		System.out.println("Opening TestDataAccess instance ");

		String fileName = c.getDbFilename();

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

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public boolean removeQuestion(Question qu) {
		Question q = db.find(Question.class, qu.getQuestionNumber());
		if (q != null) {
			db.getTransaction().begin();
			db.remove(q);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public boolean removePronostikoa(Pronostikoa pr) {
		Pronostikoa p =
				db.find(Pronostikoa.class, pr.getIdPronostikoa());
		if (p != null) {
			db.getTransaction().begin();
			db.remove(p);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}
	public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev = null;
		db.getTransaction().begin();
		try {
			ev = new Event(desc, d);
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}

	public boolean existQuestion(Event ev, Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else
			return false;

	}

	public Pronostikoa addPronostikoa() {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		db.getTransaction().begin();
		try {
			pron = new Pronostikoa();
			db.persist(pron);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pron;
	}

	public void addPronostikoa(Pronostikoa pr) {
		System.out.println(">> DataAccessTest: addPronostikoa");
		db.getTransaction().begin();
		try {
			db.persist(pr);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pronostikoa addPronostikoaWithApustuak() {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		db.getTransaction().begin();
		try {
			pron = new Pronostikoa();
			Apostua ap = new Apostua();;
			pron.addApostua(ap);
			db.persist(pron);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pron;
	}

	public void ApustuaIrabazi(Apostua ap) {
		Apostua apostua = db.find(Apostua.class, ap.getIdApustu());
		Bezero b = (Bezero) apostua.getBezeroa();
		Bezero r = (Bezero) db.find(Bezero.class, b.getErabiltzailea());
		db.getTransaction().begin();
		apostua.setEmaitzak(true);
		Double d=apostua.getApustuDiru();
		for(Apostua apos: apostua.getBezeroa().getApostuak()) {
			for(Pronostikoa pron: apos.getPronostikoa()) {
				d = d* pron.getKuota();
				}
			}
		r.setDiruKop(d);
		r.addMugimendua(r.getDiruKop()+d);
		db.persist(r);
		db.getTransaction().commit();
	}

	public void addEventToDB(Event ev) {
		db.getTransaction().begin();
		db.persist(ev);
		db.getTransaction().commit();
	}

	public void addQuestionToDB(Question qu) {
		db.getTransaction().begin();
		db.persist(qu);
		db.getTransaction().commit();
	}

	public Pertsona addUser(Pertsona usr) {
		System.out.println(">> DataAccessTest: addUser");
		db.getTransaction().begin();
		db.persist(usr);
		db.getTransaction().commit();
		return usr;
	}

	public boolean removeUser(Pertsona user) {
		System.out.println(">> DataAccessTest: removeEvent");
		Pertsona usr = db.find(Pertsona.class, user.getErabiltzailea());
		if (usr != null) {
			db.getTransaction().begin();
			db.remove(usr);
			db.getTransaction().commit();
			return true;
		}
		return false;
	}

	public Pertsona getUser(Pertsona user) {
		System.out.println(">> DataAccessTest: removeEvent");
		Pertsona usr = db.find(Pertsona.class, user.getErabiltzailea());
		if (usr != null) {
			return usr;
		}
		return null;
	}
}