package Game.Input;

import Game.Actor;
import Generic.Vector2I;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

/**
 * Created by Matthew on 23/04/2014.
 */
public class InputHandler {

    private Actor player;
    private InputContext context = InputContext.DEFAULT;

    public InputHandler(Actor player) {
        this.player = player;
    }

    public void processEvent(Event event) {


        if (event.type == Event.Type.KEY_PRESSED) {
            KeyEvent e = event.asKeyEvent();
            if (context == InputContext.DEFAULT) {
                /*************************************
                 Movement
                 */
                if (e.key == Keyboard.Key.NUMPAD4) {
                    player.move(Vector2I.WEST);
                }
                if (e.key == Keyboard.Key.NUMPAD6) {
                    player.move(Vector2I.EAST);
                }
                if (e.key == Keyboard.Key.NUMPAD8) {
                    player.move(Vector2I.NORTH);
                }
                if (e.key == Keyboard.Key.NUMPAD2) {
                    player.move(Vector2I.SOUTH);
                }

                if (e.key == Keyboard.Key.NUMPAD7) {
                    player.move(Vector2I.NW);
                }
                if (e.key == Keyboard.Key.NUMPAD9) {
                    player.move(Vector2I.NE);
                }
                if (e.key == Keyboard.Key.NUMPAD1) {
                    player.move(Vector2I.SW);
                }
                if (e.key == Keyboard.Key.NUMPAD3) {
                    player.move(Vector2I.SE);
                }
                /**********************************/


                if (e.key == Keyboard.Key.G) {
                    player.grab();
                }
                if (e.key == Keyboard.Key.I) {
                    context = InputContext.INVENTORY;
                    System.out.println("Looking in your inventory...");
                    player.listInventory();
                    return;

                }
            }

            if (context == InputContext.INVENTORY) {
                player.inventoryAction(e);
                context = InputContext.DEFAULT;
            }
        }

    }
}
