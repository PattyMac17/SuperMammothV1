import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {
    Rectangle2D hitBox;
    Pair position;
    Pair velocity;
    Pair acceleration;
    int playerHeight;
    int playerWidth;
    Color testColor;
    boolean atMiddle = false;
    boolean atStart = false;
    boolean ableToJump = false;
    boolean leftCollision = false;
    boolean rightCollision = false;
    boolean topCollision = false;
    public Player(){
        //set player height and width
        this.playerHeight = 50;
        this.playerWidth = 30;

        //set initial position
        this.position = new Pair(50, Main.HEIGHT - 99 - this.playerHeight);
        this.velocity = new Pair(0, 0);
        this.acceleration = new Pair(0, 0);

        //create hitBox
        this.hitBox = new Rectangle2D.Double(position.x, position.y, playerWidth, playerHeight);

        //set color for test rectangle
        this.testColor = Color.CYAN;
    }
    public void update(World w, double time){
        position = position.add(velocity.times(time));
        hitBox.setRect(position.x,position.y, playerWidth,playerHeight);
        velocity = velocity.add(acceleration.times(time));

        midCheck();
        wallCheck();

        jumpStop(w);
        jumpStart(w);

        pipeLeftCheck(w);
        pipeRightCheck(w);

        brickLeftCheck(w);
        brickRightCheck(w);
        brickBottomCheck(w);

        blockBottomCheck(w);
        blockLeftCheck(w);
        blockRightCheck(w);

        questionCubeBottomCheck(w);
        questionCubeLeftCheck(w);
        questionCubeRightCheck(w);

    }
    public void brickBottomCheck(World w){
        Rectangle2D brick = w.brick.bottomWall;
        if(this.hitBox.intersects(brick)){
            velocity = new Pair(velocity.x, -velocity.y);
        }
    }
    public void blockBottomCheck(World w){
        Rectangle2D block = w.block.bottomWall;
        if(this.hitBox.intersects(block)){
            velocity = new Pair(velocity.x, -velocity.y);
        }
    }
    public void questionCubeBottomCheck(World w){
        Rectangle2D questionCube = w.questionCube.bottomWall;
        if(this.hitBox.intersects(questionCube)){
            velocity = new Pair(velocity.x, -velocity.y);
        }
    }
    public void questionCubeLeftCheck(World w){
        Rectangle2D questionCube = w.questionCube.leftWall;
        if(this.hitBox.intersects(questionCube)){
            leftCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            leftCollision = false;
        }
    }
    public void questionCubeRightCheck(World w){
        Rectangle2D questionCube = w.questionCube.rightWall;
        if(this.hitBox.intersects(questionCube)){
            rightCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            rightCollision = false;
        }
    }
    public void blockLeftCheck(World w){
        Rectangle2D block = w.block.leftWall;
        if(this.hitBox.intersects(block)){
            leftCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            leftCollision = false;
        }
    }
    public void blockRightCheck(World w){
        Rectangle2D block = w.block.rightWall;
        if(this.hitBox.intersects(block)){
            rightCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            rightCollision = false;
        }
    }
    public void brickLeftCheck(World w){
        Rectangle2D brick = w.brick.leftWall;
        if(this.hitBox.intersects(brick)){
            leftCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            leftCollision = false;
        }
    }
    public void brickRightCheck(World w){
        Rectangle2D brick = w.brick.rightWall;
        if(this.hitBox.intersects(brick)){
            rightCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            rightCollision = false;
        }
    }
    public void midCheck(){
        Rectangle2D midline = new Rectangle2D.Double(Main.WIDTH/2 + playerWidth, 0, Main.WIDTH/2 -
                playerWidth, Main.HEIGHT);
        if(this.hitBox.intersects(midline)){
            atMiddle = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            atMiddle = false;
        }
    }
    public void pipeLeftCheck(World w){
        Rectangle2D pipe = w.pipe.leftWall;
        if(this.hitBox.intersects(pipe)){
            leftCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            leftCollision = false;
        }
    }
    public void pipeRightCheck(World w){
        Rectangle2D pipe = w.pipe.rightWall;
        if(this.hitBox.intersects(pipe)){
            rightCollision = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            rightCollision = false;
        }
    }
    public void wallCheck(){
        Rectangle2D start = new Rectangle2D.Double(-10, 0, 10, Main.HEIGHT);
        if(this.hitBox.intersects(start)){
            atStart = true;
            velocity = new Pair(0, velocity.y);
        }
        else{
            atStart = false;
        }
    }
    public void draw(Graphics g){
        g.setColor(testColor);
        g.fillRect((int)this.position.x, (int)this.position.y, playerWidth, playerHeight);
    }
    public void setPosition(Pair p){
        position = p;
    }
    public void setVelocity(Pair v){
        velocity = v;
    }

    public void setAcceleration(Pair acceleration) {
        this.acceleration = acceleration;
    }
    public void jumpStop(World w){
        if(hitBox.intersects(w.ground.groundLevel) && velocity.y > 0){
            velocity = new Pair(velocity.x,0);
        }
        if(hitBox.intersects(w.pipe.topWall) && velocity.y > 0){
            velocity = new Pair(velocity.x,0);
        }
        if(hitBox.intersects(w.brick.topWall) && velocity.y > 0){
            velocity = new Pair(velocity.x,0);
        }
        if(hitBox.intersects(w.block.topWall) && velocity.y > 0){
            velocity = new Pair(velocity.x,0);
        }
        if(hitBox.intersects(w.questionCube.topWall) && velocity.y > 0){
            velocity = new Pair(velocity.x,0);
        }
    }
    public void jumpStart(World w){
        if(hitBox.intersects(w.ground.groundLevel) || hitBox.intersects(w.pipe.topWall) ||
                hitBox.intersects(w.brick.topWall) || hitBox.intersects(w.block.topWall)
        || hitBox.intersects(w.questionCube.topWall)){
            ableToJump = true;
        }
        else{
            ableToJump = false;
        }
    }
}
