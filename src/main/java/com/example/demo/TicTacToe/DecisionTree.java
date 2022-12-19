package com.example.demo.TicTacToe;

import com.example.demo.board.Board;

public class DecisionTree {
    //Board board;
    //Stones stone;
    int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
    int[][] sides = {{0,1}, {1,2}, {2,1}, {1,0}};
    int[] middle = {1,1};

    public DecisionTree(){

    }
    public int getNextMove(Board board1, char stoneChar){
        /*
        returns the next move as an int from 0 to 8
         */
        //System.out.println("Starting decision");

        int piece_counter = board1.pieceCounter();
        int[] next_move = new int[2];

        if (winOrDefend(board1, stoneChar)[0] != -1){                                                            //check if there is a possibility of winning or losing.
            next_move = winOrDefend(board1, stoneChar);
        } else {
            switch (piece_counter){
                case 0:
                    next_move = corners[3];                                                     //first move, so place piece in corner
                    break;
                case 1:
                    if(board1.getBoard()[1][1].getValue() == ' '){
                        next_move = middle;                                                     //second move, place in the middle. If that's not possible we can play as if we went first
                    } else{next_move = corners[3];}
                    break;
                case 2:
                    if(board1.getBoard()[1][1].getValue() == ' '){                          //if middle is open
                        if(board1.getBoard()[2][0].getValue() == ' '){                      //choose corner 7 or 3
                            next_move = corners[2];
                        } else {next_move = corners[1];}

                    } else {                                                                    //if middle is chosen by opponent, go diagonally across
                        next_move = corners[0];
                    }
                    break;
                case 3:                                                                         //fourth move, place on side
                    for(int[] side : sides){
                        if(board1.getBoard()[side[0]][side[1]].getValue() == ' '){
                            next_move =side;
                        }
                    }
                    break;
                case 4:
                    for(int[] corner : corners){                                                //check for empty corner
                        if(board1.getBoard()[corner[0]][corner[1]].getValue() == ' '){      //found empty corner
                            next_move = corner;
                            break;
                        }
                    }
                    break;
                default:                                                                        //if all else fails, pick a random empty place on the board. Should never be reached.
                    for (int i = 0; i < board1.getSize(); i++){
                        for (int j = 0; j < board1.getSize(); j++){
                            if(board1.getBoard()[i][j].getValue() == ' '){
                                next_move = new int[]{i, j};
                            }
                        }
                    }
            }
        }
        //System.out.println("next move is: " + Arrays.toString(next_move));
        return convertToBoardPosition(next_move);
    }

    private char[][] createArrays(Board board1){
        /*
        create an array for each possible winning combination of places on the board filled with the pieces on the board.
         */
        char[][] boardArrays = new char[8][3];
        int index = 0;

        for (int i = 0; i < board1.getSize(); i++){
            char[] column = new char[3];
            char[] row = new char[3];
            for (int j = 0; j < board1.getSize(); j ++){
                column[j] = board1.getBoard()[j][i].getValue();
                row[j] = board1.getBoard()[i][j].getValue();
            }
            boardArrays[index] = column;
            index++;
            boardArrays[index] = row;
            index++;
        }
        char[] diagonal1 = {
                board1.getBoard()[0][0].getValue(),
                board1.getBoard()[1][1].getValue(),
                board1.getBoard()[2][2].getValue()
        };
        char[] diagonal2 = {
                board1.getBoard()[0][2].getValue(),
                board1.getBoard()[1][1].getValue(),
                board1.getBoard()[2][0].getValue()
        };
        boardArrays[6] = diagonal1;
        boardArrays[7] = diagonal2;
        return boardArrays;
    }

    private int[] checkDoubles(char[][] arrays, char stoneChar){
        /*
        check if there are any arrays with two of the same char in it and if there is an empty space.
        If there is more than one array that satisfies this condition, return the one where char == stone.getValue() to win.


         */
        boolean isFound = false;
        int index = 0;
        int [][] doubles = new int[8][3];
        int[] foundDouble = new int[1];

        for (int i =0; i < arrays.length; i++){
            for (int j =0; j< arrays[i].length; j++){
                for (int k = j+1; k < arrays[i].length; k++){
                    if (arrays[i][j] == arrays[i][k] && arrays[i][j] != ' '){
                        //double
                        if (spaceInROw(i, j, k, arrays)){
                            isFound = true;
                            //System.out.println("found double at " +i+ ", "+ j +", " + k);
                            foundDouble = new int[] {i,j,k};
                            doubles[index] = foundDouble;
                            index++;
                        }

                    }
                }
            }
        }

        if(isFound){
            //System.out.println(Arrays.deepToString(doubles));
            for (int[] doub : doubles){
                if(doub[1] == stoneChar){foundDouble = doub;}
            }
            return foundDouble;
        }
        return new int[]{-1,-1};
    }

    private Boolean spaceInROw(int i, int j, int k, char[][] arrays){
        /*
        check if one of the elements in a row is empty
         */
        if (j + k == 1 && arrays[i][2] == ' '){
            return true;
        } else if (j +k  == 2 && arrays[i][1] == ' ' ){
            return true;
        } else return j + k == 3 && arrays[i][0] == ' ';
    }


    private int[] winOrDefend(Board board1, char stoneChar){
        /*
            check if there are two pieces of the same type next to each other on the board,
            if so, return the place on the board that prevents or completes three in a row.
     */

        int[] checkDoubles = checkDoubles(createArrays(board1), stoneChar);
        int[] place_at = new int[2];
        if(checkDoubles[0] != -1){
            int sum = checkDoubles[1] + checkDoubles[2];
            switch(checkDoubles[0]){

                case 0:
                    //0
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                    }
                    break;
                case 1:
                    //3
                    if (sum == 1){ //place at index 2
                        place_at[1] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[1] = 1;
                    }
                    break;
                case 2:
                    //1
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                        place_at[1] = 1;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 1;
                    }else{ //place at index 0
                        place_at[1] = 1;
                    }
                    break;
                case 3:
                    //4
                    if (sum == 1){ //place at index 2
                        place_at[0] = 1;
                        place_at[1] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 1;
                    }else{ //place at index 0
                        place_at[0] = 1;
                    }
                    break;
                case 4:
                    //2
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                        place_at[1] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 2;
                    }else{ //place at index 0
                        place_at[1] = 2;
                    }
                    break;
                case 5:
                    //5
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                        place_at[1] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 2;
                        place_at[1] = 1;
                    }else{ //place at index 0
                        place_at[0] = 2;
                    }
                    break;
                case 6:
                    //6
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                        place_at[1] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 1;
                    }
                    break;
                case 7:
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 1;
                    }else{ //place at index 0
                        place_at[1] = 2;
                    }
                    //7
                    break;
            }
        } else {
            place_at[0] = -1;
            place_at[1] = -1;
        }
        return place_at;
    }


    private int convertToBoardPosition(int[] move){
        /*
    Converts the position on the 2d board array to an integer from 0 to 8.
     */
        int result = -1;
        if(move[0] == 0){
            if (move[1] == 0){result = 0;}
            else if (move[1] == 1) {result = 1;}/*
    Converts the position on the 2d board array to an integer from 0 to 8.
     */
            else{result = 2;}
        } //1, 2, 3
        else if (move[0] == 1){
            if (move[1] == 0){result = 3;}
            else if (move[1] == 1) {result = 4;}
            else{result = 5;}
        } //4, 5, 6
        else if (move[0] == 2){
            if (move[1] == 0){result = 6;}
            else if (move[1] == 1) {result = 7;}
            else{result = 8;}
        } //7, 8, 9
        return result;
    }
}
