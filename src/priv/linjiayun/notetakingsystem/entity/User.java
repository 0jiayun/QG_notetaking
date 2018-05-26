package priv.linjiayun.notetakingsystem.entity;

import java.util.HashSet;
import java.util.Set;



public class User {
	private int id;
	private String userName;
	private String password;
	private int sex;
	private String nickName;
	
	private Set<Role> roles = new HashSet<>();
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String password) {
		this.userName=userName;
		this.password=password;
		// TODO Auto-generated constructor stub
	}
	public User( String password_1, String nickName2, int sexint) {
		
		this.password=password_1;
		this.nickName=nickName2;
		this.sex=sexint; 
		// TODO Auto-generated constructor stub
	}

	public User(String userName2, String password_1, String nickName2, int sexint) {
		this.userName=userName2;
		this.password=password_1;
		this.nickName=nickName2;
		this.sex=sexint; 
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	

}
