import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    Random rand;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
        rand = new Random();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            //regen grid
            gp.seed = rand.nextLong(100000000, 999999999);
            gp.terrain.genTerrain();
            System.out.println("Regenerated");
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_EQUALS){
            gp.terrain.tileSize++;
            gp.terrain.genTerrain();
            System.out.println(gp.terrain.tileSize);
        }
        if(e.getKeyCode() == KeyEvent.VK_MINUS && gp.terrain.tileSize >= 3){
            gp.terrain.tileSize--;
            gp.terrain.genTerrain();
            System.out.println(gp.terrain.tileSize);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
