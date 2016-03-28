package parcer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by olha on 25.03.16.
 */
public class ExcelReader {
    public Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        if (excelFilePath.endsWith("xlsx")) {
            return new XSSFWorkbook(inputStream);

        } else if (excelFilePath.endsWith("xls")) {
            return new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }

    public List<Double> readDoubleFromExcelFile(String excelFilePath) throws IOException {
        List<Double> listDouble = new ArrayList<Double>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = this.getWorkbook(inputStream, excelFilePath);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                listDouble.add((Double) getCellValue(nextCell));
            }

        }
        return listDouble;
    }
}
