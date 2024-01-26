public class King extends ConcretePiece
{
    public King(ConcretePlayer own,String name)
    {
        super(own,name);
    }

    public String getType()
    {
        return "♔";
    }
}
