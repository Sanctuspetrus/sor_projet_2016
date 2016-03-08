package bean;

import java.sql.Date;

//import annotation.NonVide;
import annotation.Table;

@Table(name="t_livre")
public class Billet {

	Integer idBillet;
		
	Date jourValid;
	
	// création des accesseurs
	// clic droit 
	// Source Generate / Getters setters
	// last member
	
	public Integer getIdBillet() {
		return idBillet;
	}
	public void setIdLivre(Integer idBillet) {
		this.idBillet = idBillet;
	}
	
	public Date getJourValid() {
		return jourValid;
	}
	public void setJourValid(Date jourValid) {
		this.jourValid = jourValid;
	}

	
}
