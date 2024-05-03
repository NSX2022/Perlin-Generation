import java.awt.*;
import java.util.Random;

public class terrain2D {

    int tileSize = 35;
    int maxWorldRow;
    int maxWorldCol;
    GamePanel gp;


    public Tile[][] tiles = new Tile[9999][9999];

    public terrain2D(GamePanel gp) {
        this.gp = gp;
        Random rand = new Random();

        maxWorldRow = gp.windowWidth/tileSize;
        maxWorldCol = gp.windowHeight/tileSize;

        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                //set tile properties

                //Tile tile = new Tile(gp.perlinNoise.turbulentNoise(1f, 0.6f, 1f, 30));
                Tile tile = new Tile(gp.perlinNoise.smoothNoise(rand.nextFloat(), rand.nextFloat(),rand.nextFloat(), rand.nextInt(0,30)));

                tiles[col][row] = tile;
            }
        }
        System.out.println("maxR:"+maxWorldRow+" maxC:"+maxWorldCol);
    }

    public void genTerrain() {
        maxWorldRow = gp.windowWidth / tileSize;
        maxWorldCol = gp.windowHeight / tileSize;
        gp.repaint();

        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {

                float x = row * 0.1f;
                float y = col * 0.1f;
                float z = 1.0f;

                gp.perlinNoise = new PerlinNoise(Math.toIntExact(gp.seed));
                float noiseValue = gp.perlinNoise.noise(x, y, z);

                Tile tile = new Tile(noiseValue);

                tile.setColor();

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
        g2.setFont(new Font("Courier", Font.PLAIN, 40));
        if(tileSize != 35 && tileSize != 1 && tileSize != 800 && tileSize != 801){
            g2.setColor(Color.white);
        }else{
            g2.setColor(Color.orange);
        }

        g2.drawString(String.valueOf(tileSize), 5, 35);
    }



}
