package vn.credit.home.dao;

import java.util.List;

import vn.credit.home.entity.SecUser;

public interface IUserDAO {
	List<SecUser> listAllUser();

	SecUser getUserByUserName(String userName);

	SecUser getUserByUserId(String userId);
}
