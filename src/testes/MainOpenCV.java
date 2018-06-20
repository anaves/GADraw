package testes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.Instrumentation;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;


import utils.PDI;

public class MainOpenCV {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME );
		Mat img1 = open("images/in/draw/arara.png");
		PDI.drawCircle(img1, img1.rows()/2, img1.cols()/2);
		save("images/out/CIRCLE.png",img1);
		
		
	}
	
	public static Mat teste(Mat src1, Mat logo) {
		
		Mat dst = convert(src1);
		//logo = convert(logo);
		int linha =  dst.rows()/2;
		int coluna = dst.cols()/2;
		System.out.println(dst.channels());
		System.out.println(logo.channels());
		for(int i = 0; i < logo.rows(); i++) {
			for (int j = 0; j < logo.cols(); j++) {
				dst.put(linha+i, coluna+j, logo.get(i, j));
			}
		}
		
		return dst;
	}
	
	public static void testCrossover(Mat img1, Mat img2){
		int value = new Random().nextInt(3);
		Mat dst=null;
		switch (value) {
		case 0:
			dst = umPontoColuna(img1,img2);
			break;
		case 1:
			dst = umPontoLinha(img1,img2);
			break;
		case 2:
			dst = uniforme(img1,img2);
			break;
		default:
			break;
		}		
		
		save("images/out/traffic"+value+".png",dst);	
	}
	
	public static Mat binary(Mat src){
		Mat dst = src.clone();
		for (int i = 0; i < src.rows(); i++) {
			for (int j = 0; j < src.cols(); j++) {
				double v[] = src.get(i,j);
				
					if(v[0]<127){
						v[0]=0;
					}else{
						v[0]=255;
					}
					for(int k = 1; k < v.length; k++){
						v[k]=v[0];
					}
				
				dst.put(i, j, v);				
			}
		}
		return dst;
	}
	
	public static Mat negativo(Mat src){
		Mat dst = src.clone();
		for (int i = 0; i < src.rows(); i++) {
			for (int j = 0; j < src.cols(); j++) {
				double v[] = src.get(i,j);			
					for(int k = 0; k < v.length; k++){
						v[k]=255-v[k];
					}				
				dst.put(i, j, v);				
			}
		}
		return dst;
	}
	
	public static Mat teste(Mat src){
		Mat dst = src.clone();
		for (int i = 0; i < src.rows(); i++) {
			for (int j = 0; j < src.cols(); j++) {
				double v[] = src.get(i,j);			
					for(int k = 0; k < v.length; k++){
						
						if(v[k]<100){
							v[k]=0;
						}
					}				
				dst.put(i, j, v);				
			}
		}
		return dst;
	}
	public static Mat saltPepper(Mat src){
		Mat dst = src.clone();
		Random rnd = new Random();
		for(int i = 0; i< dst.rows(); i++){
			for(int j = 0 ; j < dst.cols(); j++){
				switch (rnd.nextInt(30)) {
				case 0:
					double v[] = dst.get(i, j);
					for(int k = 0; k < v.length; k++){
						v[k]=0;
					}
					dst.put(i, j, v);
					break;
				case 1:
					v = dst.get(i, j);
					for(int k = 0; k < v.length; k++){
						v[k]=255;
					}
					dst.put(i, j, v);
					break;
				default:
					break;
				}
			}
		}
		return dst;
	}
	
	public static double[] histogram(Mat src){
		double histogram[] = new double[256];
		src = convert2Gray(src);
		for (int i = 0; i < src.rows(); i++) {
			for(int j=0; j< src.cols(); j++){				
				double v[]= src.get(i, j);
				int k = (int) v[0];
				histogram[k]=histogram[k]+1;
				
			}			
		}		
		
		return histogram;
	}
	public static Mat medianBlur(Mat src){
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
	
	public static Mat denoising(Mat src){
		Mat dst = new Mat();
		Photo.fastNlMeansDenoising(src, dst);
		return dst;
	}
	
	public static Mat denoisingTVL(Mat src){
		List<Mat> observations = new LinkedList<Mat>();
		observations.add(src);
		Mat dst = src.clone();
		Photo.denoise_TVL1(observations, dst);
		return dst;
	}
	
	public static void save(String filename, Mat img){
		Imgcodecs.imwrite(filename, img);
	}
	
	public static Mat open(String filename){
		return Imgcodecs.imread(filename);
	}
	
	public static Mat open2(String filename){
		return Imgcodecs.imread(filename,-1);
	}
	
	public static Mat noiseOpen(String filename){
	    Mat input = open(filename);
	    Mat imGray = new Mat();
	    Imgproc.cvtColor(input,imGray,Imgproc.COLOR_BGR2GRAY);
	    System.out.println(imGray);
	    System.out.println("channels " + imGray.channels());
	    Mat noise = new Mat(imGray.size(),imGray.channels());
	    Mat result = new Mat();
	    Core.normalize(imGray, result, 0.0, 1.0, Core.NORM_MINMAX, imGray.channels());
	    Core.randn(noise, 0, 0.1);
	    Core.add(result, noise, result); 
	    Core.normalize(result, result, 0.0, 1.0, Core.NORM_MINMAX, result.channels());
	    result.convertTo(result, input.channels(), 255, 0);
	    save("images/out/output.png",result);
	    return result;
	}
	public static void print(String msg, Mat mat){
		System.out.println(msg);
		System.out.println(mat.dump());
	}
	
	public static Mat convert2Gray(Mat src){
		Mat dst = new Mat();
		Imgproc.cvtColor(src, dst,Imgproc.COLOR_RGB2GRAY);
		save("images/out/teste2.png",dst);
		return dst;
	}
	
	public static Mat convert(Mat src){
		Mat dst = new Mat();
		Imgproc.cvtColor(src, dst,Imgproc.COLOR_RGB2RGBA);
		
		return dst;
	}
	
	public static Mat junta(Mat src1, Mat src2){
		Mat dst = src1.clone();
		double alpha = 0.5;
		Core.addWeighted(src1, alpha, src2, 1-alpha,0, dst);
			
		return dst;
	}
	
	public static Mat umPontoLinha(Mat src1, Mat src2){
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
