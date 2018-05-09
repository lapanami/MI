package vn.credit.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.credit.home.dao.IRqReportDAO;
import vn.credit.home.entity.RqReport;
import vn.credit.home.service.IRqReportService;

@Service
public class RqReportService implements IRqReportService{
	
	@Autowired
	IRqReportDAO reportDAO;

	@Override
	public List<RqReport> listAllReport() {
		return reportDAO.listAllReport();
	}

	@Override
	public RqReport getReport(String reportId) {
		return reportDAO.getReport(reportId);
	}
	
	
}
