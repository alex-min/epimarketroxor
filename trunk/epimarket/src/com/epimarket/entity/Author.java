package com.epimarket.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int				id;
	private String			firstName; // prenom
	private String			lastName; // nom famille
	@OneToMany
	private List<Book>		listBooks;

	// getters
	public int			getId() { return id; }
	public String		getFirstName() { return firstName; }
	public String		getLastName() { return lastName; }
	public List<Book>	getListBooks() { return listBooks; }

	// setters
	public void		setId(int id) { this.id = id; }
	public void		setFirstName(String firstName) { this.firstName = firstName; }
	public void		setLastName(String lastName) { this.lastName = lastName; }
	public void		setListBooks(List<Book> listBooks) { this.listBooks = listBooks; }
}
