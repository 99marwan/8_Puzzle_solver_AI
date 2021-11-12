package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Controller {
    @FXML
    private Button Zero;
    @FXML
    private Button One;
    @FXML
    private Button Two;
    @FXML
    private Button Three;
    @FXML
    private Button Four;
    @FXML
    private Button Five;
    @FXML
    private Button Six;
    @FXML
    private Button Seven;
    @FXML
    private Button Eight;
    @FXML
    private Button Start;
    @FXML
    private Button Stop;
    @FXML
    private Button setArray;
    @FXML
    private TextField textField;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label11;
    @FXML
    private ComboBox combo;


    private ArrayList<String> states;
    private int Count = 0;
    private Boolean flag = false;
    private Boolean baseCaseButtons = false;
    private DFS dfs = new DFS();
    private BFS bfs = new BFS();
    private AStar astar = new AStar();
    private long time;
    private int Depth;
    private int nodesExp;
    private int cost;
    private int max;

    public Controller() throws FileNotFoundException {
    }
    public void Next(ActionEvent press){
        if(baseCaseButtons){
            if(!flag){
                Count++;
            }
            if(Count < states.size()) {
                setButtons(states.get(Count++));
                flag = true;
                if(Count==states.size()){
                    label.setText("SUCCESS");
                    label.setTextFill(Color.GREEN);
                    label.setVisible(true);
                    Start.setDisable(true);
                    Stop.setDisable(true);
                }
            }
        }
    }
    public void Array(ActionEvent press){
        label.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label11.setVisible(false);
        String state = textField.getText();
        if(Pattern.matches("[0-8]{9}",state)) {
            String str=combo.getValue().toString();
            EightPuzzle ob;
            if(str.equalsIgnoreCase("BFS")){
                ob = new BFS();
            }else if (str.equalsIgnoreCase("DFS")){
                ob = new DFS();
            }else if(str.equalsIgnoreCase("A* Manhattan")){
                ob = new AStar();
            }else{
                ob = new AStar();
            }
            Count = 0;
            boolean x;
            long time1 = System.nanoTime();
            if(str.equalsIgnoreCase("A* Euclidean") ){
                x = ob.SearchAStarEuclidean(state, "012345678");
            }else{
                x = ob.SearchTech(state, "012345678");
            }
            long time2 = System.nanoTime();
            time = (time2 - time1) / 1000;
            System.out.println(">> Running time is " + time + " in Microsecond");
            if (x) {
                nodesExp=ob.getNodesExpanded();
                cost=ob.getCostOfPath();
                max=ob.getMaxDepth();

                states = ob.pathToGoal();
                Depth = states.size()-1;

                System.out.println("Path to Goal :");
                for (String s : states) {
                    System.out.println(s);
                }
                System.out.println("==========================================");
                label1.setText("Search Depth = "+Depth);
                label2.setText("Running time = "+time+" micros");
                label3.setText("Nodes Expanded = "+nodesExp);
                label4.setText("Cost of Path = "+cost);
                label11.setText(("Max Depth = "+max));
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label11.setVisible(true);
                setButtons(state);
                baseCaseButtons = true;
                Start.setDisable(false);
                Stop.setDisable(false);
            } else {
                System.out.println("==========================================");
                label.setText("Unsolvable");
                label.setTextFill(Color.RED);
                label.setVisible(true);
                Start.setDisable(true);
                Stop.setDisable(true);
            }
            textField.clear();
        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INVALID INPUT");
            alert.setHeaderText("An error has been encountered");
            alert.setContentText("Type a valid state from between 0 and 8");
            alert.showAndWait();
        }
    }
    public void setButtons(String state){
        Zero.setText(Character.toString(state.charAt(0)));
        One.setText(Character.toString(state.charAt(1)));
        Two.setText(Character.toString(state.charAt(2)));
        Three.setText(Character.toString(state.charAt(3)));
        Four.setText(Character.toString(state.charAt(4)));
        Five.setText(Character.toString(state.charAt(5)));
        Six.setText(Character.toString(state.charAt(6)));
        Seven.setText(Character.toString(state.charAt(7)));
        Eight.setText(Character.toString(state.charAt(8)));
    }
    public void Previous(ActionEvent press){
        if(baseCaseButtons){
            if(flag){
                Count--;
                flag = false;
            }
            Count--;
            if(Count >= 0)
                setButtons(states.get(Count));
            else
                Count = 0;
        }
    }

}
