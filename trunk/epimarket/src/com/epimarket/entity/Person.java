package com.epimarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="people")
public class Person
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int			id;
	private String		firstName;
	private String		lastName;

	@Override
	public String toString()
	{
		return "Person: id=" + id + ", firstName=" + firstName + ", lastName=" + lastName;
	}

	// getters
	public int getId() { return id; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }

	// setters
	public void setId(int id) { this.id = id; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

}