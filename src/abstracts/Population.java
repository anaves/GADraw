package abstracts;

public abstract class Population {
	private int popSize;	
	public abstract Problem get(int indice);
	public abstract void set(int indice, Problem problem);
	public abstract Problem bestSolution(Problem problem);
	
	/**
	 * 
	 * @param popSize
	 * @return 
	 */
	public double getPopulationFitness(){
		double sum = 0.0;
		for (int i = 0; i < getPopSize(); i++) {
			sum = sum + get(i).getFitness();
		}		
		return sum;
	}
	
	public int getPopSize() {
		return popSize;
	}
	
	public void setPopSize(int popSize) {
		this.popSize=popSize;
	}
}
