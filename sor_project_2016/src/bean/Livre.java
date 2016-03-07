package bean;

import annotation.NonVide;
import annotation.Table;

@Table(name="t_livre")
public class Livre {

	Integer idLivre;
	
	@NonVide(mess="Veuille saisir le titre du livre")
	String titre;
	
	@NonVide(mess="Veuille saisir l'auteur du livre")
	String auteur;
	
	Integer annee;
	
	// création des accesseurs
	// clic droit 
	// Source Generate / Getters setters
	// last member
	
	public Integer getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(Integer idLivre) {
		this.idLivre = idLivre;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Integer getAnnee() {
		return annee;
	}
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	
}
