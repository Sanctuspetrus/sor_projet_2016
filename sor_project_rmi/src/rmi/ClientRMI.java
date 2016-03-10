package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {

	public static void main(String [] args) {
		
		int port = 10000;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");
			
			String res = srmi.confirmer_acces();			
			System.out.println("res = "+res);
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
		
	}
}
