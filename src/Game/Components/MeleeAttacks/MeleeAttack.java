package Game.Components.MeleeAttacks;

/**
 * Created by Matthew on 25/04/2014.
 */
public class MeleeAttack {
    public int toHit;
    public int dmg;
    public AttackFlavour flavour;

    public MeleeAttack(int toHit, int dmg, String flavour){
        this.toHit = toHit;
        this.dmg = dmg;
        this.flavour = AttackFlavour.valueOf(flavour);
    }

    public int getToHit() {
        return toHit;
    }

    public int getDmg() {
        return dmg;
    }

    public AttackFlavour getFlavour() {
        return flavour;
    }
}
