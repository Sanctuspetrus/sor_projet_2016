package rmi;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bean.Reservation;

public class ServeurRMIImpl implements ServeurRMI {

	@Override
	public ArrayList<String> listeReservation(int code) throws RemoteException {
		ArrayList<String> liste_res = new ArrayList<String>();
		
		Reservation res1 = new Reservation();
		Reservation res2 = new Reservation();
		Reservation res3 = new Reservation();
		
		liste_res.add(res1.toString());
		liste_res.add(res2.toString());
		liste_res.add(res3.toString());
		
		return liste_res;
	}
	
	public static void main(String [] args) {
		
		int port = 10000;
		
		Registry registry = null;
		
		// cr�ation registry
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
