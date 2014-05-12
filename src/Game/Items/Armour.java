package Game.Items;

import Generic.AttributeLoader;
import Generic.TextureLibrary;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.json.simple.JSONObject;

/**
 * Created by Matthew on 30/04/2014.
 */
public class Armour implements Equippable {

    private Sprite sprite;

    private int AC;
    private int EV;
    private float GDR; //guaranteed damage reduction, e.g. 0.3 = 30%

    private EquipSlot equipSlot;

    private String name;
    private Vector2i gridPos;

    public Armour(String name){
        this.name = name;

        sprite = new Sprite(TextureLibrary.getTexture(name, "armour"));
        JSONObject json = AttributeLoader.getArmourAttrJSON(name);

        AC = Integer.parseInt((String) json.get("AC"));
        EV = Integer.parseInt((String) json.get("EV"));
        GDR = Float.parseFloat((String) json.get("GDR"));

        equipSlot = EquipSlot.valueOf((String) json.get("equipSlot"));
    }

    public void draw(RenderWindow window){window.draw(sprite);}

    public void update(){}

    public void setGridPos(Vector2i gridPos){
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
