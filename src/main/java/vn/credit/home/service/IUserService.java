package vn.credit.home.service;

import java.util.List;
import java.util.Map;

import vn.credit.home.entity.mssql.UserRole;
import vn.credit.home.entity.oracle.SecUser;
import vn.credit.home.entity.oracle.User;
import vn.credit.home.util.param.Order;

public interface IUserService {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	vn.credit.home.entity.mssql.SecUser getUserByUserId(String userId);

	List<vn.credit.home.entity.mssql.UserMenu> getUserMenu(String userName);

	String genMenuStr(String userName);

	List<vn.credit.home.entity.mssql.SecUser> listAllMSUser();

	long countUser();

	Map<String, Object> searchUser(int start, int length, String searchKey);

	Map<String, Object> searchUser(int start, int length, String searchKey, List<Order> orders);

	void putUser(vn.credit.home.entity.mssql.SecUser user);

	List<UserRole> getUserRole(String userName);

	Map<String, Object> mapUserRole(String userName);

	List<User> listUsers(String userName);
}
