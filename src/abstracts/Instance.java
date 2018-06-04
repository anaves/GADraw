package abstracts;

public abstract class Instance {
	private double mutationRate = 0.0; // default: no mutation
	private double crossoverRate = 1.0; // default: crossover
	
	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public double getCrossoverRate() {
		return crossoverRate;
	}

	public void setCrossoverRate(double crossoverRate) {
		this.crossoverRate = crossoverRate;
	}
	
	
	
}
