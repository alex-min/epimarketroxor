package com.epimarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int			id;
	private	String		name;

	// getters
	public int getId() { return id; }
	public String getName() { return name; }

	// setters
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
}
