package manager;

public class Manager {

	// classe pour la création
	// d'un objet persistant
	
	boolean identifie = false;
	String code = "";

	public boolean isIdentifie() {
		return identifie;
	}

	public void setIdentifie(boolean identifie) {
		this.identifie = identifie;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String c) {
		this.code = c;
	}
}
