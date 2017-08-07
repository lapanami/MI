/**
 * @author loc.mh
 */
package vn.credit.home.dao.mssql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.mssql.IMSUserDAO;
import vn.credit.home.entity.mssql.SecUser;
import vn.credit.home.entity.mssql.UserMenu;
import vn.credit.home.util.param.Order;

/**
 * @author loc.mh
 *
 */
@Transactional
@Repository
public class MSUserDAO implements IMSUserDAO {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${application.id.blank}")
	String blankId;

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

	@Override
	public Map<String, Object> searchUser(int start, int length, String searchKey, List<Order> orders) {
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
		for (Order order : orders) {
			if (StringUtils.isNotEmpty(order.getColumn())) {
				if ("asc".equalsIgnoreCase(order.getDir())) {
					crit.addOrder(org.hibernate.criterion.Order.asc(order.getColumn()));
				} else {
					crit.addOrder(org.hibernate.criterion.Order.desc(order.getColumn()));
				}
			}
		}
		result.put("data", crit.list());
		result.put("total", total);
		return result;
	}

	@Override
	public void putUser(SecUser user) {
		Session session = getsession(entityManager);
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
	}

	@Override
	public Map<String, Object> mapUserMenu(String userName, String appId) {
		List<UserMenu> listMenu = getUserMenu(userName, appId);
		if (listMenu.isEmpty()) {
			return new LinkedHashMap<>();
		}
		Map<String, Object> result = new LinkedHashMap<>();
		Map<String, List<String>> treeMenu = new LinkedHashMap<>();
		Map<String, UserMenu> mapMenu = new LinkedHashMap<>();
		listMenu.stream().forEach((menu) -> {
			mapMenu.put(menu.getPageId(), menu);
			if (!blankId.equalsIgnoreCase(menu.getPageParentId())) {
				Object object = treeMenu.get(menu.getPageParentId());
				List<String> listSub;
				if (object == null) {
					listSub = new ArrayList<>();
				} else {
					listSub = (List<String>) object;
				}
				listSub.add(menu.getPageId());
				treeMenu.put(menu.getPageParentId(), listSub);
			}
		});
		result.put("map", mapMenu);
		result.put("tree", treeMenu);
		return result;
	}

}
