/**
 * @author loc.mh
 */
package vn.credit.home.config.ext;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

/**
 * @author loc.mh
 *
 */
@Component
public class MyUserDetailsContextMapper extends LdapUserDetailsMapper implements UserDetailsContextMapper {

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		LdapUserDetails userDetails = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);
		ExtLdapUserDetails myUserDetails = new ExtLdapUserDetails(userDetails);
		myUserDetails.setTheMenu(username);
		return myUserDetails;
	}
}
