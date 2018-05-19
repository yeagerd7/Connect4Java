package v1;

public class Connect4Game {

    private int turn;
    private int winner;
    private final char human = 'R';
    private final char ai = 'Y';
    private char[][] gameBoard;

    public Connect4Game() {
        turn = 0;
        winner = 0;
        gameBoard = new char[7][15];
        gameBoard = generateInitialBoard(gameBoard);
        System.out.println("\nHey you!  Let's play some Connect 4 :D\n");
        printBoard();
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void humanPlay(int column) {
        boolean spotFilled = false;
        int i = 6;
        while(spotFilled == false) {
            if(gameBoard[i][column] == ' ') {
                gameBoard[i][column] = human;
                spotFilled = true;
            }
            i--;
        }
    }

    public void AIPlay() {

    }

    private char[][] generateInitialBoard(char[][] board){
        for(int i = 0; i <board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(j % 2 == 0 ) {
                    board[i][j] = '|';
                }
                else {
                    board[i][j] = ' ';
                }
                if(i == 6) {
                    board[i][j] = '-';
                }
            }
        }
        return board;
    }

    public void printBoard() {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public int checkForWinner() {
        //Check for horizontal win
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j += 2) {
                if(gameBoard[i][j + 1] != ' ' && gameBoard[i][j + 3] != ' ' &&
                   gameBoard[i][j + 5] != ' ' && gameBoard[i][j + 7] != ' ' &&
                   ((gameBoard[i][j + 1] == gameBoard[i][j + 3]) &&
                    (gameBoard[i][j + 3] == gameBoard[i][j + 5]) &&
                    (gameBoard[i][j + 5] == gameBoard[i][j + 7]))){
                    //If the winner combo is "R" then the human wins
                    if(gameBoard[i][j + 1] == 'R') {
                        return 1;
                    }
                    //The AI wins
                    else if (gameBoard[i][j + 1] == 'Y') {
                        return 2;
                    }
                }
            }
        }
        //Check for vertical win
        for(int i = 1; i < 15; i += 2) {
            for(int j = 0; j < 3; j++) {
                if(gameBoard[j][i] != ' ' && gameBoard[j + 1][i] !=  ' ' &&
                   gameBoard[j + 2][i] != ' ' && gameBoard[j + 3][i] != ' ' &&
                   ((gameBoard[j][i] == gameBoard[j + 1][i]) &&
                    (gameBoard[j + 1][i] == gameBoard[j + 2][i]) &&
                    (gameBoard[j + 2][i] == gameBoard[j + 3][i]))) {
                    //You win
                    if(gameBoard[j][i] == 'R') {
                        return 1;
                    }
                    //The AI wins
                    else if (gameBoard[j][i] == 'Y') {
                        return 2;
                    }
                }
            }
        }
        //Check for diagonal wins
        //Left-up to right-down diagonal line
        for(int i = 0; i < 3; i++) {
            for(int j = 1; j < 9; j += 2) {
                if(gameBoard[i][j] != ' ' && gameBoard[i + 1][j + 2] != ' ' &&
                   gameBoard[i + 2][j + 4] != ' ' && gameBoard[i + 3][j + 6] != ' ' &&
                   ((gameBoard[i][j] == gameBoard[i + 1][j + 2]) &&
                    (gameBoard[i + 1][j + 2] == gameBoard[i + 2][j + 4]) &&
                    (gameBoard[i + 2][j + 4] == gameBoard[i + 3][j + 6]))) {
                    //You win
                    if(gameBoard[i][j] == 'R') {
                        return 1;
                    }
                    //The AI wins
                    else if (gameBoard[i][j] == 'Y') {
                        return 2;
                    }
                }
            }
        }
        //Right-up to left-down diagonal win
        for(int i = 0; i < 3; i++) {
            for(int j = 7; j < 15; j += 2) {
                if(gameBoard[i][j] != ' ' && gameBoard[i + 1][j - 2] != ' ' &&
                   gameBoard[i + 2][j - 4] != ' ' && gameBoard[i + 3][j - 6] != ' ' &&
                   ((gameBoard[i][j] == gameBoard[i + 1][j - 2]) &&
                    (gameBoard[i + 1][j - 2] == gameBoard[i + 2][j - 4]) &&
                    (gameBoard[i + 2][j - 4] == gameBoard[i + 3][j - 6]))) {
                    //You win
                    if(gameBoard[i][j] == 'R') {
                        return 1;
                    }
                    //The AI wins
                    else if (gameBoard[i][j] == 'Y') {
                        return 2;
                    }
                }
            }
        }
        return 0;
    }

}
