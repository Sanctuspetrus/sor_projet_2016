package bean;

import java.io.Serializable;
import java.util.Date;

public class DateAnimation implements Serializable{

	private Animation animation;
	private Date date;
	
	
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "DateAnimation [animation=" + animation + ", date=" + date + "]";
	}
	
	
	
}
