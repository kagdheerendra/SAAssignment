package com.infob.departmentservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private boolean groupExist;
	private boolean memberExist;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@ElementCollection(targetClass=Department.class)
	private List<Department> children;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int id, String name, boolean groupExist, boolean memberExist, List<Department> children) {
		super();
		this.id = id;
		this.name = name;
		this.groupExist = groupExist;
		this.memberExist = memberExist;
		this.children = children;
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

	public boolean isGroupExist() {
		return groupExist;
	}

	public void setGroupExist(boolean groupExist) {
		this.groupExist = groupExist;
	}

	public boolean isMemberExist() {
		return memberExist;
	}

	public void setMemberExist(boolean memberExist) {
		this.memberExist = memberExist;
	}

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", groupExist=" + groupExist + ", memberExist=" + memberExist
				+ ", children=" + children + "]";
	}
}
