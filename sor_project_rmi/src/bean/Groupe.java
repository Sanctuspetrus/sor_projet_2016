package bean;

import java.io.Serializable;

public class Groupe implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idGroupe;
	private String nom;
	private String description;
	
	
	public Groupe(int idGroupe, String nom, String description) {
		this.idGroupe = idGroupe;
		this.nom = nom;
		this.description = description;
	}
	public Groupe(String nom, String description) {
		this.idGroupe = -1;
		this.nom = nom;
		this.description = description;
	}
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
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
	@Override
	public String toString() {
		return "Groupe [nom=" + nom + ", description=" + description + "]";
	}
	
	
}
