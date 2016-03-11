package manager;

public class Manager {

	// classe pour la création
	// d'un objet persistant
	private boolean admin = false;
	private boolean identifie = false;
	private String code = "";

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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}
