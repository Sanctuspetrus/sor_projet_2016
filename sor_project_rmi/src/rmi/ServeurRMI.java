package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServeurRMI  extends Remote {
	
	public String confirmer_acces() throws RemoteException;
	
	public boolean creer_animation(String nom, String desc, String photo, int duree, int nbPlaces, int idGroupe) throws RemoteException;

	boolean modifier_animation(int idAnimation) throws RemoteException;
}
