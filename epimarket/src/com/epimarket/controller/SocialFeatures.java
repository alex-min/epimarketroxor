package com.epimarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epimarket.database.EMF;
import com.epimarket.entity.Book;
import com.epimarket.entity.Comment;
import com.epimarket.entity.User;
import com.epimarket.webdata.WD;

@Controller
public class SocialFeatures {
	@RequestMapping(value = "market/book/{id}/comment/",
			method=RequestMethod.GET)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("id") int id) {
		Criteria crit = EMF.getSession().createCriteria(Book.class);
		crit.add(Restrictions.eq("id", id));
		@SuppressWarnings("unchecked")
		List<Book> b = crit.list();
		rqst.setAttribute("books", b);
		return "social_comment";
	}

	@RequestMapping(value = "market/book/{id}/comment/add",
			method=RequestMethod.POST)
	public String addComment
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@RequestParam("comment") String comment,
			@RequestParam("rate") int rate,
			@RequestParam("id") int articleId,
			@PathVariable("id") int id
			) {
		if (WD.getData().getUser().isLogged() == false)
			return "index";
		User u = WD.getData().getUser().getUser();
		EMF.save(u);
		EMF.commit();
		Comment c = new Comment();
		c.setUser(u);
		c.setComment(comment);
		Criteria crit = EMF.getSession().createCriteria(Book.class);
		crit.add(Restrictions.eq("id", articleId));
		@SuppressWarnings("unchecked")
		List<Book> b = crit.list();
		rqst.setAttribute("books", b);
		Book book = b.get(0);
		c.setBook(book);
		book.getCommentList().add(c);
		c.setRate(rate);
		EMF.getSession().save(c);
		EMF.commit();
		Criteria crit2 = EMF.getSession().createCriteria(Comment.class);
		crit2.add(Restrictions.eq("book", book));
		@SuppressWarnings("unchecked")
		List<Comment> comments = crit2.list();
		rqst.setAttribute("comments", comments);
		System.out.println("=>" + comments.size());
		return  "social_comment";
	}

}