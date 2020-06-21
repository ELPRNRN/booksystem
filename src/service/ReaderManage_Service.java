package service;
import sqlTools.ReaderTools;
import model.Reader;
import java.util.List;


public class ReaderManage_Service 
{
	ReaderTools readerTools = new ReaderTools();
	private static ReaderManage_Service ReaderManage_Service_Instance = new ReaderManage_Service();
	
	//����
	public static ReaderManage_Service getInstance() {
		return ReaderManage_Service_Instance;
	}
	private ReaderManage_Service() {};
	
	//ͨ�����ߺŲ��Ҷ��ߣ�����Ϊ���ߺţ���ȷ�����������ض����б�
	public List<Reader> SearchReaderByID(String idReader)
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	//����ϵͳ�����ж��ߣ����ض����б�
	public List<Reader> SearchAllReaders()
	{
		List<Reader> readerlist = readerTools.ReaderData();
		return readerlist;
	}
	
	//ͨ�������������Ҷ��ߣ�����Ϊ�������ؼ��֣�ģ�������������ض����б�
	public List<Reader> SearchReaderByName(String keyword)
	{
		List<Reader> readerlist = readerTools.ReaderDataSearch(keyword);
		return readerlist;
	}
	
	//����Ա���¶�����Ϣ���������Ϊreader����������Ϣ������Ϊ��
	public boolean UpdateReader(Reader reader)
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return true;//���³ɹ�
		else 
            return false;//����ʧ��
	}
	
	//����Ա�������ߣ��������Ϊreader����������Ϣ������Ϊ��
	public boolean AddReader(Reader reader)
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return true;//��ӳɹ�
		else 
			return false;//�˶��ߺ��Ѵ��ڣ��޷���������
	}
	
	//ɾ�����ߣ�����Ϊ���ߺ�
	public boolean DeleteReader(String idReader)
	{
		int i = readerTools.DeleteReader(idReader);
		if (i == 1) 
			return true;//ɾ���ɹ�
		else 
			return false;//ɾ��ʧ��
	}
	
	
}
