package parcer;


import method.RS2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by olha on 25.03.16.
 */
public class ExcelWrighter {

    public void writeRSListToExcel(ArrayList<RS2> inputList, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        //titles
        Row r = sheet.createRow(0);
        Cell c = r.createCell(0);
        c.setCellValue("n");
        c = r.createCell(1);
        c.setCellValue("R/S");
        c = r.createCell(2);
        c.setCellValue("lg N");
        c = r.createCell(3);
        c.setCellValue("lg R/S");


        for(RS2 rs:inputList){
            Row row = sheet.createRow(++rowCount);
            writeRS(rs,row);
        }

         FileOutputStream outputStream = new FileOutputStream(excelFilePath);
         workbook.write(outputStream);
        }




    private void writeRS(RS2 rs, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(rs.get_n());
        cell = row.createCell(1);
        cell.setCellValue(rs.get_rs());
        cell = row.createCell(2);
        cell.setCellValue(Math.log10(rs.get_n()));
        cell = row.createCell(3);
        cell.setCellValue(Math.log10(rs.get_rs()));
    }

    public static void main(String[] args) throws IOException {
        ExcelWrighter excelWrighter = new ExcelWrighter();
        ArrayList<RS2> rsList = new ArrayList<RS2>();
        Random r = new Random();
        RS2 rs;
        for(int i=0; i<10;i++){
            rs = new RS2(i,r.nextDouble());
            rsList.add(rs);
        }
        excelWrighter.writeRSListToExcel(rsList,"res/output.xlsx");
    }

}
