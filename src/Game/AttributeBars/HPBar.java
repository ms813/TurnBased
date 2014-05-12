package Game.AttributeBars;

import Game.Actor;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 28/04/2014.
 */
public class HPBar extends AttributeBar {

    public HPBar(Actor parent) {
        super(parent);
        color = Color.RED;
        bar.setFillColor(color);
    }

    public void update() {
        float current = parent.getCurrentHP();
        float max = parent.getMaxHP();

        float len = (current / max) * maxLen;
        bar.setSize(new Vector2f(len, thickness));

        Vector2f pos = Vector2f.add(parent.getPixelPos(), new Vector2f(0, parent.getLocalBounds().height));
        bar.setPosition(pos);
        bg.setPosition(pos);
    }
}
