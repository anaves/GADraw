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

	@Override
	public void mutation(Problem ind) {
		PaintCod mutant = (PaintCod)ind;
		Random rnd = new Random();
		Circle []listCircle = mutant.getListCircle();

		for (int i = 0; i<listCircle.length;i++) {
			if(rnd.nextDouble()<0.5) {
				double randomFactor = rnd.nextDouble();
				Circle circle = listCircle[i];
				if(randomFactor < 0.4) {
					// muda cor (40%)
					//System.out.println("muda cor");
					circle.setR();
					circle.setG();
					circle.setB();				
				}else if(randomFactor < 0.7) {
					// muda centro (30%)
				//	System.out.println("muda centro");
					short xc = circle.getXc();
					short yc = circle.getYc();
					short hx = (short)rnd.nextInt(3);
					short hy = (short)rnd.nextInt(3);
					if(Math.random()<0.5) {
						xc = (short) (xc + hx);
					}else {
						xc = (short) (xc - hx);
					}
					if(Math.random()<0.5) {
						yc = (short) (yc + hy);
					}else {
						yc = (short) (yc - hy);
					}
					if(xc>0 && xc < mutant.getImage().rows()) {
						circle.setXc(xc);
					}
					if(yc>0 && yc < mutant.getImage().cols()) {
						circle.setYc(yc);
					}
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


	

}



