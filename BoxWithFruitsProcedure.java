package Procedures;

import Code.Bag;
import Items.Peach;

public class BoxWithFruitsProcedure implements Procedure{
    private boolean activated = false;
    @Override
    public String initialize(Object object) {
        if(object instanceof Bag && !activated){
            ((Bag) object).PutItemIntoBag(new Peach());
            activated = true;
        }
        return null;
    }
}
