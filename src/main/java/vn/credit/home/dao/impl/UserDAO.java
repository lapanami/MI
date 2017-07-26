package vn.credit.home.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.IUserDAO;
import vn.credit.home.entity.SecUser;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SecUser> listAllUser() {
		String hql = "FROM SecUser ORDER BY firstName";
		return entityManager.createQuery(hql).getResultList();
	}

	@Override
	public SecUser getUserByUserName(String userName) {
		Query query = entityManager.createQuery("FROM SecUser s WHERE s.userName = :userName");
		query.setParameter("userName", userName);
		List<SecUser> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public SecUser getUserByUserId(String userId) {
		return entityManager.find(SecUser.class, userId);
	}

}
