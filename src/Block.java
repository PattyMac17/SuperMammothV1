import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Block {
    Pair position;
    Pair velocity;
    Pair acceleration;
    Rectangle2D leftWall;
    Rectangle2D rightWall;
    Rectangle2D topWall;
    Rectangle2D bottomWall;
    int sideLength;
    int wallLength;
    public Block(int x, int y){
        position = new Pair(x, y);
        this.velocity = new Pair(0, 0);
        this.acceleration = new Pair(0, 0);
        sideLength = 40;
        wallLength = 1;
        leftWall = new Rectangle2D.Double(position.x, position.y + wallLength,
                wallLength, sideLength - wallLength);
        rightWall = new Rectangle2D.Double(position.x + sideLength - wallLength,
                position.y + wallLength, wallLength, sideLength - wallLength);
        topWall = new Rectangle2D.Double(position.x, position.y, sideLength, wallLength);
        bottomWall = new Rectangle2D.Double(position.x + wallLength, position.y + sideLength - wallLength,
                sideLength - (2 * wallLength), wallLength);
    }
    public void update(World w, double time){
        position = position.add(velocity.times(time));
        leftWall.setRect(position.x, position.y + wallLength,
                wallLength, sideLength - wallLength);
        rightWall.setRect(position.x + sideLength - wallLength,
                position.y + wallLength, wallLength, sideLength - wallLength);
        topWall.setRect(position.x, position.y, sideLength, wallLength);
        bottomWall.setRect(position.x + wallLength, position.y + sideLength - wallLength,
                sideLength - (2 * wallLength), wallLength);
        velocity = velocity.add(acceleration.times(time));
    }
    public void setVelocity(Pair v){
        velocity = v;
    }
    public void draw(Graphics g){
        BufferedImage block = null;
        try {
            block = ImageIO.read(getClass().getResourceAsStream("block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(block, (int)position.x, (int)position.y,sideLength,sideLength, null);
    }
}