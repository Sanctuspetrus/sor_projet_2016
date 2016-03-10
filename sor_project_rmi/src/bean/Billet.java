package bean;

import java.io.Serializable;
import java.sql.Date;

//import annotation.NonVide;
import annotation.Table;

@Table(name="t_livre")
public class Billet implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idBillet;		
	private String jourValid;
	
	public Billet(){
		idBillet = -1;		
	}
	
	public Billet(int idBillet, String jourValid){
		this.idBillet = idBillet;
		this.jourValid = jourValid;
	}
	
	public Billet(String jourValid){
		this.idBillet = -1;
		this.jourValid = jourValid;
	}
	
	public Integer getIdBillet() {
		return idBillet;
	}
	public void setIdBillet(Integer idBillet) {
		this.idBillet = idBillet;
	}
	
	public String getJourValid() {
		return jourValid;
	}
	public void setJourValid(String jourValid) {
		this.jourValid = jourValid;
	}
	@Override
	public String toString() {
		return "Billet [idBillet=" + idBillet + ", jourValid=" + jourValid + "]";
	}	
	
}
