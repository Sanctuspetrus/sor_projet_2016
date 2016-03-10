package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import bean.*;


public interface ServeurRMI  extends Remote {
	
	public String confirmer_acces() throws RemoteException;
	
	public boolean creer_animation(Animation animation) throws RemoteException;

	public boolean modifier_animation(Animation animation) throws RemoteException;
	
	public boolean supprimer_animation(Animation animation) throws RemoteException;
	
	public ArrayList<Reservation> liste_animations() throws RemoteException;
		
	public ArrayList<Reservation> liste_reservations(int code) throws RemoteException;
	
	public Reservation creer_reservation(Reservation res) throws RemoteException;
	
	public Reservation supprimer_reservation(Reservation res) throws RemoteException;
	
	public Groupe creer_groupe(Groupe grp) throws RemoteException;
	
	public boolean supprimer_groupe(Groupe grp) throws RemoteException;
	
	public Billet rechercher_billet(int code) throws RemoteException;
	
}
