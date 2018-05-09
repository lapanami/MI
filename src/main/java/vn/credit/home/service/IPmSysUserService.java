package vn.credit.home.service;

import java.util.List;

import vn.credit.home.entity.PmSysUser;

public interface IPmSysUserService {
	List<PmSysUser> listAllUser();
	
	PmSysUser getUser();
}
