package genetics;

import java.time.Duration;
import java.time.Instant;

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
		int n = instance.getIteracoes();
		int div = n/200;
		System.out.println("n = " + n + " div = " + div);
		do{			
			Problem p1 = operator.select(population);
			Problem p2 = operator.select(population);
			
			Problem childs[] = operator.crossover(p1,p2);
									
			if(Math.random() < instance.getMutationRate()){
				operator.mutation(childs[0]);
				operator.mutation(childs[1]);
			}
				
			population.updatePopulation(childs[0]);
			population.updatePopulation(childs[1]);
	
			melhor = population.bestSolution();
			
			if(i%div==0) {
				System.out.println(melhor);
			}
		
			System.gc();			
			i++;			
		}while(i<n);
		
		System.out.println(melhor);
	}

}
