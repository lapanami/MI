package vn.credit.home.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.credit.home.entity.SecUser;
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
			LdapUserDetails ldapUser = (LdapUserDetails) auth.getPrincipal();
			List<SecUser> listUser = userService.listAllUser();
			model.addAttribute("user", ldapUser);
			model.addAttribute("listUser", listUser);
			ObjectMapper om = new ObjectMapper();
			try {
				model.addAttribute("jsonListUser", om.writeValueAsString(listUser));
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
}
