/**
 * @author loc.mh
 */
package vn.credit.home.dao.mssql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import vn.credit.home.entity.mssql.UserRole;
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
	public String genMenuStr(String userName, String appId) {
		List<UserMenu> listMenu = getUserMenu(userName, appId);
		if (listMenu.isEmpty()) {
			return "";
		}
		Map<String, Object> treeMenu = new LinkedHashMap<>();
		Map<String, Object> mapTreeMenu = new LinkedHashMap<>();
		Map<String, UserMenu> mapMenu = new LinkedHashMap<>();
		// init the menu list
		List<UserMenu> theLoop = new ArrayList<>();
		List<Object> theTmpLoop = new ArrayList<>();
		listMenu.stream().forEach((menu) -> {
			mapMenu.put(menu.getPageId(), menu);
			if (blankId.equalsIgnoreCase(menu.getPageParentId())) {
				theLoop.add(menu);
				theTmpLoop.add(menu.getPageId());
				treeMenu.put(menu.getPageId(), new ArrayList<Object>());
				mapTreeMenu.put(menu.getPageId(), treeMenu.get(menu.getPageId()));
			}
		});
		while (!theLoop.isEmpty()) {
			UserMenu menu = theLoop.remove(0);
			List<Object> tmpList = (List<Object>) mapTreeMenu.get(menu.getPageId());
			List<UserMenu> listTmpMenu = listMenu.stream()
					.filter(subMenu -> subMenu.getPageParentId().equals(menu.getPageId())).collect(Collectors.toList());
			listTmpMenu.stream().forEach(subMenu -> {
				Map<String, Object> tmpMap = new LinkedHashMap<>();
				tmpMap.put(subMenu.getPageId(), new ArrayList<Object>());
				mapTreeMenu.put(subMenu.getPageId(), tmpMap.get(subMenu.getPageId()));
				tmpList.add(tmpMap);
				theLoop.add(subMenu);
			});
		}
		return genMenuStr(null, theTmpLoop, mapMenu, mapTreeMenu);
	}

	private String genMenuStr(UserMenu menu, List<Object> listMenu, Map<String, UserMenu> mapMenu,
			Map<String, Object> mapTreeMenu) {
		StringBuilder menuBuilder = new StringBuilder();
		if (menu != null) {
			if (listMenu.isEmpty()) {
				menuBuilder.append("<li><a href='/" + menu.getControllerName() + "/" + menu.getPageName() + "' title='"
						+ menu.getPageTitle() + "'>" + menu.getPageTitle() + "</a></li>");
			} else {
				menuBuilder.append("<li><a href='/" + menu.getControllerName() + "/" + menu.getPageName() + "' title='"
						+ menu.getPageTitle() + "'>" + menu.getPageTitle() + "</a><ul>");
				listMenu.stream().forEach(tMenu -> {
					Map<String, Object> tMapTreeMenu = (Map<String, Object>) tMenu;
					tMapTreeMenu.entrySet().forEach(entry -> {
						List<Object> subList = (List<Object>) entry.getValue();
						menuBuilder.append(genMenuStr(mapMenu.get(entry.getKey()), subList, mapMenu, mapTreeMenu));
					});
				});
				menuBuilder.append("</ul></li>");
			}
		} else {
			listMenu.stream().forEach(tmpMenu -> {
				List<Object> listTmp = (List<Object>) mapTreeMenu.get(tmpMenu);
				menuBuilder.append(genMenuStr(mapMenu.get(tmpMenu), listTmp, mapMenu, mapTreeMenu));
			});
		}
		return menuBuilder.toString();
	}

	@Override
	public List<UserRole> getUserRole(String userName, String appId) {
		Session session = getsession(entityManager);
		ProcedureCall proc = session.getNamedProcedureCall("getUserRole");
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
	public Map<String, Object> mapUserRole(String userName, String appId) {
		List<UserRole> listUserRole = getUserRole(userName, appId);
		if (listUserRole.isEmpty()) {
			return new LinkedHashMap<>();
		}
		Map<String, Object> result = new LinkedHashMap<>();
		listUserRole.stream().forEach((userRole) -> {
			List<String> listPage;
			if (result.containsKey(userRole.getControllerName())) {
				listPage = (List<String>) result.get(userRole.getControllerName());
			} else {
				listPage = new ArrayList<>();
			}
			if (!listPage.contains(userRole.getPageName())) {
				listPage.add(userRole.getPageName());
			}
			result.put(userRole.getControllerName(), listPage);
		});
		return result;
	}

}
