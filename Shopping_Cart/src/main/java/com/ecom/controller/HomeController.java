package com.ecom.controller;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecom.Service.BookService;
import com.ecom.Service.MyBookService;
import com.ecom.model.Admin;
import com.ecom.model.Book;
import com.ecom.model.Login;
import com.ecom.model.MyBooks;


@Controller
public class HomeController {
	
	@Autowired
	private BookService service;
	
	@Autowired
    private MyBookService myBookService;
	
	
	
	@Autowired
	SessionFactory sf;

	@RequestMapping({"/","/home"})
	public String home() {
		return "home";
	}
	
	@RequestMapping("/userHomePage")
	public String userHomePage() {
		return "userHomePage";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
	 List<Book> list=service.getAllBook();
		return new ModelAndView("availablebooks","book",list);
	
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBooks> list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBooks mb=new MyBooks(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/deleteMyList/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		myBookService.deleteById(id);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin() {
		return "userLogin";
	}
	
	@RequestMapping("/adminLogin")
	public String adminLogin() {
		return "adminLogin";
	}
	
	@RequestMapping("/userSignUp")
	public String userSignUp() {
		return "userSignUp";
	}
	
	@RequestMapping("/login1")
	public String login(@ModelAttribute Login login, Model model) {

		Session ss = sf.openSession();

		Login dblogin = ss.get(Login.class, login.getUsername());
		String page = "login1";
		String page2="null";

		if (dblogin != null) {
			if (login.getPassword().equals(dblogin.getPassword())) {

				page = "userHomePage";
				return page;
				

			} else {
				page2 = "userLogin";
				return page2;			}
		} else {
			page2 = "userLogin";
			return page2;			}
	}
	
	@RequestMapping("/login2")
	public String login(@ModelAttribute Admin admin, Model model) {

		Session ss = sf.openSession();

		Admin dblogin = ss.get(Admin.class, admin.getUsername());
		String page = null;
		String page2="null";

		if (dblogin != null) {
			if (admin.getPassword().equals(dblogin.getPassword())) {

				page = "/book/index";
				return page;
				

			} else
			{
				page2 = "adminLogin";
				return page2;			
				}
		} else {
			page2 = "adminLogin";
			return page2;			
			
		}
	}
	
	
	@RequestMapping("/signup1")
	public String singup(@ModelAttribute Login login, Model model) {
		try {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(login);
		tx.commit();
		return "redirect:/userLogin";
		}	
		catch(Exception e) {
			e.printStackTrace();
            model.addAttribute("error", "Username already exists. Please choose a different username.");

			return "userSignUp";
		}
	}

	 @GetMapping("/place-order")
	    public String placeOrderPage() {
		 myBookService.deleteAll();
	        return "orderSuccesfullPage";
	    }
	
	
	}

