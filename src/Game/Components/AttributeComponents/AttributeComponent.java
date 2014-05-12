package Game.Components.AttributeComponents;

import Game.Components.MeleeAttacks.AttackFlavour;
import Game.Components.MeleeAttacks.MeleeAttack;

import java.util.List;

/**
 * Created by Matthew on 25/04/2014.
 */
public interface AttributeComponent {
    void receiveAttack(String attackerName, int toHit, int damage, AttackFlavour flavour);
    int getSpeed();
    List<MeleeAttack> getAttacks();
    float getCurrentHP();
    float getMaxHP();
}
