package vn.credit.home.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.IPmsSysUserDAO;
import vn.credit.home.entity.PmSysUser;


@Transactional
@Repository
public class PmsSysUserDAO implements IPmsSysUserDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<PmSysUser> listAllUser() {
		String hql = "FROM PmSysUser";
		return entityManager.createQuery(hql).getResultList();
	}
	
	public PmSysUser getUser() {
		String hql = "SELECT s FROM PmSysUser s WHERE s.username = 'minh.lpn'";
		Query query = entityManager.createQuery(hql);
		List<PmSysUser> users = query.getResultList();
		PmSysUser user = users.get(0);
		return user;
	}

}
