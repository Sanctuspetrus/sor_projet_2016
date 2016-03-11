package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Animation;
import bean.Billet;
import bean.DateAnimation;
import bean.Groupe;
import bean.Reservation;
import rmi.ServeurRMI;

public class Manager {

	private static ServeurRMI srmi;
	
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
	
	
	public ArrayList<Reservation> listeReservation() throws RemoteException{
		System.out.println("Invocation listeReservation pour " + code);
		this.connectToServer();
		ArrayList<Reservation> res = srmi.liste_reservations(Integer.parseInt(code));
		return res;
	}
	
	public ArrayList<Groupe> listeGroupe() throws RemoteException{
		this.connectToServer();
		ArrayList<Groupe> res = srmi.liste_groupe();
		
		/*ArrayList<Groupe> res = new ArrayList<Groupe>();
		res.add(new Groupe("Grp1","Grp1"));*/
		
		System.out.println("Invocation listeGroupe pour " + code);
		return res;
	}
	
	public Groupe creer_Groupe(String nom, String desc) throws RemoteException{
		this.connectToServer();
		
		Groupe gr = new Groupe(nom, desc);
		gr = srmi.creer_groupe(gr);
		System.out.println("Invocation listeReservation pour " + code);
		return gr;
	}

	public ArrayList<Animation> listeAnimation() throws RemoteException{
		this.connectToServer();
		ArrayList<Animation> res = srmi.liste_animations();
		
		/*ArrayList<Animation> res = new ArrayList<Animation>();
		Groupe grp = new Groupe("Grp1","Grp1");
		Animation a = new Animation();
		a.setNom("anim1");
		a.setDescription("animation");
		a.setDuree(60);
		a.setNbPlacesDispo(23);
		a.setNbPlacesTotal(30);
		a.setGroupe(grp);
		res.add(a);
		
		Animation b = new Animation();
		b.setNom("anim2");
		b.setDescription("animation 2");
		b.setDuree(120);
		b.setNbPlacesDispo(3);
		b.setNbPlacesTotal(10);
		b.setGroupe(grp);
		res.add(b);*/
		
		System.out.println("Invocation listeAnimation pour " + code);
		return res;
	}
	
	public boolean creer_animation(Animation animation) throws RemoteException{
		this.connectToServer();

		boolean res = srmi.creer_animation(animation);
		System.out.println("Invocation listeReservation pour " + code);
		return res;
	}
	
	public Boolean creer_reservation(Reservation res) throws RemoteException{
		this.connectToServer();

		boolean res1 = srmi.creer_reservation(res);
		System.out.println("Invocation listeReservation pour " + code);
		return res1;
	}
	
	public Billet getBillet() throws RemoteException{
		this.connectToServer();
		Billet b = srmi.rechercher_billet(Integer.parseInt(code));
		
		System.out.println("Invocation getBillet pour " + code);
		return b;
	}
	
	public DateAnimation creer_dateAnimation(String idAnim, String b_date) throws RemoteException{
		this.connectToServer();
		DateAnimation da = new DateAnimation(Integer.parseInt(code), idAnim);
		da.setDate(b_date);
		boolean res = srmi.creer_dateAnimation(da);
		
		System.out.println("Invocation creer_dateAnimation pour " + code);
		return da;
	}
	
	public Groupe getGroupe(String idGroupe) throws RemoteException{
		this.connectToServer();
		Groupe grp = srmi.getGroupe(Integer.parseInt(idGroupe));
		
		System.out.println("Invocation getGroupe" +grp.getNom()+" pour " + code);
		return grp;
	}
	
 	private void connectToServer(){
		int port = 10000;
		
		try {
			Registry registry = LocateRegistry.getRegistry(port);
			
			srmi = (ServeurRMI)registry.lookup("serveurRMI");
			
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
	}
}
