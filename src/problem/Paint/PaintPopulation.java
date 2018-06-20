package problem.Paint;

import java.util.Random;

import abstracts.Operators;
import abstracts.Population;
import abstracts.Problem;


public class PaintPopulation extends Population{
	Problem population[];
	
	public PaintPopulation(int popSize){
		setPopSize(popSize);
		population = new PaintCod[popSize];
		initPopulation();
		
	}
	/**
	 *
	 * @param indSize
	 */
	private void initPopulation(){	
		System.out.println("print in PaintPopulation.java");
		Random rnd = new Random();
		for (int i = 0; i < population.length; i++) {				
			PaintCod ind = new PaintCod(PaintInstance.getGenesMax());
			population[i] = ind;
			System.out.println((i) + " " + ind.getFitness());
			System.gc();
		}	
		
	}
	
	
	@Override
	public Problem get(int indice) {
		return population[indice];
	}

	@Override
	public void set(int indice, Problem problem) {
		population[indice] = problem;
		
	}

	@Override
	public Problem bestSolution(Problem melhor) {
		if(melhor==null){
			melhor = population[0];
		}		
		for(int i=0; i< population.length; i++){			
			if(population[i].getFitness()>melhor.getFitness()){
			//	System.out.println("trocou " + melhor.getFitness() + " por " + population[i].getFitness());
				melhor =  population[i];
			}
		}		
		return melhor;
	}

}
