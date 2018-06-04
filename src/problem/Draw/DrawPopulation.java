package problem.Draw;

import java.util.Random;

import abstracts.Operators;
import abstracts.Population;
import abstracts.Problem;


public class DrawPopulation extends Population{
	Problem population[];
	
	public DrawPopulation(int popSize){
		population = new DrawCod[popSize];
		initPopulation();
		
	}
	/**
	 * Inicializa População segundo estratégia de AGH
	 * Jonatas de Paiva
	 * @param indSize
	 */
	private void initPopulation(){
		DrawCod ind1Original = new DrawCod(getPopSize());		
		
		
		for (int i = 0; i < population.length; i++) {	
			DrawCod ind1 = new DrawCod(getPopSize());
			population[i] = ind1;
		}
		System.out.println("print in DrawPopulation.java");
		for (int i = 0; i < population.length; i++) {
			System.out.println((i) + " " + population[i].evaluate());
		}
		
		
		
	}
	
	@Override
	public int getPopSize() {
		return population.length;
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
	/*	System.out.println("*************************");
		for(int i=0; i< population.length; i++){
			System.out.println(i + " = " + population[i].getFitness());
		}*/
		
		for(int i=0; i< population.length; i++){			
			if(population[i].getFitness()>melhor.getFitness()){
			//	System.out.println("trocou " + melhor.getFitness() + " por " + population[i].getFitness());
				melhor =  population[i];
			}
		}		
		return melhor;
	}

}
