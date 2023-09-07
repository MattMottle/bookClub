package com.mattmottle.logandreg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattmottle.logandreg.models.Book;
import com.mattmottle.logandreg.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List <Book> findAll() {
		return bookRepository.findAll();
	}
	
	public Book findById(Long bookId) {
		Optional <Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}
		return null;
	}
	public Book create(Book book) {
		return bookRepository.save(book);
	}
	public Book updateBook(Book updatedBook) {
		return bookRepository.save(updatedBook);
	}
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);	
	}
}
