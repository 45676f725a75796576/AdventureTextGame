package Code;

import Items.Bread;
import Procedures.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePanel extends JPanel {
    private BufferedImage imageOfLocation = null;
    private String locationName, locationDescription, nextLocations;
    private final JButton up,down,left,right,interact;
    private Location currentLocation = null;
    private Game game;

    public GamePanel(Game _game)
    {
        game = _game;

        JFrame frame = new JFrame();
        frame.setBounds(100,100,800,800);
        frame.setVisible(true);
        setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        up = new JButton();
        down = new JButton();
        left = new JButton();
        right = new JButton();
        interact = new JButton();

        // region addActionListener
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setNewLocation(currentLocation.getNextLocations().get(0));
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setNewLocation(currentLocation.getNextLocations().get(2));
            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setNewLocation(currentLocation.getNextLocations().get(3));
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setNewLocation(currentLocation.getNextLocations().get(1).toString());
            }
        });
        interact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentLocation.getProcedure() != null) currentLocation.getProcedure().initialize(game.bag);
                if(currentLocation.getLocationID().equalsIgnoreCase("bedroom") && game.bag.putItemOutOfBag(new Bread()) > 0) setCurrentLocationInfo(game.getLocationByID("victory_location"));
                repaint();
            }
        });
        // endregion

        up.setText("1");
        right.setText("2");
        down.setText("3");
        left.setText("4");
        interact.setText("INTERACT");

        add(up);
        add(right);
        add(down);
        add(left);
        add(interact);

        up.setBounds(0,500, 50,50);
        down.setBounds(60,500, 50,50);
        left.setBounds(120, 500,50,50);
        right.setBounds(180,500,50,50);
        interact.setBounds(240,500,90,50);

        repaint();
        frame.add(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        try {
            g2.drawImage(imageOfLocation, 0, 60, 800, 400, this);
            g2.setColor(Color.WHITE);
            g2.drawString(locationName,0,470);
            g2.drawString(locationDescription,0,490);
            g2.drawString(nextLocations,0,510);
            g2.drawImage(ImageIO.read(new File(game.bag.GetItemOnIndex(0).getItemImagePath())), 20, 700, 32, 32, this);
            g2.drawImage(ImageIO.read(new File(game.bag.GetItemOnIndex(1).getItemImagePath())), 80, 700, 32, 32, this);
            g2.drawImage(ImageIO.read(new File(game.bag.GetItemOnIndex(2).getItemImagePath())), 140, 700, 32, 32, this);
            g2.drawImage(ImageIO.read(new File(game.bag.GetItemOnIndex(3).getItemImagePath())), 200, 700, 32, 32, this);
            g2.drawImage(ImageIO.read(new File(game.bag.GetItemOnIndex(4).getItemImagePath())), 260, 700, 32, 32, this);
            g2.drawImage(ImageIO.read(new File(game.bag.GetItemOnIndex(5).getItemImagePath())), 320, 700, 32, 32, this);
        } catch (Exception e){
            System.out.println("Image not found");
        }
    }

    public void setCurrentLocationInfo(Location location)
    {
        currentLocation = location;
        try {
            imageOfLocation = ImageIO.read(new File(currentLocation.getImageOfLocationPath()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        locationName = location.getName();
        locationDescription = location.getDescription();
        nextLocations = location.getNextLocations().toString();

        up.setVisible(location.getNextLocations().get(0) != null);
        right.setVisible(location.getNextLocations().size() >= 2);
        down.setVisible(location.getNextLocations().size() >= 3);
        left.setVisible(location.getNextLocations().size() >= 4);
        setProcedures();
        repaint();
        updateUI();
    }
    public void setProcedures(){
        switch(currentLocation.getLocationID()){
            case "corridor" -> currentLocation.setProcedure(new CorridorProcedure());
            case "groceries" -> currentLocation.setProcedure(new GroceriesProcedure());
            case "box_with_fruits" -> currentLocation.setProcedure(new BoxWithFruitsProcedure());
            //case "bedroom" -> currentLocation.setProcedure(new BedroomProcedure(this.getGame()));
            case "neighbourhood" -> currentLocation.setProcedure(new NeighbourhoodProcedure());
        }
    }
    public Game getGame(){
        return game;

    }
    public void updateCurrentLocationInfo(){
        setCurrentLocationInfo(game.getCurrentLocation());
    }
}
