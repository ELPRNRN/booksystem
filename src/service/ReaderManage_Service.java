package service;
import sqlTools.BorrowTools;
import sqlTools.ReaderTools;
import model.Reader

import java.util.List;

import javax.swing.JOptionPane;

import model.Reader;

public class ReaderManage_Service 
{
	ReaderTools readerTools = new ReaderTools();
	
	//通过读者号查找读者
	List<Reader> SearchReaderByID(String idReader)
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	//返回所有读者
	List<Reader> SearchAllReaders()
	{
		List<Reader> readerlist = readerTools.ReaderData();
		return readerlist;
	}
	
	//通过读者姓名查找读者
	List<Reader> SearchReaderByName(String keyword)
	{
		List<Reader> readerlist = readerTools.ReaderDataSearch(keyword);
		return readerlist;
	}
	
	//管理员更新读者
	boolean UpdateReader(Reader reader)
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return true;
		else 
            return false;
	}
	
	//管理员新增读者
	boolean AddReader(Reader reader)
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	//删除读者
	boolean DeleteReader(String idReader)
	{
		int i = readerTools.DeleteReader(idReader);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	
}
