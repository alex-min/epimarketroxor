package com.epimarket.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer			id = null;

	@NotNull
	private	String		login;

	@NotNull
	private String		password;

	@Email
	@NotNull
	private	String			mail;
	private	int				rights;
	@OneToMany
	private List<Purchase>	commands = new ArrayList<Purchase>();

	public Integer			getId() { return id; }
	public String			getLogin() { return login; }
	public String			getMail() { return mail; }
	public String			getPassword() { return password; }
	public int				getRights() { return rights; }
	public List<Purchase>	getCommands() { return commands; }

	public void setLogin(String login) { this.login = login; }
	public void setPassword(String password) { this.password = password; }
	public void setMail(String mail) { this.mail = mail; }
	public void setRights(int rights) { this.rights = rights; }
	public void setId(Integer id) { this.id = id; }
	public void setCommands(List<Purchase> commands) { this.commands = commands; }
	
	public void	addPurchase(Purchase p) {
		commands.add(p);
	}
}
