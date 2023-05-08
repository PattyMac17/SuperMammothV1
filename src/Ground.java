import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ground {
    Pair position;
    Pair velocity;
    Pair acceleration;
    Rectangle2D groundLevel;
    int width;
    int height;
    public Ground(){
        this.height = 100;
        this.width = 8000;
        this.position = new Pair(0, Main.HEIGHT - height);
        this.velocity = new Pair(0, 0);
        this.acceleration = new Pair(0, 0);
        this.groundLevel = new Rectangle2D.Double(position.x,position.y, width,height);
    }
    public void setVelocity(Pair v){
        velocity = v;
    }
    public void update(World w, double time){
        position = position.add(velocity.times(time));
        groundLevel.setRect(position.x,position.y, width,height);
        velocity = velocity.add(acceleration.times(time));
    }
    public void draw(Graphics g){
        //insert background image
        BufferedImage ground = null;
        try {
            ground = ImageIO.read(getClass().getResourceAsStream("ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(ground, (int)position.x, (int)position.y,width,height, null);
    }
}
