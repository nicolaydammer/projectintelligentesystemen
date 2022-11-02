package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TicTacToeController implements Initializable {
    private Stones stone;
    private final Board field =  new Board(3);

    // for 2 humans playing
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    @FXML
    private Text winnerText;

    private int playerTurn = 0;
    private String symbolPlayer;
    private String symbolOpponent;

    private SharedData sharedData = SharedData.getInstance();
    ArrayList<Button> buttons;

    public TicTacToeController(Map map) throws IOException {
        if(sharedData.hasConnection()){
            ClientConnectionController connection = new ClientConnectionController();

            // Start the connection and send the initial needed data.
            connection.startConnection();
            String response = connection.sendStartData();
            Map<String, String> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, String>>() {}.getType());

            String playerToMove = response.split(" ", 4)[3].;


            //todo: code to get game info from server
            if(sharedData.getPlayer().getName().equals(retMap.get("PLAYERTOMOVE"))){
                symbolPlayer = "X";
                symbolOpponent = "O";

                while( !connection.getMessage.contains("SVR GAME WIN") || !connection.getMessage.contains("SVR GAME LOSS") || !connection.getMessage.contains("SVR GAME DRAW")){

                    if(response.contains("SVR GAME YOURTURN")){
                        int move = makeMove(sharedData.hasConnection(), symbolPlayer, field);
                        response = connection.sendMessage("move " + move);
                        updateUI();
                        //todo: code to give move back to server
                    } else{
                        String move = map.get("MOVE"); // get move opponent from server
                        makeMove(sharedData.hasConnection(), symbolOpponent, field); // update board with opponent move
                    }
                }
                s
            }else{
                symbolPlayer = "O";
                symbolOpponent = "X";
                while((fromServer = in.readline()) != null){
                    if(fromServer.equals("SVR GAME YOURTURN")){
                        int move = makeMove(sharedData.hasConnection(), symbolPlayer, field);
                        //todo: code to give move back to server
                    } else{
                        String move = map.get("MOVE"); // get move opponent from server
                        makeMove(sharedData.hasConnection(), symbolOpponent, field); // update board with opponent move
                    }
                    updateUI();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        buttons.forEach(button ->{
//            setUpButton(button);
            button.setFocusTraversable(false);
        });
    }

     public void updateUI() {
         int count = 0;
         for (int i = 1; i < field.getSize(); i++) {
             for (int j = 0; j < field.getSize(); j++) {
                 count++;
                 setPlayerSymbol(buttons.get(count), field[i][j]);
             }
         }
     }
    @FXML
    void restartGame(ActionEvent event){
        buttons.forEach(this::resetButton);
        winnerText.setText("TicTacToe");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

//    public void setUpButton(Button button){
//        button.setOnMouseClicked(mouseEvent -> {
//            setPlayerSymbol(button);
//            button.setDisable(true);
//            checkIfGameIsOver();
//        });
//    }

    public void setPlayerSymbol(Button button, String symbol){
              button.setText(symbol);
          }
//        if(playerTurn % 2 == 0){ // X starts always and is therefore always when playerTurn is even
//            button.setText("X");
//            playerTurn = 1;
//        } else{
//            button.setText("O");
//            playerTurn = 0;
//        }
//    }

    public void checkIfGameIsOver(){
        for (int i = 0; i<8; i++){
            String line;
            switch (i) {
                case 0:
                    line = button1.getText() + button2.getText() + button3.getText();
                    break;
                case 1:
                    line = button4.getText() + button5.getText() + button6.getText();
                    break;
                case 2:
                    line = button7.getText() + button8.getText() + button9.getText();
                    break;
                case 3:
                    line = button1.getText() + button4.getText() + button7.getText();
                    break;
                case 4:
                    line = button2.getText() + button5.getText() + button8.getText();
                    break;
                case 5:
                    line = button3.getText() + button6.getText() + button9.getText();
                    break;
                case 6:
                    line = button1.getText() + button5.getText() + button9.getText();
                    break;
                case 7:
                    line = button3.getText() + button5.getText() + button7.getText();
                    break;
                default:
                    line = null;
                    break;
            }

            // X winner
            if(line.equals("XXX")){
                winnerText.setText("X won!");
            }

            // O winner
            else if(line.equals("OOO")){
                winnerText.setText("O won!");
            }
        }


    }



}
