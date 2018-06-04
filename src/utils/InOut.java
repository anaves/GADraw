package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.opencv.core.Mat;

public class InOut {
	private static Scanner in = new Scanner(System.in);
	public static int inValue(String msg){
		System.out.print(msg+": ");
		return in.nextInt();
	}
	
	public static void printMat(String msg, Mat mat){
		System.out.println(msg);
		System.out.println(mat.dump());
	}
	
	public static void arrayToFile(double v[], String nome){
		FileWriter arq;
		try {
			arq = new FileWriter("file/"+ nome + ".dat");
			PrintWriter gravarArq = new PrintWriter(arq);
			
		    
			for (int i = 0; i < v.length; i++) {
				gravarArq.println(i + "," + v[i]);
			}
			arq.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
