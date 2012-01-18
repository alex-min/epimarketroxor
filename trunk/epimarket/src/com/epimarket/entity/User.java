package com.epimarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private	String		mail;
	private	int			rights;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Email
	@NotNull
	public String getMail() {
		return mail;
	}

	@Email
	@NotNull
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
