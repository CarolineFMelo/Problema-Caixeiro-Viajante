package functions;

import java.util.Scanner;

import graphs.*;

public class ProgramExecution {

	public static void main(String[] args) throws Exception {
		NewGraph ng = new NewGraph();

		// ask the file containing graph and the type of representation
		Scanner scn = new Scanner(System.in);
		System.out.println("Digite o caminho para o arquivo:");
		String file = scn.nextLine();
		
		file = "C:\\Users\\cferr\\workspace\\6-periodo\\projeto_analise_algoritmos\\problemaCaixeiroViajante\\src\\file\\teste.txt";
		
		AdjacencyMatrixGraph grafo = ng.loadGraph(file);
	}

}
