package method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by olha on 25.03.16.
 */
public class Operation {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static ArrayList<Double> generetaRation(ArrayList<Double> listDouble) {
        List<Double> list = new ArrayList<Double>();

        for (int i = 0; i < listDouble.size() - 1; i++) {
            if (listDouble.get(i + 1) == 0) {
                throw new IllegalArgumentException("Devision by zero");
            }
            list.add(round(listDouble.get(i + 1) / listDouble.get(i), 15));
        }
        return (ArrayList<Double>) list;
    }

    public static  ArrayList<Double> generateLnList(ArrayList<Double> listDouble) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (Double d : listDouble) {
            if (d > 0)
                list.add(round(Math.log(d), 15));
            else throw new IllegalArgumentException("Value is less than 0");
        }
        return list;
    }

    public static Double getAverageFromInterval(int i, int flag, ArrayList<Double> workList, int n){
        Double av = Double.valueOf(0);
        for (int j = i; j <= flag; j++) {
            av = av + workList.get(j);
        }
        av = Operation.round(av / n, 15);
        return av;
    }

    public static Double generateRazmahOnInterval(int i, int flag, ArrayList<Double> workList, Double av){
        ArrayList<Double> difSubList = new ArrayList<Double>();
        Double f = Double.valueOf(0);
        for (int j = i; j <= flag; j++) {
            f = f + (workList.get(j) - av);
            difSubList.add(Operation.round(f, 15));
        }

        Collections.sort(difSubList);

        return difSubList.get(difSubList.size()-1)-difSubList.get(0);
    }

    public static Double generateStandartOnInterval(int i, int flag, ArrayList<Double> workList, Double av, int n){
        Double f = Double.valueOf(0);
        for (int j = i; j <= flag; j++) {
            f = f + (workList.get(j)-av)*(workList.get(j)-av);
        }
        return round(Math.sqrt(Math.abs(f) / n), 15);
    }

}
