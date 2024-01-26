import java.util.Comparator;
public class comparator implements Comparator<ConcretePiece>{


    private final ComparisonType comparisonType;
    int win;

    public comparator(ComparisonType comparisonType, int whoWin) {
        this.comparisonType = comparisonType;
        this.win = whoWin;
    }
    public comparator(ComparisonType comparisonType) {
        this.comparisonType = comparisonType;
    }




    public enum ComparisonType {
        StepsHistory,
        NumOfKills,
        Squares,
    }


    @Override
    public int compare(ConcretePiece piece1, ConcretePiece piece2) {
        return switch (comparisonType) {
            case StepsHistory -> compareByStepsHistory( piece1, piece2);
            case NumOfKills -> compareByNumOfKills(piece1, piece2);
            case Squares -> compareByNumOfSquares(piece1, piece2);

            default -> throw new IllegalArgumentException("Invalid comparison type");
        };
    }



    private int compareByNumOfSquares(ConcretePiece piece1, ConcretePiece piece2) {
        int size1 = piece1.squares;
        int size2 = piece2.squares;

        int sizeComparison = Integer.compare(size2, size1);

        // If sizes are equal, compare by number
        if (sizeComparison == 0) {
            int num1 = piece1 instanceof Pawn ? Integer.parseInt(((Pawn) piece1).pieceName.substring(1)) : Integer.parseInt(((King) piece1).pieceName.substring(1)) ;
            int num2 = piece2 instanceof Pawn ? Integer.parseInt(((Pawn) piece2).pieceName.substring(1)) : Integer.parseInt(((King) piece2).pieceName.substring(1));
            sizeComparison = Integer.compare(num1, num2);
            if (sizeComparison == 0)
            {
                if(piece1.getOwner().isPlayerOne()) {
                    if(win == 1){
                        return -1;
                    } else{
                        return 1;
                    }
                }else{
                    if(win == 1){
                        return 1;
                    } else{
                        return -1;
                    }
                }
            }
        }

        return sizeComparison;
    }

    private int compareByNumOfKills(ConcretePiece piece1, ConcretePiece piece2)
    {
        int size1 = piece1.kills;
        int size2 = piece2.kills;

        int sizeComparison = Integer.compare(size2, size1);

        // If sizes are equal, compare by number
        if (sizeComparison == 0) {
            int num1 = piece1 instanceof Pawn ? Integer.parseInt(((Pawn) piece1).pieceName.substring(1)) : Integer.parseInt(((King) piece1).pieceName.substring(1)) ;
            int num2 = piece2 instanceof Pawn ? Integer.parseInt(((Pawn) piece2).pieceName.substring(1)) : Integer.parseInt(((King) piece2).pieceName.substring(1));
            sizeComparison = Integer.compare(num1, num2);
            if (sizeComparison == 0)
            {
                if(piece1.getOwner().isPlayerOne()) {
                    if(win == 1){
                        return -1;
                    } else{
                        return 1;
                    }
                }else{
                    if(win == 1){
                        return 1;
                    } else{
                        return -1;
                    }
                }
            }
        }

        return sizeComparison;
    }

    public int compareByStepsHistory(ConcretePiece piece1, ConcretePiece piece2) {
        int size1 = piece1.arraySize();
        int size2 = piece2.arraySize();

        // Compare based on the size of the position ArrayList
        int sizeComparison = Integer.compare(size1, size2);

        // If sizes are equal, compare by number
        if (sizeComparison == 0) {
            int num1 = Integer.parseInt (piece1.pieceName.substring(1));
            int num2 = Integer.parseInt (piece2.pieceName.substring(1));

            return Integer.compare(num1, num2);
        }
        return sizeComparison;
    }



}
