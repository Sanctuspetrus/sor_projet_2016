package bean;

import java.io.Serializable;
import java.util.Date;

public class Animation implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idAnimation;
	private String nom;
	private String description;
	private String photo;
	private int duree;
	private int nbPlacesDispo;
	private int nbPlacesTotal;
	private int idGroupe;
	private Groupe groupe;	
	
	public Animation(int idAnimation, String nom, String description, String photo, int duree, int nbPlacesDispo, int nbPlacesTotal, int idGroupe) {
		this.idAnimation = idAnimation;
		this.nom = nom;
		this.description = description;
		this.photo = photo;
		this.duree = duree;
		this.nbPlacesDispo = nbPlacesDispo;
		this.nbPlacesTotal = nbPlacesTotal;
		this.idGroupe = idGroupe;
	}
	
	public Animation(String nom, String description, String photo, int duree, int nbPlacesDispo, int nbPlacesTotal, int idGroupe) {
		this.idAnimation = -1;
		this.nom = nom;
		this.description = description;
		this.photo = photo;
		this.duree = duree;
		this.nbPlacesDispo = nbPlacesDispo;
		this.nbPlacesTotal = nbPlacesTotal;
		this.idGroupe = idGroupe;
	}
	
	public Animation(){
		this.idAnimation = -1;
		this.nom = "";
		this.description = "";
		this.photo = "";
		this.duree = -1;
		this.nbPlacesDispo = -1;
		this.nbPlacesTotal = -1;
		this.idGroupe = -1;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	@Override
	public String toString() {
		return "Animation [nom=" + nom + ", description=" + description + ", photo=" + photo + ", duree=" + duree + ", nbPlacesDispo=" + nbPlacesDispo +", nbPlacesTotal=" + nbPlacesTotal + ", groupe=" + groupe.getIdGroupe() + "]";
	}	
	
}
