package Game.Items;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 29/04/2014.
 */
public interface Item{
    void draw(RenderWindow window);
    void update();
    void setGridPos(Vector2i gridPos);
    Vector2i getGridPos();
    String getName();
}
