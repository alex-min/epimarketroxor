package com.epimarket.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private	int				id;
	private	Date			date = new Date();
	@OneToMany
	private List<Book>		command = new ArrayList<Book>();
	private int				nbArticles = 0;

	public int			getId() { return id; }
	public Date			getDate() { return date; }
	public List<Book>	getCommand() { return command; }

	public void setId(int id) { this.id = id; }
	public void setDate(Date date) { this.date = date; }
	public void setCommand(List<Book> command) { this.command = command; }

	public void	addBook(Book b) {
		command.add(b);
		nbArticles++;
	}
	public int getNbArticles() {
		return nbArticles;
	}
	public void setNbArticles(int nbArticles) {
		this.nbArticles = nbArticles;
	}
}
