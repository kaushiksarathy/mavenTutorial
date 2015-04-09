/**
 * Created by kaushik.p on 08/04/15.
 */

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test ()
public class testclass {


    @DataProvider
    public Object[][] data(){
        return new String[][]{
                new String[]{"data1"}, new String[]{"data2"}
        };
    }
    @Test(dataProvider = "data")
    public void test(String  d ){
        Assert.assertEquals("First Line\nSecond Line", "Third Line\nFourth Line");}

    }
