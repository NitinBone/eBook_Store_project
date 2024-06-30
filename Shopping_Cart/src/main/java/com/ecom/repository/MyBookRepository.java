package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.MyBooks;

@Repository
public interface MyBookRepository extends JpaRepository<MyBooks, Integer>{

}
