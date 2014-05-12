package Game.Map;

import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 22/04/2014.
 */
public class MapTile {
    private String type;
    private Vector2i textureGridPos;
    private String passable;
    private boolean occupied = false;

    public MapTile(String[] detailsFromFile){
        this.type = detailsFromFile[0];
        this.textureGridPos = new Vector2i(Integer.parseInt(detailsFromFile[1]), Integer.parseInt(detailsFromFile[2]));
        this.passable = detailsFromFile[3];
    }

    public Vector2i getTextureGridPos(){
        return textureGridPos;
    }

    public String getPassable(){
        return passable;
    }

    public String getType(){
        return type;
    }

    public boolean isOccupied(){return occupied;}

    public void setOccupied(boolean occupied){
        this.occupied = occupied;
    }
}
