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
	
	List<Reader> SearchReaderByID(String idReader)//ͨ�����ߺŲ��Ҷ���
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	List<Reader> SearchAllReaders()//�������ж���
	{
		List<Reader> readerlist = readerTools.ReaderData();
		return readerlist;
	}
	
	List<Reader> SearchReaderByName(String keyword)
	{
		List<Reader> readerlist = readerTools.ReaderDataSearch(keyword);
		return readerlist;
	}
	
	boolean UpdateReader(Reader reader)//����Ա���¶���
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return true;
		else 
            return false;
	}
	
	boolean AddReader(Reader reader)//����Ա��������
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	boolean DeleteReader(String idReader)//ɾ������
	{
		int i = readerTools.DeleteReader(idReader);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	
}
