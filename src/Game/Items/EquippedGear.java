package Game.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 30/04/2014.
 */
public class EquippedGear {

    List<Equippable> equippedItems = new ArrayList<Equippable>();

    public boolean equip(Equippable item){
        Equippable toUnequip = null;
        for(Equippable equippedItem : equippedItems){
            if(equippedItem == item){
                System.out.println("You already have " + equippedItem.getName() + " equipped!");
                return false;
            }
            if(equippedItem.getEquipSlot() == item.getEquipSlot()){
                toUnequip = equippedItem;
            }
        }
        if(toUnequip != null){
            unequip(toUnequip);
        }

        System.out.println("Equipping " + item.getName());
        equippedItems.add(item);
        return true;
    }

    public void unequip(Equippable item){
        System.out.println("Unequipping " + item.getName());
        equippedItems.remove(item);
    }

}
