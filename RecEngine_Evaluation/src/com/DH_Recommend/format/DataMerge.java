package com.DH_Recommend.format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class DataMerge {
	public static void main(String []args) throws Exception{
		String inpath="D://REData//Data2//in";
		String outpath="D://REData//Data2//pmatrix_day";
		BufferedReader cin= null;
		FileWriter cout=new FileWriter(outpath,true);
		for(Integer i=1;i<=16;i++){
			cin=new BufferedReader(new FileReader(inpath+i.toString()));
			String tmpString=null;
			while((tmpString=cin.readLine())!=null){
				String sarray[]=tmpString.split(",");
				String keyString=sarray[0];
				cout.write(keyString+":");
				int len=sarray.length;
				if(len>=2){
					sarray[1].replace(",", "");
					cout.write(sarray[1]);
					len=Math.min(1000, len);
					for(int j=2;j<len;j++){
						if(sarray[j]==null) continue;
						sarray[j].replace(",", "");
						sarray[j].replace(")", "");
						cout.write(","+sarray[j]);
					}
				}
				cout.write("\n");
			}
			cin.close();
		}
		cout.close();
	}
}
