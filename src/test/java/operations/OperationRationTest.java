package operations;

import method.Operation;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by olha on 28.03.16.
 */
public class OperationRationTest {
    ArrayList<Double> inputList;
    ArrayList<Double> outputList;
    Random r;
    @Before
    public void setUp(){
        inputList = new ArrayList<Double>();
        outputList = new ArrayList<Double>();
        Double d1, d2;
        r = new Random();
        d1= r.nextDouble();
        inputList.add(d1);
        for(int i=1; i<=10; i++) {
            d2=r.nextDouble();
            inputList.add(d2);
            outputList.add(d2/d1);
            d1=d2;
        }
    }
    @Test
    public void should_generate_ration() {
        ArrayList<Double> rationList = Operation.generetaRation(inputList);
//        System.out.println(inputList);
//        System.out.println(outputList);
//        System.out.println(rationList);

        int i=0;
       for(Double d:rationList){
           assertEquals(d,outputList.get(i),0.000001);
           i++;
       }
    }
    @Test (expected = IllegalArgumentException.class)
    public void should_not_generate_ration(){
        ArrayList<Double> inp = new ArrayList<Double>();
        inp.addAll(inputList);
        inp.add((double)0);
        inp.set(3, (double) 0);
        Operation.generetaRation(inp);
    }

}
