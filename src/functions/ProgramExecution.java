package functions;

import java.util.Scanner;

import graphs.*;

public class ProgramExecution {

	public static void main(String[] args) throws Exception {
		Graph ng = new Graph();

		// ask the file containing graph and the type of representation
		Scanner scn = new Scanner(System.in);
		System.out.println("Digite o caminho para o arquivo:");
		String file = scn.nextLine();
		
		file = "C:\\Users\\cferr\\workspace\\6-periodo\\projeto_analise_algoritmos\\problemaCaixeiroViajante\\src\\file\\teste.txt";
		
		AdjacencyMatrix grafo = ng.loadGraph(file);
		
		System.out.println("Selecione o algoritmo:\n[1]Algoritmo Otimo (Tentativa e Erro)\n[2]Heuristica (Algoritmo Genetico)");
		String alg = scn.nextLine();
		scn.close();
		
		switch(alg) {
			case "1":
				break;
			case "2":
				break;
			default:
				System.out.println("Opcao invalida");
		}
		
		System.out.println("Fim da execucao.");
	}

}
