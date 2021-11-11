package sample;
import java.util.*;

public class BFS implements EightPuzzle{
    int Max=0; // variable for maximum depth
    int nodesExpanded=0; // number of nodes visited
    //Variable that contains the cost of the path
    private int costOfPath = 0;
    //Map for EVERY visited state
    private HashMap<String,Node> explored =new HashMap<String,Node>();
    @Override
    public Boolean SearchTech(String initialState, String goalState) {
        //initializing all these variable with zero
        Max = 0;
        nodesExpanded = 0;
        costOfPath=0;
        explored =  new HashMap<>();

        //Object for first state
        Node initialNode = new Node (initialState,0,null);
        Helper utils = new Helper();

        //Queue for frontier
        Queue<Node> frontier = new LinkedList<>();

        //set to help finding the state in the frontier instead of iterating the queue
        HashSet<String> stateFrontier =new HashSet<>();

        frontier.add(initialNode);
        stateFrontier.add(initialState);

        while (!frontier.isEmpty()){
            //Get top of the queue
            Node state = frontier.peek();
            frontier.remove();
            stateFrontier.remove(state.getState());

            //put this state as visited
            explored.put(state.getState(),state);

            //get it's depth
            int depth = state.getDepth();
            //get the maximum depth according to the current max and the depth of the visited
            Max=Math.max(Max,depth);
            nodesExpanded++;

            //Check if we reached the goal by this state
            if(goalState.equals(state.getState())) {
                costOfPath = state.getDepth();
                System.out.println("Finished with Max depth = "+Max+" and goal depth = "+state.getDepth()+" and with expanded = "+nodesExpanded);
                return true;
            }


            //Generate every possible state from the current state
            ArrayList<Node> children = utils.Gen_States(state);

            //loop through these children
            for(Node n : children){
                //if every child is not in the frontier nor visited
                if(!stateFrontier.contains(n.getState()) && !explored.containsKey(n.getState())){
                    frontier.add(n);
                    stateFrontier.add(n.getState());
                }
            }
        }
        System.out.println("Failed with Max depth = "+Max+" and with expanded = "+nodesExpanded);
        return false;
    }

    public int getMax() {
        return Max;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
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
