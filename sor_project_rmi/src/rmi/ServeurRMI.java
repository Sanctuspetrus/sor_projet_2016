package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bean.*;


public interface ServeurRMI  extends Remote {
	
	public String confirmer_acces() throws RemoteException;
	
	public boolean creer_animation(Animation animation) throws RemoteException;

	boolean modifier_animation(Animation animation) throws RemoteException;
}
