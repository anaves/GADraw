package testes;

import abstracts.Genetic;
import abstracts.Instance;
import abstracts.Operators;
import abstracts.Population;
import genetics.MyGA;
import problem.Draw.*;
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
		System.out.println("1- Draw");
		int key = InOut.inValue("Sua opção");
		
		switch (key) {
		case 1:
			operator = new DrawOperator();
			instance = new DrawInstance(0.05,0.5,"images/in/linha.png");
			population = new DrawPopulation(100);
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
