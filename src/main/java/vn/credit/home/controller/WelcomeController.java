package vn.credit.home.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.credit.home.entity.oracle.SecUser;
import vn.credit.home.service.IUserService;

/**
 * @author loc.mh
 *
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController extends RootController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ServletContext servletContext;

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		super.initController(model, servletContext);
		List<SecUser> listUser = userService.listAllUser();
		List<vn.credit.home.entity.mssql.SecUser> listMSUser = userService.listAllMSUser();
		model.addAttribute("title", "Welcome to Home Credit Viet Nam");
		model.addAttribute("listUser", listUser);
		model.addAttribute("listMSUser", listMSUser);
		ObjectMapper om = new ObjectMapper();
		try {
			model.addAttribute("jsonListUser", om.writeValueAsString(listUser));
			model.addAttribute("jsonListMSUser", om.writeValueAsString(listMSUser));
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "home";
	}
}
