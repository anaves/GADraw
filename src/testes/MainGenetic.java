package testes;

import abstracts.Genetic;
import abstracts.Instance;
import abstracts.Operators;
import abstracts.Population;
import genetics.MyGA;
import problem.Paint.*;
import utils.InOut;
import utils.PDI;

public class MainGenetic {
	//private static Problem problem;
	private static Genetic genetic;
	private static Operators operator;
	private static Instance instance;
	private static Population population;
	
	public static void main(String[] args) {
		menu();
		run();
		pos();
		System.out.println("terminou a evolução!");
	}
	
	private static void menu(){
		problems();
		System.out.println("***Implementação do AG***");
		geneticos();
	}
	
	private static void problems(){
		int key = 1;
		
		switch (key) {
		case 1:
			instance= new PaintInstance("input/input.txt");
			//instance = new PaintInstance(0.05,1.0,4000,"images/arara/arara.png");
			population = new PaintPopulation(6);
			operator = new PaintOperator();
			
			break;
		default:
			System.out.println("opção inválida");
			menu();
			break;
		}
	}
	
	private static void geneticos(){
		//System.out.println("1- MyGA (escolher esta opção)");
		int key = 1;//InOut.inValue("Sua opção");
		
		switch (key) {
		case 1:
			System.out.println("inicia evolução");
			genetic = new MyGA(operator,instance, population);
			
			break;

		default:
			System.out.println("opção inválida");
			menu();
			break;
		}
	}
	
	private static void run(){
		genetic.execute();
		
	}

	private static void pos() {
		PaintCod melhor = (PaintCod)population.bestSolution();
		melhor.posProcessing();
	}
}
