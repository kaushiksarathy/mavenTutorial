/**
 * Created by kaushik.p on 30/03/15.
 */

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class FirstJsoupExample{
    public static void main( String[] args ) throws IOException{
        String tag="",id="",clas="";
        Scanner s=new Scanner(System.in);
        System.out.println("\nEnter the tagname you want to search");
        tag=s.nextLine();
        System.out.println("\nEnter the id you want to search");
        id=s.nextLine();
        System.out.println("\nEnter the class you want to search");
        clas=s.nextLine();

        JSONObject jobj =new JSONObject();
        jobj.put("tag",tag);
        jobj.put("id",id);
        jobj.put("class",clas);
        jobj.put("count",4);

        //Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        Document doc=Jsoup.parse(new File("/Users/kaushik.p//Documents/test/first_test/first.html"),"utf-8");
        String title=doc.title();

       // Elements links = doc.select(tag+"[id = "+id+" ]");

        Elements pelements=doc.select("span[ id=price ]");
        //.select("div.test p.maps");
//        for(Element link : links)
//        {
//            System.out.println("\nlink " + link.attr("href"));
//            System.out.println("text :" +link.text());
//        }
        for(Element pelement : pelements)
        {

            System.out.println("id "+ pelement.attr("id"));
            System.out.println("text " + pelement.text());

        }
        System.out.println("title is: " + title);
    }
}