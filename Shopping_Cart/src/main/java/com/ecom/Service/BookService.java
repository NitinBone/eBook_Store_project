package com.ecom.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.Book;
import com.ecom.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bRepo;

	 public void saveBook(Book b) {
		 bRepo.save(b);
	    }
	
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}

	

	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
	
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	
}
