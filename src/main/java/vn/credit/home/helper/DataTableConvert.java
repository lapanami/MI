/**
 * 
 */
package vn.credit.home.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * @author an.nd
 *
 */
public class DataTableConvert {

	@SuppressWarnings("rawtypes")
	public static Vector<List<Object>> read(String fileName) throws InvalidFormatException {

		OPCPackage pkg = OPCPackage.open(new File(fileName));

		Vector<List<Object>> cellVectorHolder = new Vector<List<Object>>();
		try {
			XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			@SuppressWarnings("rawtypes")
			Iterator<?> rowIter = mySheet.rowIterator();
			while (rowIter.hasNext()) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator<?> cellIter = myRow.cellIterator();
				List<Object> list = new ArrayList<>();
				while (cellIter.hasNext()) {
					if (cellIter != null) {
						XSSFCell myCell = (XSSFCell) cellIter.next();
						list.add(myCell);
					}
				}
				cellVectorHolder.addElement(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellVectorHolder;
	}



	public static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();

		case Cell.CELL_TYPE_BLANK:
			return StringUtils.EMPTY;

		}
		return null;
	}

}
