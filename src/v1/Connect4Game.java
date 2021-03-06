package v1;

public class Connect4Game {

    private int turn;
    private int winner;
    private final char human = 'R';
    private final char ai = 'Y';
    private char[][] gameBoard;

    public Connect4Game() {
        turn = 1;
        winner = 0;
        gameBoard = new char[7][15];
        gameBoard = generateInitialBoard(gameBoard);
        System.out.println("\nHey you!  Let's play some Connect 4 :D\n");
        printBoard();
    }

    public char getOccupancyAt(int row, int col)
    {
    	return gameBoard[row][col];
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
        while(!spotFilled) {
            if(gameBoard[i][column] == ' ') {
                gameBoard[i][column] = human;
                spotFilled = true;
            }
            i--;
        }
    }

    public void AIPlay(int column)
    {
        boolean spotFilled = false;
        int i = 6;
        while(!spotFilled && i > -1) {
            if(gameBoard[i][column] == ' ') {
                gameBoard[i][column] = ai;
                spotFilled = true;
            }
            i--;
        }
    }

    public boolean columnFilled(int column) {
        boolean columnFilled = true;
        int i = 5;
        while(columnFilled && i >= 0) {
            if(gameBoard[i][column] == ' ') {
                columnFilled = false;
                return columnFilled;
            }
            i--;
        }
        return columnFilled;
    }

    /*
     * Returns the next position available
     * in respect to the column number given
     * if the last row is up to question
     * it will return the last row
     */
    public int getNextPositionInCol(int columnNumber)
    {
    	int nextPosition = 5;
    	while(gameBoard[nextPosition][columnNumber] != ' ' && nextPosition > 0)
    	{
    		nextPosition--;
    	}
    	return nextPosition;
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
        System.out.println();
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
        //Left-Down to Right-Up diagonal win
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

    public boolean isPlayableMove(int row, int column) {
        boolean playable = false;
            if (gameBoard[row][column] == ' ') {
                playable = true;
            }
        return playable;
    }

    public boolean isWinnableMove(int row, int column) {
        //Check for Horizontal Winning Move
        if(column == 1) {
            if(gameBoard[row][column + 2] != ' ' && (gameBoard[row][column + 2] == gameBoard[row][column + 4] &&
                                                     gameBoard[row][column + 4] == gameBoard[row][column + 6])) {
                return true;
            }
        }
        else if(column == 3) {
            if(gameBoard[row][column + 2] != ' ' && (gameBoard[row][column + 2] == gameBoard[row][column + 4] &&
                                                     gameBoard[row][column + 4] == gameBoard[row][column + 6])) {
                return true;
            }
            else if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column + 2] &&
                                                          gameBoard[row][column + 2] == gameBoard[row][column + 4])) {
                return true;
            }
        }
        else if(column == 5) {
            if(gameBoard[row][column + 2] != ' ' && (gameBoard[row][column + 2] == gameBoard[row][column + 4] &&
                                                     gameBoard[row][column + 4] == gameBoard[row][column + 6])) {
                return true;
            }
            else if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column + 2] &&
                                                          gameBoard[row][column + 2] == gameBoard[row][column + 4])) {
                return true;
            }
            else if(gameBoard[row][column - 4] != ' ' && (gameBoard[row][column - 4] == gameBoard[row][column - 2] &&
                                                          gameBoard[row][column - 2] == gameBoard[row][column + 2])) {
                return true;
            }
        }
        else if(column == 7) {
            if(gameBoard[row][column + 2] != ' ' && (gameBoard[row][column + 2] == gameBoard[row][column + 4] &&
                                                     gameBoard[row][column + 4] == gameBoard[row][column + 6])) {
                return true;
            }
            else if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column + 2] &&
                                                          gameBoard[row][column + 2] == gameBoard[row][column + 4])) {
                return true;
            }
            else if(gameBoard[row][column - 4] != ' ' && (gameBoard[row][column - 4] == gameBoard[row][column - 2] &&
                                                          gameBoard[row][column - 2] == gameBoard[row][column + 2])) {
                return true;
            }
            else if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column - 4] &&
                                                          gameBoard[row][column - 4] == gameBoard[row][column - 6])) {
                return true;
            }
        }
        else if(column == 9) {
            if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column + 2] &&
                                                     gameBoard[row][column + 2] == gameBoard[row][column + 4])) {
                return true;
            }
            else if(gameBoard[row][column - 4] != ' ' && (gameBoard[row][column - 4] == gameBoard[row][column - 2] &&
                                                          gameBoard[row][column - 2] == gameBoard[row][column + 2])) {
                return true;
            }
            else if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column - 4] &&
                                                          gameBoard[row][column - 4] == gameBoard[row][column - 6])) {
                return true;
            }
        }
        else if(column == 11) {
            if(gameBoard[row][column - 4] != ' ' && (gameBoard[row][column - 4] == gameBoard[row][column - 2] &&
                                                     gameBoard[row][column - 2] == gameBoard[row][column + 2])) {
                return true;
            }
            else if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column - 4] &&
                                                          gameBoard[row][column - 4] == gameBoard[row][column - 6])) {
                return true;
            }
        }
        else if(column == 13) {
            if(gameBoard[row][column - 2] != ' ' && (gameBoard[row][column - 2] == gameBoard[row][column - 4] &&
                                                     gameBoard[row][column - 4] == gameBoard[row][column - 6])) {
                return true;
            }
        }

        //Check for Vertical Winning Move
        if(row == 0 || row == 1 || row == 2) {
            if(gameBoard[row + 1][column] != ' ' && (gameBoard[row + 1][column] == gameBoard[row + 2][column] &&
                                                     gameBoard[row + 2][column] == gameBoard[row + 3][column])) {
                return true;
            }
        }

        //Check for Left-Down To Right Up Diagonal Winning Move
        if(column == 1 && (row == 3 || row == 4 || row == 5)) {
            if(gameBoard[row - 1][column + 2] != ' ' &&
              (gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4] &&
               gameBoard[row - 2][column + 4] == gameBoard[row - 3][column + 6])) {
                return true;
            }
        }
        else if((column == 3 && row == 2) || (column == 9 && row == 4)) {
            if(gameBoard[row + 1][column - 2] != ' ' &&
              (gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2] &&
               gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4])) {
                return true;
            }
        }
        else if(column == 3 && (row == 3 || row == 4 )) {
            if(gameBoard[row - 1][column + 2] != ' ' &&
              (gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4] &&
               gameBoard[row - 2][column + 4] == gameBoard[row - 3][column + 6])) {
                return true;
            }
            else if(gameBoard[row + 1][column - 2] != ' ' &&
                   (gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2] &&
                    gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4])) {
                return true;
            }
        }
        else if((column == 1 || column == 3 || column == 5 || column == 7) && row == 5) {
            if(gameBoard[row - 1][column + 2] != ' ' &&
              (gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4] &&
               gameBoard[row - 2][column + 4] == gameBoard[row - 3][column + 6])) {
                return true;
            }
        }
        else if((column == 5 && row == 1) || (column == 11 && row == 3)) {
            if(gameBoard[row + 2][column - 4] != ' ' &&
              (gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2] &&
               gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2])) {
                return true;
            }
        }
        else if((column == 5 && row == 2) || (column == 9 && row == 3)) {
            if(gameBoard[row + 2][column - 4] != ' ' &&
              (gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2] &&
               gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row + 1][column - 2] != ' ' &&
                   (gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2] &&
                    gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4])) {
                return true;
            }
        }
        else if((column == 5 || column == 7) && row == 3) {
            if(gameBoard[row - 1][column + 2] != ' ' &&
              (gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4] &&
               gameBoard[row - 2][column + 4] == gameBoard[row - 3][column + 6])) {
                return true;
            }
            else if(gameBoard[row + 1][column - 2] != ' ' &&
                   (gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2] &&
                    gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4])) {
                return true;
            }
            else if(gameBoard[row + 2][column - 4] != ' ' &&
                   (gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2] &&
                    gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2])) {
                return true;
            }
        }
        else if((column == 5 || column == 7) && row == 4) {
            if(gameBoard[row - 1][column + 2] != ' ' &&
              (gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4] &&
               gameBoard[row - 2][column + 4] == gameBoard[row - 3][column + 6])) {
                return true;
            }
            else if(gameBoard[row + 1][column - 2] != ' ' &&
                   (gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2] &&
                    gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4])) {
                return true;
            }
        }
        else if((column == 7 || column == 9 || column == 11) && row == 0) {
            if(gameBoard[row + 3][column - 6] != ' ' &&
              (gameBoard[row + 3][column - 6] == gameBoard[row + 2][column - 4] &&
               gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2])) {
                return true;
            }
        }
        else if((column == 7 || column == 9) && row == 1) {
            if(gameBoard[row + 2][column - 4] != ' ' &&
              (gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2] &&
               gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row + 3][column - 6] != ' ' &&
                   (gameBoard[row + 3][column - 6] == gameBoard[row + 2][column - 4] &&
                    gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2])) {
                return true;
            }
        }
        else if((column == 7 || column == 9) && row == 2) {
            if(gameBoard[row + 1][column - 2] != ' ' &&
              (gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2] &&
               gameBoard[row - 1][column + 2] == gameBoard[row - 2][column + 4])) {
                return true;
            }
            else if(gameBoard[row + 2][column - 4] != ' ' &&
                   (gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2] &&
                    gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row + 3][column - 6] != ' ' &&
                   (gameBoard[row + 3][column - 6] == gameBoard[row + 2][column - 4] &&
                    gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2])) {
                return true;
            }
        }
        //Combo of Column 7 and Row 3 is checked above (line 353)
        //Combo of Column 7 and Row 4 is checked above (Line 370)
        //Combo of Column 7 and Row 5 is checked above (Line 327)
        //Combo of Column 9 and Row 1 is checked above (Line 389)
        //Combo of Column 9 abd Row 2 is checked above (Line 401)
        //Combo of Column 9 and Row 3 is checked above (Line 341)
        //Combo of Column 9 and Row 4 is checked above (Line 308)
        //Combo of Column 11 and Row 0 is checked above (Line 382)
        else if(column == 11 && (row == 1 || row == 2)) {
            if(gameBoard[row + 2][column - 4] != ' ' &&
              (gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2] &&
               gameBoard[row + 1][column - 2] == gameBoard[row - 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row + 3][column - 6] != ' ' &&
                   (gameBoard[row + 3][column - 6] == gameBoard[row + 2][column - 4] &&
                    gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2])) {
                return true;
            }
        }
        //Combo of Column 11 and Row 3 is checked above (Line 334)
        else if(column == 13 && (row == 0 || row == 1 || row == 2)) {
            if(gameBoard[row + 3][column - 6] != ' ' &&
              (gameBoard[row + 3][column - 6] == gameBoard[row + 2][column - 4] &&
               gameBoard[row + 2][column - 4] == gameBoard[row + 1][column - 2])) {
                return true;
            }
        }

        //Check for Left Up To Right Down Winning Moves
        if(column == 1 && (row == 0 || row == 1 || row == 2)) {
            if(gameBoard[row + 1][column + 2] != ' ' &&
              (gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4] &&
               gameBoard[row + 2][column + 4] == gameBoard[row + 3][column + 6])) {
                return true;
            }
        }
        else if ((column == 3 || column == 5 || column == 7) && row == 0) {
            if(gameBoard[row + 1][column + 2] != ' ' &&
              (gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4] &&
               gameBoard[row + 2][column + 4] == gameBoard[row + 3][column + 6])) {
                return true;
            }
        }
        else if((column == 3 | column == 5 || column == 7) && row == 1) {
            if(gameBoard[row + 1][column + 2] != ' ' &&
              (gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4] &&
               gameBoard[row + 2][column + 4] == gameBoard[row + 3][column + 6])) {
                return true;
            }
            else if(gameBoard[row - 1][column - 2] != ' ' &&
                   (gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2] &&
                    gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4])) {
                return true;
            }
        }
        else if(column == 3 && row == 2) {
            if(gameBoard[row + 1][column + 2] != ' ' &&
              (gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4] &&
               gameBoard[row + 2][column + 4] == gameBoard[row + 3][column + 6])) {
                return true;
            }
            else if(gameBoard[row - 1][column - 2] != ' ' &&
                   (gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2] &&
                    gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4])) {
                return true;
            }
        }
        else if((column == 3 && row == 3) || (column == 9 && row == 1)) {
            if(gameBoard[row - 1][column - 2] != ' ' &&
              (gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2] &&
               gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4])) {
                return true;
            }
        }
        // Combo of Column 5 and Row 0 is checked above (Line 455)
        // Combo of Column 5 and Row 1 is checked above (Line 462)
        else if((column == 5 || column == 7) && row == 2) {
            if(gameBoard[row + 1][column + 2] != ' ' &&
              (gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4] &&
               gameBoard[row + 2][column + 4] == gameBoard[row + 3][column + 6])) {
                return true;
            }
            else if(gameBoard[row - 1][column - 2] != ' ' &&
                   (gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2] &&
                    gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4])) {
                return true;
            }
            else if(gameBoard[row - 2][column - 4] != ' ' &&
                   (gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2] &&
                    gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2])) {
                return true;
            }
        }
        else if((column == 5 && row == 3) || (column == 9 && row == 2)) {
            if(gameBoard[row - 1][column - 2] != ' ' &&
              (gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2] &&
               gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4])) {
                return true;
            }
            else if(gameBoard[row - 2][column - 4] != ' ' &&
                   (gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2] &&
                    gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2])) {
                return true;
            }
        }
        else if((column == 5 && row == 4) || (column == 11 && row == 2)) {
            if(gameBoard[row - 2][column - 4] != ' ' &&
              (gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2] &&
               gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2])) {
                return true;
            }
        }
        // Combo of Column 7 and Row 0 is checked above (Line 455)
        // Combo of Column 7 and Row 1 is checked above (Line 462)
        // Combo of Column 7 and Row 2 is checked above (Line 495)
        else if((column == 7 || column == 9) && row == 3) {
            if(gameBoard[row - 1][column - 2] != ' ' &&
              (gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2] &&
               gameBoard[row + 1][column + 2] == gameBoard[row + 2][column + 4])) {
                return true;
            }
            else if(gameBoard[row - 2][column - 4] != ' ' &&
                   (gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2] &&
                    gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row - 3][column - 6] != ' ' &&
                   (gameBoard[row - 3][column - 6] == gameBoard[row - 2][column - 4] &&
                    gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2])) {
                return true;
            }
        }
        else if((column == 7 || column == 9 || column == 11) && row == 4) {
            if(gameBoard[row - 2][column - 4] != ' ' &&
              (gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2] &&
               gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row - 3][column - 6] != ' ' &&
                   (gameBoard[row - 3][column - 6] == gameBoard[row - 2][column - 4] &&
                    gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2])) {
                return true;
            }
        }
        else if((column == 7 || column == 9 || column == 11 || column == 13) && row == 5) {
            if(gameBoard[row - 3][column - 6] != ' ' &&
              (gameBoard[row - 3][column - 6] == gameBoard[row - 2][column - 4] &&
               gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2])) {
                return true;
            }
        }
        // Combo of Column 9 and Row 1 is checked above (Line 486)
        // Combo of Column 9 and Row 2 is checked above (Line 512)
        // Combo of Column 9 and Row 3 is checked above (Line 534)
        // Combo of Column 9 and Row 4 is checked above (Line 551)
        // Combo of Column 9 and Row 5 is checked above (Line 563)
        // Combo of Column 11 and Row 2 is checked above (Line 524)
        else if(column == 11 && row == 3) {
            if(gameBoard[row - 2][column - 4] != ' ' &&
              (gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2] &&
               gameBoard[row - 1][column - 2] == gameBoard[row + 1][column + 2])) {
                return true;
            }
            else if(gameBoard[row - 3][column - 6] != ' ' &&
                   (gameBoard[row - 3][column - 6] == gameBoard[row - 2][column - 4] &&
                    gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2])) {
                return true;
            }
        }
        // Combo of Column 11 and Row 4 is checked above (Line 551)
        // Combo of Column 11 and Row 5 is checked above (Line 563)
        else if(column == 13 && (row == 3 || row == 4)) {
            if(gameBoard[row - 3][column - 6] != ' ' &&
              (gameBoard[row - 3][column - 6] == gameBoard[row - 2][column - 4] &&
               gameBoard[row - 2][column - 4] == gameBoard[row - 1][column - 2])) {
                return true;
            }
        }
        // Combo of Column 13 and Row 5 is checked above (Line 563)
        //Everything failed so we return false
        return false;
    }
}