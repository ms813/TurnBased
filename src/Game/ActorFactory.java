package Game;

import Game.Components.ActionComponents.BasicActionComponent;
import Game.Components.ActionComponents.PlayerActionComponent;
import Game.Components.AttributeComponents.MonsterAttributeComponent;
import Game.Components.AttributeComponents.PlayerAttributeComponent;
import Game.Components.ItemComponents.PlayerItemComponent;

/**
 * Created by Matthew on 25/04/2014.
 */
public class ActorFactory {

    public Actor buildMonster(String monsterType, Scene scene){
        Actor monster = new Actor(monsterType, scene);
        monster.setActionComponent(new BasicActionComponent(monster, scene));
        monster.setAttributeComponent(
                new MonsterAttributeComponent(monster));
        return monster;
    }

    public Actor buildPlayer(String species, Scene scene){
        Actor player = new Actor("player", scene);
        player.setActionComponent(new PlayerActionComponent(scene));
        player.setAttributeComponent(new PlayerAttributeComponent(player, species));
        player.setItemComponent(new PlayerItemComponent(player));
        return player;
    }

}
