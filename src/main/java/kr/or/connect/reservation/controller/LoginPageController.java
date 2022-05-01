package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPageController {
	@GetMapping(path="/login")
	public String loginCheck(HttpSession session, ModelMap model) {
		String email = (String) session.getAttribute("email");
		if(email == null || email.equals("")) {
			return "login";
		}
		return "redirect:myreservation";
	}
	
	@PostMapping(path="/login")
	public String loginTry(@RequestParam(name = "resrv_email", required = true) String inputEmail, HttpSession session) {
		session.setAttribute("email",inputEmail);
		
		return "redirect:myreservation";
	}
}
