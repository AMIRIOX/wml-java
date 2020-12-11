package wml;

import java.io.File;
import java.io.IOException;

public class DataProcess implements IDataProcess {
	FileProcess fileProcessor;
	DataProcess(FileProcess _fileProcessor) {
		fileProcessor = _fileProcessor;
	}
	
	@Override
	public boolean checkFileExits(dataType type) {
		File fileToCheck = new File(fileProcessor.access(type));
		return fileToCheck.exists();
	}

	@Override
	public Integer readData(dataType type) {
		try {
			Integer result = fileProcessor.input(type);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modifyData(dataType type, Integer inc) {
		Integer originalValue;
		if(fileProcessor.isEmpty(type)) originalValue = 0;
		else originalValue = readData(type);
		originalValue += inc;
		fileProcessor.output(type, originalValue.toString());
	}

}