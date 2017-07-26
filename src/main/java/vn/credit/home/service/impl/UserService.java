package vn.credit.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.credit.home.dao.IUserDAO;
import vn.credit.home.entity.SecUser;
import vn.credit.home.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserDAO userDAO;

	@Override
	public List<SecUser> listAllUser() {
		return userDAO.listAllUser();
	}

	@Override
	public SecUser getUserByUserName(String userName) {
		try {
			return userDAO.getUserByUserName(userName);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public SecUser getUserByUserId(String userId) {
		try {
			return userDAO.getUserByUserId(userId);
		} catch (Exception ex) {
			return null;
		}
	}

}
