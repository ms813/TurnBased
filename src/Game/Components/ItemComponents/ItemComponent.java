package Game.Components.ItemComponents;

import Game.Items.Item;
import org.jsfml.window.event.KeyEvent;

/**
 * Created by Matthew on 29/04/2014.
 */
public interface ItemComponent {
    void grab(Item item);
    void inventoryAction(KeyEvent e);
    void listInventory();
}
