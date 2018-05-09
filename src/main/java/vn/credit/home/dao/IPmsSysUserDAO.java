package vn.credit.home.dao;

import java.util.List;
import vn.credit.home.entity.PmSysUser;

public interface IPmsSysUserDAO {
	List<PmSysUser> listAllUser();
	
	PmSysUser getUser();
}
