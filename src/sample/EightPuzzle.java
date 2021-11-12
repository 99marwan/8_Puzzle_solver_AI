package sample;

import java.util.ArrayList;

public interface EightPuzzle {

    Boolean SearchTech(String initialState , String goalState);
    Boolean SearchAStarEuclidean(String initialState, String goalState);
    public int getMaxDepth();
    public int getNodesExpanded();
    public ArrayList<String> pathToGoal();
    public int getCostOfPath();
}
