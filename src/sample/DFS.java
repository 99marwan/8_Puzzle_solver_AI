package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFS implements EightPuzzle{

    private int maxDepth = 0;
    //Variable that contains the cost of the path
    private int costOfPath = 0;
    private int visit = 0;
    private  HashMap<String,Node> visited = new HashMap<>();

    @Override
    public Boolean SearchTech(String initialState, String goalState) {
        maxDepth = 0;
        visit = 0;
        costOfPath=0;
        visited =  new HashMap<>();

        Stack<Node> frontier = new Stack<>();

        HashSet<String> frontierHelperMap = new HashSet<>();

        Helper helper = new Helper();

        Node initialNode = new Node (initialState,0,null);

        frontier.push(initialNode);

        frontierHelperMap.add(initialNode.getState());

        Node current;


        while (!frontier.empty()){

            current = frontier.pop();

            this.visit++;

            if (current.getDepth() > this.maxDepth){
                this.maxDepth = current.getDepth();
            }

            frontierHelperMap.remove(current.getState());

            visited.put(current.getState(),current);

            if(current.getState().equals(goalState)){
                costOfPath = current.getDepth();
                System.out.println("SUCCESS");
                System.out.println("visited nodes : " + this.visit + ",  Max depth : " + this.maxDepth
                 + ",  goal depth : " + current.getDepth());
                return true;
            }

            ArrayList<Node> frontiers = helper.Gen_States(current);

            if(!frontiers.isEmpty()){
                for (int i = 0; i < frontiers.size(); i++) {

                    String state = frontiers.get(i).getState();

                    if(!visited.containsKey(state) && !frontierHelperMap.contains(state)){
                        frontier.push(frontiers.get(i));
                        frontierHelperMap.add(state);
                    }
                }
            }
        }
        System.out.println(frontier.empty());
        System.out.println("FAILURE");
        System.out.println("visited nodes : " + this.visit + ",  Max depth : " + this.maxDepth );
        return false;
    }

    @Override
    public Boolean SearchAStarEuclidean(String initialState, String goalState) {
        return null;
    }

    public int getMaxDepth() {
        return maxDepth;
    }


    public int getNodesExpanded() {
        return visit;
    }


    public int getCostOfPath() {
        return costOfPath;
    }


    public ArrayList<String> pathToGoal(){
        ArrayList<String> path = new ArrayList<>();
        String current = "012345678";
        path.add(0,current);
        while (current != null){

            if(visited.get(current).getParent() == null)
                break;
            current = visited.get(current).getParent().getState();
            path.add(0,current);
        }
        return path;
    }

}
