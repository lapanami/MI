/**
 * 
 */
package vn.credit.home.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author an.nd
 *
 */
public class ApachePOIExcelWrite {

	/**
	 * 
	 */

	private static final String FILE_NAME = "C:\\Users\\minh.lampn\\Downloads\\MyFirstExcel.xlsx";

	public static void ApachePOIExcelWrite(String url_Save_Date, List<Object> listObject, String nameSheet) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(nameSheet);
		List<Object> listData = listObject;

		int rowNum = 0;
		for (Object datatype : listData) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;			
			for (Object field : listData) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ApachePOIExcelRead(String url_File_Read) {
		// TODO Auto-generated method stub
		try {
			FileInputStream excelFile = new FileInputStream(new File(url_File_Read));
			try {
				Workbook workbook = new XSSFWorkbook(excelFile);
				Sheet dataTypeSheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = dataTypeSheet.iterator();

				while (iterator.hasNext()) {

					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.cellIterator();
					while (cellIterator.hasNext()) {

					}

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
