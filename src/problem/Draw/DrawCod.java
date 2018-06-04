package problem.Draw;


import java.util.Random;

import org.opencv.core.Mat;
import abstracts.Problem;
import utils.PDI;

public class DrawCod extends Problem {
	private Mat original;
	private Mat image;
	private static int cont=0;
	private static Mat result;
		
	public DrawCod(int popSize) {
		super(popSize);
		original = DrawInstance.getImage();
		image = initCircle(original); 
		result = new Mat(original.size(),original.type());
		
		evaluate();
	}

	public Mat initCircle(Mat original) {
		image = new Mat(original.size(), original.type());
				
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {
				if (new Random().nextDouble()<=0.5) {					
					PDI.drawCircle(image, i, j);
				}
				
			}
		}
		return image;
	}
	
	public Mat init(Mat original) {
		image = new Mat(original.size(), original.type());
		int quad = 10;
		
		for (int i = 0; i < image.rows(); i=i+quad) {
			
			for (int j = 0; j < image.cols(); j=j+quad) {
				
				Random rnd = new Random();
				int v = rnd.nextInt(20);
				if(v>10) {
					v=255;
				}
				int auxi = i;
				for (int k = 0; k < quad && auxi<image.rows(); k++) {
					int auxj=j;
					for (int k2 = 0; k2 < quad && auxj < image.cols(); k2++) {
						image.put(auxi,auxj,v);
						auxj++;
					}					
					auxi++;					
				}
			
				
				//	System.out.println(v);
				
			}
		
		}
		
		return image;
	}
	
	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getRow() {
		Random rnd = new Random();
		int v = rnd.nextInt(image.rows());
		return v;
	}
	public int getCol() {
		Random rnd = new Random();
		int v = rnd.nextInt(image.cols());
		return v;
	}
	@Override
	public Problem getInstance() {		
		return new DrawCod(getPopSize());
	}

	@Override
	public double evaluate() {	
		
		/*Mat original = DrawInstance.getImage();
		Mat dif=null;
		double v=0;
		String local = DrawInstance.getPrefix();//.replace("/in/", "/out/");
		//Core.absdiff(original, image, dif);
		//PDI.save(local+"Out4"+"."+DrawInstance.getFileType(),dif);
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {
				if(image.get(i, j)[0]==original.get(i, j)[0]) {
					v++;
				}
			}
		}*/
		//double v = Core.norm(original, image);
		setFitness(dist()+1);
		return getFitness();
	}
	
	public double dist() {
		double r=0;
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {
				if(image.get(i,j)[0] == original.get(i,j)[0]) {
					r++;
					result.put(i,j,image.get(i,j)[0]);
				}
			}
		}
		return r;
	}
	
	public Mat getImage(){
		return image;
	}
	
	public void setImage(Mat src){
		image = src;
		evaluate();
	}	
	
	public String toString(){
		String local = DrawInstance.getPrefix();//.replace("/in/", "/out/");
		cont++;
		PDI.save(local+"Out_"+cont+"."+DrawInstance.getFileType(), image);
		PDI.save(local+"Result_"+cont+"."+DrawInstance.getFileType(), result);
		return "Verifique: " + local +"Out_"+cont+"."+DrawInstance.getFileType() + " fitness " + getFitness();
	}
	
	
}
