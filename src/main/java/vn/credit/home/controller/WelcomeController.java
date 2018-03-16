package vn.credit.home.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author loc.mh
 *
 */
@Controller
@RequestMapping("/Welcome")
public class WelcomeController extends RootController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ServletContext servletContext;	
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		
		super.initController(model, servletContext);
		model.addAttribute("title", "WELCOME");
		return "home";
	}
}
