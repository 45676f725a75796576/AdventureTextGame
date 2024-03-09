package Procedures;

import Code.Bag;
import Items.Money;

public class CorridorProcedure implements Procedure{
    private boolean activated = false;
    @Override
    public String initialize(Object object) {
        if(object instanceof Bag && !activated){
            ((Bag) object).PutItemIntoBag(new Money());
            activated = true;
        }
        return null;
    }
}
