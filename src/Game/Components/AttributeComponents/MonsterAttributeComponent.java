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
public class MonsterAttributeComponent implements AttributeComponent {

    private Actor parentActor;
    private float maxHP;
    private float currentHP;
    private int AC;
    private int EV;
    private List<MeleeAttack> attacks = new ArrayList<MeleeAttack>();
    private int speed;

    public MonsterAttributeComponent(Actor monster) {
        this.parentActor = monster;
        unpackAttributes(monster.getName());
    }

    public void receiveAttack(String attackerName, int toHit, int damage, AttackFlavour flavour) {

        if (flavour == AttackFlavour.PLAIN) {

            int dodge = Dice.roll(1, EV);

            if(toHit >= dodge){
                currentHP -= damage;
                System.out.println(parentActor.getName() + " received " + damage + " " + flavour + " damage!  [MonsterAttributeComponent.receiveAttack]");
                System.out.println(parentActor.getName() + " has " + currentHP + " hp remaining.  [MonsterAttributeComponent.receiveAttack]");
            } else{
                System.out.println("The " + attackerName + "'s attack completely missed! [MonsterAttributeComponent.receiveAttack]");
            }
        }

        if (currentHP <= 0) {
            parentActor.onDeath();
        }
    }

    public void unpackAttributes(String monsterName) {
        JSONObject json = AttributeLoader.getMonsterAttrJSON(monsterName);
        maxHP = Float.parseFloat((String) json.get("maxHP"));
        currentHP = maxHP;
        AC = Integer.parseInt((String) json.get("AC"));
        EV = Integer.parseInt((String) json.get("EV"));

        JSONObject attack1JSON = (JSONObject) json.get("attack_1");
        attacks.add(
                new MeleeAttack(
                        Integer.parseInt((String) attack1JSON.get("toHit")),
                        Integer.parseInt((String) attack1JSON.get("dmg")),
                        (String) attack1JSON.get("flavour")
                )
        );

        JSONObject attack2JSON = (JSONObject) json.get("attack_1");
        if (attack2JSON != null) {
            attacks.add(
                    new MeleeAttack(
                            Integer.parseInt((String) attack2JSON.get("toHit")),
                            Integer.parseInt((String) attack2JSON.get("dmg")),
                            (String) attack1JSON.get("flavour")
                    )
            );
        }

        speed = Integer.parseInt((String) json.get("speed"));
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

