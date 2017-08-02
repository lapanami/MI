package vn.credit.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import vn.credit.home.dao.mssql.IMSUserDAO;
import vn.credit.home.dao.oracle.IUserDAO;
import vn.credit.home.entity.oracle.SecUser;
import vn.credit.home.entity.oracle.UserMenu;
import vn.credit.home.service.IUserService;

@Service
public class UserService implements IUserService {

	@Value("${application.id}")
	String appId;

	@Autowired
	IUserDAO userDAO;

	@Autowired
	IMSUserDAO msuserDAO;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Get user menu by execute stored procedure SP_SEC_USER_MENU_GET
	 */
	@Override
	public List<UserMenu> getUserMenu(String userName) {
		return userDAO.getUserMenu(userName, appId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.credit.home.service.IUserService#listAllMSUser()
	 */
	@Override
	public List<vn.credit.home.entity.mssql.SecUser> listAllMSUser() {
		return msuserDAO.listAllUser();
	}

}
