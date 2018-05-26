package priv.linjiayun.notetakingsystem.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private int id;
	private String name;
	private String description;
	private Set<Privilege> privileges = new HashSet<>();
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
