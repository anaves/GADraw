package testes;

import abstracts.Genetic;
import abstracts.Instance;
import abstracts.Operators;
import abstracts.Population;
import genetics.MyGA;
import problem.Paint.*;
import utils.InOut;

public class MainGenetic {
	//private static Problem problem;
	private static Genetic genetic;
	private static Operators operator;
	private static Instance instance;
	private static Population population;
	
	public static void main(String[] args) {
		menu();
		run();
	}
	
	private static void menu(){
		System.out.println("***Problemas***");
		problems();
		System.out.println("***Implementação do AG***");
		geneticos();
	}
	
	private static void problems(){
		System.out.println("1-Paint");
		int key = InOut.inValue("Sua opção");
		
		switch (key) {
		case 1:
			instance = new PaintInstance(0.05,1.0,4000,"images/in/draw/araraTeste.png");
			population = new PaintPopulation(10);
			operator = new PaintOperator();
			break;
		default:
			System.out.println("opção inválida");
			menu();
			break;
		}
	}
	
	private static void geneticos(){
		System.out.println("1- MyGA (escolher esta opção)");
		int key = InOut.inValue("Sua opção");
		
		switch (key) {
		case 1:
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

}
