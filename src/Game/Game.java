package Game;

import Game.Components.ActionComponents.PlayerActionComponent;
import Game.Input.InputHandler;
import Generic.FontLibrary;
import Generic.Vector2I;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 22/04/2014.
 */
public class Game {

    private Actor player;
    private String species = "dwarf";

    private InputHandler inputHandler;

    private List<Scene> sceneList = new ArrayList<Scene>();
    private Scene currentScene;

    private RenderWindow window;
    private View playerView;
    private View uiView;


    private Text turnCounterLabel = new Text("", FontLibrary.getFont("arial"), 14);

    public Game(RenderWindow window) {
        this.window = window;
        playerView = new View(new FloatRect(0, 0, window.getSize().x, window.getSize().y));
        window.setView(playerView);

        uiView = new View(new FloatRect(0, 0, window.getSize().x, window.getSize().y));

        currentScene = new Scene(this, Vector2i.ZERO);
        sceneList.add(currentScene);
        player = new ActorFactory().buildPlayer(species, currentScene);
        currentScene.addActor(player, new Vector2i(1, 1));
        inputHandler = new InputHandler(player);

        turnCounterLabel.setString("Turn: " + TurnClock.getCurrentTurn());

    }

    public void update() {
        currentScene.update();
        turnCounterLabel.setString("Turn: " + TurnClock.getCurrentTurn());
    }

    public void draw(RenderWindow window) {
        //swap to the default view to draw the UI
        window.setView(uiView);
        window.draw(turnCounterLabel);
        //more UI drawing here

        //swap back to the player centered view and keep drawing
        playerView.setCenter(Vector2f.mul(new Vector2f(player.getGridPos()), 72));
        window.setView(playerView);
        currentScene.draw(window);
    }

    public void processEvent(Event event) {
        inputHandler.processEvent(event);
    }

    public void changeScene(Vector2i dir){
        Scene nextScene = null;
        Vector2i worldPos = Vector2i.add(dir, currentScene.getWorldPos());
        for(Scene scene : sceneList){
            if(scene.getWorldPos().equals(worldPos)){
                System.out.println("Changing to scene at ("+worldPos.x + ", "+worldPos.y +") [Game.changeScene]");
                nextScene = scene;
                nextScene.wake();
            }
        }

        if(nextScene == null){
            System.out.println("Creating new scene at ("+worldPos.x + ", "+worldPos.y +") [Game.changeScene]");
            nextScene = new Scene(this,worldPos);
            sceneList.add(nextScene);
        }

        Vector2i nextPos = Vector2i.ZERO;
        if(dir.equals(Vector2I.NORTH)){
            nextPos = new Vector2i(player.getGridPos().x, nextScene.getMap().getSize().y - 1);
        } else if(dir.equals(Vector2I.SOUTH)) {
            nextPos = new Vector2i(player.getGridPos().x, 0);
        } else if(dir.equals(Vector2I.EAST)){
            nextPos = new Vector2i(0, player.getGridPos().y);
        } else if(dir.equals(Vector2I.WEST)){
            nextPos = new Vector2i(nextScene.getMap().getSize().x - 1, player.getGridPos().y);
        }

        nextScene.addActor(player, nextPos);
        player.setActionComponent(new PlayerActionComponent(nextScene));

        currentScene = nextScene;
    }
}
