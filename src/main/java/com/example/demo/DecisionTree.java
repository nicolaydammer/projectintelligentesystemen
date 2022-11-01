package com.example.demo;

import java.util.Arrays;

public class DecisionTree {
    Board board;
    Stones stone;
    int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
    int[][] sides = {{0,1}, {1,2}, {2,1}, {1,0}};
    int[] middle = {1,1};

    public DecisionTree(Board board, Stones stone){
        this.board = board;
        this.stone = stone;
    }


    public int[] get_next_move(){
        //System.out.println("Starting decision");
        int[] next_move = new int[2];

        if (winOrDefend()[0] != -1){
            return winOrDefend();
        }

        //first round
        if(this.board.pieceCounter() == 0){   //first move, so place piece in corner
            next_move = corners[3];
        } else if(this.board.pieceCounter() == 1){ // second move, place in middle
            if(this.board.getBoard()[1][1].getValue() == ' '){
                next_move = middle;
            } else{next_move = corners[3];}

        }

        //second round
        else if(this.board.pieceCounter() == 2){                                 //third move, check if opponent in middle
            if(this.board.getBoard()[1][1].getValue() == ' '){              //if middle is open


                if(this.board.getBoard()[2][0].getValue() == ' '){
                    next_move = corners[2];
                } else {next_move = corners[1];}

            } else {    //if middle is chosen by opponent, go diagonally across
                next_move = corners[0];
            }
        } else if (this.board.pieceCounter() == 3) {    //fourth move, place on side
            for(int[] side : sides){
                if(this.board.getBoard()[side[0]][side[1]].getValue() == ' '){
                    next_move =side;
                }
            }
        }


        //third round
        else if(this.board.pieceCounter() == 4){ //fifth move
            for(int[] corner : corners){                                //check for empty corner
                if(this.board.getBoard()[corner[0]][corner[1]].getValue() == ' '){ //found empty corner
                    next_move = corner;
                    break;
                }
            }
        }

        //if all else fails
        else if (this.board.pieceCounter() > 4){
            for (int i = 0; i < this.board.getSize(); i++){
                for (int j = 0; j < this.board.getSize(); j++){
                    if(this.board.getBoard()[i][j].getValue() == ' '){
                        int[] move = {i, j};
                        next_move = move;
                    }
                }
            }
        }




        //System.out.println("next move is: " + Arrays.toString(next_move));
        return next_move;
    }




    private char[][] createArrays(){
        char[][] boardArrays = new char[8][];
        int index = 0;
        for (int i = 0; i < this.board.getSize(); i++){

            char[] column = new char[3];
            char[] row = new char[3];
            for (int j = 0; j < this.board.getSize(); j ++){
                column[j] = this.board.getBoard()[j][i].getValue();
                row[j] = this.board.getBoard()[i][j].getValue();
            }


            boardArrays[index] = column;
            index++;
            boardArrays[index] = row;
            index++;
        }
        char[] diagonal1 = {
                this.board.getBoard()[0][0].getValue(),
                this.board.getBoard()[1][1].getValue(),
                this.board.getBoard()[2][2].getValue()
        };
        char[] diagonal2 = {
                this.board.getBoard()[0][2].getValue(),
                this.board.getBoard()[1][1].getValue(),
                this.board.getBoard()[2][0].getValue()
        };


        boardArrays[6] = diagonal1;
        boardArrays[7] = diagonal2;


        return boardArrays;
    }

    private int[] checkDoubles(char[][] arrays){
        boolean foundFlag = false;
        int index = 0;
        int [][] doubles = new int[8][3];
        int[] foundDouble = new int[1];

        for (int i =0; i < arrays.length; i++){
            for (int j =0; j< arrays[i].length; j++){
                for (int k = j+1; k < arrays[i].length; k++){
                    if (arrays[i][j] == arrays[i][k] && arrays[i][j] != ' '){
                        //double
                        if (spaceInROw(i, j, k, arrays)){
                            foundFlag = true;
                            //System.out.println("found double at " +i+ ", "+ j +", " + k);
                            foundDouble = new int[] {i,j,k};
                            doubles[index] = foundDouble;
                            index++;
                            //return foundDouble; //winning needs to take precedence over defendding.
                        }

                    }
                }
            }
        }

        if(foundFlag){
            //System.out.println(Arrays.deepToString(doubles));
            for (int[] doub : doubles){
                if(doub[1] == stone.getValue()){foundDouble = doub;}
            }
            return foundDouble;
        }
        int[] temp = {-1,-1};
        return temp;
    }

    private Boolean spaceInROw(int i, int j, int k, char[][] arrays){
        if (j + k == 1 && arrays[i][2] == ' '){
            return true;
        } else if (j +k  == 2 && arrays[i][1] == ' ' ){
            return true;
        } else if(j + k == 3 && arrays[i][0] == ' ') {
            return true;
        }
        return false;
    }
    private int[] winOrDefend(){
        char [][] arrays = createArrays();
        int[] checkDoubles = checkDoubles(arrays);
        int[] place_at = new int[2];
        if(checkDoubles[0] != -1){
            int sum = checkDoubles[1] + checkDoubles[2];
            switch(checkDoubles[0]){
                case -1:
                    //found no doubles
                    break;
                case 0:
                    //0
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                        place_at[1] = 0;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 0;
                    }else{ //place at index 0
                        place_at[0] = 0;
                        place_at[1] = 0;
                    }
                    break;
                case 1:
                    //3
                    if (sum == 1){ //place at index 2
                        place_at[0] = 0;
                        place_at[1] = 2;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 0;
                        place_at[1] = 1;
                    }else{ //place at index 0
                        place_at[0] = 0;
                        place_at[1] = 0;
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
                        place_at[0] = 0;
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
                        place_at[1] = 0;
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
                        place_at[0] = 0;
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
                        place_at[1] = 0;
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
                    }else{ //place at index 0
                        place_at[0] = 0;
                        place_at[1] = 0;
                    }
                    break;
                case 7:
                    if (sum == 1){ //place at index 2
                        place_at[0] = 2;
                        place_at[1] = 0;
                    }else if (sum == 2){ // place at index 1
                        place_at[0] = 1;
                        place_at[1] = 1;
                    }else{ //place at index 0
                        place_at[0] = 0;
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
}
