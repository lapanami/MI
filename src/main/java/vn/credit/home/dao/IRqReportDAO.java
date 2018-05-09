package vn.credit.home.dao;

import java.util.List;

import vn.credit.home.entity.RqReport;

public interface IRqReportDAO {
	List<RqReport> listAllReport();
	
	RqReport getReport(String reportId);
}
