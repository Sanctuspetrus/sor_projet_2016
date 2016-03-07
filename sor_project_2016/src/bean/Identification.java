package bean;

import annotation.NonVide;
import annotation.Table;

public class Identification {

	@NonVide(mess="Veuillez saisir un identifiant")
	String ident;

	@NonVide(mess="Veuillez saisir un mot de passe")
	String  mdp;
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}
