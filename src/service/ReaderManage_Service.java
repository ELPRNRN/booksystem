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
	
	//ͨ�����ߺŲ��Ҷ���
	List<Reader> SearchReaderByID(String idReader)
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	//�������ж���
	List<Reader> SearchAllReaders()
	{
		List<Reader> readerlist = readerTools.ReaderData();
		return readerlist;
	}
	
	//ͨ�������������Ҷ���
	List<Reader> SearchReaderByName(String keyword)
	{
		List<Reader> readerlist = readerTools.ReaderDataSearch(keyword);
		return readerlist;
	}
	
	//����Ա���¶���
	boolean UpdateReader(Reader reader)
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return true;
		else 
            return false;
	}
	
	//����Ա��������
	boolean AddReader(Reader reader)
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	//ɾ������
	boolean DeleteReader(String idReader)
	{
		int i = readerTools.DeleteReader(idReader);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	
}
