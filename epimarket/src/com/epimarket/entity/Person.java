package com.epimarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="people")
public class Person
{
	@Id
	private int id;
	private String firstName;
	private String lastName;

	public String toString()
	{
		return "Person: id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName;
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