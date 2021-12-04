import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.Clip;

/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author
 * @version
 */
public class InvaderMissile
    extends Drawable {

// 42
    private static int IMAGE_SIZE = 42;
    private Image      missileImg;
    protected int      width      = 15;
    protected int      height     = 10;
    private Clip       fire;
    private Panel      panel      = new Panel();
    List<Invader>      invListTop = new ArrayList();
    List<Invader>      invListMid = new ArrayList();
    List<Invader>      invListBot = new ArrayList();

    public InvaderMissile(int x, int y) {
        super(x, y);
        missileImg = getImage("invaderMissile.gif");
        fire = getSound("aud_basefire.wav");
        fire.start();
     
    }




    protected void getImageDimensions() {

        width = missileImg.getWidth(null);
        height = missileImg.getHeight(null);

    }

    public void fireSound() {
        if (fire.isRunning()) {
            fire.close();
        }

        fire = getSound("aud_basefire.wav");
        fire.start();
    }

    /**
     * Place a description of your method here.
     * 
     * @param g2
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(
            missileImg,
            (int)getX(),
            (int)getY(),
            IMAGE_SIZE,
            IMAGE_SIZE,
            null);

    }


    /**
     * Place a description of your method here.
     * 
     * @param up
     */
    public void move(Direction d) {
        if (d == Direction.DOWN) {
            int y = (int)(getY() + 10);
            setY(y);
        }

    }


    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public boolean isVisible() {
        return getY() + IMAGE_SIZE > 0;
    }


    public void remove() {

    }


//    public InvaderMissile getMissile(int x, int y) {
//        invListTop = panel.getInvList(1);
//        invListMid = panel.getInvList(2);
////        invListBot = panel.getInvList(4);
//        invListBot = panel.getBotList();
//        Random rand = new Random();
////        if (fire.isRunning()) {
////            fire.stop();
////        }
////        fire.setFramePosition(2);
////        fire.start();
//        
//        // gets a random invader from list to shoot missile
////        if (!invListBot.isEmpty() && y >= 155 && y <= 180) {
////            System.out.println("in bottom list");
////            fire.setFramePosition(y);
////            System.out.println("MISSile y location" + y);
////            fire.start();
////
////            return new InvaderMissile(x,y);
////        }
////        else if (!invListMid.isEmpty() && y >= 105 && y <= 130) {
////            System.out.println("in mid list");
////        
////            fire.setFramePosition(y);
////            fire.start();
////            return new InvaderMissile(x,y);
////               
////        }
////        else if (!invListTop.isEmpty() && y == 80) {
////            System.out.println("in top list");
////            fire.setFramePosition(y);
////            fire.start();
////            return new InvaderMissile(x,y);
////                
////        }
//  
//
//        return new InvaderMissile(1,2);
//
//    }
    
    



    public Rectangle getBounds() {
        return new Rectangle((int)getX(), (int)getY(), width, height);
    }
}
