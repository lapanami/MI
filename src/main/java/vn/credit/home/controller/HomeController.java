package vn.credit.home.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController extends RootController {
	Logger logger = Logger.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		return "home";
	}
}
