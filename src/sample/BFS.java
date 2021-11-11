package sample;
import java.util.*;

public class BFS implements EightPuzzle{
    int Max=0;
    int nodesExpanded=0;
    HashMap<String,Node> explored =new HashMap<String,Node>();
    @Override
    public Boolean SearchTech(String initialState, String goalState) {

        Node initialNode = new Node (initialState,0,null);
        Helper utils = new Helper();

        Queue<Node> frontier = new LinkedList<>();
        HashSet<String> stateFrontier =new HashSet<>();

        frontier.add(initialNode);
        stateFrontier.add(initialState);

        while (!frontier.isEmpty()){
            Node state = frontier.peek();
            frontier.remove();
            stateFrontier.remove(state.getState());

            explored.put(state.getState(),state);

            int depth = state.getDepth();
            Max=Math.max(Max,depth);

            if(goalState.equals(state.getState())) {
                System.out.println("Finished with Max depth = "+Max+" and goal depth = "+state.getDepth()+" and with expanded = "+nodesExpanded);
                return true;
            }
            nodesExpanded++;
            ArrayList<Node> children = utils.Gen_States(state);

            for(Node n : children){
                if(!stateFrontier.contains(n.getState()) && !explored.containsKey(n.getState())){
                    frontier.add(n);
                    stateFrontier.add(n.getState());
                }
            }
        }
        System.out.println("Failed with Max depth = "+Max+" and with expanded = "+nodesExpanded);
        return false;
    }
}
