/**
 * @author loc.mh
 */
package vn.credit.home.config.ext;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.credit.home.util.constant.LoginType;

/**
 * @author loc.mh
 *
 */
public class ExtLdapUserDetails implements LdapUserDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private LdapUserDetails userDetails;

	private String theMenu;

	private Map<String, Object> controllerPage;

	private LoginType loginType = LoginType.AD;
	

	public static ExtLdapUserDetails createLDAPUserContext(String userName) {
		// TODO: get menu, user role for each page, get userType from secuser
		// appropriate with LDAP user. Use role to get menu and page permissions.
		ExtLdapUserDetails extLdapUserDetails = new ExtLdapUserDetails();
		extLdapUserDetails.setTheMenu(userName);
		extLdapUserDetails.setLoginType(LoginType.LDAP);

		return extLdapUserDetails;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public ExtLdapUserDetails() {

	}

	public ExtLdapUserDetails(LdapUserDetails ldapUserDetails) {
		userDetails = ldapUserDetails;
	}

	public LdapUserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(LdapUserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String getTheMenu() {
		return theMenu;
	}

	public void setTheMenu(String theMenu) {
		this.theMenu = theMenu;
	}

	public Map<String, Object> getRolePage() {
		return controllerPage;
	}

	public void setRolePage(Map<String, Object> rolePage) {
		this.controllerPage = rolePage;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}

	@Override
	public String getPassword() {
		return userDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return userDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return userDetails.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return userDetails.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return userDetails.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return userDetails.isEnabled();
	}

	@Override
	public void eraseCredentials() {
		userDetails.eraseCredentials();
	}

	@Override
	public String getDn() {
		return userDetails.getDn();
	}
	
	@Override
	public String toString() {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (Exception e) {
			return this.toString();
		}
	}
}
