import java.util.*;

public class GameLogic implements PlayableLogic
{
    ArrayList<ConcretePiece> PieceList = new ArrayList<>();
    ArrayList<ConcretePiece> PieceList1 = new ArrayList<>();

    Set<String>[][] positionTable = new Set[11][11];
    ArrayList<String> positionList = new ArrayList<>();

    GameLogic()
    {
        reset();
    }
    int win = 0;
    int whoTurn= 2;
    boolean gameOver = false;
    ConcretePiece[][] board = new ConcretePiece [11][11];


    ConcretePlayer player1 = new ConcretePlayer(1);
    ConcretePlayer player2 = new ConcretePlayer(2);

    private boolean checkWall(int x , int y)
    {
        if (x == 0 && y == 0)
        {
            return true;
        }
        if (x == 0 && y == 10)
        {
            return true;
        }
        if (x == 10 && y == 0)
        {
            return true;
        }
        if (x == 10 && y== 10)
        {
            return true;
        }
        return x > 10 || x < 0 || y > 10 || y < 0;
    }
    private boolean checkWall1(int x , int y)
    {
        return x > 10 || x < 0 || y > 10 || y < 0;
    }


    private void checkEat(Position p)
    {
        int px = p.getX();
        int py = p.getY();
        boolean player1Turn = whoTurn == 1;
        if (!checkWall(px,py-1))
        {
            if (board[py-1][px] != null)
            {
                if (board[py-1][px].getOwner().isPlayerOne()!=player1Turn)
                {
                    if (Objects.equals(board[py - 1][px].getType(), "♔"))
                    {
                        kingHandle(py-1,px);
                    }
                    else {
                        if (checkWall(px, py - 2)) {
                            board[py - 1][px] = null;
                            board[py][px].kills++;

                        } else if (board[py - 2][px] != null) {
                            if (board[py - 2][px].getOwner().isPlayerOne() == player1Turn && !Objects.equals(board[py - 2][px].getType(), "♔")) {
                                board[py][px].kills++;
                                board[py - 1][px] = null;
                            }
                        }
                    }
                }
            }
        }
        if (!checkWall(px,py+1))
        {
            if (board[py+1][px] != null)
            {
                if (board[py+1][px].getOwner().isPlayerOne()!=player1Turn)
                {
                    if (Objects.equals(board[py+1][px].getType(), "♔"))
                    {
                        kingHandle(py+1,px);
                    }
                    else {
                        if (checkWall(px, py + 2)) {
                            board[py][px].kills++;
                            board[py + 1][px] = null;
                        } else if (board[py + 2][px] != null) {
                            if (board[py + 2][px].getOwner().isPlayerOne() == player1Turn && !Objects.equals(board[py + 2][px].getType(), "♔")) {
                                board[py][px].kills++;
                                board[py + 1][px] = null;
                            }
                        }
                    }
                }
            }
        }
        if (!checkWall(px+1,py))
        {
            if (board[py][px+1] != null)
            {
                if (board[py][px+1].getOwner().isPlayerOne()!=player1Turn)
                {
                    if (Objects.equals(board[py][px+1].getType(), "♔"))
                    {
                        kingHandle(py,px+1);
                    }
                    else {
                        if (checkWall(px + 2, py)) {
                            board[py][px].kills++;
                            board[py][px + 1] = null;
                        } else if (board[py][px + 2] != null) {
                            if (board[py][px + 2].getOwner().isPlayerOne() == player1Turn && !Objects.equals(board[py][px+2].getType(), "♔")) {
                                board[py][px].kills++;
                                board[py][px + 1] = null;
                            }
                        }
                    }
                }
            }
        }
        if (!checkWall(px-1,py))
        {
            if (board[py][px-1] != null)
            {
                if (board[py][px-1].getOwner().isPlayerOne()!=player1Turn)
                {
                    if (Objects.equals(board[py][px-1].getType(), "♔"))
                    {
                        kingHandle(py,px-1);
                    }
                    else {
                        if (checkWall(px - 2, py)) {
                            board[py][px].kills++;
                            board[py][px - 1] = null;
                        } else if (board[py][px - 2] != null) {
                            if (board[py][px - 2].getOwner().isPlayerOne() == player1Turn && !Objects.equals(board[py][px-2].getType(), "♔")) {
                                board[py][px].kills++;
                                board[py][px - 1] = null;
                            }
                        }
                    }
                }
            }
        }

    }

    public void kingHandle(int py, int px)
    {
        boolean side1 = false;
        boolean side2 = false;
        boolean side3 = false;
        boolean side4 = false;

        if(checkWall1(px+1 ,py))
        {
            side1 = true;
        }
        else if (board[py][px+1] != null)
        {
            if (!board[py][px+1].getOwner().isPlayerOne())
            {
                side1 = true;
            }
        }
        if(checkWall1(px-1 ,py))
        {
            side2 = true;
        }
        else if (board[py][px-1] != null)
        {
            if (!board[py][px-1].getOwner().isPlayerOne())
            {
                side2 = true;
            }
        }
        if(checkWall1(px ,py+1))
        {
            side3 = true;
        }
        else if (board[py+1][px] != null)
        {
            if (!board[py+1][px].getOwner().isPlayerOne())
            {
                side3 = true;
            }
        }
        if(checkWall1(px ,py-1))
        {
            side4 = true;
        }
        else if (board[py-1][px] != null)
        {
            if (!board[py-1][px].getOwner().isPlayerOne())
            {
                side4 = true;
            }
        }
        if(side1 && side2 && side3 && side4)
        {
            player2.addWin();
            printStats();
            win = 2;
            gameOver = true;
        }




    }



    private boolean checkObs(Position a, Position b)
    {

        int x = a.getX();
        int y = a.getY();


                while (x < b.getX()) {
                    x++;
                    if (board[y][x] != null) {

                        return false;
                    }
                }


                while (x > b.getX()) {
                    x--;
                    if (board[y][x] != null) {

                        return false;
                    }
                }




                while (y < b.getY()) {
                    y++;
                    if (board[y][x] != null) {

                        return false;
                    }
                }


                while (y > b.getY()) {
                    y--;
                    if (board[y][x] != null) {

                        return false;
                    }
                }


        return true;
    }



    public boolean move(Position a, Position b)
    {

        boolean t1 = whoTurn == 1;
        boolean t2 = whoTurn == 2;
        if (board[a.getY()][a.getX()]==null)
        {
            return false;
        }
        boolean isKing = Objects.equals(board[a.getY()][a.getX()].getType(), "♔");

        if (!isKing && checkWall(b.getX(),b.getY()))
        {
            return false;
        }
        if (board[b.getY()][b.getX()] == null)
        {
            if (a.getY() == b.getY() || a.getX() == b.getX()) {

                    if ((board[a.getY()][a.getX()]).getOwner().isPlayerOne() && t1) {
                        if (checkObs(a, b)) {

                            board[b.getY()][b.getX()] = board[a.getY()][a.getX()];
                            board[a.getY()][a.getX()] = null;
                            if (board[b.getY()][b.getX()].arraySize() == 0)
                            {
                                board[b.getY()][b.getX()].addMove(a);
                            }
                            positionTable[b.getY()][b.getX()].add(board[b.getY()][b.getX()].pieceName);
                            board[b.getY()][b.getX()].addMove(b);
                            if(!isKing)
                            {
                                checkEat(b);
                            }
                            else
                            {
                                if(checkWall(b.getX(),b.getY()))
                                {
                                    player1.addWin();
                                    printStats();
                                    gameOver = true;
                                    win = 1;
                                    return true;
                                }

                            }

                            whoTurn = 2;
                            return true;
                        }
                    }


                    if (!((board[a.getY()][a.getX()]).getOwner().isPlayerOne()) && t2) {

                        if (checkObs(a, b)) {
                            board[b.getY()][b.getX()] = board[a.getY()][a.getX()];
                            board[a.getY()][a.getX()] = null;
                            if (board[b.getY()][b.getX()].arraySize() == 0)
                            {
                                board[b.getY()][b.getX()].addMove(a);
                            }
                            positionTable[b.getY()][b.getX()].add(board[b.getY()][b.getX()].pieceName);
                            board[b.getY()][b.getX()].addMove(b);
                            checkEat(b);

                            whoTurn = 1;
                            return true;
                        }

                    }

            }
        }

        return false;
    }

    /**
     * Get the piece located at a given position on the game board.
     *
     * @param position The position for which to retrieve the piece.
     * @return The piece at the specified position, or null if no piece is present.
     */

    public Piece getPieceAtPosition(Position position)
    {

        return board[position.getY()][position.getX()];
    }
    /**
     * Get the first player.
     *
     * @return The first player.
     */
    public Player getFirstPlayer()
    {
        return player1;
    }

    /**
     * Get the second player.
     *
     * @return The second player.
     */
    public Player getSecondPlayer()
    {
        return player2;
    }

    /**
     * Check if the game has finished, indicating whether a player has won or if it's a draw.
     *
     * @return true if the game has finished, false otherwise.
     */
    public void printStats()
    {
        int rows = 11;
        int cols = 11;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j]!= null)
                    {
                        if (board[i][j].getOwner().isPlayerOne())
                        {
                            if (board[i][j].arraySize()>0)
                            {
                                PieceList.add(board[i][j]);
                            }
                        }
                        else
                        {
                            if (board[i][j].arraySize()>0)
                            {
                                PieceList1.add(board[i][j]);
                            }

                        }

                    }
                }
            }
            comparator mC = new comparator(comparator.ComparisonType.StepsHistory, win);
            PieceList.sort(mC);
            PieceList1.sort(mC);
            if (win == 1)
            {
                for (ConcretePiece concretePiece : PieceList) {
                    System.out.println(concretePiece.toString());
                }
                for (ConcretePiece concretePiece : PieceList1) {
                    System.out.println(concretePiece.toString());
                }
            }
            else {
                for (ConcretePiece concretePiece : PieceList1) {
                    System.out.println(concretePiece.toString());
                }
                for (ConcretePiece concretePiece : PieceList) {
                    System.out.println(concretePiece.toString());
                }

            }
            System.out.println("***************************************************************************");
            PieceList = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j]!= null)
                    {
                        if (board[i][j].kills>0)
                        {
                            PieceList.add(board[i][j]);
                        }
                    }
                }
            }
            comparator mc1 = new comparator(comparator.ComparisonType.NumOfKills);
            PieceList.sort(mc1);
            for (ConcretePiece concretePiece : PieceList) {
                System.out.println(concretePiece.toStringKills());
            }
            System.out.println("***************************************************************************");
            PieceList = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j]!= null)
                    {
                        if (board[i][j].arraySize()>0)
                        {
                            PieceList.add(board[i][j]);
                        }
                    }
                }
            }
            comparator mc2 = new comparator(comparator.ComparisonType.Squares,win);
            PieceList.sort(mc2);
            for (ConcretePiece concretePiece : PieceList) {
                System.out.println(concretePiece.toStringSquares());
            }
            System.out.println("***************************************************************************");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    if (positionTable[i][j].size()>1)
                    {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(i).append(":").append(j).append(":").append(positionTable[i][j].size());
                        String result = stringBuilder.toString();
                        positionList.add(result);
                    }

                }
            }
            CustomComparator mc3 = new CustomComparator();
            positionList.sort(mc3);
            for (String str : positionList)
            {
                String[] arrOfStr = str.split(":", 3);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(").append(arrOfStr[1]).append(", ").append(arrOfStr[0]).append(")").append(arrOfStr[2]).append(" pieces");
                String result = stringBuilder.toString();

                System.out.println(result);
            }
            System.out.println("***************************************************************************");

    }

    public boolean isGameFinished()
    {

        return gameOver;
    }

    public static class CustomComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            String[] arrOfStr = str1.split(":", 3);
            String[] arrOfStr1 = str2.split(":", 3);
            int size1 = Integer.parseInt(arrOfStr[2]);
            int size2 = Integer.parseInt(arrOfStr1[2]);

            // Compare based on the size of the position ArrayList
            int sizeComparison = Integer.compare(size2, size1);

            // If sizes are equal, compare by number
            if (sizeComparison == 0) {
                size1 = Integer.parseInt(arrOfStr[1]);
                size2 = Integer.parseInt(arrOfStr1[1]);
                sizeComparison = Integer.compare(size1, size2);

                if (sizeComparison == 0) {
                    size1 = Integer.parseInt(arrOfStr[0]);
                    size2 = Integer.parseInt(arrOfStr1[0]);
                    sizeComparison = Integer.compare(size1, size2);
                }
            }
            return sizeComparison;
        }
    }
    /**
     * Check if it is currently the second player's turn.
     *
     * @return true if it's the second player's turn, false if it's the first player's turn.
     */
    public boolean isSecondPlayerTurn()
    {
        return whoTurn==2;
    }
    /**
     * Reset the game to its initial state, clearing the board and player information.
     */
    public void reset()
    {

        PieceList = new ArrayList<>();
        whoTurn= 2;
        gameOver = false;
        board = new ConcretePiece [11][11];

        board [0][3] = new Pawn(player2,"A1");
        board [0][4] = new Pawn(player2,"A2");
        board [0][5] = new Pawn(player2,"A3");
        board [0][6] = new Pawn(player2,"A4");
        board [0][7] = new Pawn(player2,"A5");

        board [1][5] = new Pawn(player2,"A6");

        board [3][0] = new Pawn(player2,"A7");
        board [3][5] = new Pawn(player1,"D1");
        board [3][10] = new Pawn(player2,"A8");

        board [4][0] = new Pawn(player2,"A9");
        board [4][4] = new Pawn(player1,"D2");
        board [4][5] = new Pawn(player1,"D3");
        board [4][6] = new Pawn(player1,"D4");
        board [4][10] = new Pawn(player2,"A10");

        board [5][0] = new Pawn(player2,"A11");
        board [5][1] = new Pawn(player2,"A12");
        board [5][3] = new Pawn(player1,"D5");
        board [5][4] = new Pawn(player1,"D6");
        board [5][5] = new King(player1,"K7");
        board [5][6] = new Pawn(player1,"D8");
        board [5][7] = new Pawn(player1,"D9");
        board [5][9] = new Pawn(player2,"A13");
        board [5][10] = new Pawn(player2,"A14");

        board [6][0] = new Pawn(player2,"A15");
        board [6][4] = new Pawn(player1,"D10");
        board [6][5] = new Pawn(player1,"D11");
        board [6][6] = new Pawn(player1,"D12");
        board [6][10] = new Pawn(player2,"A16");

        board [7][0] = new Pawn(player2,"A17");
        board [7][5] = new Pawn(player1,"D13");
        board [7][10] = new Pawn(player2,"A18");

        board [9][5] = new Pawn(player2,"A19");

        board [10][3] = new Pawn(player2,"A20");
        board [10][4] = new Pawn(player2,"A21");
        board [10][5] = new Pawn(player2,"A22");
        board [10][6] = new Pawn(player2,"A23");
        board [10][7] = new Pawn(player2,"A24");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                positionTable[i][j] = new HashSet<>();
                if (board[i][j]!= null)
                {
                    positionTable[i][j].add(board[i][j].pieceName);

                }

            }
        }
    }

    /**
     * Undo the last move made in the game, reverting the board state and turn order.
     */
    public void undoLastMove()
    {

    }

    /**
     * Get the size of the game board.
     *
     * @return The size of the game board, typically as the number of rows or columns.
     */
    public int getBoardSize()
    {
        return 11;
    }
}
