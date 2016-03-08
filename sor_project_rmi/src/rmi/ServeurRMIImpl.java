package rmi;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import base.Base;


public class ServeurRMIImpl 
	implements ServeurRMI {
	
	Base base = new Base();

	@Override
	public String meth() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("RMI : meth");
		return "reponse du serveur rmi";
	}
	
	public String testB() throws RemoteException {
		String res ="";
		base.ouvrir();
		ArrayList<String> resList = base.test();
		int i =0;
		for (i=0; i<resList.size(); i++){
			res = res+" "+resList.get(i);
			System.out.println(resList.get(i));
			System.out.println("wololo");
		}
		return res;
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

}
