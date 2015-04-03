package com.DH_Recommend.format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class DataMerge2 {
	public static void main(String []args) throws Exception{
		String inpath="D://REData//Data2//in";
		String outpath="D://REData//Data2//pmatrix_day";
		BufferedReader cin= null;
		FileWriter cout=new FileWriter(outpath,true);
		for(Integer i=1;i<=16;i++){
			cin=new BufferedReader(new FileReader(inpath+i.toString()));
			String tmpString=null;
			while((tmpString=cin.readLine())!=null){
				cout.write(tmpString);
				cout.write("\n");
			}
			cin.close();
		}
		cout.close();
	}
}
