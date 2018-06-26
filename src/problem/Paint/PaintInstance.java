package problem.Paint;

import java.io.BufferedReader;
import java.io.FileReader;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import abstracts.Instance;
import utils.PDI;

public class PaintInstance extends Instance{
	private static Mat image;
	private static String filename;
	private static String prefix;
	private static String fileType;
	private static int genesMax;
	private static int radiusMax;
	private static int popSize;
	private int iteracoes;
	
	public PaintInstance(String input) {
		try {
			FileReader arq = new FileReader(input);
			BufferedReader in = new BufferedReader(arq);
			String linha;
			do{
				linha=in.readLine();
			}while(!linha.equals("parametros"));
			
			setFilename(in.readLine());
			setGenesMax(Integer.parseInt(in.readLine()));
			PaintInstance.radiusMax = Integer.parseInt(in.readLine());
			setMutationRate(Double.parseDouble(in.readLine()));
			PaintInstance.popSize = Integer.parseInt(in.readLine());
			this.iteracoes=Integer.parseInt(in.readLine());
			image = PDI.open(getFilename(),0);	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PaintInstance(double mutationRate, double crossoverRate){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME );
		setMutationRate(mutationRate);
		setCrossoverRate(crossoverRate);
		image = PDI.open(getFilename(),0);
	}
	
	public PaintInstance(double mutationRate, double crossoverRate, int genesMax,String filename){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME );
		setGenesMax(genesMax);
		setMutationRate(mutationRate);
		setCrossoverRate(crossoverRate);
		setFilename(filename);		
		image = PDI.open(getFilename(),0);		
		
	}	
	
	
	public static int getRadiusMax() {
		return radiusMax;
	}

	public static int getPopSize() {
		return popSize;
	}

	public int getIteracoes() {
		return iteracoes;
	}

	public static String getFilename(){
		return PaintInstance.filename;
	}
	
	public static Mat getImage(){
		if(image == null){
			image = PDI.open(getFilename(),0);
		}
		return image;
	}

	public static void setFilename(String filename) {
		String str[] = filename.split("[.]");
		setPrefix(str[0]);
		setFileType(str[1]);
		PaintInstance.filename = filename;
	}
	
	private static void setPrefix(String prefix){
		PaintInstance.prefix = prefix;
	}
	
	public static String getPrefix(){
		return PaintInstance.prefix;
	}
	
	private static void setFileType(String fileType){
		PaintInstance.fileType = fileType;
	}
	
	public static String getFileType(){
		return PaintInstance.fileType;
	}

	public static int getGenesMax() {
		return genesMax;
	}

	public static void setGenesMax(int genesMax) {
		PaintInstance.genesMax = genesMax;
	}
}
