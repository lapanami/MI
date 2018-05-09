package vn.credit.home.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.credit.home.entity.PmSysUser;
import vn.credit.home.service.IPmSysUserService;

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
	
	@Autowired
	private IPmSysUserService userSer;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		
		
		//PmSysUser returnList = userSer.getUser();
		super.initController(model, servletContext);
		model.addAttribute("title", "WELCOME");
		return "Dashboard";
	}
}
