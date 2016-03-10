package bean;

import java.io.Serializable;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idReservation;
	private int idBillet;
	private Billet billet;
	private int idDateAnimation;
	private DateAnimation animation;
	
	public Reservation(int idReservation, int idDateAnimation, int idBillet) {
		this.idReservation = idReservation;
		this.idDateAnimation = idDateAnimation;
		this.idBillet = idBillet;
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
	public DateAnimation getAnimation() {
		return animation;
	}
	public void setAnimation(DateAnimation animation) {
		this.animation = animation;
	}
	
	@Override
	public String toString() {
		return "Reservation [billet=" + billet + ", animation=" + animation + "]";
	}
	
	
}
