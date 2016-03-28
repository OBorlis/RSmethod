import method.RS1;
import parcer.ExcelReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olha on 25.03.16.
 */
public class Main1 {
    public static ArrayList<ArrayList<Double>> finalList;
    public static void main(String[] args) throws IOException {

        String excelFilePath = "res/ao.xlsx";
        ExcelReader excelReader = new ExcelReader();
        RS1 rsMethod = new RS1();

        //Get values from input file
        List<Double> listDouble = excelReader.readDoubleFromExcelFile(excelFilePath);
        System.out.println("INPUT LIST: "+listDouble.toString());

        int n = listDouble.size()/2;

//        finalList = new ArrayList<ArrayList<Double>>();
        FileWriter fileWriter;
        BufferedWriter bufferedWrighter;

        FileWriter fw1 = new FileWriter("res/result.txt");
        BufferedWriter bf1 = new BufferedWriter(fw1);
        FileWriter fw2 = new FileWriter("res/simple_result.txt");
        BufferedWriter bf2 = new BufferedWriter(fw2);
        for(int i=3;i<n;i++){
            RS1.Result result  = rsMethod.new Result(i, (ArrayList<Double>) listDouble);


//            fileWriter = new FileWriter("res/file_"+i+".txt");
//            bufferedWrighter = new BufferedWriter(fileWriter);

            bf1.write("N = "+i+"; [R/S] = "+result.getR_s()+"\n");
            bf1.write("lg(N) = "+rsMethod.round(Math.log10(i),10)+"; lg([R/S]) = "+rsMethod.round(Math.log10(result.getR_s()),10)+"\n\n");
            bf2.write(i+" "+result.getR_s()+" ");
            bf2.write(rsMethod.round(Math.log10(i),10)+" "+rsMethod.round(Math.log10(result.getR_s()),10)+"\n");


//            bufferedWrighter.write(">>>>>>>>>>>>>>>>>>> "+i+" <<<<<<<<<<<<<<<<<<<"+"\n");
//            bufferedWrighter.write("Iteration number: "+result.getIteration_number()+"\n");
//            bufferedWrighter.write("Ration list for "+i+": "+result.getRationList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//
//            bufferedWrighter.write("Ln list for "+i+": "+result.getLnList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//            bufferedWrighter.write("Delta list for "+i+": "+result.getDeltaList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//            bufferedWrighter.write("Delta square list for "+i+": "+result.getDeltaSquareList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//            bufferedWrighter.write("Standart list for "+i+": "+result.getStandartList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//            bufferedWrighter.write("Razmah list for "+i+": "+result.getRazmahList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//
//
//            bufferedWrighter.write("R/S list for "+i+": "+result.getR_sList()+"\n");
//            bufferedWrighter.write("\n\n\n");
//            bufferedWrighter.write("[R/S]  for "+i+": "+result.getR_s()+"\n");
//            bufferedWrighter.write("\n\n\n");
//            bufferedWrighter.close();

        }
        bf1.close();
        bf2.close();
    }
}
