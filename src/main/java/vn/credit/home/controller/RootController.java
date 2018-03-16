/**
 * @author loc.mh
 */
package vn.credit.home.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
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
		model.addAttribute("user", extLdapUser);
		model.addAttribute("title", "HOME PAGE");
	}
}
