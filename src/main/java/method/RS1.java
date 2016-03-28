package method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by olha on 25.03.16.
 */
public class RS1 {
    public class Result {
        private int denominator;

        public double getR_s() {
            return r_s;
        }

        private int iteration_number;

        public Result(int denominator, ArrayList<Double> inputList) {
            this.denominator = denominator;
            this.rationList = this.generetaRation(inputList);
            this.lnList = this.generateLnList(this.rationList);
            this.generateLnDeltaList(this.lnList, this.denominator);

        }

        private ArrayList<Double> rationList;
        private ArrayList<Double> lnList;
        private ArrayList<Double> deltaList;
        private ArrayList<Double> deltaSquareList;
        private ArrayList<Double> standartList;

        public ArrayList<Double> getRazmahList() {
            return razmahList;
        }

        private ArrayList<Double> razmahList;

        public ArrayList<Double> getR_sList() {
            return r_sList;
        }


        private ArrayList<Double> r_sList;

        private double r_s;

        public  ArrayList<Double> generetaRation(ArrayList<Double> listDouble) {
            List<Double> list = new ArrayList<Double>();

            for (int i = 0; i < listDouble.size() - 1; i++) {
                if (listDouble.get(i + 1) == 0) {
                    throw new IllegalArgumentException("Devision by zero");
                }
                list.add(round(listDouble.get(i + 1) / listDouble.get(i), 15));
            }
            return (ArrayList<Double>) list;
        }

        public  ArrayList<Double> generateLnList(ArrayList<Double> listDouble) {
            ArrayList<Double> list = new ArrayList<Double>();
            for (Double d : listDouble) {
                if (d > 0)
                    list.add(round(Math.log(d), 15));
                else throw new IllegalArgumentException("Value is less than 0");
            }
            return list;
        }

        public int getIteration_number() {
            return iteration_number;
        }

        public void generateLnDeltaList(ArrayList<Double> listDouble, int denominator) {


            if (denominator < 3 || denominator > listDouble.size() / 2)
                throw new IllegalArgumentException("Wrong value for denominator " + denominator);

            ArrayList<Double> lnDeltaList = new ArrayList<Double>();
            ArrayList<Double> difList = new ArrayList<Double>();
            ArrayList<Double> difSquerList = new ArrayList<Double>();
            ArrayList<Double> razmahList = new ArrayList<Double>();
            ArrayList<Double> standartList = new ArrayList<Double>();
            ArrayList<Double> r_s_list = new ArrayList<Double>();

            ArrayList<Double> difSubList, difSquareSublist;

            int length = listDouble.size() / denominator;

            double r_s = 0;

            System.out.println("Input List size = " + listDouble.size());
            System.out.println("length = " + length);

            int flag = denominator - 1;
            int i = 0;
            Double f = Double.valueOf(0);
            Double ff = Double.valueOf(0);
            double av;

            while (i <= listDouble.size() - denominator) {
                av = 0;
                for (int j = i; j <= flag; j++) {
                    av = av + listDouble.get(j);
                }
                av = round(av / denominator, 15);

                difSubList = new ArrayList<Double>();
                difSquareSublist = new ArrayList<Double>();


                for (int j = i; j <= flag; j++) {
                    f = f + (listDouble.get(j) - av);
                    difSubList.add(round(f, 15));
                    ff = (listDouble.get(j)-av);
                    difSquareSublist.add(round(ff*ff, 15));
                }

                f = Double.valueOf(0);
                ff = Double.valueOf(0);

                difList.addAll(difSubList);
                Collections.sort(difSubList);
                razmahList.add(difSubList.get(difSubList.size()-1)-difSubList.get(0));

                Double ll = Double.valueOf(0);
                for (Double r : difSquareSublist) {
                    ll = ll + r;
                }

                standartList.add(round(Math.sqrt(Math.abs(ll) / denominator), 15));
                i += denominator;
                flag += denominator;
                lnDeltaList.add(av);

            }

            for (Double dd : difList) {
                difSquerList.add(round(dd * dd, 15));
            }

            for (int x = 0; x < standartList.size(); x++) {
                if (standartList.get(x) != 0)
                    r_s_list.add(round(razmahList.get(x) / standartList.get(x), 15));
                else r_s_list.add(Double.POSITIVE_INFINITY*razmahList.get(x));
            }

            for (int x = 0; x < r_s_list.size(); x++) {
                r_s = r_s + r_s_list.get(x);
            }

            r_s = round(r_s / r_s_list.size(), 15);

            this.deltaList = difList;
            this.deltaSquareList = difSquerList;
            this.standartList = standartList;
            this.r_sList = r_s_list;
            this.iteration_number = length;
            this.razmahList = razmahList;
            this.r_s = r_s;
        }


        public int getDenominator() {
            return denominator;
        }

        public ArrayList<Double> getRationList() {
            return rationList;
        }

        public ArrayList<Double> getLnList() {
            return lnList;
        }

        public ArrayList<Double> getDeltaList() {
            return deltaList;
        }

        public ArrayList<Double> getDeltaSquareList() {
            return deltaSquareList;
        }

        public ArrayList<Double> getStandartList() {
            return standartList;
        }


    }




    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
