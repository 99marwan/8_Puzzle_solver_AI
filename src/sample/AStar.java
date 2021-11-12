package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar implements EightPuzzle{

    //Create hashmap for explored entries
    HashMap<String,Node> explored =new HashMap<String,Node>();

    //Variable that tracks max depth in search
    private int maxDepth=0;
    //Variable that contains first node in path to goal after finding the path
    private Node startOfPath;
    //Variable that contains number of nodes expanded
    private int nodesExpanded = 0;
    //Variable that contains the cost of the path
    private int costOfPath = 0;

    public int getMaxDepth() {
        return maxDepth;
    }


    public Node getStartOfPath() {
        return startOfPath;
    }


    public int getNodesExpanded() {
        return nodesExpanded;
    }


    public int getCostOfPath() {
        return costOfPath;
    }


    //Resets max depth and performs A* search using Manhattan Distance as the heuristic
    @Override
    public Boolean SearchTech(String initialState, String goalState) {
        maxDepth=0;
        startOfPath = null;
        nodesExpanded = 0;
        explored = new HashMap<>();
        costOfPath = 0;
        return SearchAStarManhattan(initialState, goalState);
    }


    //Resets max depth and performs A* search using Euclidean Distance as the heuristic
    public Boolean SearchTechEuclidean(String initialState, String goalState) {
        maxDepth=0;
        startOfPath = null;
        nodesExpanded = 0;
        costOfPath = 0;
        explored = new HashMap<>();
        return SearchAStarEuclidean(initialState,goalState);
    }

    private Boolean SearchAStarManhattan(String initialState, String goalState) {

        Helper utils = new Helper();

        //Create initial state node
        Node initialNode = new Node (initialState,0,null);

        //Set cost of initial node
        initialNode.setCost(utils.ManhattanDist(initialNode.getState(),goalState));

        //Create priority queue frontier
        PriorityQueue<Node> frontier = new PriorityQueue<Node>();

        //Create hashmap for frontier entries
        HashMap<String,Node> frontierMap=new HashMap<String,Node>();



        //Add initial node to frontier and frontier map (with key as the state in frontier map)
        frontier.add(initialNode);
        frontierMap.put(initialNode.getState(),initialNode);

        Node currState;

        while(!frontier.isEmpty()) {

            //Remove top of frontier (minimum value of key)
            currState=frontier.remove();
            frontierMap.remove(currState.getState());

            //Add to explored
            explored.put(currState.getState(), currState);

            //Update maxDepth if necessary
            if(currState.getDepth()>maxDepth)
                maxDepth=currState.getDepth();

            //If goal state is found, show statistics of search, return path to goal and return true
            if(currState.getState().equals(goalState)) {

                nodesExpanded = explored.size();
                costOfPath = currState.getDepth();

                /*startOfPath = currState.getParent();
                while (startOfPath != null) {
                    startOfPath.setChild(currState);
                    currState = startOfPath;
                    startOfPath = startOfPath.getParent();

                }
                startOfPath = currState;*/

                System.out.println("Goal Reached Man !");
                System.out.println("Search Depth " + maxDepth);
                System.out.println("# of nodes expanded = " + nodesExpanded);
                System.out.println("Cost of Path = "+ costOfPath);

                return true;
            }

            //Array list of nodes of all possible children of current state (all possible moves)
            ArrayList<Node> children = utils.Gen_States(currState);

            for (Node n : children) {

                boolean inFrontier=frontierMap.containsKey(n.getState());
                n.setCost(utils.ManhattanDist(n.getState(),goalState));
                //If state is not in frontier and not in explored, add it to frontier
                if(!inFrontier && !explored.containsKey(n.getState())) {
                    frontier.add(n);
                    frontierMap.put(n.getState(), n);
                }
                //Else if it is in frontier, check if the state was reached with smaller key value (cost + heuristic) than the current
                //value in the frontier. If that is the case, replace the parent and cost with the newer values
                else if (inFrontier) {
                    Node temp=(Node)frontierMap.get(n.getState());
                    if(temp.getCost()>n.getCost()) {
                        frontier.remove(temp);
                        frontier.add(n);
                        frontierMap.remove(temp.getState());
                        frontierMap.put(n.getState(), n);
                    }

                }

            }

        }
        //If no solution could be found, show statistics of search and return false
        System.out.println("Unsolvable Case !");
        System.out.println("# of nodes expanded = " + (explored.size()));
        return false;
    }

     public Boolean SearchAStarEuclidean(String initialState, String goalState) {

        Helper utils = new Helper();

        //Create initial state node
        Node initialNode = new Node (initialState,0,null);

        //Set cost of initial node
        initialNode.setCost(utils.EuclideanDist(initialNode.getState(),goalState));

        //Create priority queue frontier
        PriorityQueue<Node> frontier = new PriorityQueue<Node>();

        //Create hashmap for frontier entries
        HashMap<String,Node> frontierMap=new HashMap<String,Node>();


        //Add initial node to frontier and frontier map (with key as the state in frontier map)
        frontier.add(initialNode);
        frontierMap.put(initialNode.getState(),initialNode);

        Node currState;

        while(!frontier.isEmpty()) {

            //Remove top of frontier (minimum value of key)
            currState=frontier.remove();
            frontierMap.remove(currState.getState());

            //Add to explored
            explored.put(currState.getState(), currState);

            //Update maxDepth if necessary
            if(currState.getDepth()>maxDepth)
                maxDepth=currState.getDepth();

            //If goal state is found, show statistics of search, return path to goal and return true
            if(currState.getState().equals(goalState)) {

                nodesExpanded = explored.size();
                costOfPath = currState.getDepth();

                /*startOfPath = currState.getParent();
                while (startOfPath != null) {
                    startOfPath.setChild(currState);
                    currState = startOfPath;
                    startOfPath = startOfPath.getParent();

                }
                startOfPath = currState;*/

                System.out.println("Goal Reached Euc !");
                System.out.println("Search Depth " + maxDepth);
                System.out.println("# of nodes expanded = " + nodesExpanded);
                System.out.println("Cost of Path = "+ costOfPath);

                return true;
            }

            //Array list of nodes of all possible children of current state (all possible moves)
            ArrayList<Node> children = utils.Gen_States(currState);

            for (Node n : children) {

                boolean inFrontier=frontierMap.containsKey(n.getState());
                n.setCost(utils.EuclideanDist(n.getState(),goalState));
                //If state is not in frontier and not in explored, add it to frontier
                if(!inFrontier && !explored.containsKey(n.getState())) {
                    frontier.add(n);
                    frontierMap.put(n.getState(), n);
                }
                //Else if it is in frontier, check if the state was reached with smaller key value (cost + heuristic) than the current
                //value in the frontier. If that is the case, replace the parent and cost with the newer values
                else if (inFrontier) {
                    Node temp=(Node)frontierMap.get(n.getState());
                    if(temp.getCost()>n.getCost()) {
                        frontier.remove(temp);
                        frontier.add(n);
                        frontierMap.remove(temp.getState());
                        frontierMap.put(n.getState(), n);
                    }

                }

            }

        }
        //If no solution could be found, show statistics of search and return false
        System.out.println("Unsolvable Case !");
        System.out.println("# of nodes expanded = " + (explored.size()));
        return false;
    }

    //Function to compute path to goal according to explored
    public ArrayList<String> pathToGoal(){
        ArrayList<String> path = new ArrayList<>();
        String current = "012345678";
        path.add(0,current);
        while (current != null){

            if(explored.get(current).getParent() == null)
                break;
            current = explored.get(current).getParent().getState();
            path.add(0,current);
        }
        return path;
    }


}
