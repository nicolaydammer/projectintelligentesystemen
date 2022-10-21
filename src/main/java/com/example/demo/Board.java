package src.main.java.com.example.demo;

public class Board {
    private int size;
    private char[][] board;

    public Board(int size, char[][] board) {
        this.size = size;
        this.board = board;
        board =  new char[size][size];
        initialization();
    }

    public void initialization(){
        for(int i=0; i<=this.size; i++) {
            for (int j = 0; j <= this.size;j++){
                this.board[i][j] = '-';
            }
        }
    }

    public void printBoard(){
        String border = "";
        for(int i=0; i<=this.size * 3; i++){
            border += "_";
        }
        System.out.println(border);

        for(int i=0; i<=this.size; i++) {
            System.out.print("| ");
            for (int j = 0; j <= this.size;j++){
                System.out.print(this.board[i][j] + " | ");
            }

            System.out.println();
            System.out.println(border);
        }
        System.out.println(border);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
