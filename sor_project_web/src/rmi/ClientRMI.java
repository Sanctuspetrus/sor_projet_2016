package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Reservation;

public class ClientRMI {
	
	private static ServeurRMI srmi;
	
	
	
	public static ArrayList<String> listeReservation(int code) throws RemoteException{
		ArrayList<String> res = srmi.listeReservation(code);
		System.out.println("res = "+res);
		return res;
	}

	public static void main(String [] args) {
		
		int port = 10000;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			srmi = (ServeurRMI)registry.lookup("serveurRMI");
			
			ClientRMI.listeReservation(1234);

		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
		
	}
}
