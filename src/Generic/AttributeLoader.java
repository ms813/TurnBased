package Generic;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Matthew on 25/04/2014.
 */
public class AttributeLoader {

    private static JSONParser parser = new JSONParser();
    private static JSONObject monsterAttrJSON = null;
    private static JSONObject playerSpeciesAttrJSON = null;
    private static JSONObject meleeWeaponAttrJSON = null;
    private static JSONObject armourAttrJSON = null;

    public static JSONObject getMonsterAttrJSON(String monster) {
        if (monsterAttrJSON == null) {
            monsterAttrJSON = loadFromFile("resources/vals/monsterAttr.txt");
        }

        return (JSONObject) monsterAttrJSON.get(monster);
    }

    public static JSONObject getPlayerSpeciesAttrJSON(String species) {
        if (playerSpeciesAttrJSON == null) {
            playerSpeciesAttrJSON = loadFromFile("resources/vals/playerSpeciesAttr.txt");
        }
        return (JSONObject) playerSpeciesAttrJSON.get(species);
    }

    public static int getMonsterDifficulty(String monster){
        if(monsterAttrJSON == null){
            monsterAttrJSON = loadFromFile("resources/vals/monsterAttr.txt");
        }

        return Integer.parseInt((String)((JSONObject)monsterAttrJSON.get(monster)).get("diff"));
    }

    public static JSONObject getMeleeWeaponAttrJSON(String name){
        if(meleeWeaponAttrJSON == null){
            meleeWeaponAttrJSON = loadFromFile("resources/vals/meleeWeaponAttr.txt");
        }
        return (JSONObject) meleeWeaponAttrJSON.get(name);
    }

    public static JSONObject getArmourAttrJSON(String name){
        if(armourAttrJSON == null){
            armourAttrJSON = loadFromFile("resources/vals/armourAttr.txt");
        }
        return (JSONObject) armourAttrJSON.get(name);
    }

    private static JSONObject loadFromFile(String path) {
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }
}
