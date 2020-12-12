package wml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Client {
	static Map<String, Commands<String[]> > instMap = new HashMap<String, Commands<String[]>>();
	static Reflection instRef;
	public Client() {
	}
	public static void main(String[] args) throws IOException {
		// some pre test: ok
//		FileProcess fpc = new FileProcess();
//		Integer t = fpc.input(dataType.AHCI);
//		System.out.println(t);
//		fpc.clearFile(dataType.AHCI);
//		t = fpc.input(dataType.AHCI);
//		Display display = new Display();
//		display.outputColor("   test...  ", colorType.TRANS, colorType.TRANS, false);
//		display.showStatusBar(0.21, colorType.B_RED);
		
		// test victory: about args
//		StringBuffer argsAppend = new StringBuffer();
//		for(String arg : args) {
//			argsAppend.append(arg);
//			argsAppend.append(" ");
//			System.out.println(arg);
//		}
		
		// test failed
//		Reflection ref = new Reflection();
//		int res = ref.toTask.exec(argsAppend.toString());
//		if(true) System.out.println(res);
//		System.out.println("\n ok \n");
		
		// FileProcess OK
//		FileProcess fpc = new FileProcess();
//		fpc.output(dataType.LOG, "1000");
//		Integer res = fpc.input(dataType.LOG);
//		System.out.println(res);
//		fpc.output(dataType.LOG, "21");
//		res = fpc.input(dataType.LOG);
//		System.out.println(res);
				
		// Time Process Test, OK
//		TimeProcess tp = new TimeProcess();
//		System.out.println(tp.calcTimeDiffSec(tp.getTime()));
		
//		instMap.put("test", instRef.test);

		// display one test, OK
//		Display disp = new Display();
//		disp.displayContribution();

		
		instRef = new Reflection();
		instMap.put("-d", instRef.modifyValue);
		instMap.put("-t", instRef.modifyValue);
		instMap.put("-v", instRef.showVersion);
		instMap.put("-m", instRef.showTimeRemain);
		instMap.put("-r", instRef.refresh);
		instMap.put("-s", instRef.displayStatus);
		
		int result = instMap.get(args[0]).exec(args);
		if(result != 0) {
			System.err.print("ERROR.");
		}

	}
}
