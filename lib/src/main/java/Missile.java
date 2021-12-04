import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.sound.sampled.Clip;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class Missile extends Drawable {
//42
    private static int IMAGE_SIZE = 42;
    private Image missileImg;
    //400 for testing 15 orig 
    protected int width = 15;
    protected int height = 10;
    private Clip fireClip;

    public Missile(int x, int y) {
        super(x,y);
        missileImg = getImage("missile2.gif");
        fireClip = getSound("aud_basefire.wav");
    }
    
    protected void getImageDimensions() {
        
        width = missileImg.getWidth(null);
        height = missileImg.getHeight(null);
        
    }
    
    /**
     * Place a description of your method here.
     * @param g2
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(missileImg, (int)getX(), (int)getY(), IMAGE_SIZE, IMAGE_SIZE, null);
        
    }
    /**
     * Place a description of your method here.
     * @param up
     */
    public void move(Direction d) {
        System.out.println("**missile going up.....");
        if(d == Direction.UP) {
            int y = (int)(getY()-10);
            setY(y);
        }
        
    }
    /**
     * Place a description of your method here.
     * @return
     */
    public boolean isVisible() {
        return getY()+IMAGE_SIZE>0;
    }

    public void remove() {
        
    }
    
    
    
    public Missile getMissile() {
        if(fireClip.isRunning()) {
            fireClip.stop();
        }
        fireClip.setFramePosition(440);
        fireClip.start();
     return new Missile((int)getX(),(int)getY());
        
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int)getX(),(int)getY(),width,height);
    }
}
