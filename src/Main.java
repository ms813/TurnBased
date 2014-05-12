import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import Game.Game;

/**
 * Created by Matthew on 22/04/2014.
 */
public class Main {

    public static void main(String[] args) {

        RenderWindow window = new RenderWindow();

        window.create(new VideoMode(1500, 900), "JRogueLike_TurnBased");
        window.setFramerateLimit(60);
        window.setVerticalSyncEnabled(true);

        Game game = new Game(window);

        //game loop
        while(window.isOpen()){
            window.clear();
            //event loop
            for (Event event : window.pollEvents()) {

                //if the window is closed
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
                game.processEvent(event);
            }
            game.update();
            game.draw(window);
            window.display();
        }
    }
}
