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
		String sql="select idBook,nameBook,price,kind,author,publisher,inrto from Book";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                book.setIntro(rs.getString("intro"));
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
		String sql="select idBook,nameBook,price,kind,author,publisher intro from Book where nameBook like'%" + nameBook + "%'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                                                                book.setIntro(rs.getString("intro"));
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
	 * @return �����ض���ŵ�ͼ�飬��ò��ҵ��Ķ��󣬴洢��Book�У�������Book��
	 */
	public Book Search_Book(String idBook) {
		String sql="select idBook,nameBook,price,kind,author,publisher,intro from Book where idBook='" + idBook + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		Book book = null;
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                                                                book.setIntro(rs.getString("intro"));
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
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
		String sql="insert into book (idBook,nameBook,price,kind,author,publisher,intro)values(?,?,?,?,?,?,?)";
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
		String sql="update book set idBook=?,nameBook=?,price=?,kind=?,author=?,publisher=?,intro=? where idBook=?";
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
			st.setString(8, book.getIdBook());
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
		String sql="delete from Book where idBook=?";
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
}
