package functions;

import java.util.ArrayList;
import java.util.Scanner;
import file.FileManager;
import graphs.*;

public class ProgramExecution {

	public static void main(String[] args) throws Exception {
		AdjacencyMatrix graph;
		
		//ask the file containing graph and the type of representation
		Scanner scn = new Scanner(System.in);
		System.out.println("Digite o caminho para o arquivo:");
		String path = scn.nextLine();
		
		path = "C:\\Users\\cferr\\workspace\\6-periodo\\projeto_analise_algoritmos\\problemaCaixeiroViajante\\src\\file\\teste.txt";
		
		//open the file containing graph
		ArrayList<String> file = FileManager.stringReader(path);
		if(file == null) {
			System.out.println("file not found");
		}
		//System.out.println(file);
		
		System.out.println("Digite o numero de vertices desejado:");
		String maxNumVertices = scn.nextLine();
		
		//load graph
		graph = new AdjacencyMatrix(file, maxNumVertices);
		
		//ask the algorithm
		System.out.println("Selecione o algoritmo:\n[1]Algoritmo Otimo (Tentativa e Erro)\n[2]Heuristica (Algoritmo Genetico)");
		String alg = scn.nextLine();
		scn.close();
		
		//run chosen algorithm
		long start;
		long elapsed;
		switch(alg) {
			case "1":
				start = System.currentTimeMillis();
				
				new Backtracking(graph);
				Backtracking.printAnswer();
				
				elapsed = System.currentTimeMillis() - start;
				System.out.println("\nTempo de execução (ms): " + elapsed);
				break;
			case "2":
				start = System.currentTimeMillis();
				
				GeneticAlgorithm ga = new GeneticAlgorithm(graph);
				ga.printAnswer();
				
				elapsed = System.currentTimeMillis() - start;
				System.out.println("Tempo de execução (ms): " + elapsed);
				break;
			default:
				System.out.println("Opcao invalida");
		}
		
		System.out.println("\nFim da execucao.");
	}

}
