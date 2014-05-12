package Generic;

import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 23/04/2014.
 */
public class Vector2I {

    public static final Vector2i WEST = new Vector2i(-1, 0);
    public static final Vector2i EAST = new Vector2i(1, 0);
    public static final Vector2i NORTH = new Vector2i(0, -1);
    public static final Vector2i SOUTH = new Vector2i(0, 1);

    public static final Vector2i NE = Vector2i.add(NORTH, EAST);
    public static final Vector2i NW = Vector2i.add(NORTH, WEST);
    public static final Vector2i SE = Vector2i.add(SOUTH, EAST);
    public static final Vector2i SW = Vector2i.add(SOUTH, WEST);


    public static strictfp Vector2i unit(Vector2i in){
        int x = 0;
        int y = 0;

        if(in.x != 0){
            x = in.x / Math.abs(in.x);
        }

        if(in.y != 0){
            y = in.y / Math.abs(in.y);
        }

        return new Vector2i(x, y);
    }
}
