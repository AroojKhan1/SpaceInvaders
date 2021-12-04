import java.util.Random;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class InvaderMid extends Invader {
    private int x;
    private int y;
    private Invader invader;
    private int points = 20;

//    private static String IMG_NAME ="img_invadermiddleA.gif";
    private static String IMG_NAME ="midInvGif.gif";

    protected InvaderMid(int x, int y) {
        super(x, y, IMG_NAME);


    }
    
    public int getPoints() {
   
        return points;
        
    }
}
