import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

//could be part of a larger obstacle and move-able hierarchy

public class Pipe {
    Rectangle2D leftWall;
    Rectangle2D rightWall;
    Rectangle2D topWall;
    int wallWidth = 1;
    int wallHeight;
    int height;
    int width;
    Pair position; // based on ground position
    Pair velocity;
    Pair acceleration;
    public Pipe(int x, int y){
        height = 100;
        width = 70;
        wallHeight = height - wallWidth;
        position = new Pair(x, y);
        leftWall = new Rectangle2D.Double(position.x, position.y + wallWidth, wallWidth, wallHeight);
        rightWall = new Rectangle2D.Double(position.x + width - wallWidth,position.y + wallWidth, wallWidth, wallHeight);
        topWall = new Rectangle2D.Double(position.x, position.y, width, wallWidth);
        this.velocity = new Pair(0, 0);
        this.acceleration = new Pair(0, 0);
    }
    public void update(World w, double time){
        position = position.add(velocity.times(time));
        leftWall.setRect(position.x, position.y + wallWidth, wallWidth, wallHeight);
        rightWall.setRect(position.x + width - wallWidth,position.y + wallWidth, wallWidth, wallHeight);
        topWall.setRect(position.x, position.y, width, wallWidth);
        velocity = velocity.add(acceleration.times(time));
    }
    public void draw(Graphics g){
        BufferedImage pipe = null;
        try {
            pipe = ImageIO.read(getClass().getResourceAsStream("pipe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(pipe, (int)position.x, (int)position.y,width,height, null);
        //g.setColor(Color.green);
        //draw left wall
        //g.drawRect((int)position.x, (int)position.y + wallWidth, wallWidth, wallHeight);
        //draw right wall
        //g.drawRect((int)(position.x + width - wallWidth),(int)position.y + wallWidth, wallWidth, wallHeight);
        //draw top wall
        //g.drawRect((int)position.x, (int)position.y, width, wallWidth);
    }
    public void setVelocity(Pair v){
        velocity = v;
    }
}
