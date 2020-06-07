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
	
	String UpdateReader(Reader reader)//����Ա���¶���
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return  "�ɹ����¶�����Ϣ��";
		else 
            return  "���¶�����Ϣʧ�ܣ�";
	}
	
	String AddReader(Reader reader)//����Ա��������
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return  "�ɹ�����������Ϣ��";
		else 
			return "����������Ϣʧ�ܣ�";
	}
}
