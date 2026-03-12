package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;


//Excel Engine using Apache POI
public class TestDataUtils {

    private static final String EXCEL_PATH = "src/test/resources/TestData.xlsx";

    /**
     * Fetches data from a specific cell in your Excel sheet.
     */
    public static String getExcelData(String sheetName, int rowNum, int colNum )
    {
        String cellValue="";
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Cell cell = workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum);

            // This handles numbers and strings properly
            DataFormatter formatter = new DataFormatter();
            cellValue = formatter.formatCellValue(cell);

        } catch (Exception e) {
            System.out.println("Excel read failed! Returning empty string.");
        }
        return cellValue;
    }


    }


