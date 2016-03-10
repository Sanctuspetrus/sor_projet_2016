package rmi;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import base.*;
import bean.*;


public class ServeurRMIImpl 
	implements ServeurRMI {
	
	Base base = new Base();
	
	@Override
	public String confirmer_acces() throws RemoteException {
		System.out.println("Client => Demande de confirmation d'accès au serveur");
		return "Serveur => Accès confirmé";
	}
	
	@Override
	public boolean creer_animation(Animation animation) throws RemoteException {
		if (base.ajouter_animation(animation)){
		return true;
		}else{return false;}
	}
	
	@Override
	public boolean modifier_animation(Animation animation) throws RemoteException{
		try{
			base.ouvrir();
			base.modifier_animation_nom(animation);
			base.fermer();
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
			return false;
		}
	}
	
	public static void main(String [] args) {
		
		int port = 10000;
		
		Registry registry = null;
		
		// crÃ©ation registry
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur RMI createRegistry "+e.getMessage());
		}
		
		ServeurRMIImpl srmii = new ServeurRMIImpl();
		ServeurRMI srmi = null;
		
		// crÃ©ation objet distant
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

}
