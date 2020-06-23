package sqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseTools;
import model.Author;

public class AuthorTools {
	/**
	 * 
	 * @param keyword
	 * @return �����ض����ֵ����ߣ�List<Author>����ò��ҵ��Ķ��󣬴���Java�༯list�У�������list��
	 */
	public List<Author> AuthorData(String keyword) {
		String sql="select name,nationality "
				+ "from author "
				+ "where name = '" + keyword + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Author> ls=new ArrayList<Author>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Author author=new Author();
				author.setName(rs.getString("name"));
				author.setNationality(rs.getString("nationality"));
				ls.add(author);
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
	 * @return ����ȫ�����ߣ�List<Author>����ò��ҵ��Ķ��󣬴���Java�༯list�У�������list��
	 * 
	 */
	public List<Author> AuthorData() {
		String sql="select name,nationality "
				+ "from author";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Author> ls=new ArrayList<Author>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Author author=new Author();
				author.setName(rs.getString("name"));
				author.setNationality(rs.getString("nationality"));
				ls.add(author);
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
	 * @param author
	 * @return �������ݲ�������������Ӧ��Author����
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	/*
	public int AddAuthor(Author author) {
		int i=0;
		String sql="insert into author (name,workplace) values(?,?)";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			st.setString(1, author.getName());
			st.setString(2, author.getWorkplace());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	*/
	/**
	 * 
	 * @param author
	 * @return �������ݸ��²��������¶�Ӧ��Author����
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	public int AddUpdateAuthor(Author author) {
		int i=0;
		String sql="if not exists (select name from author where name = ?)" + 
				"   	INSERT INTO author VALUES (?,?)" + 
				"else"+
				"   UPDATE author SET name = ?, nationality = ?" + 
				"   WHERE name = ? ";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			st.setString(1, author.getName());
			st.setString(2, author.getName());
			st.setString(3, author.getNationality());
			st.setString(4, author.getName());
			st.setString(5, author.getNationality());
			st.setString(6, author.getName());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}
