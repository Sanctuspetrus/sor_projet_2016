package bean;

import java.io.Serializable;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idReservation;
	private int idBillet;
	private Billet billet;
	private int idDateAnimation;
	private DateAnimation dateAnimation;
	
	public Reservation(int idReservation, int idDateAnimation, int idBillet) {
		this.idReservation = idReservation;
		this.idDateAnimation = idDateAnimation;
		this.idBillet = idBillet;
	}
	public Reservation(DateAnimation datanim, Billet billet) {
		this.idDateAnimation = datanim.getIdDateAnimation();
		this.idBillet = billet.getIdBillet();
		this.dateAnimation = datanim;
		this.billet = billet;
	}
	public int getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
	public int getIdBillet() {
		return idBillet;
	}
	public void setIdBillet(int idBillet) {
		this.idBillet = idBillet;
	}
	public int getIdDateAnimation() {
		return idDateAnimation;
	}
	public void setIdDateAnimation(int idDateAnimation) {
		this.idDateAnimation = idDateAnimation;
	}
	public Billet getBillet() {
		return billet;
	}
	public void setBillet(Billet billet) {
		this.billet = billet;
	}
	public DateAnimation getDateAnimation() {
		return dateAnimation;
	}
	public void setDateAnimation(DateAnimation dateAnimation) {
		this.dateAnimation = dateAnimation;
	}
	
	@Override
	public String toString() {
		return "Reservation [billet=" + billet + ", animation=" + dateAnimation + "]";
	}
	
	
}
