package vn.credit.home.util.constant;
/**
 * @author loc.mh
 */


/**
 * @author loc.mh
 *
 */
public enum LoginType {
	LDAP(1), AD(2);

	private int type;

	LoginType(int t) {
		this.type = t;
	}

	public int getType() {
		return this.type;
	}
}
