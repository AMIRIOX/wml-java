package wml;

import java.util.Date;

public interface ITimeProcess {
	public Date getTime();
	public String transToStr();
	public int calcTimeDiffSec(Date now);
	public simpleTime calcTimeDiff(Date now);
}
