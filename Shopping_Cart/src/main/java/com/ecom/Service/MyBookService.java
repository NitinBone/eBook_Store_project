package com.ecom.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.MyBooks;
import com.ecom.repository.MyBookRepository;

@Service
public class MyBookService {
	
	@Autowired
	private MyBookRepository myBook;

	public void saveMyBooks(MyBooks book) {
		myBook.save(book);
	}
	
	

	public List<MyBooks> getAllMyBooks() {
		// TODO Auto-generated method stub
		return myBook.findAll();
	}



	public void deleteById(int id) {
		myBook.deleteById(id);
	}
	
	public void deleteAll() {
		myBook.deleteAll();
	}



	public List<MyBooks> findAll() {
		// TODO Auto-generated method stub
		return myBook.findAll();
	}


	
}
