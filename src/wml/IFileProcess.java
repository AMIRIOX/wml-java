package wml;

import java.io.IOException;

public interface IFileProcess {
	void clearFile(dataType t);
	void outputApp(dataType t, String str) throws IOException;
	void output(dataType t, String str);  // as clearFile() then outputApp()
	Integer input(dataType t) throws IOException;
	boolean isEmpty(dataType t);
}
