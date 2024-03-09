package Code;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private HashMap<String, Location> locations;
    private GamePanel panel;
    String currentLocationID = "bedroom";
    public Bag bag;

    public Game() {
        bag = new Bag();
        locations = new HashMap<>();
        try{
            Scanner sc = new Scanner(new File("src/LocationsConfig"));
            while(sc.hasNextLine()){
                Location l = LocationReadWrite.Read(sc.nextLine());
                locations.put(l.getLocationID(), l);
                System.out.println(l.nameAndDescription() + " & \n" + l.getImageOfLocationPath() + " & \n" + l.getLocationID() + " & \n" + l.getNextLocations());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        panel = new GamePanel(this);
        TestGame();

    }
    public void TestGame() {
        panel.setCurrentLocationInfo(locations.get(currentLocationID));
    }

    public void setNewLocation(String locationID) {
        Location currentLocation = locations.get(currentLocationID);
        if(currentLocation.getNextLocations().contains(locationID)) {
            currentLocationID = locationID;
            TestGame();
        }
    }
    public Location getCurrentLocation(){
        return locations.get(currentLocationID);
    }
    public Location getLocationByID(String ID){
        return locations.get(ID);
    }
    public GamePanel getPanel(){
        return panel;
    }
}