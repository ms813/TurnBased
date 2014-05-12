package Game.Components.ActionComponents;

import Game.Actor;
import Game.Components.MeleeAttacks.AttackFlavour;
import Game.Map.Map;
import Game.Map.MapTile;
import Game.Scene;
import Game.TurnClock;
import Generic.Dice;
import Generic.Vector2I;
import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 23/04/2014.
 */
public class PlayerActionComponent implements ActionComponent {

    private Map map;
    private Scene scene;

    public PlayerActionComponent(Scene scene) {
        this.scene = scene;
        map = scene.getMap();
    }

    public void move(Actor actor, Vector2i dir) {

        Vector2i currentPos = actor.getGridPos();
        Vector2i nextPos = Vector2i.add(currentPos, dir);

        if (nextPos.x < 0 || nextPos.y < 0 || nextPos.x > map.getSize().x - 1 || nextPos.y > map.getSize().y - 1) {
            //player is at the edge of the map

            if (dir.equals(Vector2I.NORTH)
                    || dir.equals(Vector2I.SOUTH)
                    || dir.equals(Vector2I.EAST)
                    || dir.equals(Vector2I.WEST)) {
                scene.changeScene(dir);
            } else {
                //player trying to move diagonally

                if (nextPos.x < 0) {
                    scene.changeScene(Vector2I.WEST);
                } else if (nextPos.y < 0) {
                    scene.changeScene(Vector2I.NORTH);
                } else if (nextPos.x > map.getSize().x) {
                    scene.changeScene(Vector2I.EAST);
                } else if(nextPos.y > map.getSize().y){
                    scene.changeScene(Vector2I.SOUTH);
                }

            }

        } else {
            MapTile tile = map.getTile(nextPos);

            if (tile.getPassable().equals("TRUE")) {
                if (!tile.isOccupied()) {
                    map.getTile(currentPos).setOccupied(false);
                    actor.setGridPos(nextPos);
                    tile.setOccupied(true);
                } else {
                    attack(scene.getActorAtPos(nextPos));
                }
                actor.scheduleTurn();
                TurnClock.nextTurn();
            } else {
                //tile is impassable
            }
        }
    }

    private void attack(Actor actor) {
        int toHit = Dice.roll(1, 10);
        int dmg = 3;

        actor.receiveAttack("Player", toHit, dmg, AttackFlavour.PLAIN);
    }

    public void doTurn(Actor player) {
        //System.out.println("PlayerActionComponent.doTurn");
    }

}
