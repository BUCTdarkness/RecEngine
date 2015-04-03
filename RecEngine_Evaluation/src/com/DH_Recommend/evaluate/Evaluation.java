package com.DH_Recommend.evaluate;

import java.io.*;
import java.util.*;

public class Evaluation {
	
	private  Integer I = 81482;
	private  String tmatrixPath="D://REData//Data//tmatrix_day.txt";
	private  String rmatrixPath="D://REData//Data//pmatrix_5";
	private  Map<String,Set<Integer> > rMap =null;
	private  Map<String,Set<Integer> > tMap =null;
	private  Integer molecular=0; //R(u)&T(u)
	private  Integer All_t=0;
	private  Integer All_r=0;	
	private  Integer Union_r=0;	
	
	public Evaluation() {
		// TODO Auto-generated constructor stub
		rMap = new HashMap<String,Set<Integer> >();
		tMap = new HashMap<String,Set<Integer> >();
		molecular=0;All_t=0; All_r=0;Union_r=0;I = 81482;
		inputMap(tmatrixPath, tMap);inputMap(rmatrixPath, rMap);
		Caculate();
	}
	
	public Evaluation(String tpath,String rpath,Integer MAX){
		tmatrixPath=tpath;rmatrixPath=rpath;I=MAX;
		rMap = new HashMap<String,Set<Integer> >();
		tMap = new HashMap<String,Set<Integer> >();
		molecular=0;All_t=0; All_r=0;Union_r=0;
		inputMap(tmatrixPath, tMap);inputMap(rmatrixPath, rMap);
		Caculate();
	}
	
	private  Integer getIntersection(Set<Integer> Rmatrix,Set<Integer> Tmatrix){
		Set<Integer> Result= new HashSet<Integer>();
		Result.addAll(Tmatrix);
		Result.retainAll(Rmatrix);
		return Result.size();
	}
	
	private void printMap(Map<String, Set<Integer> > mp){
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
	
	private void Caculate(){
		
		Set<String> keys1=rMap.keySet();
		Set<Integer> r_unionSet=new HashSet<Integer>();
		
		for(String skey:keys1){
			Set<Integer> rSet=  rMap.get(skey);
			Set<Integer> tSet=tMap.get(skey);
			if(rSet==null||tSet==null ) continue;
			molecular+=getIntersection(rSet, tSet); 
			All_t+=tSet.size();
			All_r+=rSet.size();
			r_unionSet.addAll(rSet);
		}		
		Union_r=r_unionSet.size();
	}

	private void inputMap(String path,Map<String,Set<Integer> > mp){
	       File file = new File(path);
	       BufferedReader reader = null;
	     //  FileWriter writer = new FileWriter("D://Data//1year_item_rec", true);
	       Integer step=1;
	       try{
	    	   reader = new BufferedReader(new FileReader(file));
	    	   String tmpString=null;
	    	   while((tmpString = reader.readLine()) != null){
	    		  if(step%10000==0)  System.out.println("step= "+step);
	    		   step++;
	    		   String sarray[]=tmpString.split(":");
	    		   String  key=null,items=null;
	    		   Set<Integer> itemSet = null;
	    		   key = sarray[0];
	    		   System.out.println("key="+key);
	    		   if(sarray.length>=2){
		    		   items=sarray[1];
		    		   itemSet=new HashSet<Integer>();
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
	    		   }
	    		   mp.put(key, itemSet);

	    	   }
    		   System.out.println(" done..");
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }finally{
	    	   if(reader!=null){
	    		   try{
	    			   reader.close();
	    		   }catch(IOException e1){
	    			   e1.printStackTrace();
	    		   }
	    	   }
	       }		
	}
	
	public Double getRecall(){
		return (Double)((molecular+0.0)/(All_t+0.0));
	}
	
	public Double getPrecision(){
		return (Double)((molecular+0.0)/(All_r+0.0));
	}
	
	public  Double getCoverage(){
		return (Double)((Union_r+0.0)/(I+0.0));
	}
	
	public void show(){
		System.out.println( "molecular="+molecular);
		System.out.println("All_t="+All_t);
		System.out.println("All_r="+All_r);
		System.out.println("Union_r="+Union_r);
		System.out.println("Precision= "+ getPrecision());
		System.out.println("Recall= "+getRecall());
		System.out.println("getCoverage= "+getCoverage());				
	}
	

	public String getIn1() {
		return tmatrixPath;
	}

	public void setIn1(String in1) {
		this.tmatrixPath = in1;
	}

	public String getIn2() {
		return rmatrixPath;
	}

	public void setIn2(String in2) {
		this.rmatrixPath = in2;
	}

	public Map<String, Set<Integer>> getrMap() {
		return rMap;
	}

	public void setrMap(Map<String, Set<Integer>> rMap) {
		this.rMap = rMap;
	}

	public Map<String, Set<Integer>> gettMap() {
		return tMap;
	}

	public void settMap(Map<String, Set<Integer>> tMap) {
		this.tMap = tMap;
	}

	public Integer getMolecular() {
		return molecular;
	}

	public void setMolecular(Integer molecular) {
		this.molecular = molecular;
	}

	public Integer getAll_t() {
		return All_t;
	}

	public void setAll_t(Integer all_t) {
		All_t = all_t;
	}

	public Integer getAll_r() {
		return All_r;
	}

	public void setAll_r(Integer all_r) {
		All_r = all_r;
	}

	public Integer getUnion_r() {
		return Union_r;
	}

	public void setUnion_r(Integer union_r) {
		Union_r = union_r;
	}

	public Integer getI() {
		return I;
	}
		
}

