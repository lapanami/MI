package vn.credit.home.service;

import java.util.List;

import vn.credit.home.entity.RqReport;

public interface IRqReportService {
	List<RqReport> listAllReport();
	
	RqReport getReport(String reportId);
}
