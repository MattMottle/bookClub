package com.mattmottle.logandreg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mattmottle.logandreg.models.LoginUser;
import com.mattmottle.logandreg.models.User;
import com.mattmottle.logandreg.services.BookService;
import com.mattmottle.logandreg.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	
	@GetMapping("/")
	public String loginRegPage(@ModelAttribute("registerUser") User registerUser, 
		@ModelAttribute("loginUser") LoginUser loginUser) {
		return "logReg.jsp";
	}
	@PostMapping("/register")
	public String processRegistraion(@Valid @ModelAttribute("registerUser") User registerUser, 
			BindingResult result, @ModelAttribute("loginUser") LoginUser loginUser, HttpSession session) {
		// perform the additional validations and add to db if validations are good
		User theNewUser = userService.register(registerUser, result);
		if(result.hasErrors()){
			return "logReg.jsp";
		}else {
			// save the id of the logged in user in session
			session.setAttribute("userId", theNewUser.getId());
			return "redirect:/books";
		}
	}
	
	@PostMapping("/login")
	public String processLogin(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result,
			@ModelAttribute("registerUser") User registerUser, HttpSession session) {
		User foundUser = userService.login(loginUser,  result);
		if(result.hasErrors() ) {
			return "logReg.jsp";
		} else {
		session.setAttribute("userId", foundUser.getId());
		return "redirect:/books";	
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
