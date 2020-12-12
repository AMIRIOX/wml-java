package wml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileProcess implements IFileProcess {
	private Map<dataType, String> fileEnumMap;
	FileProcess() {
		// TODO: support settings file.
		fileEnumMap = new HashMap<dataType, String>();
		fileEnumMap.put(dataType.AHCI, "/disk01/apps/wml/achieve.data");
		fileEnumMap.put(dataType.CONTRIBUTION, "/disk01/apps/wml/contribution.data");
		fileEnumMap.put(dataType.TIREDNESS, "/disk01/apps/wml/tiredness.data");
		fileEnumMap.put(dataType.LOG, "/disk01/apps/wml/.wml.log");
		fileEnumMap.put(dataType.RECORD, "/disk01/apps/wml/.record.log");
		fileEnumMap.put(dataType.DAYS, "/disk01/apps/wml/days.txt");
		fileEnumMap.put(dataType.DAYSREC, "/disk01/apps/wml/daysrec.txt");
		
		for(dataType files : dataType.values()) {
			File creator = new File(fileEnumMap.get(files));
			if(!creator.exists()) {
				try {
					creator.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	FileProcess(FileProcess fp) {
		this.fileEnumMap = fp.fileEnumMap;
		
		for(dataType files : dataType.values()) {
			File creator = new File(this.fileEnumMap.get(files));
			if(!creator.exists()) {
				try {
					creator.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	String access(dataType t) {
		return fileEnumMap.get(t);
	}
	
	@Override
	public void clearFile(dataType t) {
		File fileToClear = new File(fileEnumMap.get(t));
		if(!fileToClear.exists()) {
			try {
				fileToClear.createNewFile();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter fileWriter = new FileWriter(fileToClear);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void outputApp(dataType t, String str) throws IOException {
		FileWriter writer = new FileWriter(fileEnumMap.get(t), true);
		writer.write(str);
		writer.close();
	}

	@Override
	public void output(dataType t, String str) {
		FileWriter writer;
		try {
			writer = new FileWriter(fileEnumMap.get(t));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO input string(easy!)
	@Override
	public Integer input(dataType t) throws IOException {
		File fileToRead = new File(fileEnumMap.get(t));
		if(!fileToRead.exists()) {
//			throw new IOException();
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		try {
			char[] temp = new char[1024];
			FileInputStream fileInputStream = new FileInputStream(fileToRead);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");
			while(inputStreamReader.read(temp)!=-1) {
				buffer.append(temp);
				temp = new char[1024]; // GC?
			}
			inputStreamReader.close();
			String result = buffer.toString().trim();
			if(result=="") {
				return null;
			}
			return Integer.parseInt(result);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isEmpty(dataType t) {
		File fileToCheck = new File(fileEnumMap.get(t));
		if(fileToCheck.exists() && fileToCheck.length()==0) return true;
		return false;
	}

}
