package com.epimarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	@Id
	private	int		id;
	private	String	title;
	@ManyToOne
	private Author	author;
	

	// getters
	public int		getId() { return id; }
	public String	getTitle() { return title; }
	public Author	getAuthor() { return author; }
	
	// setters
	public void setId(int id) { this.id = id; }
	public void setTitle(String title) { this.title = title; }
	public void setAuthor(Author author) { this.author = author; }

	public String		toString() {
		return "id:" + id + ", title:" + title + ", author:" + author.getFirstName() + " " + author.getLastName();
	}
}
