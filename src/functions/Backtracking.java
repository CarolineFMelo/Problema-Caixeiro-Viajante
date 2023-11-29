package functions;

import java.util.ArrayList;

import graphs.AdjacencyMatrix;

public class Backtracking {

    private static int[][] graph;
    private static AdjacencyMatrix adjGraph;
    private int v;
    private int pos = 0;
    private static int bestCurrentPath[];
    private static int lowerCostPath = Integer.MAX_VALUE;
    private int solutionPath[];
    private boolean available[];
	private static int cost = 0;

    public Backtracking(AdjacencyMatrix g) {
        graph = g.weights;
        adjGraph = g;
        v = g.vertices.size();
        bestCurrentPath = new int[v];
        solutionPath = new int[v];
        available = new boolean[v];
        for (int i = 0; i < v; i++) {
        	available[i] = true;
        }
        available[0] = false;
        solutionPath[0] = 0;
        pos++;
        
        btFunc(0, pos, v, available, solutionPath);
    }

    public static void btFunc(int node, int pos, int graphSize, boolean spare[], int solution[]) {
    	if(pos == graphSize) {
    		cost += graph[solution[pos - 1]][0];
    		
    		if(cost < lowerCostPath) {
    			lowerCostPath = cost;
    			for(int i = 0; i < graphSize; i++) {
        			bestCurrentPath[i] = solution[i];
        		}
    		}
    		cost -= graph[solution[pos - 1]][0];
    	}
    	else {
    		ArrayList<Integer> adjVertices = adjGraph.adjVertices(node);
    		//System.out.println(adjVertices.toString());
    		for(int i : adjVertices) {
    			if(spare[i] == true) {
    				solution[pos] = i;
    				cost += graph[node][i]; //partial solution
    				spare[i] = false;
    				pos++;
    				
    				if(cost < lowerCostPath) {
    					btFunc(i, pos, graphSize, spare, solution);
    				}
    				
    				pos--;
    				spare[i] = true;
    				cost -= graph[node][i];
    			}
    		}
    	}
    }

    public static void printAnswer() {
    	System.out.println("\n" + lowerCostPath);
    	
    	for(int i = 0; i < bestCurrentPath.length; i++) {
    		System.out.print(bestCurrentPath[i]);
	        if(i < bestCurrentPath.length - 1) {
	        	System.out.print(" - ");
	        }
	        else {
	        	System.out.print(" - " + bestCurrentPath[0]);
	        }
        }
    }
    
}