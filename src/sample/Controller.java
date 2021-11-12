package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Duration;
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

    private ArrayList<String> states;
    private int Count = 0;
    private Boolean flag = false;
    private Boolean baseCaseButtons = false;
    private DFS dfs = new DFS();
    private BFS bfs = new BFS();
    private AStar astar = new AStar();
    private long time;


    public void Next(ActionEvent press){
        if(baseCaseButtons){
            if(!flag){
                Count++;
            }
            if(Count < states.size()) {
                setButtons(states.get(Count++));
                flag = true;
                if(Count==states.size()){
                    Start.setDisable(true);
                    Stop.setDisable(true);
                }
            }
        }
    }
    public void Array(ActionEvent press){
        String state = textField.getText();
        if(Pattern.matches("[0-8]{9}",state)) {
            Count = 0;
            long time1 = System.nanoTime();
            boolean x = astar.SearchTechEuclidean(state, "012345678");
            long time2 = System.nanoTime();
            time = (time2 - time1) / 1000000;
            System.out.println(">> Running time is " + time + " in Millisecond");
            if (x) {
                states = astar.pathToGoal();
                System.out.println("Path to Goal :");
                for (int i = 0; i < states.size(); i++) {
                    System.out.println(states.get(i));
                }
                System.out.println("==========================================");
                setButtons(state);
                baseCaseButtons = true;
                Start.setDisable(false);
                Stop.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FAILED");
                alert.setHeaderText("An error has been encountered");
                alert.setContentText("Unsolvable");
                alert.showAndWait();
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
