import java.util.ArrayList;

public class ConcretePiece implements Piece{
    private final ConcretePlayer owner;
    private final ArrayList<Position> positionList = new ArrayList<>();
    public int kills;
    public int squares;
    String pieceName;
    public ConcretePiece (ConcretePlayer own , String name)
    {
        owner = own;
        pieceName = name;

    }
    public void addMove(Position p) {
        positionList.add(p);

        if (positionList.size() > 1) {
            Position lastPosition = positionList.get(positionList.size() - 2);
            int distance = calculateDistance(lastPosition, p);
            squares += distance;
        }
    }

    private int calculateDistance(Position p1, Position p2) {
        int deltaX = Math.abs(p2.getX() - p1.getX());
        int deltaY = Math.abs(p2.getY() - p1.getY());
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public int arraySize()
    {
        return positionList.size();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(pieceName).append(": [");

        for (Position position : positionList) {
            result.append("(").append(position.getX()).append(", ").append(position.getY()).append("), ");
        }

        // Remove the trailing comma and space if the list is not empty
        if (!positionList.isEmpty()) {
            result.setLength(result.length() - 2);
        }

        result.append("]");

        return result.toString();
    }

    public String toStringKills()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pieceName).append(": ").append(kills).append(" kills");
        return stringBuilder.toString();
    }

    public String toStringSquares()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pieceName).append(": ").append(squares).append(" squares");
        return stringBuilder.toString();

    }


    /**
     * Get the player who owns the piece.
     *
     * @return The player who is the owner of this game piece.
     */
    public Player getOwner()
    {
        return owner;
    }

    /**
     * Get a Unicode character representing the type of the game piece.
     * <a href="https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode">...</a>
     *
     * @return A Unicode character representing the type of this game piece
     * (e.g., ♟ for pawn, ♞ for knight, ♜ for rook, etc.).
     */
    public String getType()
    {
        return "nothing";
    }
}
