package vn.credit.home.config.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import vn.credit.home.config.ext.ExtLdapUserDetails;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

	@Value("${server.session.cookie.name}")
	String sessionID = "sessionID";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("this is interceptor, preHandle method");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String info = auth.getPrincipal().toString();
		if (info == null || "anonymousUser".equalsIgnoreCase(info)) {
			// chua login yet
			return false;
		} else {
			ExtLdapUserDetails userDetails = (ExtLdapUserDetails) auth.getPrincipal();
			Map<String, Object> mapRolePage = userDetails.getRolePage();
			List<String> listHome = (mapRolePage.get("Home") == null ? new ArrayList<>()
					: (List<String>) mapRolePage.get("Home"));
			String controllerUri = request.getRequestURI().substring(request.getContextPath().length());
			if ("/welcome".equalsIgnoreCase(controllerUri) || "/home".equalsIgnoreCase(controllerUri)
					|| "/index".equalsIgnoreCase(controllerUri)) {
				if (!listHome.isEmpty()) {
					return true;
				} else {
					return false;
				}
			} else {
				String[] arrStr = controllerUri.split("/");
				if (arrStr.length != 3) {
					// invalid uri
					return false;
				} else {
					List<String> listPageName = (mapRolePage.get(arrStr[1]) == null ? new ArrayList<>()
							: (List<String>) mapRolePage.get(arrStr[1]));
					if (listPageName.contains(arrStr[2])) {
						return true;
					} else {
						// access denied
						return false;
					}
				}
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("this is interceptor, postHandle method");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("this is interceptor, afterCompletion method");
	}
}
