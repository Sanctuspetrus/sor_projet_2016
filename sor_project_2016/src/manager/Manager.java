package manager;

public class Manager {

	// classe pour la création
	// d'un objet persistant
	
	boolean identifie = false;
	String nom = "";

	public boolean isIdentifie() {
		return identifie;
	}

	public void setIdentifie(boolean identifie) {
		this.identifie = identifie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
