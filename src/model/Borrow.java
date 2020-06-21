package model;

import java.sql.Date;
public class Borrow 
{
	private String idReader;//读者号
	private String idBook;//书号
	private Date lendDate;//借阅日期
	private Date dueDate;//归还日期
	private String overtime;//过期未还标志（是、否）
	
	public String getIdReader() {
		return idReader;
	}
	public void setIdReader(String idReader) {
		this.idReader = idReader;
	}
	public String getIdBook() {
		return idBook;
	}
	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public String toString() {
		return "Borrow [idReader=" + idReader + ", idBook=" + idBook + ", lendDate=" + lendDate + ", dueDate=" + dueDate
				+ ", overtime=" + overtime + "]";
	}
	
}

