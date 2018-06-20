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
		Instant init = Instant.now();
		do{	
		
			Problem p1 = operator.select(population);
			Problem p2 = operator.select(population);
			
			Problem childs[] = operator.crossover(p1,p2);
			Problem child1 = childs[0];
			Problem child2 = childs[1];
						
			if(Math.random() < instance.getMutationRate()){
				operator.mutation(child1);
				operator.mutation(child2);
			}
				
			operator.updatePopulation(population,child1);
			operator.updatePopulation(population,child2);
	
			melhor = population.bestSolution(melhor);
			if(i%10==0) {
				Instant fim = Instant.now();
				Duration duracao = Duration.between(init, fim);
				System.out.println(duracao.toMinutes());
				System.out.println(melhor);
				init=Instant.now();
			}
		//	Instant fim = Instant.now();
			//Duration duracao = Duration.between(init, fim);
			//System.out.println(duracao.toMillis());
		
			System.gc();
			
			
			i++;
			
		}while(i<10000);
		
		System.out.println(melhor);
	}

}
