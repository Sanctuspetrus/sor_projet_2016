package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import bean.Reservation;


public interface ServeurRMI  extends Remote {

	public ArrayList<String> listeReservation(int code) throws RemoteException ;
}
