package wml;

enum dataType{
	AHCI, CONTRIBUTION, TIREDNESS, DAYS, DAYSREC,
	LOG, RECORD
}; // global contribution, today's contribution and tiredness

//TODO: the dataType register

public interface IDataProcess {
	boolean checkFileExits(dataType type);
	Integer readData(dataType type);
	void modifyData(dataType type, Integer inc);
}
