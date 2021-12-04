
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import javax.sound.sampled.Clip;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class Invader extends Drawable{
    private Image invader;

    protected int width;
    protected int height;
    protected  float velocity;
    private Image missile;
    private Clip fire;
    
    
    private static int IMAGE_SIZE = 80;

    protected Invader(int x, int y, String img) {
        super(x, y);
        //chng to 0.125
//        velocity =0.215f;
        velocity =0.125f;
        missile = getImage("invaderMissile.gif");
        fire = getSound("aud_basefire.wav");
  
        invader = getImage(img);
        getImageDimensions();
    }
    
    
    
    
    protected void getImageDimensions() {
        
        width = invader.getWidth(null);
        height = invader.getHeight(null);
        
//        System.out.println("************"+width + " " + height );
    }
    
    public void draw(Graphics2D g2) {
        int x = (int)getX();
        g2.drawImage(invader, x, (int)getY(), null );
    }

   public boolean isVisible() {
       return getY() + IMAGE_SIZE>0;
   }
   
   @Override 
   void move(Direction d) {
       float x = getX();
       float y = getY();
       switch (d) {
           case LEFT :x -=10;  break;
           case RIGHT:x +=10; break;
           case UP   :x -=10;  break;
           case DOWN :x +=10; break;

       }
       setX(x);
       setY(y);
       
     
   }
   
   public void move2() {
       float x = getX();
       float y = getY();
       
       if(x<=0) {
           velocity = -velocity;
           y+=12;
       }
       if(x>=450) {
           velocity = -velocity;           
           y+=12;        
       }
       
       x+=velocity;
       setX(x);
       setY(y);
   }
   

   public Rectangle getBounds() {
       return new Rectangle((int)getX(),(int)getY(),width,height);
   }

   
   public float getVelocity() {
       return velocity;
   }
   
   public void setVelocity(float vel) {
       velocity = vel;
   }
   
//   public InvaderMissile getMissile(int x, int y) {
//     
//    return new InvaderMissile((int)getX(),(int)getY()).getMissile(x,y);
//
//       
//   }


}
