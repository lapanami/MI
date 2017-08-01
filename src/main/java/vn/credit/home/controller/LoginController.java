package vn.credit.home.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.credit.home.service.IUserService;

@Controller
@RequestMapping("/")
@Scope("session")
public class LoginController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String info = auth.getPrincipal().toString();
		if (info == null || "anonymousUser".equalsIgnoreCase(info)) {
			// chua login
			return "login";
		} else {
			return "redirect:/welcome";
		}
	}
}
