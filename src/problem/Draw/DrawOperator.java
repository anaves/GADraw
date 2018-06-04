package problem.Draw;

import java.util.Random;

import abstracts.Operators;
import abstracts.Population;
import abstracts.Problem;
import utils.PDI;

public class DrawOperator implements Operators {

	@Override
	public Problem crossover(Problem p1, Problem p2) {
		DrawCod pai1 = (DrawCod)p1;
		DrawCod pai2 = (DrawCod)p2;
		DrawCod child = new DrawCod(p1.getIndSize());
		int value = new Random().nextInt(2);
		switch (value) {
			case 0:
				child.setImage(PDI.umPontoLinha(pai1.getImage(),pai2.getImage()));
				break;
			case 1:
				child.setImage(PDI.umPontoColuna(pai1.getImage(),pai2.getImage()));
				break;
			case 2:
				child.setImage(PDI.uniforme(pai1.getImage(),pai2.getImage()));
			default:
		}
		return child;
	}

	@Override
	public void mutation(Problem ind) {
		DrawCod aux = (DrawCod)ind;
		
		//int row = new Random().nextInt(aux.getImage().rows());
		//int col = new Random().nextInt(aux.getImage().cols());
	
		//aux.getImage().put(row, col, 1-aux.getImage().get(row, col)[0]);
		//aux.setImage(PDI.dilate(aux.getImage(), 5));
		Random rnd = new Random();
		int caso = rnd.nextInt(4);
		//System.out.println("Mutation " + caso);
		switch (caso) {
		case 0:
			aux.setImage(PDI.dilate(aux.getImage(), 5));
			break;
		case 1:
			aux.setImage(PDI.morphology(aux.getImage(), 5));
			break;
		case 2:
			aux.setImage(PDI.erode(aux.getImage(), 5));
			break;
		case 3:			
			DrawCod novo = new DrawCod(aux.getPopSize());
			aux.setImage(novo.getImage());
			break;			
		}
		
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
		/*Problem pior = pop.get(0);
		for (int i = 1; i < pop.getPopSize(); i++) {
			if(pior.getFitness() > pop.get(i).getFitness()){ // max <, min >
				pior = pop.get(i);
				indexPoor = i;
			}
		}*/
		pop.set(indexPoor, child);
	}

}

