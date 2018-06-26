package abstracts;
/**
 * 
 * @author Alysson A. Naves Silva
 *
 */
public abstract class Problem{
	
	private int indSize;	
	private double fitness;
	
	public Problem(int indSize){
		setIndSize(indSize);
	}
	
	public abstract boolean stop();
	
	public abstract Problem getInstance();
	
	public abstract double evaluate();

	public int getIndSize() {
		return indSize;
	}

	public void setIndSize(int indSize) {
		this.indSize = indSize;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	
}
