package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import graphs.AdjacencyMatrix;

public class GeneticAlgorithm {

    private int[][] graph;
    private int populationSize;
    private double mutationRate;
    private int generations;
    private List<Integer> bestPath;

    public GeneticAlgorithm(AdjacencyMatrix g) {
        this.graph = g.weights;
        this.populationSize = 50;
        this.mutationRate = 0.02;
        this.generations = 1000;
        
        bestPath = gaFunc();
    }
    
    public List<Integer> gaFunc() {
    	List<List<Integer>> population = generateInitialPopulation();
        
        for(int generation = 0; generation < generations; generation++) {
        	population = selectTheBests(population);
        	population = crossPopulation(population);
        	applyMutation(population);
        }
        
        return getBestPath(population);
    }
    
    private List<List<Integer>> generateInitialPopulation() {
        List<List<Integer>> population = new ArrayList<>();

        for(int i = 0; i < populationSize; i++) {
            List<Integer> path = new ArrayList<>();
            
            for (int j = 0; j < graph.length; j++) {
                path.add(j);
            }
            
            Collections.shuffle(path);
            population.add(path);
        }

        return population;
    }
    
    private List<List<Integer>> selectTheBests(List<List<Integer>> population) {
        List<List<Integer>> newPopulation = new ArrayList<>();

        for(int i = 0; i < populationSize; i++) {
            int index1 = new Random().nextInt(populationSize);
            int index2 = new Random().nextInt(populationSize);

            List<Integer> path1 = population.get(index1);
            List<Integer> path2 = population.get(index2);

            if(calculateFitness(path1) < calculateFitness(path2)) {
            	newPopulation.add(new ArrayList<>(path1));
            } 
            else {
            	newPopulation.add(new ArrayList<>(path2));
            }
        }

        return newPopulation;
    }
    
    private List<List<Integer>> crossPopulation(List<List<Integer>> population) {
        List<List<Integer>> newPopulation = new ArrayList<>();

        for(int i = 0; i < populationSize; i += 2) {
            int cutPoint = new Random().nextInt(graph.length - 1) + 1;

            List<Integer> father1 = population.get(i);
            List<Integer> father2 = population.get(i + 1);

            List<Integer> son1 = new ArrayList<>(father1.subList(0, cutPoint));
            List<Integer> son2 = new ArrayList<>(father2.subList(0, cutPoint));

            for(int city : father2) {
                if(!son1.contains(city)) {
                	son1.add(city);
                }
            }

            for(int city : father1) {
                if(!son2.contains(city)) {
                	son2.add(city);
                }
            }

            newPopulation.add(son1);
            newPopulation.add(son2);
        }

        return newPopulation;
    }
	
    private void applyMutation(List<List<Integer>> population) {
        for(List<Integer> path : population) {
            if(Math.random() < mutationRate) {
                Collections.swap(path, new Random().nextInt(graph.length), new Random().nextInt(graph.length));
            }
        }
    }
    
    private List<Integer> getBestPath(List<List<Integer>> population) {
        List<Integer> bestPath = population.get(0);
        double bestFitness = calculateFitness(bestPath);

        for(List<Integer> path : population) {
            double fitness = calculateFitness(path);
            if(fitness < bestFitness) {
            	bestPath = path;
                bestFitness = fitness;
            }
        }

        return bestPath;
    }
    
    private int calculateFitness(List<Integer> path) {
        int fitness = 0;
        
        for(int i = 0; i < path.size() - 1; i++) {
            int currentCity = path.get(i);
            int nextCity = path.get(i + 1);
            fitness += graph[currentCity][nextCity];
        }
        
        fitness += graph[path.get(path.size() - 1)][path.get(0)];
        
        return fitness;
    }
    
    public void printAnswer() {
    	System.out.println(calculateFitness(bestPath));
    	System.out.println(bestPath);
    }
    
}
