import method.RS2;
import parcer.ExcelReader;
import parcer.ExcelWrighter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olha on 25.03.16.
 */
public class Main2 {
    public static void methodRS(String inputFilePath) throws IOException {
//        String excelFilePath = "res/ao.xlsx";
        ExcelReader excelReader = new ExcelReader();
        ExcelWrighter excelWrighter = new ExcelWrighter();
        List<Double> listDouble = excelReader.readDoubleFromExcelFile(inputFilePath);

        System.out.println("INPUT LIST: "+listDouble.toString());

        ArrayList<RS2> output = new ArrayList<RS2>();

        int n = listDouble.size()/2;

        FileWriter fileWriter;
        BufferedWriter bufferedWrighter;
        fileWriter = new FileWriter(inputFilePath.substring(0,inputFilePath.length()-5)+"_output.txt");
        bufferedWrighter = new BufferedWriter(fileWriter);
        RS2 rs;
        for(int i=3;i<n;i++){
            rs = RS2.generateRS(i, (ArrayList<Double>) listDouble);
            output.add(rs);
            bufferedWrighter.write(rs.toString()+"\n");

        }
        excelWrighter.writeRSListToExcel(output,inputFilePath.substring(0,inputFilePath.length()-5)+"_output.xlsx");
        bufferedWrighter.close();
    }
    public static void main(String [] args) throws IOException {

        methodRS("res/ao.xlsx");
        methodRS("res/nao.xlsx");
        methodRS("res/mo.xlsx");

    }
}
