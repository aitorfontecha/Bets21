package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Pronostikoa> pronostikoak;
    @OneToOne(cascade=CascadeType.ALL)
    @XmlIDREF
    Bezero bezeroa;
    List<Boolean> emaitzak;
    boolean kopiatuta;
    Bezero noriKopiatuta;

    
    public Apostua() {
    	super();
    }
    public Apostua(double apustuDiru,Bezero bezeroa) {
        this.apustuDiru = apustuDiru;
        this.bezeroa = bezeroa;
		this.pronostikoak = new Vector<Pronostikoa>();
		this.emaitzak = new Vector<Boolean>();
		this.kopiatuta = false;
		
    }


    public Vector<Pronostikoa> getPronostikoa() {
        return (Vector<Pronostikoa>) this.pronostikoak;
    }

    public void addPronostikoa(Pronostikoa pronostikoa) {
        this.pronostikoak.add(pronostikoa);
        this.emaitzak.add(false);
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

    public void setEmaitzak(boolean bool){
        this.emaitzak.set(this.emaitzak.indexOf(false),bool);
    }
    public Vector<Boolean> getEmaitzak(){
        return (Vector<Boolean>) this.emaitzak;
    }
    public void setKopiatuta(Bezero b) {
    	this.kopiatuta=true;
    	this.noriKopiatuta=b;
    }
	public boolean getKopiatuta() {
		return kopiatuta;
	}
	
	public String toString() {
		return this.idApustu + ";" + this.pronostikoak.toString() + ";" ;
	}
    
    
}
