package com.ecom.controller;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ecom.Service.BookService;
import com.ecom.model.Book;
import com.ecom.model.Login;

@Controller
@RequestMapping("/book")
public class bookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	SessionFactory sf;
	
	
	@GetMapping({"/","/index"})
	public String index() {
		return "book/index";
	}
	
	
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "book/bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		 List<Book> list=service.getAllBook();
			return new ModelAndView("book/bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.saveBook(b);
		return "redirect:/book/available_books";
	}
	
	 @GetMapping("/my_books")
	 public ModelAndView getAllBook1() {
		 List<Book> list=service.getAllBook();
			return new ModelAndView("book/myBooks","book",list);
	    }
	

	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "book/bookEdit";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/book/available_books";
	}
	
}
