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
public class Base extends Drawable{
    private Image base;
    private Clip fire;
    protected int width = 15;
    protected int height = 10;
    private int x;
    private int y;
    
    
    private static int IMAGE_SIZE = 80;

    protected Base(int x, int y) {
        super(x, y);
        base = getImage("img_base.gif");
        fire = getSound("aud_basefire.wav");

    }
    
    protected void getImageDimensions() {
        
        width = base.getWidth(null);
        height = base.getHeight(null);
        
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(base, (int)getX(), (int)getY(), null );
    }

   public boolean isVisible () {
       return getY() + IMAGE_SIZE>0;
   }
   
   @Override 
   void move(Direction d) {
       float x = getX();
       float y = getY();
       switch (d) {
           case LEFT :x -=10;  break;
           case RIGHT:x +=10; break;

       }
       setX(x);
       setY(y);
       
     
   }
   
   public Missile getMissile() {
       if(fire.isRunning()) {
           fire.stop();
       }
       fire.setFramePosition(0);
       fire.start();
    return new Missile((int)getX(),(int)getY());
       
   }

   public Rectangle getBounds() {
       return new Rectangle((int)getX(),(int)getY(),width,height);
   }

}
