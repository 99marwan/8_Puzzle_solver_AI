package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.util.ArrayList;


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



    public void Start(ActionEvent press){
        if(Count < states.size())
            setButtons(states.get(Count++));
    }
    public void Array(ActionEvent press){
        states = new ArrayList<>();
        states.add("125340678");
        states.add("120345678");
        states.add("102345678");
        states.add("012345678");
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
    public void Exit(ActionEvent press){
        System.exit(0);
    }

}
