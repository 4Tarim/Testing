package utilityLibraries;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	final static Logger logger = Logger.getLogger(ExcelManager.class);

	private static String filePath;
	private static Workbook wb;
	private static Sheet sh;

	/***
	 * Constructor
	 * 
	 * @param excelFile
	 * @param sheetName
	 */
	public ExcelManager(String excelFile, String sheetName) {
		try {
			File excelDataFile = new File(excelFile);
			filePath = excelDataFile.getAbsolutePath();
			logger.info("Reading Excel File ---> " + filePath);
			FileInputStream fs = new FileInputStream(excelDataFile);
			wb = getWorkbook(fs, filePath);
			sh = wb.getSheet(sheetName);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * Constructor
	 * 
	 * @param excelFile
	 * @param sheetIndex
	 */
	public ExcelManager(String excelFile, int sheetIndex) {
		try {
			File excelDataFile = new File(excelFile);
			filePath = excelDataFile.getAbsolutePath();
			logger.info("Reading Excel File ---> " + filePath);
			FileInputStream fs = new FileInputStream(excelDataFile);
			wb = getWorkbook(fs, filePath);
			sh = wb.getSheetAt(sheetIndex);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return String
	 */
	public String readExcelData(int rowIndex, int colIndex) {
		String cellData = null;
		try {
			Row row = sh.getRow(rowIndex);
			Cell cell = row.getCell(colIndex);
			cellData = formatDataCellToString(cell);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return cellData;
	}

	public String[][] getExcelAllData() {
		String[][] arrayExcelData = null;
		try {
			Iterator<Row> iterator = sh.iterator();
			int totalCols = sh.getRow(0).getPhysicalNumberOfCells();
			int totalRows = sh.getPhysicalNumberOfRows();
			arrayExcelData = new String[totalRows - 1][totalCols];
			int iRowCount = 0;

			while (iterator.hasNext()) {
				Row row = iterator.next();
				// skip the row 1 because it is always the table data header information
				if (iRowCount > 0) {
					Iterator<Cell> colIterator = row.iterator();
					int iColCount = 0;
					while (colIterator.hasNext()) {
						Cell cell = colIterator.next();
						// need to format the cells before read it as a string
						String data = formatDataCellToString(cell);
						arrayExcelData[iRowCount - 1][iColCount] = data;
						logger.debug("Row:" + iRowCount + ", Col:" + iColCount + ", Data: [" + data +"]");
						iColCount++;
					}
				}
				iRowCount++;
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return arrayExcelData;
	}

	/***
	 * 
	 * @param cell
	 * @return String
	 */
	private String formatDataCellToString(Cell cell) {
		String cellString = null;
		try {
			DataFormatter formatter = new DataFormatter();
			cellString = formatter.formatCellValue(cell);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return cellString;
	}

	/***
	 * Automatically handle older version of excel and new version
	 * 
	 * @param fis
	 * @param excelFilePath
	 * @return Workbook
	 */
	private Workbook getWorkbook(FileInputStream fis, String excelFilePath) {
		Workbook workbook = null;
		try {
			if (excelFilePath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (excelFilePath.endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
			} else {
				throw new IllegalArgumentException("The specified file is not Excel file.");
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return workbook;
	}
	
	public static void main(String[] args) {	
		
		ExcelManager exceUtil2 = new ExcelManager("src/test/resources/testData/"
				+ "CalculaterTestData2.xls", 0);
		
		logger.info("Excel data 2 --------");
		logger.info(exceUtil2.getExcelAllData());		
				
	}
	
}
