package vn.credit.home.dao.impl;

import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.credit.home.dao.ICustomerServiceDAO;
import vn.credit.home.entity.TOpsMisCustomerFeedback;

@Transactional
@Repository
public class CustomerServiceDAO implements ICustomerServiceDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean feedback(String username, int point) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH.mm.ss");
		BigDecimal vote = new BigDecimal(point);
		
//		entityManager.getTransaction().begin();
//		
//		String hql = "UPDATE T_OPS_MIS_CUSTOMER_FEEDBACK t SET t.id = :id, t.username= :username, t.vote = :vote, t.timestamp = :timestamp";
//		Query query = entityManager.createNativeQuery(hql);
//		query.setParameter("id", id);
//		query.setParameter("username", username);
//		query.setParameter("vote", point);
//		query.setParameter("timestamp", sdf.format(timestamp));
//		query.executeUpdate();
//		
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		
		try {
			TOpsMisCustomerFeedback feedback = new TOpsMisCustomerFeedback();
			feedback.setId(id);
			feedback.setUsername(username);
			feedback.setVote(vote);
			feedback.setTimestamp(timestamp);
			entityManager.persist(feedback);
			return true;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		finally {
			entityManager.close();
		}
		return false;
	}

	@Override
	public List<TOpsMisCustomerFeedback> getReport(String fromDate, String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	Date dateFrom, dateTo;
		try {
			dateFrom = sdf.parse(fromDate);
			dateTo = sdf.parse(toDate);
			sdf.applyPattern("yyyy/MM/dd"); 
//	    	String from = sdf.format(dateFrom);
//	    	String to = sdf.format(dateTo);
	    	
	    	String hql = "SELECT f FROM TOpsMisCustomerFeedback f WHERE f.timestamp BETWEEN :fromDate AND :toDate";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("fromDate", dateFrom);
	    	query.setParameter("toDate", dateTo);
	    	
//			String hql1 = "FROM TOpsMisCustomerFeedback";
			List<TOpsMisCustomerFeedback> returnList = query.getResultList();
			return returnList;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
    	return null;   			
	}

}
