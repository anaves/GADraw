package abstracts;

/**
 * Abstract class Popoulation
 * @author Alysson A. Naves Silva *
 */

public abstract class Population {
	private int popSize;	
	public abstract Problem get(int index);
	public abstract void set(int index, Problem problem);
	public abstract Problem bestSolution();
	public abstract void updatePopulation(Problem child);
	
	/**
	 * Calculates the fitness sum of individuals in the population.
	 * @return the fitness sum of individuals
	 */
	public double getPopulationFitness(){
		double sum = 0.0;
		for (int i = 0; i < getPopSize(); i++) {
			sum = sum + get(i).getFitness();
		}		
		return sum;
	}
	
	/**
	 * This method returns quantity of individuals present in the population
	 * @return the size of the population
	 */
	public int getPopSize() {
		return popSize;
	}
	/**
	 * This method changes the amount of individuals present in the population
	 * @param popSize quantity of individuals present in the population
	 */	
	public void setPopSize(int popSize) {
		this.popSize=popSize;
	}
}
