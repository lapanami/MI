package vn.credit.home.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.credit.home.entity.PmSysUser;
import vn.credit.home.entity.RqReport;
import vn.credit.home.helper.LibaryUtil;
import vn.credit.home.service.IPmSysUserService;
import vn.credit.home.service.IRqReportService;

/**
 * @author minh.lpn
 *
 */
@Controller
public class ReportController extends RootController{
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ServletContext servletContext;	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IPmSysUserService userService;
	
	@Autowired
	private IRqReportService reportService;
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String getAllReport(Model model, HttpSession session) {
		List<RqReport> reports = reportService.listAllReport();
		super.initController(model, servletContext);
		model.addAttribute("reportList", reports);
//		Connection connection = null;
//		try {
//			connection = LibaryUtil.getOracleConnection();
//			Statement stmt = connection.createStatement();
//			ResultSet rs = null;
//			rs = stmt.executeQuery("select a.CREATION_DATE, a.CONTRACT_CODE, B.name "
//					+ "from OWNER_INT.VH_HOM_CONTRACT a "
//					+ "join OWNER_INT.VH_HOM_PRODUCT b on a.PRODUCT_ID = B.id "
//					+ "where a.CREATION_DATE between sysdate-1 and sysdate");
//			int x = 1;
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int columnsNumber = rsmd.getColumnCount();
//			while(rs.next()){
//			    System.out.println(x + ". " + rs.getString(1).trim() + " " 
//			                       + rs.getString(2).trim() + " " + rs.getString(3).trim());
//			    x++;
//			}
//		} catch (SQLException e) {
//			System.out.println("Connection Failed! Check output console");
//            e.printStackTrace();
//		}

		return "Report";
	}
	
	@RequestMapping(value = "/report/{id}", method = RequestMethod.POST)
	public RqReport getReport(@RequestParam("reportID") String reportId) {
		RqReport report = reportService.getReport(reportId);
		return report;
	}

}
