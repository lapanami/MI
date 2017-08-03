package vn.credit.home.service;

import java.util.List;

import vn.credit.home.entity.oracle.SecUser;

public interface IUserService {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);

	List<vn.credit.home.entity.mssql.UserMenu> getUserMenu(String userName);

	List<vn.credit.home.entity.mssql.SecUser> listAllMSUser();
}
