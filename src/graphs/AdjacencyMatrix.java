package graphs;

import java.util.ArrayList;

public class AdjacencyMatrix {

	public int weights[][];
	public int baseGraph[][];
	public int maxNumVertices;
	public String[] line = null;
	public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	public ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public AdjacencyMatrix(ArrayList<String> file, String maxNumVertices) throws Exception {
		int numVertices = Integer.parseInt(file.get(0));
		this.baseGraph = new int[numVertices][numVertices];
		this.maxNumVertices = Integer.parseInt(maxNumVertices);
		this.weights = new int[this.maxNumVertices][this.maxNumVertices];
		
		//add the vertices to the ArrayList
		for(int i = 1; i < file.size(); i++) {
			line = file.get(i).split(" ");
			vertices.add(new Vertex(Integer.parseInt(line[0])));
		}
		
		//load base graph
		for(int i = 1; i < file.size(); i++) {
			line = file.get(i).split(" ");
			
			for(int j = 1; j < line.length; j++) {
				String[] edge = line[j].split("-");
				Vertex v_origin = vertices.get(Integer.parseInt(line[0]));
				Vertex v_destiny = vertices.get(Integer.parseInt(edge[0]));
				int weight = Integer.parseInt(edge[1].replace(";", ""));
				
				if(weight <= 1) {
					addEdge(v_origin, v_destiny);
				}
				else {
					addEdge(v_origin, v_destiny, weight);
				}
			}
		}
		
		//load specific graph
		for(int i = 0; i < this.maxNumVertices; i++) {
		    for(int j = 0; j < this.maxNumVertices; j++) {
		    	weights[i][j] = baseGraph[i][j];
		    }
	    }
	}
	
	public void addEdge(Vertex origin, Vertex destiny) {
		edges.add(new Edge(origin, destiny));
		this.baseGraph[origin.id()][destiny.id()] = 1;
	}
	
	public void addEdge(Vertex origin, Vertex destiny, int weight) {
		edges.add(new Edge(origin, destiny, weight));
		this.baseGraph[origin.id()][destiny.id()] = weight;
	}
	
	public ArrayList<Integer> adjVertices(int node) {
        ArrayList<Integer> adj = new ArrayList<>();
        
        for(int j = 0; j < this.weights.length; j++) {
            if(node != j && this.weights[node][j] != 0) {
                adj.add(j);
            }
        }
        
        return adj;
    }
}