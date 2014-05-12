package Game.Map;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 22/04/2014.
 */
public class Map extends BasicTransformable implements Drawable{

    private VertexArray vertexArray;
    private Texture texture = new Texture();

    private MapTile[][] mapTiles;

    Map(Texture texture, MapTile[][] mapTiles, VertexArray vertexArray){
        this.texture = texture;
        this.mapTiles = mapTiles;
        this.vertexArray = vertexArray;
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {

        RenderStates s = new RenderStates(
                states.blendMode,
                Transform.combine(states.transform, this.getTransform()),
                this.texture,  //this is the key line that applies the texture to the vertex array
                states.shader);

        target.draw(vertexArray, s);
    }

    public MapTile getTile(Vector2i coords){
        return mapTiles[coords.x][coords.y];
    }

    public MapTile getTile(int x, int y){
        return mapTiles[x][y];
    }

    public boolean isTileOccupied(int x, int y){
        return mapTiles[x][y].isOccupied();
    }

    public boolean isTileOccupied(Vector2i coords){
        return isTileOccupied(coords.x, coords.y);
    }

    public Vector2i getSize(){
        return new Vector2i(mapTiles.length, mapTiles[0].length);
    }
}
