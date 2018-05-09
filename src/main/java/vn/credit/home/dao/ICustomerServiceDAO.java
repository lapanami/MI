package vn.credit.home.dao;

import java.util.List;

import vn.credit.home.entity.TOpsMisCustomerFeedback;

public interface ICustomerServiceDAO {
	boolean feedback(String username, int point);
	
	List<TOpsMisCustomerFeedback> getReport(String fromDate, String toDate);
}
