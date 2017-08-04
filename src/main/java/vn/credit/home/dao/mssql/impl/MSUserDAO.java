/**
 * @author loc.mh
 */
package vn.credit.home.dao.mssql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
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

import vn.credit.home.dao.mssql.IMSUserDAO;
import vn.credit.home.entity.mssql.SecUser;
import vn.credit.home.entity.mssql.UserMenu;

/**
 * @author loc.mh
 *
 */
@Transactional
@Repository
public class MSUserDAO implements IMSUserDAO {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("mssqlEntityManager")
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

	@Override
	public long countUser() {
		Session session = getsession(entityManager);
		Criteria crit = session.createCriteria(SecUser.class);
		return (long) crit.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Map<String, Object> searchUser(int start, int length, String searchKey) {
		Map<String, Object> result = new HashMap<>();
		Session session = getsession(entityManager);
		Criteria crit = session.createCriteria(SecUser.class);
		if (StringUtils.isNotEmpty(searchKey)) {
			crit.add(Restrictions.like("userName", "%" + searchKey + "%"));
		}

		ScrollableResults results = crit.scroll();
		results.last();
		int total = results.getRowNumber() + 1;
		results.close();

		crit.setFirstResult(start).setMaxResults(length);
		result.put("data", crit.list());
		result.put("total", total);
		return result;
	}

}
