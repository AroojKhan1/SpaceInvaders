
import java.awt.Graphics2D;
import java.awt.Image;
import javax.sound.sampled.Clip;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class InvaderTop extends Invader{


    private int x;
    private int y;
    private Invader invader;
    private int points = 30;

    private static String IMG_NAME ="topInvGif.gif";
//    private static String IMG_NAME ="img_invadertopA.gif";

    protected InvaderTop(int x, int y) {
        super(x, y, IMG_NAME);


    }

    public int getPoints() {
        
        return points;
        
    }
  

}
