package model;

public class Reader 
{
	private String idReader;//读者号
	private String nameReader;//读者名
	private String type;//读者类型（学生、老师）
	private String sex;//读者性别
	private String password;//读者密码
	
	public String getIdReader() {
		return idReader;
	}
	public Reader() {}
	public Reader(String idReader, String nameReader, String type, String sex, String password) {
		super();
		this.idReader = idReader;
		this.nameReader = nameReader;
		this.type = type;
		this.sex = sex;
		this.password = password;
	}
	public void setIdReader(String idReader) {
		this.idReader = idReader;
	}
	public String getNameReader() {
		return nameReader;
	}
	public void setNameReader(String nameReader) {
		this.nameReader = nameReader;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idReader == null) ? 0 : idReader.hashCode());
		result = prime * result + ((nameReader == null) ? 0 : nameReader.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reader other = (Reader) obj;
		if (idReader == null) {
			if (other.idReader != null)
				return false;
		} else if (!idReader.equals(other.idReader))
			return false;
		if (nameReader == null) {
			if (other.nameReader != null)
				return false;
		} else if (!nameReader.equals(other.nameReader))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String toString() {
		return "Reader [idReader=" + idReader + ", nameReader=" + nameReader + ", type=" + type + ", sex=" + sex
				+ ", password=" + password + "]";
	}

}