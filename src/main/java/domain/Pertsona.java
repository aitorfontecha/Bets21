package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public abstract class Pertsona implements Serializable{
	@Id
	@XmlID
	String erabiltzaileIzena;
	String pasahitza;

	/**
	 * Pertsona objektuaren eraikitzailea
	 * 
	 * @param erabiltzaileIzena
	 * @param pasahitza
	 */
	public Pertsona(String erabiltzaileIzena, String pasahitza) {
		this.erabiltzaileIzena = erabiltzaileIzena;
		this.pasahitza = pasahitza;
	}

	/**
	 * Pasahitzaren geterra
	 * 
	 * @return pasahitza
	 */
	public String getPasahitza() {
		return this.pasahitza;
	}

	/**
	 * Erabiltzaile-izenaren geterra
	 * 
	 * @return erabiltzailea
	 */
	public String getErabiltzailea() {
		return erabiltzaileIzena;
	}

}
