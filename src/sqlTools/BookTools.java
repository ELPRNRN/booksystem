package sqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseTools;
import model.Book;

public class BookTools {
	/**
	 * 
	 * @param Book
	 * @return ����ȫ��ͼ�顣List<Book>����ò��ҵ��Ķ��󣬴���Java�༯list�У�������list��
	 */
	public List<Book> BookData() {
		String sql="select idBook,nameBook,price,kind,author,publisher,intro,amount "
				+ "from book";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                book.setIntro(rs.getString("intro"));
                book.setAmount(rs.getInt("amount"));
				ls.add(book);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	/**
	 * 
	 * @param idBook
	 * @return �����������Ƶ�ͼ�飬List<Book>����ò��ҵ��Ķ��󣬴���Java�༯list�У�������list��
	 */
	public List<Book> BookData(String nameBook) {
		String sql="select idBook,nameBook,price,kind,author,publisher,intro,amount "
				+ "from book "
				+ "where nameBook like'%" + nameBook + "%'";//ģ������
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                book.setIntro(rs.getString("intro"));
                book.setAmount(rs.getInt("amount"));
				ls.add(book);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	/**
	 * 
	 * @param idReader
	 * @return �����ض�ͼ��ŵ�ͼ����Ϣ
	 */
	public List<Book> BookData_Search_idBook(String idBook) {
		String sql="select book.idBook,nameBook,price,book.kind,author,publisher,intro,amount "
				+ "from book "
				+ "where book.idBook = '" + idBook + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                book.setIntro(rs.getString("intro"));
                book.setAmount(rs.getInt("amount"));
				ls.add(book);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	/**
	 * 
	 * @param book
	 * @return �������ݲ�������������Ӧ��Book����
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	public int AddBook(Book book) {
		int i=0;
		String sql="insert into book (idBook,nameBook,price,kind,author,publisher,intro,amount) values(?,?,?,?,?,?,?,?)";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);		
			st.setString(1, book.getIdBook());
			st.setString(2, book.getNameBook());
			st.setInt(3, book.getPrice());
			st.setString(4, book.getType());
			st.setString(5, book.getAuthor());
			st.setString(6, book.getPublisher());
            st.setString(7,book.getIntro());
            st.setInt(8,book.getAmount());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	/**
	 * 
	 * @param book
	 * @return �������ݸ��²��������¶�Ӧ��book����
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	public int UpdateBook(Book book) {
		int i=0;
		String sql="update book "
				+ "set idBook=?,nameBook=?,price=?,kind=?,author=?,publisher=?,intro=?,amount=? "
				+ "where idBook=?";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, book.getIdBook());
			st.setString(2, book.getNameBook());
			st.setInt(3, book.getPrice());
			st.setString(4, book.getType());
			st.setString(5, book.getAuthor());
			st.setString(6, book.getPublisher());
            st.setString(7,book.getIntro());
            st.setInt(8, book.getAmount());
			st.setString(9, book.getIdBook());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	/**
	 * 
	 * @param book
	 * @return ��������ɾ��������ɾ����Ӧ��Book����
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	public int DeleteBook(String idbook) {
		int i=0;
		String sql="delete from book where idBook=?";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, idbook);
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	
	public int GetBookAmount(String idBook)
	{
		int amount = 0;
		String sql="select amount "
				+ "from book "
				+ "where book.idBook = '" + idBook + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				amount=rs.getInt("amount");
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return amount;
	}
	
	public int UpdateBookAmount (String idbook,int amount) {
		int i=0;
		String sql="update book "
				+ "set amount=? "
				+ "where idBook=? ";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, amount);
			st.setString(2, idbook);
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}
