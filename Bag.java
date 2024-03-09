package Code;

import Items.Air;
import Items.Item;

public class Bag {
    Item[] items;
    public Bag(){
        items = new Item[]{new Air(), new Air(), new Air(), new Air(), new Air(), new Air()};
    }
    public void setItemOnIndex(int index, Item newItem){
        items[index] = newItem;
    }
    public Item GetItemOnIndex(int index){
        return items[index];
    }
    public int PutItemIntoBag(Item item){
        boolean b = false;
        for (int i = 0; i < items.length; i++) {
            if(items[i] instanceof Air || items[i] == null){
                b = true;
                items[i] = item;
                return 1;
            }
        }
        return -1;
    }
    public int putItemOutOfBag(Item _typeof){
        for (int i = 0; i < items.length; i++) {
            if (items[i].getItemImagePath().equalsIgnoreCase(_typeof.getItemImagePath())){
                items[i] = new Air();
                return 1;
            }
        }
        return -1;
    }
}
