package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

public class PDI {
	
	private static void load(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME );
	}
	
	public static Mat medianBlur(Mat src){
		load();
		Mat dst = new Mat();
		Random rnd = new Random();
		int alpha = rnd.nextInt(2);
		int dim = (1-alpha)*3+(alpha)*5; 
		
		if(Math.random()<0.5){
			Imgproc.medianBlur(src, dst, dim);
		}else{
			Imgproc.medianBlur(src, dst, dim);
		}
		
		return dst;
	}
	
	public static Mat gaussianBlur(Mat src){
		load();
		Mat dst = new Mat();
		Random rnd = new Random();
		int alpha = rnd.nextInt(2);
		int dim = (1-alpha)*3+(alpha)*5; 
		
		if(Math.random()<0.5){
			Imgproc.GaussianBlur(src, dst, new Size(dim,dim), 0.0);
		}else{
			Imgproc.GaussianBlur(src, dst, new Size(dim,dim), 0.0);
		}
		return dst;
	}
	
	public static Mat denoisingFNL(Mat src){
		load();
		Mat dst = new Mat();
		Photo.fastNlMeansDenoising(src, dst);
		return dst;
	}
	
	public static Mat denoisingTVL(Mat src){
		load();
		List<Mat> observations = new LinkedList<Mat>();
		observations.add(src);
		Mat dst = src.clone();
		Photo.denoise_TVL1(observations, dst);
		return dst;
	}
	public static void save(String filename, Mat img){
		load();
		Imgcodecs.imwrite(filename, img);
	}
	
	public static Mat open(String filename){
		load();
		Mat src = Imgcodecs.imread(filename);
		src = convert2Gray(src);
		Imgproc.equalizeHist(src, src);
		return src;
	}
	
	public static Mat convert2Gray(Mat src){
		load();
		Mat dst = new Mat();
		Imgproc.cvtColor(src, dst,Imgproc.COLOR_RGB2GRAY);
		save("images/out/teste3.png",dst);
		return dst;
	}
	
	public static Mat junta(Mat src1, Mat src2){
		load();
		Mat dst = src1.clone();
		double alpha = 0.5;
		Core.addWeighted(src1, alpha, src2, 1-alpha,0, dst);
			
		return dst;
	}
	
	public static Mat umPontoLinha(Mat src1, Mat src2){
		load();
		Mat dst = src1.clone();
		Random rnd = new Random();
		int cut = rnd.nextInt(src1.rows());
		for (int i = 0; i < dst.rows(); i++) {
			for (int j = 0; j < dst.cols(); j++) {
				if(i < cut){
					dst.put(i,j,src2.get(i, j));
				}
			}			
		}		
		return dst;
		
	}
	
	public static Mat umPontoColuna(Mat src1, Mat src2){
		load();
		Mat dst = src1.clone();
		Random rnd = new Random();
		int cut = rnd.nextInt(src1.cols());
		for (int i = 0; i < dst.rows(); i++) {
			for (int j = 0; j < dst.cols(); j++) {
				if(j < cut){
					dst.put(i,j,src2.get(i, j));
				}
			}			
		}		
		return dst;		
	}
	
	public static Mat uniforme(Mat src1, Mat src2){
		load();
		Mat dst = src1.clone();
		for (int i = 0; i < dst.rows(); i++) {
			for (int j = 0; j < dst.cols(); j++) {
				if(Math.random() < 0.5){
				
					dst.put(i,j,src2.get(i, j));
				}
				
			}			
		}		
		return dst;	
	}
	
	public static Mat morphology(Mat src,int n) {
		Mat dst = src.clone();
		Mat kernel = Mat.ones(new Size(n,n), src.type());
		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_TOPHAT, kernel);
		return dst;
	}
	
	public static Mat dilate(Mat src,int n) {
		Mat dst = src.clone();
		Mat kernel = Mat.ones(new Size(n,n), src.type());
		
		Imgproc.dilate(src, dst,kernel);
		return dst;
	}
	public static Mat erode(Mat src,int n) {
		Mat dst = src.clone();
		Mat kernel = Mat.ones(new Size(n,n), src.type());
		
		Imgproc.erode(src, dst,kernel);
		return dst;
	}
	
	
	public static Mat equalizeHistogram(Mat src){
		load();
		Mat dst = src.clone();
		Imgproc.equalizeHist(src, dst);;
		return dst;
	}
	
	public static void drawCircle(Mat src, int x, int y) {
		Random rnd = new Random();
		int c = rnd.nextInt(2);
		int r=255-255*c, g=255-255*c, b=255-255*c;			
		int radius = rnd.nextInt(100);
		Imgproc.circle (
		         src,                 //Matrix obj of the image
		         new Point(x,y),    //Center of the circle
		         radius,                    //Radius
		         new Scalar(r, g, b)  //Scalar object for color
		       );
	}
	
	/**
	 * 
	 * @param src Imagem em tons de cinza
	 * @return
	 */
	public static double[] histogram(Mat src){
		double histogram[] = new double[256];
		
		for (int i = 0; i < src.rows(); i++) {
			for(int j=0; j< src.cols(); j++){				
				double v[]= src.get(i, j);
				int k = (int) v[0];
				histogram[k]=histogram[k]+1;
				
			}			
		}		
		
		return histogram;
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
