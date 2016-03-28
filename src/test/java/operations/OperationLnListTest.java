package operations;

import method.Operation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by olha on 28.03.16.
 */
public class OperationLnListTest {
    ArrayList<Double> inputList;
    ArrayList<Double> outputList;
    @Before
    public void setUp(){
        inputList = new ArrayList<Double>();
        outputList = new ArrayList<Double>();
        Random r =  new Random();
        Double d;
        for(int i=0; i<10; i++){
            d= Math.abs(r.nextDouble());
            inputList.add(d);
            outputList.add(Math.log(d));
        }
    }

    @Test
    public void should_generate_ln_list_from_list() {
        ArrayList<Double> output = Operation.generateLnList(inputList);
        int i=0;
        for(Double d:output){
            assertEquals(d,outputList.get(i),0.000001);
            i++;
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void should_not_get_ln_from_negative(){
       ArrayList<Double> inp = new ArrayList<Double>();
        inp.addAll(inputList);
        inp.add((double)-2);
        inp.set(1, (double) -1);
        Operation.generateLnList(inp);
    }
}
