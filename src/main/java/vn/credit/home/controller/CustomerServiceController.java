package vn.credit.home.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.credit.home.entity.TOpsMisCustomerFeedback;
import vn.credit.home.helper.ApachePOIExcelWrite;
import vn.credit.home.service.ICustomerService;

@Controller
public class CustomerServiceController extends RootController{
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ServletContext servletContext;	
	
	@Autowired
	private ICustomerService custService;
	
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public String listAllFeedback(Model model, HttpSession session) {
		super.initController(model, servletContext);
		return "Feedback";
	}
	
	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public String feedback(@RequestParam(value = "name") String username, @RequestParam(value = "point") int point) {
		System.out.println(username);
		System.out.println(point);
		try {
			boolean result = custService.feedback(username, point);
			return result?"success":"fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}		
	}
	
	@PostMapping("/export")
	public String exportReport(@RequestParam(value = "from") String fromDate, @RequestParam(value = "to") String toDate) {
//		System.out.println(fromDate);
//		System.out.println(toDate);
		List<TOpsMisCustomerFeedback> returnList = custService.getReport(fromDate, toDate);
//		List<Object> objectList = new ArrayList<Object>(returnList);
		
		
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		
		for(int i = 0; i < returnList.size(); i ++) {
			data.put(i+1, new Object[]{ returnList.get(i).getUsername(), returnList.get(i).getVote(), returnList.get(i).getTimestamp() });			
		}
        
        String[] headers = {"Username", "Vote", "Date"};
				
		ApachePOIExcelWrite("08.05.18", headers, data, "TestExcel","Sheet1");
//		System.out.println(returnList.get(0).getTimestamp());
//		System.out.println(toDate);
		return "redirect:/feedback";
	}
	
	public void ApachePOIExcelWrite(String url_Save_Date, String[] headers, Map<Integer, Object[]> data, String fileName, String nameSheet) {
		
		String home = System.getProperty("user.home");
		String fileDirectory = home+"/Downloads/" + fileName + ".xlsx";
		File file = new File(fileDirectory);
		
		// Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
 
        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Sheet 1");
        
        DataFormat df = workbook.createDataFormat();
		CellStyle cs = workbook.createCellStyle();
		cs.setDataFormat(df.getFormat("dd-MM-yyyy"));
		
		
		// Create a Font for styling header cells
		CellStyle headerCs = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		headerCs.setFont(font);
		headerCs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT
				.getIndex());
		
		
		// Create a Row
        Row headerRow = sheet.createRow(0);
        
        
		// Creating header cells
        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCs);
        }
 
        // This data needs to be written (Object[])
        
 
        // Iterate over data and write to sheet
        Set<Integer> keyset = data.keySet();
        int rownum = 1;
        for (int key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                
                if (obj instanceof Date) {
					cell.setCellStyle(cs);
					cell.setCellValue((Date) obj);
				} 
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof BigDecimal)
					cell.setCellValue(((BigDecimal) obj).doubleValue());
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				else if (obj instanceof Long)
					cell.setCellValue((Long) obj);
				else if (obj instanceof Float)
					cell.setCellValue((Float) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				else if (obj instanceof BigInteger)
					cell.setCellValue((Double) obj);
				else if (obj != null)
					cell.setCellValue(obj.toString());
            }
        }
        
        // Resize all columns to fit the content size
        for(int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        try {
            // this Writes the workbook gfgcontribute
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            System.out.println("File has been written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
