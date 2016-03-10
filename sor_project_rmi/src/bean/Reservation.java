package bean;

import java.io.Serializable;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	private Billet billet;
	private DateAnimation animation;
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
