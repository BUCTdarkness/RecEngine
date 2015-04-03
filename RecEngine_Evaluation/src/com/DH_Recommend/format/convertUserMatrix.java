package com.DH_Recommend.format;

import java.io.*;
import java.util.*;
import java.math.*;

public class convertUserMatrix {
    public static void main(String[] args) throws IOException {
    	 
        BufferedReader reader = null;
  FileWriter writer = new FileWriter("D://Data//User-U", true);

  HashMap<String, String> rec = new HashMap<String, String>();  
  try {
           
           for (int i = 0; i< 9; i++){
             String fileX = "D://Data//-tmp-liujiawen-matrix-userbased-part-r-0000" + Integer.toString(i);
        reader = new BufferedReader(new FileReader(fileX));
        String tempString = null;
        String value = null;
        while ((tempString = reader.readLine()) != null) {
            String sarray[] = tempString.split(",");
            String item1 = sarray[0];
            String item2 = sarray[1];
            String num = sarray[2];
            if (rec.get(item1) != null)
               value = rec.get(item1) +" "+ item2 ;
//            value = rec.get(item1) +" "+ item2 +"(" + num + ")";
            else
               value = item2 ; 
//            value = item2 +"(" + num + ")"; 
//         System.out.println(item1+ "-->"+ value);
            rec.put(item1, value);
        }
        reader.close();
        System.out.println("Finish part" + i );
           }

                        Iterator<String> iter =  rec.keySet().iterator();
                        while (iter.hasNext()) {
                           String key = iter.next();
                           String val = rec.get(key);
//                        System.out.println(key+ "-->"+ val);
                           writer.write(key+ "-->"+ val);
                           writer.write("\n");
                        }
                       
                        writer.close();
                       
       
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
    }
}
}
