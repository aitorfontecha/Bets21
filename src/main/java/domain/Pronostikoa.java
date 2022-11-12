package domain;
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

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pronostikoa implements Serializable {
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
    private Integer idPronostikoa;
    private String aukera;
    private double kuota;
    @XmlIDREF
    @OneToOne
    private Question galdera;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Apostua> apostuak;

    
    public Pronostikoa() {
    	super();
    }
    public Pronostikoa(String aukera,double kuota){
    	System.out.println("Pronostikoa sortuta.");
        this.aukera=aukera;
        this.kuota=kuota;
        this.apostuak=new Vector<Apostua>();
        //this.apostuAnitzak= new Vector<ApostuAnitza>();
    }

    public Question getGaldera() {
    	return galdera;
    }
    public Integer getIdPronostikoa() {
        return idPronostikoa;
    }

    public void setIdPronostikoa(Integer idPronostikoa) {
        this.idPronostikoa = idPronostikoa;
    }

    public String getAukera() {
        return aukera;
    }

    public void setAukera(String aukera) {
        this.aukera = aukera;
    }

    public double getKuota() {
        return kuota;
    }

    public void setKuota(Integer kuota) {
        this.kuota = kuota;
    }

    public void addApostua(Apostua apostua){
        this.apostuak.add(apostua);
    }
    

    public Vector<Apostua> getApostuak() {
        return (Vector<Apostua>) apostuak;
    }
    
    public void inprimatuApostuak() {
    	System.out.println("Hauek dira apostuak:");
		for (Apostua p:this.apostuak) {
			System.out.println(p.getIdApustu());
		}
    }
    
    public String toString() {
		return this.idPronostikoa + ";" + this.aukera + ";" + Double.toString(this.kuota);
	}

}