package vn.credit.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.credit.home.dao.IPmsSysUserDAO;
import vn.credit.home.entity.PmSysUser;
import vn.credit.home.service.IPmSysUserService;

@Service
public class PmSysUserService implements IPmSysUserService {

	@Autowired
	IPmsSysUserDAO userDAO;
	
	@Override
	public List<PmSysUser> listAllUser() {
		return userDAO.listAllUser();
	}

	@Override
	public PmSysUser getUser() {
		return userDAO.getUser();
	}
	
}
