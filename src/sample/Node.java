package sample;

public class Node {
	
	private String state;
	private int depth;
	private Node parent;
	private boolean visited = false;
	
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
	
	public boolean getVisited() {
		return this.visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
