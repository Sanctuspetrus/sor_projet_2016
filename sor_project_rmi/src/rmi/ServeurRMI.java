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
	
	public ArrayList<Animation> liste_animations() throws RemoteException;
		
	public ArrayList<Reservation> liste_reservations(int code) throws RemoteException;
	
	public boolean creer_reservation(Reservation res) throws RemoteException;
	
	public boolean supprimer_reservation(Reservation res) throws RemoteException;
	
	public Groupe creer_groupe(Groupe grp) throws RemoteException;
	
	public boolean supprimer_groupe(Groupe grp) throws RemoteException;
	
	public Billet rechercher_billet(int code) throws RemoteException;
	
	public ArrayList<Groupe> liste_groupe() throws RemoteException;

	public ArrayList<Billet> liste_billet() throws RemoteException;
	
	public int creer_dateAnimation(DateAnimation da) throws RemoteException;
	
	public Groupe getGroupe(int id) throws RemoteException;

	ArrayList<DateAnimation> liste_dateAnimations() throws RemoteException;

	boolean modifier_reservation(Reservation res) throws RemoteException;

	ArrayList<Reservation> liste_reservations_by_billet(Billet billet) throws RemoteException;

	ArrayList<Reservation> liste_reservations_by_dateAnimation(DateAnimation datanim) throws RemoteException;

	ArrayList<DateAnimation> liste_dateAnimations_by_animation(Animation anim) throws RemoteException;

	boolean supprimer_dateAnimation(DateAnimation da) throws RemoteException;

	boolean modifier_dateAnimatin(DateAnimation datanim) throws RemoteException;
	
}
