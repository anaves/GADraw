package problem.Paint;

import java.util.Random;

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
