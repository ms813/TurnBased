package Game.Items;

import Game.Actor;
import Generic.AttributeLoader;
import Generic.TextureLibrary;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.json.simple.JSONObject;

/**
 * Created by Matthew on 29/04/2014.
 */
public class MeleeWeapon implements Equippable {

    private Sprite sprite;

    private int toHit;
    private int dmg;

    private EquipSlot equipSlot;

    private Vector2i gridPos;

    private String name;


    public MeleeWeapon(String name) {
        this.name = name;
        sprite = new Sprite(TextureLibrary.getTexture(name, "meleeWeapons"));
        JSONObject json = AttributeLoader.getMeleeWeaponAttrJSON(name);

        toHit = Integer.parseInt((String) json.get("toHit"));
        dmg = Integer.parseInt((String) json.get("dmg"));

        equipSlot = EquipSlot.valueOf((String) json.get("equipSlot"));
    }

    public void draw(RenderWindow window) {
        window.draw(sprite);
    }

    public void update() {
    }

    public void setGridPos(Vector2i gridPos) {
        this.gridPos = gridPos;
        sprite.setPosition(Vector2f.mul(new Vector2f(gridPos), 72));
    }

    public Vector2i getGridPos(){
        return gridPos;
    }

    public String getName(){
        return name;
    }

    public EquipSlot getEquipSlot(){
        return equipSlot;
    }

}
