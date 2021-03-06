/**
 * @author loc.mh
 */
package vn.credit.home.controller;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 * @author loc.mh
 *
 */
@Controller
@RequestMapping("/404")
public class Error404Controller extends RootController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	ErrorPageFilter errorPageFilter;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpRequestHandlerServlet req, Model model) {
		super.initController(model, servletContext);
		model.addAttribute("title",  "404");
		return "404";
	}
}
