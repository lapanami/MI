package vn.credit.home.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.credit.home.entity.SecUser;
import vn.credit.home.service.IUserService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getPrincipal());
		List<SecUser> listUser = userService.listAllUser();
		model.addAttribute("listUser", listUser);
		return "home";
	}
}
