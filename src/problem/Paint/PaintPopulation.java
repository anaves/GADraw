package problem.Paint;

import java.util.Random;

import abstracts.Population;
import abstracts.Problem;
import utils.Circle;



public class PaintPopulation extends Population{
	Problem population[];
	Problem melhor;
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
		
		Circle.setFactor();
		for (int i = 0; i < population.length; i++) {				
			PaintCod ind = new PaintCod(PaintInstance.getGenesMax());
			population[i] = ind;
			//System.out.println(ind);
			//System.out.println((i) + " " + ind.getFitness());
			System.gc();
		}	
		System.out.println("start Population");
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
	public void updatePopulation(Problem child) {
		int indexPoor = new Random().nextInt(getPopSize());
		Problem pior = get(0);
		for (int i = 1; i < getPopSize(); i++) {
			if(pior.getFitness() > get(i).getFitness()){ // max <, min >
				pior = get(i);
				indexPoor = i;
			}
		}
		if(child.getFitness()>pior.getFitness()) {
			set(indexPoor, child);
		}
		
		
	}
	

	@Override
	public Problem bestSolution() {
		if(melhor==null){
			melhor = population[0];
		}		
		for(int i=0; i< population.length; i++){		
			if(population[i].getFitness()>melhor.getFitness()){
			//	System.out.println("trocou " + melhor.getFitness() + " por " + population[i].getFitness());
				melhor =  population[i];
			}
		}	
		Circle.setFactor();
		return melhor;
	}

}
