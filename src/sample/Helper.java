package sample;

import java.awt.Point;
import java.util.ArrayList;


public class Helper {

	//Index of last element in state (for 8-puzzle = 8)
	private int lastStateIndex = 8;
	//Maximum number of possible new states from current state
	private int maxPossibleNewStates = 4;
	//Number of elements in a row or column (for 8-puzzle = 3)
	private int dimension = 3;

	//Method that takes in the current node and returns an array list of all possible states that can be achieved
	//by making one move from the current state
	public ArrayList<Node> Gen_States(Node currentNode){

		//Obtains index of empty space (0) in current state
		int emptyIndex = Find_EmptySpace(currentNode.getState());
		//Calculates x-coordinate of empty space in current state using (emptyIndex mod 3 for 8-puzzle). Grid Coordinates are:
		// 0,0 1,0 2,0
		// 0,1 1,1 2,1
		// 0,2 1,2 2,2
		int x = emptyIndex % dimension;
		//Calculates y-coordinate of empty space in current state using (emptyIndex divided by 3 for 8-puzzle).
		int y = emptyIndex / dimension;

		//Calculates new depth of child nodes of new states as the depth of the current node + 1
		int newDepth = currentNode.getDepth() + 1;

		//Creates an array of points of size = maxPossibleNewStates (which is 4 as only 4 moves are possible at any
		//given state). Sequence is Left, Right, Up, Down.
		Point[] possiblePositions = new Point[maxPossibleNewStates];
		//Move Empty Space Left (And Element On Its Left To The Right)
		possiblePositions[0] = new Point(x-1,y);
		//Move Empty Space Right (And Element On Its Right To The Left)
		possiblePositions[1] = new Point(x+1,y);
		//Move Empty Space Up (And Element Above It Down)
		possiblePositions[2] = new Point(x,y-1);
		//Move Empty Space Down (And Element Below It Up)
		possiblePositions[3] = new Point(x,y+1);

		//Create an array list that only contains all the legal moves from the above possible moves (does not allow empty
		//space to go out of bounds)
		ArrayList<Node> newStates = new ArrayList<Node>();

		for (int i = 0; i < maxPossibleNewStates ; i++) {
			//Check for each of the new possible positions of the empty index that the x-coordinate and y-coordinate
			//are in the range [0,2] (if either has a value of -1 or 3 then the new position of the empty index would
			//be out of bounds so the new position is refused)
			if (possiblePositions[i].x >= 0 && possiblePositions[i].y >= 0
					&& possiblePositions[i].x <= dimension - 1 && possiblePositions[i].y <= dimension - 1) {
				//Calculate the index of the empty space in the state string using the formula
				//index = y*3 + x (formula comes from the way the grid coordinates are represented as shown above)
				//(dimension is equal to 3 in 8-puzzle)
				int indexToSwap = (possiblePositions[i].y * dimension) + possiblePositions[i].x;
				//Use the method writeState to create a node for the new state given the index of the empty space in the current
				//state and the index of the element that will swap places with the empty space and the depth of the new node
				//and add that node to the array list
				newStates.add(new Node(writeState(currentNode.getState(),emptyIndex,indexToSwap,newDepth),newDepth,currentNode));
			}
		}

		return newStates;

	}

	//Method that finds the index of the empty space (0) in the given state (index in string)
	private int Find_EmptySpace(String state) {

		for (int i=0 ; i <= lastStateIndex ; i++) {
			//If empty space (0) is found, return index i
			if (state.charAt(i) == '0') {
				return i;
			}
		}
		//If empty space (0) is not found, return -1
		return -1;
	}

	//Method that creates a new state (string) given the index of the empty space in the current
	//state and the index of the element that will swap places with the empty space and the depth of the new node
	private String writeState(String state, int emptyIndex, int indexToSwap, int newDepth) {
		//Character value in location that will swap places with empty space
		char swapped = state.charAt(indexToSwap);
		String newState = "";
		for (int i = 0; i <= lastStateIndex ; i++) {
			//If current index is the index of the empty space, place the character that will swap places with the empty space
			if (i == emptyIndex) {
				newState += swapped;
			}
			//If current index is the index of the character that will swap places with the empty space, place the empty space (0)
			else if (i == indexToSwap) {
				newState += "0";
			}
			//Otherwise, place the character at the current index at the same index (does not move)
			else {
				newState += state.charAt(i);
			}
		}

		return newState;
	}

	//Calculate Summation Of Manhattan Distances for each element of its position in the current state from its final position
	//in the goal state
	public double ManhattanDist(String state, String goalState) {
		double manhattans = 0;
		int[] indexInGoal = new int[lastStateIndex+1];
		for (int i = 0; i <= lastStateIndex ; i++) {
			indexInGoal[ (Integer.parseInt( Character.toString( goalState.charAt(i) ) ) )] = i;
		}
		int valueAtIndex;
		for (int i = 0; i <= lastStateIndex ; i++) {
			valueAtIndex = Integer.parseInt( Character.toString( state.charAt(i) ) ) ;
			manhattans += Math.abs( (i % dimension) -
					(indexInGoal[valueAtIndex] % dimension) )
					+ Math.abs( (i / dimension) -
					(indexInGoal[valueAtIndex] / dimension) );
		}
		return manhattans;

	}

	//Calculate Summation Of Euclidean Distances for each element of its position in the current state from its final position
	//in the goal state
	public double EuclideanDist(String state, String goalState) {
		double euclideans = 0;
		int[] indexInGoal = new int[lastStateIndex+1];
		for (int i = 0; i <= lastStateIndex ; i++) {
			indexInGoal[ (Integer.parseInt( Character.toString( goalState.charAt(i) ) ) )] = i;
		}
		int valueAtIndex;
		for (int i = 0; i <= lastStateIndex ; i++) {
			valueAtIndex = Integer.parseInt( Character.toString( state.charAt(i) ) ) ;
			euclideans += Math.sqrt( Math.pow( ( (i % dimension) -
					(indexInGoal[valueAtIndex] % dimension) ) , 2)
					+ Math.pow( ( (i / dimension) -
					(indexInGoal[valueAtIndex] / dimension) ) , 2) );
		}
		return euclideans;

	}



}
