package sample;
public class Node implements Comparable<Node>  {

	private String state;
	private int depth;
	private Node parent;

	private double cost;


	public double getCost() {
		return cost;
	}


	public void setCost(double criCost) {

		cost=criCost+depth;
	}


	public Node(String state, int depth, Node parent) {
		this.state = state;
		this.depth = depth;
		this.parent = parent;
	}


	public String getState() {
		return this.state;
	}

	public int getDepth() {
		return this.depth;
	}

	public Node getParent() {
		return this.parent;
	}


	@Override
	public int compareTo(Node o) {
		if(this.getCost()==o.getCost())
			return 0;
		else if (getCost() > o.getCost())
			return 1;
		else
			return -1;
	}


}