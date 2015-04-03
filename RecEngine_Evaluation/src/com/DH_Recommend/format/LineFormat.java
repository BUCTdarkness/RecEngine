package com.DH_Recommend.format;

import java.util.*;
import java.math.*;
import java.io.*;

public class LineFormat {

	public static void main(String []args) throws Exception{
		String inpath="D://REData//Test.txt";
		String outpath="D://REData//tmatrix";
		BufferedReader cin=new BufferedReader(new FileReader(inpath));
		FileWriter cout=new FileWriter(outpath,true);
		String tmpString=null;
		while((tmpString=cin.readLine())!=null){
			String sarray[]=tmpString.split(",");
			String keyString=sarray[0];
			cout.write(keyString+":");
			int len=sarray.length;
			if(len>=2){
				cout.write(sarray[1]);
				for(int i=2;i<len;i++){
					cout.write(","+sarray[i]);
				}
				cout.write("\n");
			}
		}
		cin.close();
		cout.close();
	}
}
