import java.awt.*;
import java.awt.geom.Rectangle2D;

//this is the world that contains all game elements
public class World {
    int width;
    int height;
    //player is our main character
    Player player;
    int playerVelocity;
    //ground is the actual level itself
    Ground ground;
    Pipe pipe;
    Brick brick;
    Block block;
    QuestionCube questionCube;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.player = new Player();
        this.ground = new Ground();
        this.playerVelocity = 500;
        this.pipe = new Pipe(width/4, Main.HEIGHT - 100 - 100);
        this.brick = new Brick(700, 500);
        this.block = new Block(600, 500);
        this.questionCube = new QuestionCube(500, 500);
    }
    public void draw(Graphics g) {
        //draws all game elements
        player.draw(g);
        ground.draw(g);
        pipe.draw(g);
        brick.draw(g);
        block.draw(g);
        questionCube.draw(g);
    }

    public void update(double time) {
        //updates all game elements
        player.update(this, time);
        ground.update(this, time);
        pipe.update(this, time);
        brick.update(this, time);
        block.update(this, time);
        questionCube.update(this, time);
        /*if(player.atMiddle) {
            System.out.println("middle");
        }
        if (player.atStart) {
            System.out.println("start");
        }*/
    }
}
