package wml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Reflection {
	// TODO: JNI call C function `system`
	private Map<String, dataType> instTypeMap = new HashMap<String, dataType>();
	private static final String version = "0.1.0";	
	private static final String versionInfo = 
		"Java refactored version of wml. Refactored version: 0.0.4. \n OPEN SOURCE AND FREE.";
	Reflection() {
		instTypeMap.put("-d", dataType.CONTRIBUTION);
		instTypeMap.put("-t", dataType.TIREDNESS);
	}
	
	public Commands<String[]> toTask = (_args) -> {
	try {
		ProcessBuilder pb = new ProcessBuilder("task", "next");
		Process proc = pb.start();
//		InputStream inputStream = proc.getInputStream();
//		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf8");
//		BufferedReader buf = new BufferedReader(inputStreamReader);
//		String line = null;
//		while((line = buf.readLine()) != null) {
//			String strs = new String(line.getBytes(), "utf-8");
//			System.out.println(strs);
//		}
		int resultv = proc.waitFor();
		System.out.println(proc.exitValue());
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GB-2312"))) {
		    String result = reader.lines()
		            .collect(Collectors.joining("\n"));
		    System.out.println(result);
		}
		return resultv;
	} catch (InterruptedException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		return 444;
	};
	
	// test OK
//	public Commands<String[]> test = (_args) -> {
//		DataProcess dp = new DataProcess(new FileProcess());
//		dp.modifyData(dataType.valueOf(_args[1]), Integer.parseInt(_args[2]));
//		return 1;
//	};
	
	public Commands<String[]> modifyValue = (_args) -> {
		if(_args.length < 2) return 4;
		DataProcess dp = new DataProcess(new FileProcess());
		dataType dt = instTypeMap.get(_args[0]);
		Integer incVal = Integer.parseInt(_args[1]);
		dp.modifyData(dt, incVal, true);
		Recorder recorder = new Recorder();
		recorder.addRecord(dt, incVal);
		return 0;
	};
	
	public Commands<String[]> showVersion = (_args) -> {
		System.out.println("wml version "+version+". ");
		System.out.println("specific info: \n"+versionInfo);
		// TODO: build info
		return 0;
	};
	
	public Commands<String[]> showTimeRemain = (_args) -> {
		TimeProcess tp = new TimeProcess();
		simpleTime st = tp.calcTimeDiff(tp.getTime());
		System.out.println(st.hour + "hrs " + st.minute + "mins remaining.");
		return 0;
	};
	
	public Commands<String[]> refresh = (_args) -> {
		DataProcess dp = new DataProcess(new FileProcess());
		dp.refresh();
		return 0;
	};
	
	public Commands<String[]> displayStatus = (_args) -> {
		Display disp = new Display();
		disp.display();
		return 0;
	};
}
