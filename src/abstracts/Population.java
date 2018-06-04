package abstracts;

public abstract class Population {
	public abstract int getPopSize();
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
	
}
