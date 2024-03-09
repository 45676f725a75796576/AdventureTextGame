package Code;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class LocationReadWrite {
    // dont use it pls
    public static void Write(Location location)
    {
        try {
            PrintStream p = new PrintStream("src/Locations/" + location.getName() + ".txt");
            p.println("locationID=" + location.getLocationID());
            p.println("name=" + location.getName());
            p.println("description=" + location.getDescription());
            p.println("imageOfLocationPath=" + location.getImageOfLocationPath());
            p.println("nextLocations=" + location.getNextLocations());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Location Read(String locationPath){
        Location result = new Location();
        try{
            File l = new File(locationPath);
            Scanner sc = new Scanner(l);
            String[] s;
            while(sc.hasNextLine()){
                s = sc.nextLine().split("=");
                if(s[0].equalsIgnoreCase("name")) result.setName(s[1]);
                else if(s[0].equalsIgnoreCase("locationID")) result.setLocationID(s[1]);
                else if(s[0].equalsIgnoreCase("imageOfLocationPath")) result.setImageOfLocationPath(s[1]);
                else if(s[0].equalsIgnoreCase("description")) result.setDescription(s[1]);
                else if(s[0].equalsIgnoreCase("nextLocations")) {
                    s = s[1].split(",");
                    for (int i = 0; i < s.length; i++) {
                        result.addNextLocation(s[i]);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
