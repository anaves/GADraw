package abstracts;

/**
 * Interface with the methods that must be implemented regarding genetic operators.
 * 
 * @author Alysson A. Naves Silva
 *
 */
public interface Operators {
	/**
	 * Crossover operator
	 * @param p1 first selected individual
	 * @param p2 second selected individual
	 * @return a vector containing the two children generated
	 */
	public Problem[] crossover(Problem p1, Problem p2);
	public void mutation(Problem ind);
	public Problem select(Population pop);
	
}
