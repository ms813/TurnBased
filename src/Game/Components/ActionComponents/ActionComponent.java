package Game.Components.ActionComponents;

import Game.Actor;
import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 23/04/2014.
 */
public interface ActionComponent {
    void move(Actor actor, Vector2i dir);
    void doTurn(Actor actor);
}
