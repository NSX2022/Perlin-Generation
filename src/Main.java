import javax.swing.*;
import java.util.Objects;

public class Main {

    public static JFrame window = new JFrame();


    public static void main(String[] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Loading...");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setUndecorated(true);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


        gamePanel.startGameThread();
    }
    public static void setTitle(String title) {
        window.setTitle(title);
    }
}
