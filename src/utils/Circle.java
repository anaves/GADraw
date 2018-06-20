package utils;

import java.util.Random;

import org.opencv.core.Mat;

import problem.Paint.PaintCod;

public class Circle {
	private short xc;
	private short yc;
	private short radius;
	private short r;
	private short g;
	private short b;
	private double alpha;
	public static double factor=0.1;
	public Circle(int rows, int cols) {
		Random rnd = new Random();
		setXc((short)rnd.nextInt(rows));
		setYc((short)rnd.nextInt(cols));
		setRadius();
		if(PaintCod.getOriginal().get(getXc(),getYc())!=null) {
			//System.out.println("na cor");
			r=(short)(PaintCod.getOriginal().get(getXc(),getYc())[0]*factor);
			g=(short)(PaintCod.getOriginal().get(getXc(),getYc())[1]*factor);
			b=(short)(PaintCod.getOriginal().get(getXc(),getYc())[2]*factor);
		}else {
			r=(short)rnd.nextInt(256);
			g=(short)rnd.nextInt(256);
			b=(short)rnd.nextInt(256);
		}
		setR(r);
		setG(g);
		setB(b);
		setAlpha(rnd.nextDouble());		
	}
	
	public int getXc() {
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
		this.radius = (short)(new Random().nextInt(10));
	}
	public void setRadius(short radius) {
		this.radius = radius;
	}
	public short getR() {
		return r;
	}
	public void setR(short r) {
		if(PaintCod.getOriginal().get(getXc(),getYc())!=null) {
			this.r = (short)(PaintCod.getOriginal().get(getXc(),getYc())[0]*factor);
		}else {
			this.r=r;
		}
	}
	public int getG() {
		return g;
	}
	public void setG(short g) {
		if(PaintCod.getOriginal().get(getXc(),getYc())!=null) {
			this.g = (short)(PaintCod.getOriginal().get(getXc(),getYc())[1]*factor);
		}else {
			this.g=g;
		}
	}
	public short getB() {
		return b;
	}
	public void setB(short b) {
		if(PaintCod.getOriginal().get(getXc(),getYc())!=null) {
			this.b = (short)(PaintCod.getOriginal().get(getXc(),getYc())[2]*factor);
		}else {
			this.b=b;
		}
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	
}

