package vn.credit.home.dao.oracle.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.oracle.IUserDAO;
import vn.credit.home.entity.oracle.SecUser;
import vn.credit.home.entity.oracle.User;
import vn.credit.home.entity.oracle.UserMenu;

@Transactional
@Repository
public class UserDAO implements IUserDAO {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("oracleEntityManager")
	EntityManager entityManager;

	private Session getsession(EntityManager entityManager) {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List<SecUser> listAllUser() {
		Criteria criteria = getsession(entityManager).createCriteria(SecUser.class);
		return criteria.list();
	}

	@Override
	public SecUser getUserByUserName(String userName) {
		Criteria criteria = getsession(entityManager).createCriteria(SecUser.class);
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
		return getsession(entityManager).get(SecUser.class, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Get user menu by execute stored procedure SP_SEC_USER_MENU_GET
	 */
	@Override
	public List<UserMenu> getUserMenu(String userName, String appId) {
		Session session = getsession(entityManager);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.credit.home.dao.oracle.IUserDAO#listUsers(java.lang.String)
	 */
	@Override
	public List<User> listUsers(String userName) {
		Session session = getsession(entityManager);
		ProcedureCall proc = session.getNamedProcedureCall("listUser");
		proc.getRegisteredParameters().stream().forEach((param) -> {
			if ("I_USERNAME".equalsIgnoreCase(param.getName())) {
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
