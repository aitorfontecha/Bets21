package domain;

import java.util.ArrayList;
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
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Vector<Apostua> apostuak;
	private Vector<Bezero> kopioiak;
	private Vector<Double> mugimenduak;
	
	

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
		this.kopioiak = new Vector<Bezero>();
		this.mugimenduak = new Vector<Double>();

	}
	

	
	public void addApostua(Apostua apostua){
		this.apostuak.add(apostua);
	}
	
	public void addMugimendua(double mugimendua) {
		this.mugimenduak.add(mugimendua);
	}
	
	public Vector<Double> getMugimenduak(){
		return this.mugimenduak;
	}
	
	public void addKopioia(Bezero b) {
		this.kopioiak.add(b);
	}

	public Vector<Bezero> getKopioiak(){
		return this.kopioiak;
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
		this.mugimenduak.add(diru);
	}

}
