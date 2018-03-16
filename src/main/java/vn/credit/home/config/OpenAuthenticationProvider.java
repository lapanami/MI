/**
 * @author loc.mh
 */
package vn.credit.home.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import vn.credit.home.config.ext.ExtLdapUserDetails;
import vn.credit.home.config.object.AuthenException;

/**
 * @author loc.mh
 *
 */
@Component
public class OpenAuthenticationProvider implements AuthenticationProvider {

	@Value("${openam.url}")
	private String URL;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String openURL = String.format(URL, authentication.getName());
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(openURL);

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("password", String.valueOf(authentication.getCredentials())));
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new BadCredentialsException("External system authentication failed");
		}

		HttpResponse response;
		try {
			response = client.execute(post);

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(instream));
					String line = null;
					while ((line = in.readLine()) != null) {
						if (StringUtils.contains(line, "exception")) {
							ObjectMapper om = new ObjectMapper();
							AuthenException ex = om.readValue(line, AuthenException.class);
							if (ex != null && ex.getException().getMessage().equals("Authentication Error!!")) {
								throw new BadCredentialsException("External system authentication error");
							} else if (ex != null) {
								throw new BadCredentialsException(
										"External system authentication fail, wrong password");
							}
						}
					}
					ExtLdapUserDetails principal = ExtLdapUserDetails.createLDAPUserContext(authentication.getName());
					return new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(),
							Collections.emptyList());
				} finally {
					instream.close();
				}
			}
		} catch (IOException e) {
			throw new BadCredentialsException("External system authentication failed");
		}

		throw new BadCredentialsException("External system authentication failed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports(
	 * java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
