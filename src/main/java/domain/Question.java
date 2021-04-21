package domain;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
	private Integer questionNumber;
	private String question;
	private float betMinimum;
	private String result;
	@XmlIDREF
	private Event event;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Pronostikoa> pronostikoak;
	@OneToOne(orphanRemoval=true)
	private Pronostikoa emaitza;

	public Question() {
		super();
	}

	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum = betMinimum;
		this.event = event;
		this.emaitza= null;
		this.pronostikoak= new Vector<Pronostikoa>();
	}

	public Question(String query, float betMinimum, Event event) {
		super();
		this.question = query;
		this.betMinimum = betMinimum;
		this.pronostikoak= new Vector<Pronostikoa>();


	
	}

	/**
	 * Get the number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}

	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */

	public float getBetMinimum() {
		return betMinimum;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	/**
	 * Get the result of the query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Get the result of the query
	 * 
	 * @param result of the query to be setted
	 */

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString() {
		return questionNumber + ";" + question + ";" + Float.toString(betMinimum);
	}
	
	public void pronostikoaGehitu(Pronostikoa p) {

			this.pronostikoak.add(p);
		}
	
	public void emaitzaIpini(Pronostikoa p) {
		this.emaitza = p;
	}
	
	public void inprimatuPronostikoak() {
		System.out.println("Hauek dira pronostikoak:");
		for (Pronostikoa p:this.pronostikoak) {
			System.out.println(p.getAukera());
		}
	}
	
	public List<Pronostikoa> getPronostikoak(){
		return this.pronostikoak;
	}
	public void inprimatuEmaitza() {
		System.out.println("Hau da emaitza: " + this.emaitza.getAukera());
	}
	

}