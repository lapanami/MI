package vn.credit.home.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import vn.credit.home.entity.SecUser;
import vn.credit.home.service.IUserService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		authentication.getCredentials().toString();

		SecUser user = userService.getUserByUserName(name);
		if (user == null) {
			return null;
		} else {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUserName(),
					user.getPassword(), new ArrayList<>());
			auth.setDetails(user);
			return auth;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}