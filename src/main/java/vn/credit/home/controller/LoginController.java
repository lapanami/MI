package vn.credit.home.controller;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
@Scope("session")
public class LoginController extends RootController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
	
		model.addAttribute("contextPath", servletContext.getContextPath());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String info = auth.getPrincipal().toString();

		if (info == null || "anonymousUser".equalsIgnoreCase(info)) {
			// chua login
			model.addAttribute("title", "LOGIN");
			return "index";
		} else {
			return "redirect:/Welcome";
		}
	}
}
