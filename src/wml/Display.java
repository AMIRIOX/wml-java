package wml;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Display implements IDisplay {
	private Map<colorType, String> colorEnumMap;
	private DataProcess dp;
	public static final double STATUS_LENGTH = 60.0;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	Display() {
		colorEnumMap = new HashMap<colorType, String>();
		dp = new DataProcess(new FileProcess());
		colorEnumMap.put(colorType.RED, ANSI_RED);
		colorEnumMap.put(colorType.BLUE, ANSI_BLUE);
		colorEnumMap.put(colorType.GREEN, ANSI_GREEN);
		colorEnumMap.put(colorType.TRANS, "");
		colorEnumMap.put(colorType.PURPLE, ANSI_PURPLE);
		colorEnumMap.put(colorType.BLACK, ANSI_BLACK);

		colorEnumMap.put(colorType.B_BLUE, ANSI_BLUE_BACKGROUND);
		colorEnumMap.put(colorType.B_RED, ANSI_RED_BACKGROUND);
		colorEnumMap.put(colorType.B_GREEN, ANSI_GREEN_BACKGROUND);
		colorEnumMap.put(colorType.B_TRANS, "");
		colorEnumMap.put(colorType.B_PURPLE, ANSI_PURPLE_BACKGROUND);
		colorEnumMap.put(colorType.B_BLACK, ANSI_BLACK_BACKGROUND);

	}

	@Override
	public void outputColor(String s, colorType color, colorType back, boolean isMid) {
		// TODO Centered output
		System.out.print(colorEnumMap.get(color) + colorEnumMap.get(back) + s + ANSI_RESET);
	}

	@Override
	public void showStatusBar(double percent, colorType color, colorType back) {
		System.out.print("[");
		int realLength = (int) Math.round(STATUS_LENGTH * percent);
//		System.out.print(realLength);
		for (int i = 1; i <= realLength; i++) {
			outputColor(" ", colorType.TRANS, color, false);
		}
		for (int i = 1; i <= STATUS_LENGTH - realLength; i++) {
			outputColor(" ", colorType.TRANS, back, false);
		}
		System.out.print("]");
	}

	// owner method (but not private)
	public void displayAHCI() {
		Integer ahci = dp.totalAHCI();
		outputColor("-> Achieve: ", colorType.BLUE, colorType.TRANS, false);
		System.out.println(ahci);
	}

	public void displayContribution() {
		outputColor("-> Contribution: ", colorType.BLUE, colorType.TRANS, false);		
		Integer contribution = dp.todayContribution();
		System.out.println(contribution);
//		if (contribution > 100)
//			contribution = 100;
		double percent = contribution.doubleValue() / 100.0;
		if (percent>=0 && percent < 0.5) 
			showStatusBar(percent, colorType.B_RED, colorType.TRANS);
		else if(percent >= 0.5 && percent < 1) 
			showStatusBar(percent, colorType.B_BLUE, colorType.TRANS);
		else if(percent == 1) 
			showStatusBar(percent, colorType.B_GREEN, colorType.TRANS);
		else showStatusBar(percent,colorType.B_PURPLE, colorType.TRANS);
		System.out.println();
	}
	public void displayTiredness() {
		outputColor("-> Tiredness: ", colorType.BLUE, colorType.TRANS, false);		
		Integer tiredness = dp.tiredness();
		System.out.println(tiredness);
//		if (tiredness > 100)
//			tiredness = 100;
		double percent = tiredness.doubleValue() / 100.0;
		if (percent>=0 && percent < 0.5) 
			showStatusBar(percent, colorType.B_GREEN, colorType.TRANS);
		else if(percent >= 0.5 && percent < 0.75) 
			showStatusBar(percent, colorType.B_BLUE, colorType.TRANS);
		else if(percent >=0.75 && percent <= 1) 
			showStatusBar(percent, colorType.B_RED, colorType.TRANS);
		else showStatusBar(percent,colorType.B_PURPLE, colorType.TRANS);
		System.out.println();
	}
	public void showTimeRemain() {
		TimeProcess tp = new TimeProcess();
		Date now = new Date();
		Integer remainMin = tp.calcTimeDiffSec(now);
		double remainPercent = remainMin.doubleValue() / TimeProcess.totalMinutes;
		outputColor("-> Time Remain: ", colorType.BLUE, colorType.TRANS, false);
		System.out.print((double) Math.round((remainPercent * 100) * 100) / 100 + "%, ");
		simpleTime st = tp.calcTimeDiff(now);
		System.out.println(st.hour + "hrs " + st.minute + "mins remaining. ");
		if (remainPercent>=0 && remainPercent < 0.5) 
			showStatusBar(1 - remainPercent, colorType.TRANS, colorType.B_GREEN);
		else if(remainPercent >= 0.5 && remainPercent < 0.65) 
			showStatusBar(1 - remainPercent, colorType.TRANS, colorType.B_BLUE);
		else if(remainPercent >=0.65 && remainPercent <= 1) 
			showStatusBar(1 - remainPercent, colorType.TRANS, colorType.B_RED);
		System.out.println();
	}
	public void display() {
		System.out.println("                            The Wing Master Lab Result of day " + dp.totalDays());
		displayAHCI();
		displayContribution();
		displayTiredness();
		showTimeRemain();
		// TODO: Time remaining display
	}
}
