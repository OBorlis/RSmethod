package method;

import java.util.ArrayList;

/**
 * Created by olha on 25.03.16.
 */
public class RS2 {

    //two imortant outputs
    private int n;
    private double rs;

    @Override
    public String toString() {
        return "RS{" +
                "n=" + n +
                ", rs=" + rs +
                '}';
    }

    public static RS2 generateRS(int n, ArrayList<Double> inputList){
        ArrayList<Double> workList = new ArrayList<Double>();
        Double r_s;
        if (n < 3 || n > inputList.size() / 2)
            throw new IllegalArgumentException("Wrong value for denominator " + n);
        if(inputList.size() == 0)
            throw new  IllegalArgumentException("Can't handle zero-length inputList.");
        else if(inputList == null)
            throw new NullPointerException("Can't handle null inputList");

        workList.addAll(inputList);

        workList = Operation.generetaRation(workList);
        workList = Operation.generateLnList(workList);
        r_s =  findRS(workList,n);

        return new RS2(n,r_s);
    }

    private static Double findRS(ArrayList<Double> workList, int n){
        int i = 0;
        int flag = n -1;
        Double av;
        ArrayList<Double> razmahList = new ArrayList<Double>();
        ArrayList<Double> standartList = new ArrayList<Double>();
        while (i <= workList.size() - n) {
            av = Operation.getAverageFromInterval(i, flag, workList, n);
            razmahList.add(Operation.generateRazmahOnInterval(i,flag,workList,av));
            standartList.add(Operation.generateStandartOnInterval(i,flag,workList,av,n));

            i += n;
            flag += n;
        }
        return getValueOfRS(razmahList,standartList);
    }

    private static Double getValueOfRS(ArrayList<Double> razmahList, ArrayList<Double>standartList){
        ArrayList<Double> r_s_list = new ArrayList<Double>();
        Double r_s = Double.valueOf(0);
        for (int x = 0; x < standartList.size(); x++) {
            if (standartList.get(x) != 0)
                r_s_list.add(Operation.round(razmahList.get(x) / standartList.get(x), 15));
            else r_s_list.add(Double.POSITIVE_INFINITY*razmahList.get(x));
        }

        for (int x = 0; x < r_s_list.size(); x++) {
            r_s = r_s + r_s_list.get(x);
        }

        r_s = Operation.round(r_s / r_s_list.size(), 15);
        return r_s;
    }

    //geters/////
    public int get_n() {
        return n;
    }

    public double get_rs() {
        return rs;
    }

    public RS2(int n, double rs){
        this.n = n;
        this.rs = rs;
    }


}
