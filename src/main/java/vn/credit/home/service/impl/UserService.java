package vn.credit.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import vn.credit.home.dao.mssql.IMSUserDAO;
import vn.credit.home.dao.oracle.IUserDAO;
import vn.credit.home.entity.oracle.SecUser;
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
	public List<vn.credit.home.entity.mssql.UserMenu> getUserMenu(String userName) {
		return msuserDAO.getUserMenu(userName, appId);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.credit.home.service.IUserService#countUser()
	 */
	@Override
	public long countUser() {
		return msuserDAO.countUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.credit.home.service.IUserService#searchUser(int, int,
	 * java.lang.String)
	 */
	@Override
	public Map<String, Object> searchUser(int start, int length, String searchKey) {
		return msuserDAO.searchUser(start, length, searchKey);
	}

}
