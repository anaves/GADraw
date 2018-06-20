package problem.Paint;


import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Random;

import org.opencv.core.CvType;
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
		image = new Mat(original.size(), original.type());
		initBackground();
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
		Random rnd = new Random();
		//short c=(short)(255-rnd.nextInt(2)*255);
		//byte r=(byte)rnd.nextInt(c),g=(byte)rnd.nextInt(c),b=(byte)rnd.nextInt(c);
		byte [] cor= {0,0,0};
		//System.out.println(cor[0]+" "+cor[1]+ " "+ cor[2]);
		// Cor do fundo
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {
				image.put(i, j, cor);
			}
		}	
	//	System.out.println(this);
		//System.exit(0);
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
		
			image=PDI.drawCircle2(image,circle);
			System.gc();
		}
		
		evaluate();
	}
	
	public void createImage() {
		setImage();
		for (int i = 0; i < listCircle.length; i++) {
			image=PDI.drawCircle2(image,listCircle[i]);
		}
		evaluate();
		System.gc();
	}
	
	public void setImage() {
		this.image = new Mat(original.size(), original.type());
		initBackground();
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
	
		return (1.0+Math.sqrt(r+g+b));
	}
	
	public String toString(){
		String local = PaintInstance.getPrefix();//.replace("/in/", "/out/");
		cont++;
		
		PDI.save(local+"Out_"+cont+"."+PaintInstance.getFileType(), image);
	
		return "Verifique: " + local +"NOut_"+cont+"."+PaintInstance.getFileType() + " fitness " + getFitness() + " " + super.toString();
	}

	
}
	
	


