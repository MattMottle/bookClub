package com.mattmottle.logandreg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mattmottle.logandreg.models.Book;
import com.mattmottle.logandreg.models.User;
import com.mattmottle.logandreg.services.BookService;
import com.mattmottle.logandreg.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/books")
	public String dashboard(HttpSession session, Model viewModel, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		// talk to service to grab user by the saved id in session
		viewModel.addAttribute("loggedUser", userService.findById(userId));
		model.addAttribute("books", bookService.findAll());
		return "dashboard.jsp";
	}
	
	@GetMapping("/books/{bookId}")
	public String oneBook(Model model, @PathVariable("bookId") Long bookId, HttpSession session, Model viewModel) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		viewModel.addAttribute("loggedUser", userService.findById(userId));
		Book book = bookService.findById(bookId);
		model.addAttribute("book", book);
		return "book.jsp";
	}
	@GetMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book, Model model, HttpSession session) {
		User user = userService.findById((Long)session.getAttribute("userId"));
		model.addAttribute("user", user);
		return "newBook.jsp";
	}
	@PostMapping("/books/new/process")
	public String createBook(Model model, @Valid @ModelAttribute("book") Book book, BindingResult result) {
		if( result.hasErrors()) {
			return "newBook.jsp";
		} else {
			bookService.create(book);
			return "redirect:/books";
		}
	}
	@GetMapping("/books/{bookId}/edit")
	public String editBook(@PathVariable("bookId") Long bookId, HttpSession session, Model viewModel, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		viewModel.addAttribute("loggedUser", userService.findById(userId));
		Book book = bookService.findById(bookId);
		model.addAttribute("book", book);
		
		return "editBook.jsp";
	}
	@RequestMapping(value="/books/{bookId}/edit/process", method=RequestMethod.PUT)
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("book", book);
			return "editBook.jsp";
		} else {
			bookService.updateBook(book);
			return "redirect:/books";
		}
	}
	@DeleteMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") Long bookId) {
    	bookService.deleteBook(bookId);
    	return "redirect:/books";
    }
	
}

