package bean;

import java.io.Serializable;
import java.sql.Date;

//import annotation.NonVide;
import annotation.Table;

@Table(name="t_livre")
public class Billet implements Serializable{

	private Integer idBillet;
		
	private String jourValid;
	
	// création des accesseurs
	// clic droit 
	// Source Generate / Getters setters
	// last member
	
	public Integer getIdBillet() {
		return idBillet;
	}
	public void setIdBillet(Integer idBillet) {
		this.idBillet = idBillet;
	}
	
	public String getJourValid() {
		return jourValid;
	}
	public void setJourValid(String date) {
		this.jourValid = date;
	}
	@Override
	public String toString() {
		return "Billet [idBillet=" + idBillet + ", jourValid=" + jourValid + "]";
	}

	
	
}
