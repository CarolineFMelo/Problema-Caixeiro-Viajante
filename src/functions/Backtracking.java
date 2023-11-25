package functions;

import java.util.ArrayList;

import graphs.AdjacencyMatrix;

public class Backtracking {

    private static int[][] graph;
    private static AdjacencyMatrix adjGraph;
    private int v;
    private int pos = 0;
    private static int node = 0;;
    private static int bestCurrentPath[];
    private static int lowerCostPath = Integer.MAX_VALUE;
    private int solutionPath[];
    private boolean available[];
    private static int cost;

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
        
        btFunc(pos, v, available, solutionPath);
    }

    public static void btFunc(int pos, int graphSize, boolean spare[], int solution[]) {
    	if(pos == graphSize) {
    		cost = 0;
    		for(int i = 0; i < graphSize - 1; i++) {
    		    int origin = solution[i];
    		    int destiny = solution[i + 1];
    		    cost += graph[origin][destiny];
    		}
    		cost += graph[solution[graphSize - 1]][solution[0]];
    		
    		if(cost < lowerCostPath) {
    			lowerCostPath = cost;
    			for(int i = 0; i < graphSize; i++) {
        			bestCurrentPath[i] = solution[i];
        		}
    		}
    	}
    	else {
    		ArrayList<Integer> adjVertices = adjGraph.adjVertices(node);
    		//System.out.println(adjVertices.toString());
    		for(int i : adjVertices) {
    			if(spare[i] == true) {
    				solution[pos] = i;
    				spare[i] = false;
    				pos++;
    				node = i;
    				btFunc(pos, graphSize, spare, solution);
    				pos--;
    				spare[i] = true;
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