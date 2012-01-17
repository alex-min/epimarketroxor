package com.epimarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	@Id
	private	int			id;
	private	String		title;
	private int			stock;
	@ManyToOne
	private Author		author;
	@OneToOne
	private Category	category;
	private String		picture;


	// getters
	public int		getId() { return id; }
	public String	getTitle() { return title; }
	public int		getStock() { return stock; }
	public Author	getAuthor() { return author; }
	public Category	getCategory() { return category; }

	// setters
	public void setId(int id) { this.id = id; }
	public void setTitle(String title) { this.title = title; }
	public void setStock(int stock) { this.stock = stock; }
	public void setAuthor(Author author) { this.author = author; }
	public void setCategory(Category category) { this.category = category; }

	@Override
	public String		toString() {
		return "id:" + id + ", title:" + title + ", author:" + author.getFirstName() + " " + author.getLastName();
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

}
