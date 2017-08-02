package vn.credit.home.dao.oracle;

import java.util.List;

import vn.credit.home.entity.oracle.SecUser;
import vn.credit.home.entity.oracle.UserMenu;

public interface IUserDAO {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);

	List<UserMenu> getUserMenu(String userName, String appId);
}
