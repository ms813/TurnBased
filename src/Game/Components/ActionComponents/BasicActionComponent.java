package Game.Components.ActionComponents;

import Game.Actor;
import Game.Components.MeleeAttacks.MeleeAttack;
import Game.Map.Map;
import Game.Map.MapTile;
import Game.Scene;
import Generic.Dice;
import Generic.Vector2I;
import org.jsfml.system.Vector2i;

import java.util.List;

/**
 * Created by Matthew on 25/04/2014.
 */
public class BasicActionComponent implements ActionComponent {

    /*
     * Basic action behaviour is:
     *     - Walk straight towards the player
     *     - MeleeAttack the player in melee
     */

    private Map map;
    private Scene scene;

    private Actor parent;

    public BasicActionComponent(Actor parent, Scene scene) {
        this.scene = scene;
        map = scene.getMap();

        this.parent = parent;
    }

    public void move(Actor actor, Vector2i dir) {

        Vector2i currentPos = actor.getGridPos();
        Vector2i nextPos = Vector2i.add(currentPos, dir);

        MapTile tile = map.getTile(nextPos);

        if (tile.getPassable().equals("TRUE")) {
            if (!tile.isOccupied()) {
                map.getTile(currentPos).setOccupied(false);
                actor.setGridPos(nextPos);
                System.out.println(actor.getName() + " moved to " + nextPos);
                tile.setOccupied(true);
            } else {
                Actor actorAtNextPos = scene.getActorAtPos(nextPos);
                if (actorAtNextPos.getName().equals("Player")) {
                    attack(actorAtNextPos);
                } else {
                   //actor is blocked by another monster
                }
            }
        } else {
            //tile is impassable
        }
        actor.scheduleTurn();
        //System.out.println("TRACE scehduling " + parent.getName());
    }

    public void doTurn(Actor actor) {
        Vector2i playerDir = Vector2i.sub(scene.getPlayer().getGridPos(), actor.getGridPos());
        move(actor, Vector2I.unit(playerDir));
    }

    private void attack(Actor actor){
        List<MeleeAttack> attacks = parent.getAttacks();
        for(MeleeAttack a : attacks){
            System.out.println(parent.getName() + " attacked " + actor.getName() + "! [BasicActionComponent.attack]");

            int toHit = Dice.roll(1, a.getToHit());
            int dmg = Dice.roll(1, a.getDmg());

            //actor.receiveAttack(parent.getName(), toHit, dmg, a.getFlavour());
        }
    }
}
