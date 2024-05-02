import java.awt.*;
import java.util.Random;

public class terrain2D {

    int tileSize = 10;
    int maxWorldRow;
    int maxWorldCol;
    GamePanel gp;


    public Tile[][] tiles = new Tile[999][999];

    public terrain2D(GamePanel gp) {
        this.gp = gp;

        maxWorldRow = gp.windowWidth/tileSize;
        maxWorldCol = gp.windowHeight/tileSize;

        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                //set tile properties

                //Tile tile = new Tile(gp.perlinNoise.turbulentNoise(1f, 0.6f, 1f, 30));
                Tile tile = new Tile(gp.perlinNoise.smoothNoise(1f,1f,1f,30));

                tiles[col][row] = tile;
            }
        }
        System.out.println("maxR:"+maxWorldRow+" maxC:"+maxWorldCol);
    }

    public void genTerrain() {
        maxWorldRow = gp.windowWidth / tileSize;
        maxWorldCol = gp.windowHeight / tileSize;

        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                // Calculate noise parameters based on row and col
                float x = row * 0.1f;  // Adjust the multiplier as needed
                float y = col * 0.1f;  // Adjust the multiplier as needed
                float z = 1.0f;  // Adjust the noise generation as needed

                // Generate noise value for the current tile
                gp.perlinNoise = new PerlinNoise(Math.toIntExact(gp.seed));
                float noiseValue = gp.perlinNoise.noise(x, y, z);

                // Create a new tile based on the noise value
                Tile tile = new Tile(noiseValue);

                // Set the color of the tile
                tile.setColor();

                // Assign the tile to the corresponding position in the tiles array
                tiles[row][col] = tile;
            }
        }
    }


    public void draw(Graphics2D g2) {
        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                if (tiles[row][col] != null) {  // Check if the tile is not null
                    g2.setColor(tiles[row][col].tileColor);
                    g2.fillRect(row * tileSize, col * tileSize, tileSize, tileSize);  // Use fillRect to fill the tile with color
                }
            }
        }
    }



}
