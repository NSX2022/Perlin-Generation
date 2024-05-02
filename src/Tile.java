import java.awt.*;

public class Tile {
    float colorVal;
    public Color tileColor;

    public Tile(float colorVal) {
        this.colorVal = colorVal;
        setColor();
    }

    public void setColor(){
        if(colorVal < 0.1){
            tileColor = Color.blue;
        }
        else if(colorVal < 0.3){
            tileColor = new Color(159, 137, 91);
        }
        else if(colorVal < 0.5){
            tileColor = Color.green;
        }
        else{
            tileColor = new Color(0, 99, 0);
        }
    }
}
