import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    Thread thread;
    public KeyHandler keyH = new KeyHandler(this);
    int windowWidth = 1440;
    int windowHeight = 800;
    public Random rand = new Random();
    public Long seed = rand.nextLong(100000000, 999999999);
    public terrain2D terrain;
    public PerlinNoise perlinNoise;
    Graphics2D g2d;
    public final int FPS = 120;
    public boolean canRun = false;

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Load all stuff
        perlinNoise = new PerlinNoise(Math.toIntExact(seed));
        terrain = new terrain2D(this);
        terrain.genTerrain();
        g2d = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_ARGB).createGraphics();

        canRun = true;

        this.setVisible(true);
    }

    @Override
    public void run() {
        if (canRun) {
            double drawInterval = (double) 1000000000 / FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while (thread != null) {

                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;
                if (delta >= 1) {
                    update(g2d);
                    delta = 0;
                    drawCount++;
                    //terrain.draw(g2d); // Remove this line, as drawing should be handled in paintComponent
                    repaint(); // Render the graphics context to the screen
                }
                if (timer >= 1000000000) {
                    //System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the super method to properly render the background

        Graphics2D g2 = (Graphics2D) g;

        // Draw the terrain
        terrain.draw(g2);
    }
}
