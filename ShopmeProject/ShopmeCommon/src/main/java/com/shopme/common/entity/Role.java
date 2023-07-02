package com.shopme.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=40, nullable=false,unique=true)
	private String name;
	@Column(length=150,nullable=false)
	private String discription;
	public Integer getId() {
		return id;
	}
	public Role() {
		
		
		
	}
	public Role(Integer id) {
		
		this.id = id;
	}
	public Role(String name, String discription) {
		super();
		
		this.name = name;
		this.discription = discription;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}
	
	
	

}
