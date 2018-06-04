package abstracts;

public abstract class Problem{
	
	private int popSize, indSize;	
	private double fitness;
	
	public Problem(int popSize, int indSize){
		setPopSize(popSize);
		setIndSize(indSize);
	}
	
	public Problem(int popSize){
		setPopSize(popSize);
	}
	
	public abstract boolean stop();
	
	public abstract Problem getInstance();
	
	public abstract double evaluate();

	public int getPopSize() {
		return popSize;
	}

	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}

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
