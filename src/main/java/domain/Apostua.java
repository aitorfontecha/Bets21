package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apostua implements Serializable{
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
    Integer idApustu;
    double apustuDiru;
    @XmlIDREF
    Pronostikoa pronostikoa;
    @OneToOne(cascade=CascadeType.ALL)
    @XmlIDREF
    Bezero bezeroa;

    
    public Apostua() {
    	super();
    }
    public Apostua(double apustuDiru,Bezero bezeroa, Pronostikoa pronostikoa) {
        this.apustuDiru = apustuDiru;
        this.bezeroa = bezeroa;
		this.pronostikoa = pronostikoa;
    }


    public Pronostikoa getPronostikoa() {
        return pronostikoa;
    }

    public void setPronostikoa(Pronostikoa pronostikoa) {
        this.pronostikoa = pronostikoa;
    }

    public Bezero getBezeroa() {
        return bezeroa;
    }

    public void setBezeroa(Bezero bezeroa) {
        this.bezeroa = bezeroa;
    }

    public Apostua(int id, double dirua ) {
        this.idApustu = id;
        this.apustuDiru = dirua;
    }

    public int getIdApustu() {
        return idApustu;
    }

    public void setIdApustu(int idApustu) {
        this.idApustu = idApustu;
    }

    public double getApustuDiru() {
        return apustuDiru;
    }

    public void setApustuDiru(double apustuDiru) {
        this.apustuDiru = apustuDiru;
    }
}
