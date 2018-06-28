package utils;

import java.util.Random;

import problem.Paint.PaintCod;
import problem.Paint.PaintInstance;

public class Circle implements Cloneable{
	private short xc;
	private short yc;
	private short radius;
	private short r;
	private short g;
	private short b;
	private double alpha;
	private static double factor=0.0;
	
	public Circle(int rows, int cols) {
		Random rnd = new Random();
		setXc((short)rnd.nextInt(rows));
		setYc((short)rnd.nextInt(cols));
		setRadius();	
		this.r = (short)(rnd.nextInt(256));
		this.g = (short)(rnd.nextInt(256));
		this.b = (short)(rnd.nextInt(256));
		setAlpha(rnd.nextDouble());		
	}
	
	public Circle getClone() {
        try {
            // call clone in Object.
            return (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println (" Cloning not allowed. " );
            return this;
        }
    }
	
	public short getXc() {
		return xc;
	}
	public void setXc(short xc) {
		this.xc = xc;
	}
	public short getYc() {
		return yc;
	}
	public void setYc(short yc) {
		this.yc = yc;
	}
	public short getRadius() {
		return radius;
	}
	public void setRadius() {
		this.radius = (short)(new Random().nextInt(PaintInstance.getRadiusMax()));
	}
	public void setRadius(short radius) {
		this.radius = radius;
	}
	public short getR() {
		return r;
	}
	public void setR() {
		Random rnd = new Random();
	//	if(rnd.nextDouble() < 0.5) {
	//		this.r = (short)rnd.nextInt(256);
	//	}else {
			this.r = (short)(PaintCod.getOriginal().get(getXc(),getYc())[0]*Math.random());
	//	}
		
	}
	public int getG() {
		return g;
	}
	public void setG() {		
		Random rnd = new Random();
	//	if(rnd.nextDouble() < 0.5) {
	//		this.g = (short)rnd.nextInt(256);
	//	}else {
			this.g = (short)(PaintCod.getOriginal().get(getXc(),getYc())[1]*Math.random());
	//	}
	}
	public short getB() {
		return b;
	}
	public void setB() {	
		Random rnd = new Random();
//		if(rnd.nextDouble() < 0.5) {
//			this.b = (short)rnd.nextInt(256);
//		}else {
			this.b = (short)(PaintCod.getOriginal().get(getXc(),getYc())[2]*Math.random());
//		}
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	public static void setFactor() {
		if(factor < 1.0){
			factor = factor + 0.001;
		}	
	}
	
	private double getFactor() {		
		return factor;
	}

	@Override
	public String toString() {
		return "Circle [xc=" + xc + ", yc=" + yc + ", radius=" + radius + ", r=" + r + ", g=" + g + ", b=" + b
				+ ", alpha=" + alpha + "]";
	}
	
}

