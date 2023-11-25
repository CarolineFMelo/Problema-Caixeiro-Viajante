package graphs;

import java.util.ArrayList;

public class AdjacencyMatrix {

	public int weights[][];
	public String[] line = null;
	public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	public ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public AdjacencyMatrix(ArrayList<String> file) throws Exception {
		int numVertices = Integer.parseInt(file.get(0));
		this.weights = new int[numVertices][numVertices];
		
		//add the vertices to the ArrayList
		for(int i = 1; i < file.size(); i++) {
			line = file.get(i).split(" ");
			vertices.add(new Vertex(Integer.parseInt(line[0])));
		}
		
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
	}
	
	public void addEdge(Vertex origin, Vertex destiny) {
		edges.add(new Edge(origin, destiny));
		this.weights[origin.id()][destiny.id()] = 1;
	}
	
	public void addEdge(Vertex origin, Vertex destiny, int weight) {
		edges.add(new Edge(origin, destiny, weight));
		this.weights[origin.id()][destiny.id()] = weight;
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
