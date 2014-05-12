package Game.Map;

import Generic.CSVLoader;
import Generic.TextureLibrary;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.Vertex;
import org.jsfml.graphics.VertexArray;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Matthew on 22/04/2014.
 */
public class MapBuilder {

    //tiles are 1 cm by 1 cm at a resolution of 72 pixels/cm
    private int tilePixels = 72;

    private Random rnd = new Random();

    private List<String[]> tileRefFromFile = null;

    public MapBuilder() {
    }

    public Map buildRandom(String tileSet, int noOfTilesX, int noOfTilesY) {

        if (tileRefFromFile == null) {
            try {
                tileRefFromFile = CSVLoader.load("resources/map/tileRef");

                //ignore header row
                tileRefFromFile.remove(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MapTile[][] mapTiles = new MapTile[noOfTilesX][noOfTilesY];
        VertexArray vertexArray = new VertexArray(PrimitiveType.QUADS);

        for (int i = 0; i < noOfTilesY; i++) {
            for (int j = 0; j < noOfTilesX; j++) {

                Vector2f[] corners = getCorners(i, j);
                MapTile tile;

                //if (i == 0 || j7 == 0 || i == noOfTilesX - 1 || j == noOfTilesY - 1) {
                //    tile = buildTile("WALL");
                // } else {
                tile = buildTile("GRASS");
                //}
                mapTiles[j][i] = tile;

                Vector2f[] textureCorners = getTextureCorners(tile.getTextureGridPos());

                for (int k = 0; k < corners.length; k++) {
                    vertexArray.add(new Vertex(corners[k], textureCorners[k]));
                }
            }
        }
        return new Map(TextureLibrary.getTexture(tileSet, "tilesheets"), mapTiles, vertexArray);
    }

    private Vector2f[] getCorners(int i, int j) {
        Vector2f[] corners = new Vector2f[4];
        corners[0] = new Vector2f(j * tilePixels, i * tilePixels);
        corners[1] = new Vector2f((j + 1) * tilePixels, i * tilePixels);
        corners[2] = new Vector2f((j + 1) * tilePixels, (i + 1) * tilePixels);
        corners[3] = new Vector2f(j * tilePixels, (i + 1) * tilePixels);
        return corners;
    }

    private Vector2f[] getTextureCorners(Vector2i textureGridPos) {
        Vector2f[] corners = new Vector2f[4];
        corners[0] = new Vector2f(textureGridPos.x * tilePixels, textureGridPos.y * tilePixels);
        corners[1] = new Vector2f((textureGridPos.x + 1) * tilePixels, textureGridPos.y * tilePixels);
        corners[2] = new Vector2f((textureGridPos.x + 1) * tilePixels, (textureGridPos.y + 1) * tilePixels);
        corners[3] = new Vector2f(textureGridPos.x * tilePixels, (textureGridPos.y + 1) * tilePixels);
        return corners;
    }

    private MapTile buildTile(String tileType) {
        MapTile tile = null;
        for (String[] line : tileRefFromFile) {
            if (line[0].equals(tileType)) {
                tile = new MapTile(line);
            }
        }

        if (tile == null) {
            throw new IllegalArgumentException();
        } else {
            return tile;
        }
    }
}
