
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class InvaderBottom extends Invader{

    private int x;
    private int y;
    private Invader invader;
    private int points = 10;

//    private static String IMG_NAME ="img_invaderbottomA.gif";
    private static String IMG_NAME ="bottomInvGif2.gif";

    protected InvaderBottom(int x, int y) {
        super(x, y, IMG_NAME);


    }
    
    public int getPoints() {
        
        return points;
        
    }
    
}


