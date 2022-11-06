import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class BlusterCritter extends Critter {
    private int courageFactor;

    public BlusterCritter(int c){
        super();
        courageFactor = c;
    }

    public ArrayList<Actor> getActors(){
        ArrayList<Actor> actors = new ArrayList<>();

        Location loc = getLocation();
        for(int r =loc.getRow()-2;r <= loc.getRow()+2; r++){
            for(int c=loc.getCol()-2;c <= loc.getCol()+2; c++){
                Location temLoc = new Location(r,c);
                if(getGrid().isValid(temLoc)){
                    Actor a = getGrid().get(temLoc);
                    if(a != null && a != this)
                        actors.add(a);
                }
            }
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors){
        int count = 0;
        for(Actor a : actors)
            if(a instanceof Critter)
                count++;
        if (count < courageFactor)
            lighten();
        else
            darken();
    }

    private void darken(){
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if(red > 0)
            red--;
        if(green > 0)
            green--;
        if(blue > 0)
            blue--;

        setColor(new Color(red,green,blue));
    }

    private void lighten(){
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if(red < 255)
            red++;
        if(green < 255)
            green++;
        if(blue < 255)
            blue++;

        setColor(new Color(red,green,blue));
    }

}
