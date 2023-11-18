package graphs;

public class Edge {

	private Vertex origin;
	private Vertex destiny;
	private double weight;
	
	public Edge(Vertex o, Vertex d) {
		this.origin = o;
		this.destiny = d;
	}
	
	public Edge(Vertex o, Vertex d, double w) {
		this.origin = o;
		this.destiny = d;
		this.weight = w;
	}
	
	public Vertex getOrigin() {
		return origin;
	}
	
	public void setOrigin(Vertex origin) {
		this.origin = origin;
	}
	
	public Vertex getDestiny() {
		return destiny;
	}
	
	public void setDestiny(Vertex destiny) {
		this.destiny = destiny;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
