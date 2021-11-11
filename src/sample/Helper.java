package sample;

import java.awt.Point;
import java.util.ArrayList;


public class Helper {
	
	private int lastStateIndex = 8;
	private int maxPossibleNewStates = 4;
	private int dimension = 3;
	
	public ArrayList<Node> Gen_States(Node currentNode){
		
		int emptyIndex = Find_EmptySpace(currentNode.getState());
		int x = emptyIndex % dimension;
		int y = emptyIndex / dimension;
		
		int newDepth = currentNode.getDepth() + 1;
		
		Point[] possiblePositions = new Point[maxPossibleNewStates];
		possiblePositions[0] = new Point(x-1,y);
		possiblePositions[1] = new Point(x+1,y);
		possiblePositions[2] = new Point(x,y-1);
		possiblePositions[3] = new Point(x,y+1);
		
		ArrayList<Node> newStates = new ArrayList<Node>();
		
		for (int i = 0; i < maxPossibleNewStates ; i++) {
			if (possiblePositions[i].x >= 0 && possiblePositions[i].y >= 0 
					&& possiblePositions[i].x <= dimension - 1 && possiblePositions[i].y <= dimension - 1) {
				// index = y*3 + x
				int indexToSwap = (possiblePositions[i].y * dimension) + possiblePositions[i].x;
				newStates.add(new Node(writeState(currentNode.getState(),emptyIndex,indexToSwap,newDepth),newDepth,currentNode));
			}
		}
			
		return newStates;
	
	}


	private int Find_EmptySpace(String state) {
		
		for (int i=0 ; i <= lastStateIndex ; i++) {
			if (state.charAt(i) == '0') {
				return i;
			}
		}
		return -1;
	}
	
	/*
	private int getDepth(String state) {
		String depthString = state.substring(lastStateIndex+1);
		int depth = Integer.parseInt(depthString);
		return depth;
	}
	*/
	private String writeState(String state, int emptyIndex, int indexToSwap, int newDepth) {
		char swapped = state.charAt(indexToSwap);
		String newState = "";
		for (int i = 0; i <= lastStateIndex ; i++) {
			if (i == emptyIndex) {
				newState += swapped;
			}
			else if (i == indexToSwap) {
				newState += "0";
			}
			else {
				newState += state.charAt(i);
			}
		}
		
		return newState;
	}
	
	public double ManhattanDist(String state) {
		double manhattans = 0;
		for (int i = 0; i <= lastStateIndex ; i++) {
			
			manhattans += Math.abs( (i % dimension) - (Integer.parseInt( Character.toString( state.charAt(i) ) ) % dimension) )
							+ Math.abs( (i / dimension) - (Integer.parseInt( Character.toString( state.charAt(i) ) ) / dimension) );
		}
		return manhattans;
		
	}
	
	public double EuclideanDist(String state) {
		double euclideans = 0;
		for (int i = 0; i <= lastStateIndex ; i++) {
			
			euclideans += Math.sqrt( Math.pow( ( (i % dimension) - (Integer.parseInt( Character.toString( state.charAt(i) ) ) % dimension) ) , 2)
							+ Math.pow( ( (i / dimension) - (Integer.parseInt( Character.toString( state.charAt(i) ) ) / dimension) ) , 2) );
		}
		return euclideans;
		
	}

}
