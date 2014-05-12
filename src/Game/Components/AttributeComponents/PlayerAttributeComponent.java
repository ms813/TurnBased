package Game.Components.AttributeComponents;

import Game.Actor;
import Game.Components.MeleeAttacks.AttackFlavour;
import Game.Components.MeleeAttacks.MeleeAttack;
import Generic.AttributeLoader;
import Generic.Dice;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 25/04/2014.
 */
public class PlayerAttributeComponent implements AttributeComponent {

    private float maxHP = 10;
    private float currentHP;
    private int speed = 10;
    private int EV = 3;
    private List<MeleeAttack> attacks = new ArrayList<MeleeAttack>();

    private Actor parent;

    public PlayerAttributeComponent(Actor parent, String species) {

        this.parent = parent;

        JSONObject json = AttributeLoader.getPlayerSpeciesAttrJSON(species);

        maxHP *= Float.parseFloat((String) json.get("maxHP"));
        currentHP = maxHP;
    }

    public void receiveAttack(String attackerName, int toHit, int damage, AttackFlavour flavour) {
        if (flavour == AttackFlavour.PLAIN) {
            int dodge = Dice.roll(1, EV);

            if (toHit >= dodge) {
                currentHP -= damage;
                System.out.println("The " + attackerName + " hit you! "+damage +" [PlayerAttributeComponent.receiveAttack]");
            } else{
                System.out.println("The " + attackerName + "'s attack completely missed! [PlayerAttributeComponent.receiveAttack]");
            }
        }

        if (currentHP <= 0) {
            System.out.println("Oh no, you have died! (FATAL) [PlayerAttributeComponent.receiveAttack]");
            System.exit(0);
        } else if (currentHP <= maxHP * 0.2f) {
            System.out.println("LOW HP WARNING! [PlayerAttributeComponent.receiveAttack]");
        }
    }

    public int getSpeed() {
        return speed;
    }

    public List<MeleeAttack> getAttacks() {
        return attacks;
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public float getMaxHP() {
        return maxHP;
    }
}
