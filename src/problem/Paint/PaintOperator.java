package problem.Paint;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import abstracts.Operators;
import abstracts.Population;
import abstracts.Problem;
import utils.Circle;
import utils.PDI;

public class PaintOperator implements Operators {
	
	@Override
	public Problem [] crossover(Problem p1, Problem p2) {
		Random rnd = new Random();
		PaintCod pai1 = (PaintCod)p1;
		PaintCod pai2 = (PaintCod)p2; 
		PaintCod child1 = new PaintCod(p1.getIndSize());
		PaintCod child2 = new PaintCod(p1.getIndSize());
		Circle[] listChild1 = child1.getListCircle();
		Circle[] listChild2 = child2.getListCircle();
		Circle[] listPai1 = pai1.getListCircle();
		Circle[] listPai2 = pai2.getListCircle();
			
		int ptCut1 = rnd.nextInt(p1.getIndSize());
		int ptCut2 = rnd.nextInt(p1.getIndSize());
		int max = Math.max(ptCut1, ptCut2);
		int min = Math.min(ptCut1, ptCut2);
		for (int i = 0; i < listChild1.length && (i <= min || i >= max); i++) {
			listChild1[i] = listPai1[i];
			listChild2[i] = listPai2[i];
        }
        
        for (int i = min+1; i <= max; i++) {
        	listChild1[i] = listPai2[i];
			listChild2[i] = listPai1[i];
        }
        
        
        
	//	child.setListCircle(listCircleChild);
		child1.createImage();
		child2.createImage();
	//	Instant fim = Instant.now();
	//	Duration duracao1 = Duration.between(inicio, fim);
	//	System.out.println(duracao1.toMillis()+" ms");
	/*	System.out.println(pai1);
		System.out.println(pai2);
		System.out.println(child);
		System.exit(0);*/
		PaintCod [] childs = new PaintCod[2];
		childs[0]=child1;
		childs[1]=child2;
		if(Circle.factor<1.0) {
			Circle.factor=Circle.factor+0.1;
		}
		return childs;
	}

	@Override
	public void mutation(Problem ind) {
		PaintCod mutant = (PaintCod)ind;
		Random rnd = new Random();
		//System.out.println("MUTAÇÃO");
		Circle []listCircle = mutant.getListCircle();
	
	//	System.out.println(mutant);
		// 20% dos genes sofrem mutação
		int countGene = rnd.nextInt(1+(int)0.2*listCircle.length);
		for (int i = 0; i<countGene;i++) {			
			if(rnd.nextDouble()<0.05) {
				double randomFactor = rnd.nextDouble();
				Circle circle = listCircle[rnd.nextInt(listCircle.length)];
				if(randomFactor < 0.4) {
					// muda cor (40%)
					//System.out.println("muda cor");
					circle.setR((short)rnd.nextInt(256));
					circle.setG((short)rnd.nextInt(256));
					circle.setB((short)rnd.nextInt(256));				
				}else if(randomFactor < 0.7) {
					// muda centro (30%)
				//	System.out.println("muda centro");
					circle.setXc((short)rnd.nextInt(mutant.getImage().cols()));
					circle.setYc((short)rnd.nextInt(mutant.getImage().rows()));					
				}else if(randomFactor < 0.85) {
					// muda raio (15%)
				//	System.out.println("muda raio");
					circle.setRadius();
				}else {
					// remove (15%)
			//		System.out.println("remove");
					circle.setRadius((short)0);
				}
			}
			
		}
	//	mutant.setListCircle(listCircle);
		mutant.createImage();
	//	System.out.println(mutant);
		//System.exit(0);
		
		
	}

	@Override
	public Problem select(Population pop) {
		return roleta(pop);
	}
	
	private Problem roleta(Population pop){
		//Roleta
		double total = pop.getPopulationFitness();
		double value = Math.random()*total;
		double sum = 0.0;
		for(int i = 0; i < pop.getPopSize(); i++){
			sum = sum + pop.get(i).getFitness();
			if(sum>=value){
				return pop.get(i);
			}
		}
		//int indice = rnd.nextInt(pop.getPopSize());
		return null;	
	}

	@Override
	public void updatePopulation(Population pop, Problem child) {
		int indexPoor = new Random().nextInt(pop.getPopSize());
		Problem pior = pop.get(0);
		for (int i = 1; i < pop.getPopSize(); i++) {
			if(pior.getFitness() > pop.get(i).getFitness()){ // max <, min >
				pior = pop.get(i);
				indexPoor = i;
			}
		}
		if(child.getFitness()>pior.getFitness()) {
			pop.set(indexPoor, child);
		}
		
		
	}

	

}



