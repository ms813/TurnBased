package Game.AttributeBars;

import Game.Actor;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 28/04/2014.
 */
public abstract class AttributeBar {

    protected RectangleShape bar = new RectangleShape();
    protected RectangleShape bg = new RectangleShape();
    protected Color color;

    protected float maxLen;
    protected float thickness = 5f;

    protected Actor parent;

    public AttributeBar(Actor parent) {
        this.parent = parent;
        maxLen = parent.getLocalBounds().width;
        bar.setSize(new Vector2f(maxLen, thickness));
        bg.setOutlineColor(Color.BLACK);
        bg.setOutlineThickness(1f);
        bg.setSize(bar.getSize());
        bg.setFillColor(Color.WHITE);
    }

    public void draw(RenderWindow window) {
        window.draw(bg);
        window.draw(bar);
    }

    public abstract void update();
}
