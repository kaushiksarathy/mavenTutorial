/**
 * Created by kaushik.p on 30/03/15.
 */

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;



class traverse {





     public String readconfig(String html,String config) {
         BufferedReader br = null;
         JSONParser parser=new JSONParser();
         JSONObject parent=null;
         JSONObject element=null;
         String ans="";//for testing

         try {

             String sCurrentLine;
             Document doc = Jsoup.connect(html)
                     .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
                     .data("name", "jsoup")
                     .timeout(0)
                     .get();
//             Document doc= Jsoup.parse(new File("/Users/kaushik.p/IdeaProjects/mavenTutorial/src/main/resources/amazon1.html"),null);

             InputStream is = TraverseHTML.class.getClassLoader().getResourceAsStream(config);
             JSONObject obj =(org.json.simple.JSONObject) parser.parse(new BufferedReader((new InputStreamReader(is))));
             System.out.println(obj.get("name"));
             System.out.println(obj.get("urlPattern"));
             JSONArray entities= (JSONArray) obj.get("entities");

             Iterator i=entities.iterator();
             int k=0;
             while(i.hasNext())
             {
                 String selector="",selector_t="",selector_temp="";
                 JSONObject entity=(JSONObject)i.next();
                 JSONArray uPaths= (JSONArray) entity.get("uPath");

                 Iterator j=uPaths.iterator();

                 while(j.hasNext())
                 {

                     JSONObject uPath=(JSONObject)j.next();
                     selector=">"+uPath.get("tagName").toString().toLowerCase();
                     if(uPath.get("id")!=null)
                     {
                         selector+="[id = "+uPath.get("id").toString()+" ]";
                     }
                      if(uPath.get("classNames")!=null)
                     {
                         selector+="[class = "+uPath.get("classNames").toString()+" ]";
                     }
                    selector_t=selector+selector_t;
                 }


                 char[] selectorstr=selector_t.toCharArray();
                 selectorstr[0]=' ';
                 selector_t=new String(selectorstr);// to replace the first > symbol
                 System.out.println("\nThe selector string is "+selector_t);

                 Elements intrests = doc.select(selector_t);

                 System.out.println(intrests.size());

                 for (Element intrest : intrests) {
                     System.out.println(intrest.text());
                     ans+=intrest.text();
                    }
             }



         }
         catch (ParseException p)
        {
            p.printStackTrace();
        }
         catch (IOException e) {
             e.printStackTrace();
         }
        return ans;
     }

    // not used right now
     void dfs1(Element element,int level,JSONObject obj) throws JSONException {
         System.out.print("\n");
         if(level==0&&
                 (!obj.get("tag").toString().equals("")&&element.tag().toString().equals(obj.get("tag")))||
                 (!obj.get("id").toString().equals("")&&element.id().equals(obj.get("id")))||
                 (!obj.get("class").toString().equals("")&&element.className().equals(obj.get("class"))))
             System.out.print(element.tag()+" "+element.id()+" "+element.className());

         Elements children = element.children();
         if(children!=null) {

             for(Element child :children)
             {
                 dfs1(child, level - 1,obj);
             }
         }
     }
 }

public class TraverseHTML {



    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);

        traverse t = new traverse();
        String html="http://www.amazon.in/gp/product/B00SWFDP3Y/ref=s9_acss_bw_ln_test_1_3?pf_rd_m=A1VBAL9TL5WCBF&pf_rd_s=merchandised-search-leftnav&pf_rd_r=0YA6JCRTX6XKXQZQHC4M&pf_rd_t=101&pf_rd_p=606053127&pf_rd_i=976392031",
                config="config_file7.txt";

        t.readconfig(html, config);
    }
}
