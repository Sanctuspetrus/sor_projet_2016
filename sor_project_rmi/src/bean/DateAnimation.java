package bean;

import java.io.Serializable;
import java.util.Date;

public class DateAnimation implements Serializable{
	private static final long serialVersionUID = 1L;
	private Animation animation;
	private String date;
	
	
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
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
