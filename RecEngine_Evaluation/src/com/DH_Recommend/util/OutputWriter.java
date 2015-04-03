package com.DH_Recommend.util;

import java.util.*;
import java.math.*;
import java.io.*;

public class OutputWriter {  
    private final PrintWriter writer;  
  
    public OutputWriter(OutputStream outputStream) {  
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));  
    }  
  
    public OutputWriter(Writer writer) {  
        this.writer = new PrintWriter(writer);  
    }  
  
    public void print(Object...objects) {  
        for (int i = 0; i < objects.length; i++) {  
            if (i != 0)  
                writer.print(' ');  
            writer.print(objects[i]);  
        }  
    }  
  
    public void printDouble(double x, int precision) {  
        writer.printf("%." + precision + "f", x);  
    }  
  
    public void printLineDouble(double x, int precision) {  
        printDouble(x, precision);  
        printLine();  
    }  
  
    public void printLine(Object...objects) {  
        print(objects);  
        writer.println();  
    }  
  
    public void printIntArray(int[] data) {  
        for (int i = 0; i < data.length; i ++)  
            if (i < data.length - 1) {  
                print(data[i] + " ");  
            } else {  
                print(data[i]);  
            }  
    }  
  
    public void printLongArray(long[] data) {  
        for (int i = 0; i < data.length; i ++)  
            if (i < data.length - 1) {  
                print(data[i] + " ");  
            } else {  
                print(data[i]);  
            }  
    }  
  
    public void close() {  
        writer.close();  
    }  
}  
