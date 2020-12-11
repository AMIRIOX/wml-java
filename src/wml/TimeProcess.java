package wml;

import java.util.Date;

public class TimeProcess implements ITimeProcess {
	private Date standardTime;
	public static final int totalMinutes = 900;
	
	@SuppressWarnings("deprecation")
	public TimeProcess() {
		standardTime = new Date();
		standardTime.setHours(22);
		standardTime.setMinutes(30);
		standardTime.setSeconds(0);
		System.out.println(standardTime.toLocaleString());
	}
	@Override
	public Date getTime() {
		return new Date();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String transToStr() {
		// TODO Auto-generated method stub
		return getTime().toLocaleString();
	}

	@Override
	public simpleTime calcTimeDiff(Date now) {
		long from = now.getTime();
		long to = standardTime.getTime();
	    int hours = (int) ((to - from)/(1000 * 60 * 60));
	    int minutes = (int) ((to - from)/(1000 * 60));
		return new simpleTime(hours, minutes % 60);
	}
	@Override
	public int calcTimeDiffSec(Date now) {
		long from = now.getTime();
		long to = standardTime.getTime();
	    int minutes = (int) ((to - from)/(1000 * 60));
		return minutes;
	}

	
}
