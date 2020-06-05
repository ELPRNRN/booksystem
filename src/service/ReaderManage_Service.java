package service;
import sqlTools.BorrowTools;
import sqlTools.ReaderTools;

import java.util.List;

import model.Reader;

public class ReaderManage_Service 
{
	ReaderTools readerTools = new ReaderTools();
	
	List<Reader> SearchReaderByID(String idReader)
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	
	
}
