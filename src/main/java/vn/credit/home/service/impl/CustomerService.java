package vn.credit.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.credit.home.dao.ICustomerServiceDAO;
import vn.credit.home.entity.TOpsMisCustomerFeedback;
import vn.credit.home.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	ICustomerServiceDAO customerServiceDAO;
	
	@Override
	public boolean feedback(String username, int point) {
		return customerServiceDAO.feedback(username, point);
	}

	@Override
	public List<TOpsMisCustomerFeedback> getReport(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return customerServiceDAO.getReport(fromDate, toDate);
	}

}
