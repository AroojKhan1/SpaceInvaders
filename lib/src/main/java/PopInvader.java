import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author
 * @version
 */
public class PopInvader
    extends Invader {

    private PopInvader obj;
    private Image         pop;
    private Clip popSound;

    private static String POP_IMG = "img_invaderhit.gif";

    /**
     * Create a new PopInvader object.
     * 
     * @param x
     * @param y
     */
    protected PopInvader(int x, int y, String img) {
        super(x, y, POP_IMG);
        
        pop = getImage("img_invaderhit.gif");
        popSound = getSound("aud_hit.wav");
      
    }


    /**
     * {@inheritDoc}
     */
    @Override
    void move(Direction d) {
        float x = getX();
        float y = getY();
        switch (d) {
            case LEFT:
                x -= 10;
                break;
            case RIGHT:
                x += 10;
                break;
            case UP:
                x -= 10;
                break;
            case DOWN:
                x += 10;
                break;

        }
        setX(x);
        setY(y);

    }


    public void draw(Graphics2D g2) {
        int x = (int)getX();
        g2.drawImage(pop, x, (int)getY(), null);

    }
    
    

    public void setPop(PopInvader popInv) {
        this.obj = popInv;
    }
    
    public PopInvader getPop() {
       
        return this.obj;
    }
}
