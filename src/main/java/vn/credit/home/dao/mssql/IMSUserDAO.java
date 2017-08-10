/**
 * @author loc.mh
 */
package vn.credit.home.dao.mssql;

import java.util.List;
import java.util.Map;

import vn.credit.home.entity.mssql.SecUser;
import vn.credit.home.entity.mssql.UserMenu;
import vn.credit.home.entity.mssql.UserRole;
import vn.credit.home.util.param.Order;

/**
 * @author loc.mh
 *
 */
public interface IMSUserDAO {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);

	List<UserMenu> getUserMenu(String userName, String appId);

	String genMenuStr(String userName, String appId);

	long countUser();

	Map<String, Object> searchUser(int start, int length, String searchKey);

	Map<String, Object> searchUser(int start, int length, String searchKey, List<Order> orders);

	void putUser(SecUser user);

	List<UserRole> getUserRole(String userName, String appId);

	Map<String, Object> mapUserRole(String userName, String appId);

}
