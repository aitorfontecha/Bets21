package domain;

import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Bezero extends Pertsona {
	private String kredituTxartela;
	private Date jaiotzeData;
	private double diruKop;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Apostua> apostuak;

	/**
	 * Bezero objektuaren eraikitzailea
	 *
	 * @param erabiltzaileIzena
	 * @param pasahitza
	 * @param kredituTxartela
	 * @param jaiotzeData
	 */
	public Bezero(String erabiltzaileIzena, String pasahitza, String kredituTxartela, Date jaiotzeData) {
		super(erabiltzaileIzena, pasahitza);
		this.kredituTxartela = kredituTxartela;
		this.jaiotzeData = jaiotzeData;
		this.diruKop=0;
		this.apostuak= new Vector<Apostua>();
	}
	

	public void addApostua(Apostua apostua){
		this.apostuak.add(apostua);
	}


	public String getKredituTxartela() {
		return kredituTxartela;
	}


	public void setKredituTxartela(String kredituTxartela) {
		this.kredituTxartela = kredituTxartela;
	}


	public Date getJaiotzeData() {
		return jaiotzeData;
	}


	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}


	public double getDiruKop() {
		return diruKop;
	}


	public void setDiruKop(double diruKop) {
		this.diruKop = diruKop;
	}


	public Vector<Apostua> getApostuak() {
		return apostuak;
	}

/*
	public void setApostuak(Vector<Apostua> apostuak) {
		this.apostuak = apostuak;
	}
	*/
	
	public void diruaSartu(double diru) {
		this.diruKop=this.diruKop+diru;
	}

}
