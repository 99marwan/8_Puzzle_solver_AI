package sample;

import java.util.ArrayList;


public class TestHelper {

	public static void main(String[] args) {

		Helper test = new Helper();
		String state1 = "012345678";
		int depth1 = 0;
		Node node1 = new Node(state1,depth1,null);
		String state2 = "302465187";
		int depth2 = 25;
		Node node2 = new Node(state2,depth2,null);
		String state3 = "123405678";
		int depth3 = 102;
		Node node3 = new Node(state3,depth3,null);
		
		ArrayList<Node> results = test.Gen_States(node3);
		
		
		for (int i = 0; i < results.size() ; i++) {
			System.out.println(results.get(i).getState() + " " + results.get(i).getDepth() + " " + results.get(i).getParent().getState()
			+ " " + results.get(i).getVisited() + "\n") ;
		}
		//System.out.println(results);
		
		
		double manhattans = test.ManhattanDist(state1);
		double euclideans = test.EuclideanDist(state1);
		
		System.out.print("\nManhattan: " + manhattans);
		/*
		for (int i = 0; i < manhattans.length; i++) {
			System.out.print(manhattans[i] + " ");
		}
		*/
		
		System.out.print("\nEuclidean: " + euclideans);
		/*
		for (int i = 0; i < euclideans.length; i++) {
			System.out.print(euclideans[i] + " ");
		}
		*/
		PrioQueue pQueue = new PrioQueue();
		
		System.out.println("\n///////////////////////////////\nPrioQueue\n///////////////////////////////");
		for (Node n : results) {
			pQueue.insert(n, test.ManhattanDist(n.getState()));
			System.out.println(n.getState() + "  " + test.ManhattanDist(n.getState()));
		}
		
		for (int i = 0; i < results.size() ; i++) {
			Node minimum = pQueue.removeMin();
			System.out.println(test.ManhattanDist(minimum.getState()) + "  " + minimum.getState());
		}

	}

}
