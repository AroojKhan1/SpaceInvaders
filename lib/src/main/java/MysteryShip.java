import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class MysteryShip extends Invader {
    
    private int x;
    private int y;
    private boolean visible = false;

    protected int width = 15;
    protected int height = 10;
    
    private static int IMAGE_SIZE = 80;
    
   private ArrayList<Integer> points = new ArrayList<Integer>();
   

    private static String IMG_NAME ="img_mystery.gif";
    
    

    protected MysteryShip(int x, int y) {
        super(x, y, IMG_NAME);
    }


    
    public int getPoints() {
        points.add(150);
        points.add(50);
        points.add(200);
        points.add(250);
        points.add(300);
        
        Random rand = new Random();
        int randomEle = points.get(rand.nextInt(points.size()));
        
        return randomEle;
        
    }
    

    public boolean isVisible() {
        return this.visible;
    }
   
    /**
     * Set the value of visible for this object.
     * @param visible The new value for visible.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    

}
