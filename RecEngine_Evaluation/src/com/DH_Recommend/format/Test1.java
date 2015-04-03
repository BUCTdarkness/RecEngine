package com.DH_Recommend.format;

import java.util.*;
import java.io.*;

public class Test1 {	
	
	private static void printMap(Map<String, Set<Integer> > mp){
	       Set<String> keys=mp.keySet();
	       for(String skey:keys){
	    	   System.out.println("key= "+skey);
	    	   System.out.println("values= ");
	    	   Set<Integer> tmpSet=mp.get(skey);
	    	   for(Integer ans:tmpSet){
	    		   System.out.println(ans);
	    	   }
	       }		
		}	
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{

	    
	    Set<String>  btype =new HashSet<String>();
	    Set<String>  ctype =new HashSet<String>();
		Map<String,Set<Integer> > tMap = new HashMap<String,Set<Integer> >();
//		Map<String,Set<Integer> > rMap = new HashMap<String,Set<Integer> >();
	 //   System.out.println("ssss");
	    
	    try {
	    	
			File file1=new File("D://REData//user_Recitem_Expand_Ordered_By_CommonBuy-NoWeight-100");
			File file2=new File("D://REData//buyer_type");
			File file3=new File("D://REData//buyerid_items.txt");
			
		    FileWriter cout = new FileWriter("D://REData//ans_20", true);
		    BufferedReader cin1=null;
		    BufferedReader cin2=null;
		    BufferedReader cin3=null;

	    	
	    	
            cin1 = new BufferedReader(new FileReader(file1));
            cin2 = new BufferedReader(new FileReader(file2));
            cin3 = new BufferedReader(new FileReader(file3));
            //System.out.println("ssss");
            
            String tmpString=null;
            while ((tmpString = cin2.readLine()) != null){
            	String sarray[]=tmpString.split(",");
            	if(sarray[1].equals("For Company")){
            		btype.add(sarray[0]);
            	}
            	else if(sarray[1].equals("End Consumer")){
            		ctype.add(sarray[0]);
            	}
            }
       //     System.out.println("sddfd");
            cin2.close();
      //      System.out.println(ctype);
           System.out.println("ctype");
            System.out.println(ctype);
            
            tmpString=null;
            while ((tmpString = cin3.readLine()) != null){
                String weightStr = tmpString.substring(tmpString.indexOf("(")+1, tmpString.indexOf(")"));
                String sarray[]=weightStr.split(",");
                if(ctype.contains(sarray[0])){
                	Set<Integer> tmpSet=new HashSet<Integer>();
                	for(int i=1;i<sarray.length;i++){
                		tmpSet.add(Integer.parseInt(sarray[i]));
                	}
                	tMap.put(sarray[0], tmpSet);
                }
            }
            cin3.close();
            
            printMap(tMap);
            
            tmpString=null;
    	   while((tmpString = cin1.readLine()) != null){
    //		   System.out.println(tmpString);
    		   String sarray[]=tmpString.split(":");
    		   String  key=null,items=null;
    		   List<Integer> itemSet = null;
    		   key = sarray[0];
    		   

    		   
    		   if(sarray.length>=2){
	    		   items=sarray[1];
	    		   itemSet=new ArrayList<Integer>();
	    		   itemSet.clear();
	    		   String itemarray[] = null;
	    		   if(items!=null&&items!=""){
		    		   if(items.contains(",")){
		    			   itemarray = items.split(",");
			    		   for(String tmp:itemarray){
			    			   if(tmp==null||tmp=="") continue;
			    			   itemSet.add(Integer.parseInt(tmp));
			    		   }
		    		   }
		    		   else{
		    			   itemSet.add(Integer.parseInt(items));
		    		   }
	    		   }
	    		   if(tMap.get(key)!=null)
	    		   itemSet.removeAll(tMap.get(key));
    		   }
    	//	   rMap.put(key, itemSet);
    		   cout.write(key+":");
    		   int cc=0;
    		   for(Integer tt:itemSet){
    			   if(cc>=20) break;
    			   cout.write(tt+",");cc++;
    		   }
    		   cout.write("\n");
    		   
		   }
    	   
	
           System.out.println("done..");
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    
	    

	}
}

