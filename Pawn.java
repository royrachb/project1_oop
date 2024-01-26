public class Pawn extends ConcretePiece{


    public Pawn(ConcretePlayer own, String name) {
        super(own,name);
    }
    public String getType()
    {
        return "♟";
    }
}
