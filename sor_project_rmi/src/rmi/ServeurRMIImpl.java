package rmi;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import base.*;
import bean.*;

public class ServeurRMIImpl	implements ServeurRMI {
	
	ArrayList<Animation> listAnim = new ArrayList<Animation>();
	ArrayList<Groupe> listGroupe = new ArrayList<Groupe>();
	ArrayList<DateAnimation> listDateAnim = new ArrayList<DateAnimation>();
	ArrayList<Reservation> listReserv = new ArrayList<Reservation>();
	ArrayList<Billet> listBillet = new ArrayList<Billet>();
	
	Base base = new Base();
	
	@Override
	public String confirmer_acces() throws RemoteException {
		System.out.println("Client => Demande de confirmation d'accès au serveur");
		return "Serveur => Accès confirmé";
	}
	
	@Override
	public boolean creer_animation(Animation animation) throws RemoteException {
		base.ouvrir();
		if (base.ajouter_animation(animation)){
			base.fermer();	
			return true;
		}else{base.fermer();return false;}
	}
	
	@Override
	public boolean modifier_animation(Animation animation) throws RemoteException{
		try{
			base.ouvrir();
			base.modifier_animation(animation);
			base.fermer();
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean supprimer_animation(Animation animation) throws RemoteException{
		try{
			base.ouvrir();
			base.supprimer_animation(animation);
			base.fermer();
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return false;
		}
	}
	
	@Override
	public Groupe creer_groupe(Groupe grp) throws RemoteException{
		try{
			base.ouvrir();
			base.ajouter_groupe(grp);
			Groupe res = base.getGroupe_by_nom(grp.getNom()).get(0);
			base.fermer();
			return res;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return null;
		}
	}
	
	@Override
	public boolean supprimer_groupe(Groupe grp) throws RemoteException{
		try{
			base.ouvrir();
			base.supprimer_groupe(grp);
			base.fermer();
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return false;
		}
	}
	
	@Override
	public Billet rechercher_billet(int code) throws RemoteException{
		try{
			base.ouvrir();
			Billet res = base.getBillet(code);
			base.fermer();
			return res;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return null;
		}
	}
	
	@Override
	public ArrayList<Groupe> liste_groupe() throws RemoteException {
		try{
			base.ouvrir();
			this.listGroupe = base.getListGroupe();
			base.fermer();
			return listGroupe;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return null;
		}
	}
	
	@Override
	public ArrayList<Billet> liste_billet() throws RemoteException {
		try{
			base.ouvrir();
			this.listBillet = base.getListBillet();
			base.fermer();
			return listBillet;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return null;
		}
	}
		
	public static void main(String [] args) {
		
		int port = 10000;
		
		Registry registry = null;
		
		// création registry
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur RMI createRegistry "+e.getMessage());
		}
		
		ServeurRMIImpl srmii = new ServeurRMIImpl();
		ServeurRMI srmi = null;
		
		// création objet distant
		try {
			srmi = (ServeurRMI) UnicastRemoteObject.
					exportObject(srmii,0);
		} catch (RemoteException e) {
			System.out.println("Erreur RMI exportObject "+e.getMessage());
		}
		
		// enregistrement objet distant
		
		try {
			registry.rebind("serveurRMI", srmi);
		} catch (Exception e) {
			System.out.println("Erreur RMI rebind "+
					e.getMessage());			
		}
		
		System.out.println("Serveur RMI lancé");		
		
	}

	@Override
	public ArrayList<Animation> liste_animations() throws RemoteException {
		try{
			base.ouvrir();			
			this.listAnim = base.getListAnimation();
			for (int i=0; i<listAnim.size();i++){
				for (int j=0; j<listGroupe.size();j++){
					if (listGroupe.get(j).getIdGroupe() == listAnim.get(i).getIdGroupe()){
						listAnim.get(i).setGroupe(listGroupe.get(j));
					}
				}
			}
			base.fermer();
			return listAnim;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Reservation> liste_reservations(int code) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation creer_reservation(Reservation res) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation supprimer_reservation(Reservation res) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public DateAnimation creer_dateAnimation(DateAnimation da) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}	


}
