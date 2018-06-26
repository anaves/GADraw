package problem.Paint;


import java.util.Random;

import org.opencv.core.Mat;
import abstracts.Problem;
import utils.Circle;
import utils.PDI;

public class PaintCod extends Problem{
	private static Mat original;
	private Circle [] listCircle;
	private Mat image;
	private static int cont=0;
	
	public PaintCod(int indSize) {		
		super(indSize);	
		if(original==null) {
			original = PaintInstance.getImage();
			System.out.println("ORIGINAL INSTANCIADO!");
		}
		//image = new Mat(original.size(), original.type());
		
		listCircle = new Circle[indSize];
		initImage();		
	}

	
	public static Mat getOriginal() {
		if(original==null) {
			original = PaintInstance.getImage();
		}
		return original;
	}
	public void initBackground() {
		byte [] cor= {0,0,0};
		// cor do fundo
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {
				image.put(i, j, cor);
			}
		}	
	}
	
	public void setListCircle(Circle[] listCircle) {
		this.listCircle=listCircle;
	}
	public Circle[] getListCircle(){
		return this.listCircle;
	}
	
	public void initImage() {	
		Circle circle;
		
		for (int i = 0; i < listCircle.length; i++) {
			circle = new Circle(original.rows(),original.cols()); 
			listCircle[i]=circle;		
		}
		
		createImage();
		System.gc();
	}
	
	public void createImage() {
		this.image = new Mat(original.size(), original.type());
		initBackground();
		image=PDI.drawCircles(image,listCircle);
		
		evaluate();
		System.gc();
	}
	
	public Mat getImage() {
		return image;
	}
	

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Problem getInstance() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public double evaluate() {	
		setFitness(1+(10000000.0/rmseVet()));
		return getFitness();
	}
	
	
	public double rmseVet() {
		double r=0,g=0,b=0;	
		
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {				
				r = r + Math.pow(image.get(i, j)[0]-original.get(i,j)[0],2.0);
				g = g + Math.pow(image.get(i, j)[0]-original.get(i,j)[0],2.0);
				b = b + Math.pow(image.get(i, j)[0]-original.get(i,j)[0],2.0);
			}
		}
	
		return (1.0+Math.sqrt(r)+Math.sqrt(g)+Math.sqrt(b));
	}
	
	public void posProcessing() {
		Mat m1 = PDI.denoisingFNL(this.image);
		double rmseM1=rmseVet(m1);
		Mat m2 = PDI.gaussianBlur(this.image);
		double rmseM2=rmseVet(m2);
		Mat m3 = PDI.medianBlur(this.image);
		double rmseM3 = rmseVet(m3);
		Mat m4 = this.image;
		double rmseM4 = rmseVet(m4);
		Mat melhor;
		if(rmseM1<rmseM2 && rmseM1 <rmseM3 && rmseM1 < rmseM4) {
			melhor = m1;
		}else if(rmseM2<rmseM1 && rmseM2 <rmseM3 && rmseM2 < rmseM4) {
			melhor = m2;
		}else if(rmseM3<rmseM1 && rmseM3 <rmseM2 && rmseM3 < rmseM4) {
			melhor = m3;
		}else {
			melhor = m4;
		}
		String local = PaintInstance.getPrefix();
		PDI.save(local+"Circ"+listCircle.length+"_Radiusmax_"+PaintInstance.getRadiusMax()+"_"+cont+"."+PaintInstance.getFileType(), melhor);
	}
	
	public double rmseVet(Mat m) {
		double r=0,g=0,b=0;	
		
		for (int i = 0; i < m.rows(); i++) {
			for (int j = 0; j < m.cols(); j++) {				
				r = r + Math.pow(m.get(i, j)[0]-original.get(i,j)[0],2.0);
				g = g + Math.pow(m.get(i, j)[0]-original.get(i,j)[0],2.0);
				b = b + Math.pow(m.get(i, j)[0]-original.get(i,j)[0],2.0);
			}
		}
	
		return (1.0+Math.sqrt(r)+Math.sqrt(g)+Math.sqrt(b));
	}
	
	public String toString(){
		String local = PaintInstance.getPrefix();//.replace("/in/", "/out/");		
		PDI.save(local+"Circ"+listCircle.length+"_Radiusmax_"+PaintInstance.getRadiusMax()+"_"+cont+"."+PaintInstance.getFileType(), image);
		cont++;
	
		return "Verifique: " + local +"Out_"+cont+"."+PaintInstance.getFileType() + " fitness " + getFitness() + " " + super.toString();
	}

	
}
	
	


