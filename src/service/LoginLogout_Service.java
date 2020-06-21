package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;

import model.Reader;
import model.Librarian;

public class LoginLogout_Service 
{
	//�洢��ǰ���߻����Ա��Ϣ
	private static String idReader;//���ߺ�
	private static String nameReader;//������
	private static String nameUser;//����Ա��
	
	public Librarian lib;
	private static LoginLogout_Service loginLogout_Service_Instance = new LoginLogout_Service();
	
	//����
	public static LoginLogout_Service getInstance() {
		return loginLogout_Service_Instance;
	}
	private LoginLogout_Service() {};
	
	//���ߵ�¼
	//���ݿ�������Ӷ����˺�
	public boolean ReaderLogin(String UserID,String Password)
	{
		ReaderTools rTools = new ReaderTools();
		
		boolean whether_login = rTools.ReaderLogin(UserID, Password);//�������ݳ��Ե�¼
		if (whether_login == true) //��½�ɹ�
		{
			nameReader=rTools.ReaderData(UserID).get(0).getNameReader();//��ȡ����������¼��nameReader
			idReader = UserID;//��¼���ߺŵ�idReader
			return true;
		}
		else
			return false;
		
	}
	
	//����Ա��½
	//���ݿ�������ӹ���Ա�ʺ�
	public boolean LibLogin(String UserName,String Password)
	{
		LibrarianTools libTools = new LibrarianTools();
		
		boolean whether_login = libTools.LibrarianLogin(UserName, Password);//�������ݳ��Ե�½
		if (whether_login == true) //��½�ɹ�
		{
			nameUser = UserName;//��¼�����ߺŵ�nameUser��
			return true;
		}
		else
			return false;
		
	}
	
	//���ߵǳ�
	//��ն��ߵ�¼��Ϣ
	public void ReaderLogout()
	{
		idReader=null;
		nameReader=null;
	}
	
	//����Ա�ǳ�
	//��չ����ߵ�¼��Ϣ
	public void LibLogout()
	{
		nameUser=null;
	}

	//��ȡ�ѵ�¼���ߺ�
	public static String getIdReader() {
		return idReader;
	}

	//��ȡ�ѵ�¼�����ߺ�
	public static String getNameUser() {
		return nameUser;
	}

	//��ȡ�ѵ�¼������
	public static String getNameReader() {
		return nameReader;
	}
	
	public static void setNameUser(String nameUser) {
		LoginLogout_Service.nameUser = nameUser;
	}

	public static void setIdReader(String idReader) {
		LoginLogout_Service.idReader = idReader;
	}

	public static void setNameReader(String nameReader) {
		LoginLogout_Service.nameReader = nameReader;
	}

	
}
