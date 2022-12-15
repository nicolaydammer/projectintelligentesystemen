package com.example.demo.Othello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class OController implements Initializable {

    @FXML
    private Button A1;
    @FXML
    private Button A2;
    @FXML
    private Button A3;
    @FXML
    private Button A4;
    @FXML
    private Button A5;
    @FXML
    private Button A6;
    @FXML
    private Button A7;
    @FXML
    private Button A8;


    @FXML
    private Button B1;
    @FXML
    private Button B2;
    @FXML
    private Button B3;
    @FXML
    private Button B4;
    @FXML
    private Button B5;
    @FXML
    private Button B6;
    @FXML
    private Button B7;
    @FXML
    private Button B8;

    @FXML
    private Button C1;
    @FXML
    private Button C2;
    @FXML
    private Button C3;
    @FXML
    private Button C4;
    @FXML
    private Button C5;
    @FXML
    private Button C6;
    @FXML
    private Button C7;
    @FXML
    private Button C8;

    @FXML
    private Button D1;
    @FXML
    private Button D2;
    @FXML
    private Button D3;
    @FXML
    private Button D4;
    @FXML
    private Button D5;
    @FXML
    private Button D6;
    @FXML
    private Button D7;
    @FXML
    private Button D8;

    @FXML
    private Button E1;
    @FXML
    private Button E2;
    @FXML
    private Button E3;
    @FXML
    private Button E4;
    @FXML
    private Button E5;
    @FXML
    private Button E6;
    @FXML
    private Button E7;
    @FXML
    private Button E8;

    @FXML
    private Button F1;
    @FXML
    private Button F2;
    @FXML
    private Button F3;
    @FXML
    private Button F4;
    @FXML
    private Button F5;
    @FXML
    private Button F6;
    @FXML
    private Button F7;
    @FXML
    private Button F8;

    @FXML
    private Button G1;
    @FXML
    private Button G2;
    @FXML
    private Button G3;
    @FXML
    private Button G4;
    @FXML
    private Button G5;
    @FXML
    private Button G6;
    @FXML
    private Button G7;
    @FXML
    private Button G8;

    @FXML
    private Button H1;
    @FXML
    private Button H2;
    @FXML
    private Button H3;
    @FXML
    private Button H4;
    @FXML
    private Button H5;
    @FXML
    private Button H6;
    @FXML
    private Button H7;
    @FXML
    private Button H8;

    @FXML
    private Text ScoreWhiteText;

    @FXML
    private Text ScoreBlackText;


    private int ScoreBlack;
    private int ScoreWhite;

    private int playerTurn = 0;

    ArrayList<Button> buttons;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(A1, A2, A3, A4, A5, A6, A7, A8, B1, B2, B3, B4, B5, B6, B7, B8, C1, C2, C3, C4, C5, C6, C7, C8, D1, D2, D3, D4, D5, D6, D7, D8, E1, E2, E3, E4, E5, E6, E7, E8, F1, F2, F3, F4, F5, F6, F7, F8, G1, G2, G3, G4, G5, G6, G7, G8, H1, H2, H3, H4, H5, H6, H7, H8));


        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
            button.setDisable(true);
        });
    }

    public void LegalMove(){
        // move alleen toestaan wanneer aan het eind van de row dezelfde kleur als de huidige speler ligt
        //
        //Horizontaal

        //Verticaal

        //Diagonaal

    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
        });
    }

    public void CalcScore(){

        for (Button button : buttons) {
            if(button.getStyle().equals("fx-background-color: #000000")){
                ScoreBlack += 1;

            }
            else if(button.getStyle().equals("fx-background-color: #FFFFFF")){
                ScoreWhite += 1;
            }

        }
    }

    public void SetWhiteScore(){
        ScoreWhiteText.setText(Integer.toString(GetWhiteScore()));
    }

    public void SetBlackScore() {
        ScoreBlackText.setText(Integer.toString(GetBlackScore()));

    }
    public int GetWhiteScore(){
        return ScoreWhite;

    }
    public int GetBlackScore(){
        return ScoreBlack;
    }


    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
             ColorInput colorInput = new ColorInput();
             colorInput.setHeight(100);
             colorInput.setWidth(100);
             colorInput.setPaint(Color.BLACK);
             button.setEffect(colorInput);
            playerTurn = 1;
        } else {
            ColorInput colorInput = new ColorInput();
            colorInput.setHeight(100);
            colorInput.setWidth(100);
            colorInput.setPaint(Color.WHITE);
            button.setEffect(colorInput);
            playerTurn = 0;
        }
    }

//    public void setPlayerSymbol(Button button) {
//        if (playerTurn % 2 == 0) {
//            button.setStyle("fx-background-color: #000000");
//            playerTurn = 1;
//        } else {
//            button.setStyle("fx-background-color: #FFFFFFF");
//            playerTurn = 0;
//        }




    }


