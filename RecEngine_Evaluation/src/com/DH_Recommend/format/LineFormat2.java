package com.DH_Recommend.format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class LineFormat2 {
	public static void main(String []args) throws Exception{
		String inpath="D://REData//user-SimilarUser-Unionitem1000";
		String outpath="D://REData//r2matrix";
		BufferedReader cin=new BufferedReader(new FileReader(inpath));
		FileWriter cout=new FileWriter(outpath,true);
		String tmpString=null;
		while((tmpString=cin.readLine())!=null){
			String sarray[]=tmpString.split(":");
			String keyString=sarray[0];
			cout.write(keyString+":");
			
			int len=sarray.length;
			if(len>=2){
				String tmpsarray[]=sarray[1].split(" ");
				cout.write(tmpsarray[0]);
				int len2=Math.min(10, tmpsarray.length);
				for(int i=1;i<len2;i++){
					cout.write(","+tmpsarray[i]);
				}
			}
			cout.write("\n");
		}
		cin.close();
		cout.close();
	}
}
