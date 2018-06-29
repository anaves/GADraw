package problem.Paint;
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
	public double evaluate() {	
		setFitness(1+(100000000.0/dist1()));
		return getFitness();
	}
	
	
	public double dist1() {
		double r=0,g=0,b=0;	
		
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {				
				r = r + Math.abs(image.get(i, j)[0]-original.get(i,j)[0]);
				g = g + Math.abs(image.get(i, j)[1]-original.get(i,j)[1]);
				b = b + Math.abs(image.get(i, j)[2]-original.get(i,j)[2]);
			}
		}
	
		return (1.0+r+g+b);
	}
	
	public void posProcessing() {
		Mat m1 = PDI.denoisingFNL(this.image);
		double distM1=dist(m1);
		Mat m2 = PDI.gaussianBlur(this.image);
		double distM2=dist(m2);
		Mat m3 = PDI.medianBlur(this.image);
		double distM3 = dist(m3);
		Mat m4 = this.image;
		double distM4 = dist(m4);
		Mat melhor;
		if(distM1<distM2 && distM1 <distM3 && distM1 < distM4) {
			melhor = m1;
		}else if(distM2<distM1 && distM2 <distM3 && distM2 < distM4) {
			melhor = m2;
		}else if(distM3<distM1 && distM3 <distM2 && distM3 < distM4) {
			melhor = m3;
		}else {
			melhor = m4;
		}
		String local = PaintInstance.getPrefix();
		PDI.save(local+"Circ"+listCircle.length+"_Radiusmax_"+PaintInstance.getRadiusMax()+"_"+cont+"."+PaintInstance.getFileType(), melhor);
	}
	
	
	public double dist(Mat m) {
		double r=0,g=0,b=0;	
		
		for (int i = 0; i < m.rows(); i++) {
			for (int j = 0; j < m.cols(); j++) {				
				r = r + Math.abs(m.get(i, j)[0]-original.get(i,j)[0]);
				g = g + Math.abs(m.get(i, j)[1]-original.get(i,j)[1]);
				b = b + Math.abs(m.get(i, j)[2]-original.get(i,j)[2]);
			}
		}
	
		return (1.0+r+g+b);
	}
	
	public String toString(){
		String local = PaintInstance.getPrefix();		
		PDI.save(local+"Circ"+listCircle.length+"_Radiusmax_"+PaintInstance.getRadiusMax()+"_"+cont+"."+PaintInstance.getFileType(), image);
		cont++;	
		return "Verifique: " + local +"Out_"+cont+"."+PaintInstance.getFileType() + " fitness " + getFitness() + " " + super.toString();
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
	
}
	
	


