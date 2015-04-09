import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by kaushik.p on 08/04/15.
 */
public class TraverseHTMLTest {
    String html,config;
    traverse t;
    @BeforeMethod
    public void initialize()
    {
        t=new traverse();
        html="";
        config="";
    }
    @AfterMethod
    public void aftermethod()
    {
        System.out.println("The input and output were correct");
    }
    @DataProvider
    public Object[][] data(){
        return new String[][]{
                new String[]{"http://www.flipkart.com/moto-g-2nd-gen/p/itme5rgyshvbeyxy?pid=MOBDYGZ6SHNB7RFC&srno=b_1&ref=5ccdb6dc-ff4e-4ea0-afc0-952ce834abef"
                            ,"config_file1.txt"
                            ,"Rs. 12,999"},
                new String[]{"http://www.amazon.in/gp/product/B00SWFDP3Y/ref=s9_acss_bw_ln_test_1_3?pf_rd_m=A1VBAL9TL5WCBF&pf_rd_s=merchandised-search-leftnav&pf_rd_r=0YA6JCRTX6XKXQZQHC4M&pf_rd_t=101&pf_rd_p=606053127&pf_rd_i=976392031"
                            ,"config_file7.txt"
                            ,}
        };
    }
    @Test(dataProvider = "data",expectedExceptions = AssertionError.class)
    public void test(String  html,String config,String result ){
        this.html=html;
        this.config=config;
        Assert.assertEquals(t.readconfig(html,config),result);
    }

}

