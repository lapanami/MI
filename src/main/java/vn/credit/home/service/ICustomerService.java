package vn.credit.home.service;

import java.util.List;

import vn.credit.home.entity.TOpsMisCustomerFeedback;

public interface ICustomerService {
	boolean feedback(String username, int point);
	
	List<TOpsMisCustomerFeedback> getReport(String fromDate, String toDate);
}
