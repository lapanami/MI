package vn.credit.home.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.IRqReportDAO;
import vn.credit.home.entity.RqReport;

@Transactional
@Repository
public class RqReportDAO implements IRqReportDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<RqReport> listAllReport() {
		String hql = "FROM RqReport r WHERE r.dsc is not null AND r.name is not null";
		
		@SuppressWarnings("unchecked")
		List<RqReport> resultList = entityManager.createQuery(hql).getResultList();
		return resultList;
	}

	@Override
	public RqReport getReport(String reportId) {
		// TODO Auto-generated method stub
		String hql = "SELECT r FROM RqReport r WHERE r.id LIKE :reportID";
		
		Query query = entityManager.createQuery(hql).setParameter("reportID", reportId);
		
		@SuppressWarnings("unchecked")
		List<RqReport> resultList = query.getResultList();
		RqReport report = resultList.get(0);
		return report;
	}

}
