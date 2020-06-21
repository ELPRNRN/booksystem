package model;

public class Librarian 
{
	private String nameUser;//管理员号
	private String password;//管理员密码
	
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
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
		result = prime * result + ((nameUser == null) ? 0 : nameUser.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Librarian other = (Librarian) obj;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		return true;
	}

	public String toString() {
		return "Librarian [nameUser=" + nameUser + ", password=" + password + "]";
	}

}

