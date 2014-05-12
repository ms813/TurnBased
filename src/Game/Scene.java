package Game;

import Game.Items.Item;
import Game.Items.ItemFactory;
import Game.Map.Map;
import Game.Map.MapBuilder;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 22/04/2014.
 */
public class Scene {

    private Map map;
    private List<Actor> actors = new ArrayList<Actor>();
    private List<Item> items = new ArrayList<Item>();

    private ItemFactory itemFactory = new ItemFactory();
    private ActorFactory actorFactory = new ActorFactory();

    private Vector2i worldPos;
    private Game game;

    public Scene(Game game, Vector2i worldPos) {
        this.game = game;
        this.worldPos = worldPos;
        MapBuilder mapBuilder = new MapBuilder();
        map = mapBuilder.buildRandom("MapTiles", 10, 10);

        populate();
        System.out.println("Scene at " + worldPos + " created and populated [Scene]");
    }

    public void update() {
        for (Actor actor : actors) {
            actor.update();
        }
    }

    public void draw(RenderWindow window) {
        window.draw(map);
        for(Item item : items){
            item.draw(window);
        }
        for (Actor actor : actors) {
            actor.draw(window);
        }
    }

    private void populate(){
        addActor(actorFactory.buildMonster("rat", this), new Vector2i(6, 6));
        addActor(actorFactory.buildMonster("skeleton", this), new Vector2i(7,4));

        addItem(itemFactory.buildItem("meleeWeapon", "dagger"), new Vector2i(2, 2));
        addItem(itemFactory.buildItem("meleeWeapon", "longsword"), new Vector2i(4,1));
        addItem(itemFactory.buildItem("armour", "chainmail"), new Vector2i(1,8));
        addItem(itemFactory.buildItem("armour", "helmet"), new Vector2i(1,7));
    }

    public void addActor(Actor actor, Vector2i gridPos) {
        if (!map.isTileOccupied(gridPos)) {
            actor.setGridPos(gridPos);
            map.getTile(gridPos).setOccupied(true);
        } else {
            System.err.print(gridPos + " is already occupied, cannot place actor");
        }
        actors.add(actor);
        actor.scheduleTurn();
    }

    public Map getMap() {
        return map;
    }

    public Actor getActorAtPos(int x, int y) {
        return getActorAtPos(new Vector2i(x, y));
    }

    public Actor getActorAtPos(Vector2i pos) {
        for (Actor a : actors) {
            if (a.getGridPos().equals(pos)) {
                return a;
            }
        }
        System.err.println("Actor at (" + pos.x + ", " + pos.y + ") not found!");
        return null;
    }

    public Actor getPlayer() {

        for (Actor a : actors) {
            if (a.getName().equals("Player")) {
                return a;
            }
        }
        System.err.println("No player found!");
        return null;
    }

    public void removeActor(Actor a) {
        actors.remove(a);
    }

    private void addItem(Item item, Vector2i gridPos){
        item.setGridPos(gridPos);
        items.add(item);
    }

    public Item getItemAtPos(Vector2i pos){
        //this will only return the first item on the tile (if there are more than 1)
        //optimise later!
        for(Item item : items){
            if(item.getGridPos().equals(pos)){
                return item;
            }
        }
        System.out.println("[Scene.getItemAtPos] No items at " + pos + " to grab!");
        return null;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void changeScene(Vector2i dir){
        map.getTile(getPlayer().getGridPos()).setOccupied(false);
        actors.remove(getPlayer());

        TurnClock.cancelAllMonsterTurns();
        game.changeScene(dir);
    }

    public Vector2i getWorldPos(){
        return worldPos;
    }

    public void wake(){
        for(Actor a : actors){
            if(!a.getName().equals("Player")){
                a.scheduleTurn();
            }
        }
    }
}
