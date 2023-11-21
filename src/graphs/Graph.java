package graphs;

import java.util.ArrayList;

import file.FileManager;

public class Graph {
	
	public AdjacencyMatrix graph;

	public AdjacencyMatrix loadGraph(String path) throws Exception {
		//open the file containing graph
		ArrayList<String> file = FileManager.stringReader(path);
		if(file == null) {
        	System.out.println("file not found");
        }
		System.out.println(file);
		
		this.graph = new AdjacencyMatrix(file);
		
		return null;
	}
	
}
