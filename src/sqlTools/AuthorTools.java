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
	 * @return 返回特定名字的作者，List<Author>，获得查找到的对象，存在Java类集list中，并返回list。
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
	 * @return 返回全部作者，List<Author>，获得查找到的对象，存在Java类集list中，并返回list。
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
	 * @return 进行数据插入操作，插入对应的Author对象。
	 * 返回值：
	 * (1) SQL 数据操作语言 (DML) 语句的行数 (2) 对于无返回内容的 SQL 语句，返回 0 
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
	 * @return 进行数据更新操作，更新对应的Author对象。
	 * 返回值：
	 * (1) SQL 数据操作语言 (DML) 语句的行数 (2) 对于无返回内容的 SQL 语句，返回 0 
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
