package tests;

import java.rmi.RemoteException;

import org.junit.Test;

import junit.framework.TestCase;
import rmi.ServeurRMI;
import rmi.ServeurRMIImpl;

public class test_ServeurRMIImpl extends TestCase {
	
	ServeurRMI srmi;
	
	public void setUp(){
		srmi = new ServeurRMIImpl();
	}
	
	@Test
	public void test_confirmer_accès() throws RemoteException {
		String res = srmi.confirmer_acces();
		assertEquals(res, "Serveur => Accès confirmé");
	}

}
