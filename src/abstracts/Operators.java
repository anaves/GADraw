package abstracts;

public interface Operators {
	public Problem crossover(Problem p1, Problem p2);
	public void mutation(Problem ind);
	public Problem select(Population pop);
	public void updatePopulation(Population pop, Problem child);
}
