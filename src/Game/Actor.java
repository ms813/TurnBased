package Game;

import Game.AttributeBars.HPBar;
import Game.Components.ActionComponents.ActionComponent;
import Game.Components.AttributeComponents.AttributeComponent;
import Game.Components.ItemComponents.ItemComponent;
import Game.Components.MeleeAttacks.AttackFlavour;
import Game.Components.MeleeAttacks.MeleeAttack;
import Game.Items.Item;
import Generic.TextureLibrary;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.KeyEvent;

import java.util.List;

/**
 * Created by Matthew on 23/04/2014.
 */
public class Actor {

    protected Scene scene;

    protected Sprite sprite;

    protected String name;

    protected Vector2i gridPos = Vector2i.ZERO;

    protected boolean dead = false;

    protected ActionComponent actionComponent;
    protected AttributeComponent attributeComponent;
    protected ItemComponent itemComponent;
    protected HPBar hpBar;

    public Actor(String textureName, Scene scene) {
        this.scene = scene;
        sprite = new Sprite(TextureLibrary.getTexture(textureName, "monsters"));
        this.name = textureName.substring(0, 1).toUpperCase() + textureName.substring(1);

        hpBar = new HPBar(this);
    }

    public void update() {
        hpBar.update();
        sprite.setPosition(Vector2f.mul(new Vector2f(gridPos), 72));
    }

    public void draw(RenderWindow window) {
        window.draw(sprite);
        hpBar.draw(window);
    }

    public void move(Vector2i dir) {
        actionComponent.move(this, dir);
    }

    public Vector2i getGridPos() {
        return gridPos;
    }

    public void setGridPos(Vector2i pos) {
        gridPos = pos;
    }

    public void setActionComponent(ActionComponent actionComponent) {
        this.actionComponent = actionComponent;
    }

    public void setAttributeComponent(AttributeComponent attributeComponent) {
        this.attributeComponent = attributeComponent;
    }

    public void setItemComponent(ItemComponent itemComponent){
        this.itemComponent = itemComponent;
    }

    public String getName() {
        return name;
    }

    public void receiveAttack(String attackerName, int toHit, int damage, AttackFlavour flavour) {
        attributeComponent.receiveAttack(attackerName, toHit, damage, flavour);
    }

    public void onDeath() {
        System.out.println("The " + name + " has died!");
        scene.removeActor(this);
        scene.getMap().getTile(gridPos).setOccupied(false);
        dead = true;

    }

    public void doTurn() {

        //n.b. the 'dead' flag stops turns being scheduled indefinitely for dead monsters
        if (!dead) {
            //System.out.println(name + " at (" + gridPos.x + ", " + gridPos.y + ") is taking its turn");
            actionComponent.doTurn(this);
        }
    }

    public void scheduleTurn(){
        //if no delay parameter is passed it is assumed the actor is moving
        scheduleTurn(attributeComponent.getSpeed());
    }

    public void scheduleTurn(int delay){
        //schedule the actors next turn
        TurnClock.cancelFutureTurns(this);
        TurnClock.scheduleTurn(delay, this);
    }

    public List<MeleeAttack> getAttacks(){
        return attributeComponent.getAttacks();
    }

    public FloatRect getLocalBounds(){
        return sprite.getLocalBounds();
    }

    public float getCurrentHP(){
        return attributeComponent.getCurrentHP();
    }

    public float getMaxHP(){
        return attributeComponent.getMaxHP();
    }

    public Vector2f getPixelPos(){
        return Vector2f.mul(new Vector2f(gridPos), 72);
    }

    public void grab(){
        Item item = scene.getItemAtPos(gridPos);
        if(item != null) {
            itemComponent.grab(item);
            scene.removeItem(item);
            scheduleTurn(5);
        }
    }

    public void inventoryAction(KeyEvent e){
        itemComponent.inventoryAction(e);
    }

    public void listInventory(){
        itemComponent.listInventory();
    }
}
