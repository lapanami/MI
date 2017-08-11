/**
 * @author loc.mh
 */
package vn.credit.home.controller;

import javax.servlet.ServletContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import vn.credit.home.config.ext.ExtLdapUserDetails;

/**
 * @author loc.mh Root Controller auto init, get menu, logged in user info
 */
public class RootController {
	public void initController(Model model, ServletContext servletContext) {
		model.addAttribute("contextPath", servletContext.getContextPath());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ExtLdapUserDetails extLdapUser = (ExtLdapUserDetails) auth.getPrincipal();
		model.addAttribute("theMenu", extLdapUser.getTheMenu());
		model.addAttribute("rolePage", extLdapUser.getRolePage());
		model.addAttribute("user", extLdapUser);
		model.addAttribute("title", "Welcome to Home Credit Viet Nam");
	}
}
