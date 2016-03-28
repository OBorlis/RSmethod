package operations;

import method.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by olha on 28.03.16.
 */
@RunWith(Parameterized.class)
public class OperationRoundTest {

    double d;
    int i;
    double dInput;
    double dOutput;

    public OperationRoundTest(double dInput, double dOutput){
        this.dInput = dInput;
        this.dOutput = dOutput;

    }
    @Before
    public void setUp(){
        Random r = new Random();
        d =r.nextDouble();
        i=Math.abs(r.nextInt());
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                { -0.123701, -0.124},
                { 2.01, 2.010},
                { 19.02, 19.020 },
                { 18.400237, 18.400},
                { 16.70093701, 16.701 }
        });
    }

    @Test
    public void round_should_round_doubles(){
        assertEquals(Operation.round(dInput,3),dOutput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void round_should_throw_illegal_argument_exception(){
        Operation.round(d,-1);
    }




}
