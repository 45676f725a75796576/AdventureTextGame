package Procedures;

import Code.Bag;
import Items.EmptyBottle;

public class NeighbourhoodProcedure implements Procedure{
    boolean activated = false;
    @Override
    public String initialize(Object object) {
        if(((Bag)object) instanceof Bag && !activated) {
            ((Bag)object).PutItemIntoBag(new EmptyBottle());
            activated = true;
        }
        return null;
    }
}
