package bean;

import java.io.Serializable;
import java.util.Date;

public class Animation implements Serializable{
	private int idAnimation;
	private String nom;
	private String description;
	private String photo;
	private String date;
	//private String date;
	private int duree;
	private int nbPlacesDispo;
	private int nbPlacesTotal;
	private int idGroupe;	
	
	public Animation(String idAnimation, String nom, String description, String photo, String date, String duree, String nbPlacesDispo, String nbPlacesTotal,
			String idGroupe) {
		this.idAnimation = Integer.parseInt(idAnimation);
		this.nom = nom;
		this.description = description;
		this.photo = photo;
		this.date=date;
		this.duree = Integer.parseInt(duree);
		this.nbPlacesDispo = Integer.parseInt(nbPlacesDispo);
		this.nbPlacesTotal = Integer.parseInt(nbPlacesTotal);
		this.idGroupe = Integer.parseInt(idGroupe);
	}
	
	public int getIdAnimation() {
		return idAnimation;
	}
	public void setIdAnimation(int idAnimation) {
		this.idAnimation = idAnimation;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDuree(String date) {
		this.date = date;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public int getNbPlacesDispo() {
		return nbPlacesDispo;
	}
	public void setNbPlacesDispo(int nbPlacesDispo) {
		this.nbPlacesDispo = nbPlacesDispo;
	}
	
	public int getNbPlacesTotal() {
		return nbPlacesTotal;
	}
	public void setNbPlacesTotal(int nbPlacesTotal) {
		this.nbPlacesTotal = nbPlacesTotal;
	}
	
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	@Override
	public String toString() {
		return "Animation [nom=" + nom + ", description=" + description + ", photo=" + photo + ", duree=" + duree + ", nbPlacesDispo=" + nbPlacesDispo +", nbPlacesTotal=" + nbPlacesTotal + ", groupe=" + idGroupe + "]";
	}	
	
}
