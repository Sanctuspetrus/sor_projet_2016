package bean;

import java.io.Serializable;
import java.util.Date;

public class DateAnimation implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idDateAnimation;
	private Animation animation;
	private int idAnimation;
	private String date;
	
	public DateAnimation(int idDateAnimatino, String date, int idAnimation) {
	}
	
	public int getIdDateAnimation() {
		return idDateAnimation;
	}
	public void setIdDateAnimation(int idDateAnimation) {
		this.idDateAnimation = idDateAnimation;
	}
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public int getIdAnimation() {
		return idAnimation;
	}
	public void setIdAnimation(int idAnimation) {
		this.idAnimation = idAnimation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "DateAnimation [animation=" + animation + ", date=" + date + "]";
	}
	
	
	
}
