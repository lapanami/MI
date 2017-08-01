package vn.credit.home.service;

import java.util.List;

import vn.credit.home.entity.SecUser;
import vn.credit.home.entity.UserMenu;

public interface IUserService {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);

	List<UserMenu> getUserMenu(String userName);
}
