package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;
import model.Reader;
import model.Librarian;

public class LoginLogout_Service 
{
	//�洢��ǰ������Ϣ�����Ա��Ϣ
	private static String idReader;
	private static String nameReader;
	private static String nameUser;
	public Reader reader;
	public Librarian lib;

	//���ߵ�¼
	boolean ReaderLogin(String UserID,String Password)
	{
		reader=new Reader();
		ReaderTools rTools = new ReaderTools();
		reader.setIdReader(UserID);
		reader.setPassword(Password);
		
		boolean whether_login = rTools.ReaderLogin(reader.getIdReader(), reader.getPassword());
		if (whether_login == true) 
		{
			nameReader=rTools.ReaderData(reader.getIdReader()).get(0).getNameReader();
			idReader = reader.getIdReader();
			return true;
		}
		else
			return false;
		
	}
	
	//����Ա��½
	boolean LibLogin(String UserName,String Password)
	{
		LibrarianTools libTools = new LibrarianTools();
		lib = new Librarian();
		lib.setNameUser(UserName);
		lib.setPassword(Password);
		
		boolean whether_login = libTools.LibrarianLogin(lib.getNameUser(), lib.getPassword());
		if (whether_login == true) 
		{
			nameUser = lib.getNameUser();
			return true;
		}
		else
			return false;
		
	}
	
	//���ߵǳ�
	void ReaderLogout()
	{
		idReader=null;
		nameReader=null;
	}
	
	//����Ա�ǳ�
	void LibLogout()
	{
		nameUser=null;
	}

	public static String getIdReader() {
		return idReader;
	}

	public static String getNameUser() {
		return nameUser;
	}

	public static void setNameUser(String nameUser) {
		LoginLogout_Service.nameUser = nameUser;
	}

	public static void setIdReader(String idReader) {
		LoginLogout_Service.idReader = idReader;
	}

	public static String getNameReader() {
		return nameReader;
	}

	public static void setNameReader(String nameReader) {
		LoginLogout_Service.nameReader = nameReader;
	}

	
}
