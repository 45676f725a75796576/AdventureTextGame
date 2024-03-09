package Procedures;

import Code.Bag;
import Items.Bread;
import Items.Money;

public class GroceriesProcedure implements Procedure {

    @Override
    public String initialize(Object object) {
        if(object instanceof Bag){
            if(((Bag) object).putItemOutOfBag(new Money()) > 0) ((Bag) object).PutItemIntoBag(new Bread());
        }
        return null;
    }
}
