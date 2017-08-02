package vn.credit.home.service;

import java.util.List;

import vn.credit.home.entity.oracle.SecUser;
import vn.credit.home.entity.oracle.UserMenu;

public interface IUserService {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);

	List<UserMenu> getUserMenu(String userName);

	List<vn.credit.home.entity.mssql.SecUser> listAllMSUser();
}
