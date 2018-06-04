package genetics;

import abstracts.Genetic;
import abstracts.Instance;
import abstracts.Operators;
import abstracts.Population;
import abstracts.Problem;

public class MyGA implements Genetic{
	private Operators operator; 
	private Instance instance;
	private Population population;
	private Problem melhor;
	
	public MyGA(Operators operator, Instance instance, Population population){
		this.operator = operator;
		this.instance = instance;
		this.population = population;
		this.melhor=null;
	}

	public void execute() {
		int i = 0;
		
		do{			
		
			if(Math.random() < instance.getCrossoverRate()){
				Problem p1 = operator.select(population);
				Problem p2 = operator.select(population);
			
				Problem child = operator.crossover(p1,p2);
			
						
				if(Math.random() < instance.getMutationRate()){
					operator.mutation(child);
				}
				
				operator.updatePopulation(population,child);
			}
			melhor = population.bestSolution(melhor);
			if(i%5000==0) {
				System.out.println(melhor);
			}
			i++;
			
		}while(i<500000);
		System.out.println(melhor);
	}

}
