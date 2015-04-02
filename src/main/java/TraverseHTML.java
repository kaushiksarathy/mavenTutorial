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
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;

//import java.io.File;

//import org.json.JSONObject;

//import org.json.simple.JSONObject;

class traverse {
     void travesehtml(Element e) {

         // for(Element e: list)
         {
             System.out.println(e.tag());
             int i = 0;
             Elements children = e.children();
             for (Element child : children) {
                 travesehtml(child);
             }
         }
     }


     void dfs(Element element) {
         System.out.print("\n");

//         for (int i=0;i<level;i++)
//             System.out.print(" ");
//        if(
//                 (!obj.get("tag").equals("")&&element.tag().toString().equals(obj.get("tag")))||
//                         (!obj.get("id").equals("")&&element.id().equals(obj.get("id")))||
//                         (!obj.get("class").equals("")&&element.className().equals(obj.get("class"))))
//         System.out.print(" "+element.tag()+" "+element.id()+" "+element.className()+element.text());

         Elements children = element.children();
         if(children!=null) {

             for(Element child :children)
             {

                 dfs(child);
             }
         }
         else
         {
             System.out.print(element.text());
         }
     }

    @Test
     public void readconfig(String html,String config) {
         BufferedReader br = null;
         JSONParser parser=new JSONParser();
         JSONObject parent=null;
         JSONObject element=null;

         try {

             String sCurrentLine;
             Document doc = Jsoup.connect(html).userAgent("Mozilla").data("name", "jsoup").get();
             //System.out.println(doc.html());

//             Document doc= Jsoup.parse(new File("/Users/kaushik.p/IdeaProjects/mavenTutorial/src/main/resources/amazon1.html"),null);

             InputStream is = TraverseHTML.class.getClassLoader().getResourceAsStream(config);
             JSONObject obj =(org.json.simple.JSONObject) parser.parse(new BufferedReader((new InputStreamReader(is))));
             System.out.println(obj.get("name"));
             System.out.println(obj.get("urlPattern"));
             //System.out.println(obj.get("entities"));
             JSONArray entities= (JSONArray) obj.get("entities");

             Iterator i=entities.iterator();
             int k=0;
             while(i.hasNext())
             {
                 String selector="",selector_t="",selector_temp="";
                 JSONObject entity=(JSONObject)i.next();
//                 System.out.println(entity.get("name"));
//                 System.out.println(entity.get("type"));
//                 System.out.println(entity.get("id"));
//                 //System.out.println(entity.get("uPath"));
                 JSONArray uPaths= (JSONArray) entity.get("uPath");
                 element=(JSONObject)uPaths.get(0);

                 int len=uPaths.size();
                 if(len!=1)
                     parent=(JSONObject)uPaths.get(len-1);
                 Iterator j=uPaths.iterator();

                 while(j.hasNext())
                 {

                     JSONObject uPath=(JSONObject)j.next();
//                     System.out.println(uPath.get("classNames"));
//                     System.out.println(uPath.get("tagName"));
//                     System.out.println(uPath.get("indexNumber"));
//                     System.out.println(uPath.get("unique"));
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
                 //System.out.println(uPaths.get(0));
//                 System.out.println("Element");
//                 System.out.println(element.get("classNames"));
//                 System.out.println(element.get("tagName"));
//                 System.out.println(element.get("indexNumber"));
//                 System.out.println(element.get("unique"));
//                 if(parent!=null) {
//
//                     System.out.println("Parent");
//                     System.out.println(parent.get("id"));
//                     System.out.println(parent.get("classNames"));
//                     System.out.println(parent.get("tagName"));
//                     System.out.println(parent.get("indexNumber"));
//                     System.out.println(parent.get("unique"));
//                     if(parent.get("unique").toString().equals("true"))// parent unique
//                     {
//                         selector_temp+=parent.get("tagName").toString().toLowerCase();
//                         if(parent.get("id")!=null)
//                         {
//                             selector_temp+="[id = "+parent.get("id").toString()+" ]";
//                         }
//                         if(parent.get("classNames")!=null)
//                         {
//                             selector_temp+="[class = "+parent.get("classNames").toString()+" ]";
//                         }
//                     }
//                     else// parent not unique so no id but may have a class name.
//                     {
//                         selector_temp+=parent.get("tagName").toString().toLowerCase();
//                         if(parent.get("classNames")!=null)
//                         {
//                             selector_temp+="[class = "+parent.get("classNames").toString()+" ]";
//                         }
//                     }
//
//
//                 }
//                 else if(element!=null)// means parent absent and element must be unique
//                 {
//                     selector_temp+=element.get("tagName").toString().toLowerCase();
//                     if(element.get("id")!=null)
//                     {
//                         selector_temp+="[id = "+element.get("id").toString()+" ]";
//                     }
//                     if(element.get("classNames")!=null)
//                     {
//                         selector_temp+="[class = "+element.get("classNames").toString()+" ]";
//                     }
//                 }


                 char[] selectorstr=selector_t.toCharArray();
                 selectorstr[0]=' ';
                 selector_t=new String(selectorstr);// to replace the first > symbol
                 System.out.println("\nThe selector string is "+selector_t);
//                 Document doc= Jsoup.parse(new File("/Users/kaushik.p/IdeaProjects/mavenTutorial/src/main/resources/flipkart1.html"),null);

//                 System.out.println(doc.title());
                 Elements intrests = doc.select(selector_t);

                 System.out.println(intrests.size());
                 for (Element intrest : intrests) {
                     System.out.println(intrest.text());
                     //dfs(intrest);
                    }
             }


//             jobj.put("tag",tag);
//             jobj.put("id",id);
//             jobj.put("class",clas);
//             jobj.put("count",4);

         }
         catch (ParseException p)
        {
            p.printStackTrace();
        }
         catch (IOException e) {
             e.printStackTrace();
         }
//         finally {
//             try {
//                 if (br != null)br.close();
//             } catch (IOException ex) {
//                 ex.printStackTrace();
//             }
//         }
     }


     void dfs1(Element element,int level,JSONObject obj) throws JSONException {
         System.out.print("\n");
         //for (int i=0;i<level;i++)
         //    System.out.print(" ");
         if(level==0&&
                 (!obj.get("tag").toString().equals("")&&element.tag().toString().equals(obj.get("tag")))||
                 (!obj.get("id").toString().equals("")&&element.id().equals(obj.get("id")))||
                 (!obj.get("class").toString().equals("")&&element.className().equals(obj.get("class"))))
             System.out.print(element.tag()+" "+element.id()+" "+element.className());

         Elements children = element.children();
         if(children!=null) {

             for(Element child :children)
             {
                 //System.out.print(child.tag());
                 dfs1(child, level - 1,obj);
             }
         }
     }
 }

public class TraverseHTML {



    public static void main(String args[])throws IOException
    {
        String tag="",id="",clas="";
        String Ptag="",Pid="",Pclas="";
        String selectorstr="";
        Scanner s=new Scanner(System.in);
//        System.out.println("Is there a unique parent (y/n): ");
//        String ch=s.nextLine();
//        if(ch.toLowerCase().equals("y"))
//        {
//            do {
//                System.out.println("\nEnter the parent tagname you want to search");
//                Ptag = s.nextLine();
//            }while(Ptag.equals(""));
//                selectorstr+=Ptag;
//
//            System.out.println("\nEnter the parent id you want to search");
//            Pid=s.nextLine();
//                if(!Pid.equals(""))
//                    selectorstr=selectorstr+"[id ="+Pid+"]";
//            System.out.println("\nEnter the parent class you want to search");
//            Pclas=s.nextLine();
//            if(!Pclas.equals(""))
//                selectorstr=selectorstr+"[class ="+Pclas+"]";
//
//        }
//        else
//        selectorstr=":root";
//
//
//        System.out.println(selectorstr);
//        System.out.println("\nEnter the tagname you want to search");
//        tag=s.nextLine();
//        System.out.println("\nEnter the id you want to search");
//        id=s.nextLine();
//        System.out.println("\nEnter the class you want to search");
//        clas=s.nextLine();






//        Map<String,String> obj=new LinkedHashMap<String, String>();
//        obj.put("tag",tag);
//        obj.put("id",id);
//        obj.put("class",clas);
//
//
//
//        JSONObject jobj =new JSONObject();
//        jobj.put("tag",tag);
//        jobj.put("id",id);
//        jobj.put("class",clas);
//        jobj.put("count",4);
//
//        Document doc=Jsoup.parse(new File("/Users/kaushik.p//Documents/test/first_test/first.html"), "utf-8");
//        Elements parent=doc.getElementsByTag("/");
//        Elements children;
//        Elements roots = doc.select(selectorstr);
//
//        //System.out.print(roots);
//        String retstr="";
//        int level=0;
//        traverse t = new traverse();
//        for (Element root : roots) {
//            //System.out.print(root.tag());
//            t.dfs(root, level,jobj);
//        }

        traverse t = new traverse();
        String html="http://www.amazon.in/gp/product/B00L8WT2UI/ref=s9_hps_bw_g23_i3?pf_rd_m=A1VBAL9TL5WCBF&pf_rd_s=merchandised-search-3&pf_rd_r=1SM5NCQ7BYQKC0H680Q2&pf_rd_t=101&pf_rd_p=601904567&pf_rd_i=1389401031#",
                config="config_file7.txt";

        t.readconfig(html,config);
    }
}
