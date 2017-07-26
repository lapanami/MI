package vn.credit.home.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.credit.home.entity.SecUser;
import vn.credit.home.service.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String loginPost(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password, Model model) {
		if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
			SecUser user = userService.getUserByUserName(username);
			if (user != null) {
				List<SecUser> listUser = userService.listAllUser();
				model.addAttribute("user", user);
				model.addAttribute("listUser", listUser);
				ObjectMapper om = new ObjectMapper();
				try {
					model.addAttribute("jsonListUser", om.writeValueAsString(listUser));
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
					e.printStackTrace();
				}
				return "welcome";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}
}
