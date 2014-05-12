package Game.Components.ItemComponents;

import Game.Actor;
import Game.Items.Equippable;
import Game.Items.EquippedGear;
import Game.Items.Item;
import Game.TurnClock;
import org.jsfml.window.event.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew on 29/04/2014.
 */
public class PlayerItemComponent implements ItemComponent {

    private Actor parent;

    private HashMap<Character, Item> inventory= new HashMap<Character, Item>();

    private EquippedGear equippedGear = new EquippedGear();

    private char label = 'A';

    public PlayerItemComponent(Actor parent){
        this.parent = parent;
    }

    public void grab(Item item){
        if(item != null){
            System.out.println("Picking up " + item.getName() + " [PlayerItemComponent.grab]");
            inventory.put(label, item);
            label++;
            parent.scheduleTurn(5);
            TurnClock.nextTurn();
        }
    }

    public void inventoryAction(KeyEvent e){

        //TODO this line needs bug-proofing!
        char key = e.key.toString().toCharArray()[0];

        if(inventory.containsKey(key)){
            Item item = inventory.get(key);
            System.out.println("You pressed " + key + ", selecting " + inventory.get(key).getName()+ " [PlayerItemComponent.inventoryAction]");

            if(item instanceof Equippable){
                if(equippedGear.equip((Equippable) item)){
                    parent.scheduleTurn(5);
                    TurnClock.nextTurn();
                }
            }

        } else {
            System.out.println("You aren't carrying anything labelled " + key + " [PlayerItemComponent.inventoryAction]");
        }
    }

    public void listInventory(){
        for(Map.Entry<Character, Item> entry : inventory.entrySet()){
            char key = entry.getKey();
            String value = entry.getValue().getName();

            System.out.println(key + " – " + value);
        }
    }
}
