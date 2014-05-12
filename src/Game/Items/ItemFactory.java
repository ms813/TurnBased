package Game.Items;

/**
 * Created by Matthew on 29/04/2014.
 */
public class ItemFactory {
    public Item buildItem(String category, String name){

        if(category.equals("meleeWeapon")){
            return new MeleeWeapon(name);
        } else if(category.equals("armour")){
            return new Armour(name);
        }
        System.err.println("Weapon category" + category + " does not exist!");
        return null;
    }
}
