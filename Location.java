package Code;

import Procedures.Procedure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Location implements Serializable {
    private String locationID;
    private String name, description;
    private String imageOfLocationPath;
    private ArrayList<String> nextLocations = new ArrayList<>();

    private Procedure procedure = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageOfLocationPath() {
        return imageOfLocationPath;
    }

    public void setImageOfLocationPath(String imageOfLocationPath) {
        this.imageOfLocationPath = imageOfLocationPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addNextLocation(String locationID)
    {
        nextLocations.add(locationID);
    }

    public ArrayList<String> getNextLocations() {
        return nextLocations;
    }

    public String getLocationID(){
        return locationID;
    }
    public void setLocationID(String ID){
        locationID = ID;
    }

    @Override
    public String toString() {
        return name;
    }
    public String nameAndDescription()
    {
        return name + "\n" + description;
    }

    public Procedure getProcedure(){
        return procedure;
    }

    public void setProcedure(Procedure procedure1){
        if(procedure==null)procedure = procedure1;
    }

}
