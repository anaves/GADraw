package problem.Draw;

import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import abstracts.Instance;
import utils.PDI;

public class DrawInstance extends Instance{
	private static Mat image;
	private static String filename;
	private static String prefix;
	private static String fileType;
	
	public DrawInstance(double mutationRate, double crossoverRate){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME );
		setMutationRate(mutationRate);
		setCrossoverRate(crossoverRate);
		image = PDI.open(getFilename());
		
	//	image = PDI.convert2Gray(imageOri);
		image = PDI.equalizeHistogram(image);
	}
	
	public DrawInstance(double mutationRate, double crossoverRate, String filename){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME );
		setMutationRate(mutationRate);
		setCrossoverRate(crossoverRate);
		setFilename(filename);		
		image = PDI.open(getFilename());
		//image = PDI.convert2Gray(imageOri);
		image = PDI.equalizeHistogram(image);
		
	}	
	
	public static String getFilename(){
		return DrawInstance.filename;
	}
	
	public static Mat getImage(){
		if(image == null){
			image = PDI.open(getFilename());
		}
		return image;
	}

	public static void setFilename(String filename) {
		String str[] = filename.split("[.]");
		setPrefix(str[0]);
		setFileType(str[1]);
		DrawInstance.filename = filename;
	}
	
	private static void setPrefix(String prefix){
		DrawInstance.prefix = prefix;
	}
	
	public static String getPrefix(){
		return DrawInstance.prefix;
	}
	
	private static void setFileType(String fileType){
		DrawInstance.fileType = fileType;
	}
	
	public static String getFileType(){
		return DrawInstance.fileType;
	}
}
