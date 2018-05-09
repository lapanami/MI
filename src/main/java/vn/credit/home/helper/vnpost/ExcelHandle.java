package vn.credit.home.helper.vnpost;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelHandle {
	private File file; 
	public ExcelHandle(File file){
		this.file = file;
	}

	public Vector<Object[]> read() throws Exception {
	    //Get the workbook instance for XLS file 
		Vector<Object[]> ret = new Vector<Object[]>();
		Object[] rowData;
		int rowIndex;
		FileInputStream fileInputStream = new FileInputStream(file);
		try{
		    HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
	        	rowIndex = 0;
	        	rowData = new Object[5]; 
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		            Cell cell = cellIterator.next();
		             
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                	rowData[rowIndex] = cell.getBooleanCellValue();
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                	rowData[rowIndex] = cell.getNumericCellValue();
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                	rowData[rowIndex] = cell.getStringCellValue();
		                    break;
		            }
		            
		            rowIndex++;
		        }
		        ret.add(rowData);
		    }
		    return ret;
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println();
			throw ex;
		}
		finally{
			fileInputStream.close();
		}
	}
	
//	public void writeExcelFile(Object[][] rows, String processID) throws Exception {
//		writeExcelFile(rows, processID, -1);
//	}
	
//	public void writeExcelFile(Object[][] rows, String processID, int column) throws Exception {
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet(processID);
//		int rownum = 0;
//		
//		if(column != -1){
//			String lastCol = CellReference.convertNumToColString(column - 1);
//			sheet.setAutoFilter(CellRangeAddress.valueOf("A1:" + lastCol + "1"));
//		}
//		
//		CellStyle headerCs = workbook.createCellStyle();
//		Font font = workbook.createFont();
//		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//		headerCs.setFont(font);
//		headerCs.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//		headerCs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//		
//
//		
//		CellStyle rowCs = workbook.createCellStyle();
//		rowCs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		rowCs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//		//font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//		//rowCs.setFont(font);
//		
//		Object[] objArr;
//		for (int i = 0; i< rows.length; i++) {
//			objArr = rows[i];
//			Row row = sheet.createRow(rownum++);
//			int cellnum = 0;
//			for (Object obj : objArr) {
//				Cell cell = row.createCell(cellnum++);
//				
//				if(i==0){
//					cell.setCellStyle(headerCs);
//				}
//				else if (i%2==0){
//					cell.setCellStyle(rowCs);
//				}
//				
//				if (obj instanceof Date)
//					cell.setCellValue((Date) obj);
//				else if (obj instanceof Boolean)
//					cell.setCellValue((Boolean) obj);
//				else if (obj instanceof String)
//					cell.setCellValue((String) obj);
//				else if (obj instanceof Double)
//					cell.setCellValue((Double) obj);
//				else if (obj instanceof Integer)
//					cell.setCellValue((Integer) obj);
//				else if (obj instanceof Long)
//					cell.setCellValue((Long) obj);
//				else if (obj instanceof Float)
//					cell.setCellValue((Float) obj);
//				
//				//sheet.autoSizeColumn(cellnum-1);
//			}
//		}
//		try {			
//			FileOutputStream out = new FileOutputStream(file);
//			workbook.write(out);
//			out.close();
//			System.out.println("Excel written successfully..");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
