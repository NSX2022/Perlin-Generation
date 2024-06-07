import java.awt.*;

public class Tile {
    float colorVal;
    //set to negative for land gen, 0.0 for wetlands, 0.2 for islands,0.5 for tiny islands
    public static float baseVal = 0.2f;
    public Color tileColor;

    public Tile(float colorVal) {
        this.colorVal = colorVal;
        setColor();
    }

    public void setColor(){
        if(colorVal < baseVal){
            tileColor = Color.blue;
        }
        else if(colorVal < baseVal + 0.1f){
            tileColor = new Color(159, 137, 91);
        }
        else if(colorVal < baseVal + 0.3f){
            tileColor = Color.green;
        }
        else{
            tileColor = new Color(0, 99, 0);
        }
    }
}
