package domain;

import javax.persistence.Entity;

@Entity
public class Administratzaile extends Pertsona {

	public Administratzaile(String erabiltzaileIzena, String pasahitza) {
		super(erabiltzaileIzena, pasahitza);
	}
}
