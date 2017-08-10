/**
 * @author loc.mh
 */
package vn.credit.home.config.ext;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

/**
 * @author loc.mh
 *
 */
public class ExtLdapUserDetails implements LdapUserDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private LdapUserDetails userDetails;

	private String theMenu;

	private Map<String, Object> rolePage;

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
		return rolePage;
	}

	public void setRolePage(Map<String, Object> rolePage) {
		this.rolePage = rolePage;
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
}
