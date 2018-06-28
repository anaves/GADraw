package problem.Paint;

import java.util.Random;

import abstracts.Operators;
import abstracts.Population;
import abstracts.Problem;
import utils.Circle;

public class PaintOperator implements Operators {
	
	@Override
	public Problem [] crossover(Problem p1, Problem p2) {
		
		PaintCod pai1 = (PaintCod)p1;
		PaintCod pai2 = (PaintCod)p2; 
		PaintCod child1 = new PaintCod(pai1.getIndSize());
		
		PaintCod child2 = new PaintCod(pai1.getIndSize());
		Circle[] listChild1 = child1.getListCircle();
		Circle[] listChild2 = child2.getListCircle();
		Circle[] listPai1 = pai1.getListCircle();
		Circle[] listPai2 = pai2.getListCircle();
			
		int ptCut = p1.getIndSize()/2;
		
		for (int i = 0; i < ptCut; i++) {
			listChild1[i] = listPai1[i].getClone();
			listChild2[i] = listPai2[i].getClone();
        }
        
        for (int i = ptCut; i < p1.getIndSize(); i++) {
        	listChild1[i] = listPai2[i].getClone();
			listChild2[i] = listPai1[i].getClone();
        }       
      
		child1.createImage();
		child2.createImage();
	
		PaintCod [] childs = new PaintCod[2];
		childs[0]=child1;
		childs[1]=child2;
		
		return childs;
	}

	/* Estratégia de mutação de:
	 * https://github.com/LuisAraujo/
	 * @see abstracts.Operators#mutation(abstracts.Problem)
	 */
	@Override
	public void mutation(Problem ind) {
		PaintCod mutant = (PaintCod)ind;
		Random rnd = new Random();
		Circle []listCircle = mutant.getListCircle();

		for (int i = 0; i<listCircle.length;i++) {
			if(rnd.nextDouble()<0.5) {
				double randomFactor = rnd.nextDouble();
				//System.out.println("RANDOM " + randomFactor);
				Circle circle = listCircle[i];
				if(randomFactor < 0.6) {
					// altera cor
					if(randomFactor < 0.15) // 10%
						circle.setR();
					else if(randomFactor < 0.3) // 10%
						circle.setG(); 
					else if (randomFactor < 0.45) // 10%
						circle.setB();
					else //10%
						circle.setAlpha(new Random().nextDouble());
				}else if(randomFactor < 0.9) {
					//5% muda x e y
					if(randomFactor < 0.7) {
						circle.setXc((short)new Random().nextInt(mutant.getImage().rows()));
						circle.setYc((short)new Random().nextInt(mutant.getImage().cols()));
					}else if (randomFactor < 0.8) {
							circle.setXc((short)new Random().nextInt(mutant.getImage().rows()));
					}else if (randomFactor < 0.9) {
							circle.setYc((short)new Random().nextInt(mutant.getImage().cols()));
					}		
				
				}else {
					if (randomFactor < 0.95) {
						//10% muda raio
						circle.setRadius();
					}else {		
						// 	remove (15%)
						circle.setRadius((short)0);
					}
				}
			}
			
		}
	
		mutant.createImage();
		
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


	

}



