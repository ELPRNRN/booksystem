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
	
	List<Reader> SearchReaderByID(String idReader)
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	String UpdateReader(Reader reader)//管理员更新读者
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return  "成功更新读者信息！";
		else 
            return  "更新读者信息失败！";
	}
	
	String AddReader(Reader reader)//管理员新增读者
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return  "成功新增读者信息！";
		else 
			return "新增读者信息失败！";
	}
}
