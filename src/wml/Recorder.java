package wml;

import java.io.IOException;
import java.util.Date;

public class Recorder extends FileProcess {
	@SuppressWarnings("deprecation")
	public void addRecord(dataType t, Integer changedValue) {
		String recContext = ("[" + new Date().toLocaleString() + "] ");
		recContext += t.toString();
		recContext += " has changed " + changedValue.toString() + "\n";
		try {
			outputApp(dataType.RECORD, recContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void addSummary() {
		/*
		 *  add a day's summary like this:
		    >Thu Dec 10 23:48:13 2020<
			until now, global contribution: 750
			and today's contribution: 5
			until now tiring value: 40
		 */
		StringBuffer summaryContext = new StringBuffer();
		DataProcess dp = new DataProcess(new FileProcess());
		summaryContext.append("\n " + new Date().toLocaleString() + " \n");
		summaryContext.append("Until now, global achieve: " + dp.totalAHCI() + "\n");
		summaryContext.append("And today's contribution: " + dp.todayContribution() + "\n");
		summaryContext.append("Labor value up to now: " + dp.tiredness() + "\n");
		try {
			outputApp(dataType.DAYSREC, summaryContext.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
