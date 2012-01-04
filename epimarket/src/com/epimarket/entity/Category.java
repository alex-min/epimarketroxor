package com.epimarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	private int			id;
	private	String		name;

	// getters
	public int getId() { return id; }
	public String getName() { return name; }

	// setters
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
}
