package wml;

import java.util.HashMap;
import java.util.Map;

public class Display implements IDisplay {
	private Map<colorType, String> colorEnumMap;
	public static final double STATUS_LENGTH = 60.0;

	public static final String ANSI_RESET ="\u001B[0m";
	public static final String ANSI_BLACK ="\u001B[30m";
	public static final String ANSI_RED ="\u001B[31m";
	public static final String ANSI_GREEN ="\u001B[32m";
	public static final String ANSI_YELLOW ="\u001B[33m";
	public static final String ANSI_BLUE ="\u001B[34m";
	public static final String ANSI_PURPLE ="\u001B[35m";
	public static final String ANSI_CYAN ="\u001B[36m";
	public static final String ANSI_WHITE ="\u001B[37m";
	
	public static final String ANSI_BLACK_BACKGROUND ="\u001B[40m";
	public static final String ANSI_RED_BACKGROUND ="\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND ="\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND ="\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND ="\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND ="\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND ="\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND ="\u001B[47m";
	
	Display() {
		colorEnumMap = new HashMap<colorType, String>();
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
		System.out.print(colorEnumMap.get(color)+colorEnumMap.get(back)+s+ANSI_RESET);
	}

	@Override
	public void showStatusBar(double percent, colorType color) {
		System.out.print("[");
		int realLength = (int) Math.round(STATUS_LENGTH*percent);
//		System.out.print(realLength);
		for(int i=1; i<=realLength; i++) {
			outputColor(" ", colorType.TRANS, color, false);	
		}
		for(int i=1; i<=STATUS_LENGTH-realLength; i++) {
			outputColor(" ", colorType.TRANS, colorType.TRANS, false);	
		}
		System.out.print("]");
	}

}
