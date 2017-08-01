package vn.credit.home.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.IUserDAO;
import vn.credit.home.entity.SecUser;
import vn.credit.home.entity.UserMenu;

@Transactional
@Repository
public class UserDAO implements IUserDAO {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<SecUser> listAllUser() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SecUser.class);
		return criteria.list();
	}

	@Override
	public SecUser getUserByUserName(String userName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SecUser.class);
		criteria.add(Restrictions.eq("userName", userName));
		List<SecUser> list = criteria.list();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public SecUser getUserByUserId(String userId) {
		return sessionFactory.getCurrentSession().get(SecUser.class, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Get user menu by execute stored procedure SP_SEC_USER_MENU_GET
	 */
	@Override
	public List<UserMenu> getUserMenu(String userName, String appId) {
		Session session = sessionFactory.getCurrentSession();
		ProcedureCall proc = session.getNamedProcedureCall("getUserMenu");
		proc.getRegisteredParameters().stream().forEach((param) -> {
			if ("I_APPLICATION_ID".equalsIgnoreCase(param.getName())) {
				param.bindValue(appId);
			} else if ("I_USERNAME".equalsIgnoreCase(param.getName())) {
				param.bindValue(userName);
			}
		});
		Output output = proc.getOutputs().getCurrent();
		if (output.isResultSet()) {
			return ((ResultSetOutput) output).getResultList();
		}
		return new ArrayList<>();

	}

}
