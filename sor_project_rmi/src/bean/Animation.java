package bean;

import java.awt.image.BufferedImageFilter;

public class Animation {
	private String nom;
	private String description;
	private BufferedImageFilter photo;
	private int duree;
	private int nbPlaces;
	private Groupe groupe;
	
	
	
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
	public BufferedImageFilter getPhoto() {
		return photo;
	}
	public void setPhoto(BufferedImageFilter photo) {
		this.photo = photo;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	@Override
	public String toString() {
		return "Animation [nom=" + nom + ", description=" + description + ", photo=" + photo + ", duree=" + duree + ", nbPlaces=" + nbPlaces + ", groupe=" + groupe + "]";
	}
	
	
}
