/**
 * @author loc.mh
 */
package vn.credit.home.dao.mssql;

import java.util.List;
import java.util.Map;

import vn.credit.home.entity.mssql.SecUser;
import vn.credit.home.entity.mssql.UserMenu;

/**
 * @author loc.mh
 *
 */
public interface IMSUserDAO {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);

	List<UserMenu> getUserMenu(String userName, String appId);

	long countUser();

	Map<String, Object> searchUser(int start, int length, String searchKey);

}
