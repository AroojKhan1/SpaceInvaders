import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * keeps track of invaders, missiles, the laser base, the mystery ship, the
 * score, and it also receives events from the keyboard to move the base and to
 * fire missiles, and events from a Timer to advance the state of the game.
 * 
 * @author
 * @version
 */
public class Panel
    extends JPanel
    implements ActionListener {

    // TODO end game when either the invader shoots or reaches base
    // fix new game
    // fix pause - can we make it where if we press p it pauses the game? (:
    // it should be easy, just need to put the method in the key press thing
    // fix resume
    // fix game over - not popping up right now
    // make invaders shoot missiles
    // make invaders "pop" when they are hit
    // make it so when invader hits base game ends
    // add mystery ship (in allINv list? maybe?)
    // I don't think so b/c it behaves differently
    // points for mystery ship
    // mystery ship movement
    // mystery ship sound
    // DONT FORGET to uncomment break and readjust missilse SIZE
    // if there is nothing next to ANY invader then it shoots.

    private Timer          timer;
    private SpaceInvaders  spcInvdr;
    private int            idx       = 0;

    private Base           base      = new Base(225, 373);
    private final int      DELAY     = 15;
    private List<Invader>  invader;
    private boolean        ingame;

    private static String  IMG_NAME1 = "topInvGif.gif";
    private static String  IMG_NAME2 = "midInvGif.gif";
    private static String  IMG_NAME3 = "bottomInvGif2.gif";
    private static String  POP_IMG   = "img_invaderhit.gif";

    private List<Invader>  topInv;
    private List<Invader>  botInv;
    private List<Invader>  midInv;
    private List<Invader>  allInvaders;
    private List<Integer>  idxList;
    private int            userScore = 0;
    private PopInvader     popcorn;
    private InvaderMissile invMissile;

    private SpaceInvaders  spcInv;

    private Missile        missile   =
        new Missile((int)base.getX(), (int)base.getY() + 20);

    public Panel() {

        initPanel();
        createInvader();
        checkCollision();

    }


    public void setMissile(Missile m) {
        this.missile = m;

    }


    
    private void initPanel() {
        timer = new Timer(DELAY, this);
        timer.start();
        initInvaders();
        ingame = true;
    }


    public void initInvaders() {
        invader = new ArrayList<>();

    }

    public void actionPerformes(ActionEvent e) {
        inGame();
        updateBase();
        updateInvaders();
        checkCollision();
        repaint();

    }


    private void inGame() {
        if (!ingame) {
// timer.stop();
        }
    }


    private void updateBase() {
        if (!base.isVisible()) {
            ingame = false;
            return;
            // double check it
        }
    }


    // moves invaders back and forth and down
    public void updateInvaders() {
        boolean flagLeft = false;
        boolean flagRight = false;
        for (int i = 0; i < allInvaders.size(); i++) {
            Invader inv = allInvaders.get(i);
            float x = inv.getX();
            float y = inv.getY();
            // invader hits left wall increase speed going to right
            if (x <= 0) {
                flagLeft = true;
            }
            // invader hits right wall increase speed going to left
            if (x >= 450) {
                flagRight = true;
            }
        }

        for (int i = 0; i < allInvaders.size(); i++) {
            Invader inv = allInvaders.get(i);
            float x = inv.getX();
            float y = inv.getY();
            if (flagLeft) {
                inv.setVelocity(-inv.getVelocity() + 0.043f);
                y += 12;
            }
            if (flagRight) {
                inv.setVelocity(-inv.getVelocity() - 0.8f);
                y += 12;
            }
            x += inv.getVelocity();
            inv.setX(x);
            inv.setY(y);

        }

        // add a for loop for removing or moving use zetCode website
    }


    @SuppressWarnings("serial")
    public boolean checkCollision() {

        Rectangle r3 = base.getBounds();

        Iterator<Invader> itr2 = allInvaders.iterator();
        while (itr2.hasNext()) {

            Invader temp = itr2.next();
            Rectangle r2 = temp.getBounds();
            if (r3.intersects(r2)) {
                base.setVisible(false);

                System.out.println("***IN GAME IS FALSE NOW***");
                this.ingame = false;
// timer.stop();
            }

        }

        Rectangle r1 = missile.getBounds();
        Iterator<Invader> itr = allInvaders.iterator();

        topInv = new ArrayList<>();
        midInv = new ArrayList<>();
        botInv = new ArrayList<>();
        idxList = new ArrayList<>();

        for (int i = 0; i < allInvaders.size(); i++) {
            if (i <= 9) {
                topInv.add(allInvaders.get(i));
            }
            if (i > 9 && i <= 29) {
                midInv.add(allInvaders.get(i));
            }
            if (i > 29 && i <= 49) {
                botInv.add(allInvaders.get(i));
            }
        }

        while (itr.hasNext()) {

            Invader temp = itr.next();

            Rectangle r2 = temp.getBounds();

            if (r1.intersects(r2)) {

                popcorn =
                    new PopInvader((int)r2.getX(), (int)r2.getY(), POP_IMG);

                temp.setVisible(false);

// popcorn.setVisible(true);

                missile.setVisible(false);
                missile.setY(-500);

                if (allInvaders.contains(temp)) {
                    idx = allInvaders.indexOf(temp);
                    idxList.add(idx);
                }

                JPanel p = new JPanel() {

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D)g;
                        popcorn.draw(g2);
                        System.out.println("!!!!POP DRAW  NOW!!!!");
                    }
                };
                repaint();
                add(p);
                p.setFocusable(true);
                // when hit top
                if (topInv.contains(temp)) {
                    userScore += 30;
                    topInv.remove(temp);
                }
                // when hit mid
                if (midInv.contains(temp)) {
                    userScore += 20;
                    midInv.remove(temp);
                }
                // when hit bot
                if (botInv.contains(temp)) {
                    userScore += 10;
                    botInv.remove(temp);
                }
                itr.remove();

                break;

            }

        }

        if (topInv.isEmpty() && midInv.isEmpty() && botInv.isEmpty()
            && ingame) {

            spcInv = new SpaceInvaders();
            repaint();

        }

// allInvaders.remove idx

        rmvIdx(idxList);
        return this.ingame;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


    public List<Invader> createInvader() {

        allInvaders = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Invader inv = new Invader(70 + 35 * i, 80, IMG_NAME1);
            allInvaders.add(inv);
        }

        // midRow1
        for (int i = 0; i < 10; i++) {
            Invader inv = new Invader(70 + 35 * i, 105, IMG_NAME2);
            allInvaders.add(inv);
        }

        // midRow2
        for (int i = 0; i < 10; i++) {
            Invader inv = new Invader(70 + 35 * i, 130, IMG_NAME2);
            allInvaders.add(inv);
        }

        // botRow1
        for (int i = 0; i < 10; i++) {
            Invader inv = new Invader(70 + 35 * i, 155, IMG_NAME3);
            allInvaders.add(inv);
        }

        // botRow2
        for (int i = 0; i < 10; i++) {
            Invader inv = new Invader(70 + 35 * i, 180, IMG_NAME3);
            allInvaders.add(inv);
        }

        return allInvaders;
    }


    public int getUserScore() {
        return userScore;
    }


    // ******************NEWW**************************
    /**
     * uses invaderMissile class to make random invaders shoot missile and
     * returns missile stops the game if it
     * 
     * @return
     */

    public void invaderShoots(List<InvaderMissile> invMissiles, Base base) {

        for (InvaderMissile missile : invMissiles) {

            if (missile != null && missile.isVisible()) {
                missile.move(Drawable.Direction.DOWN);

            }

            Rectangle missileBounds = missile.getBounds();
            Rectangle baseBounds = base.getBounds();

            if (missileBounds.intersects(baseBounds)) {
                ingame = false;
            }
        }

//        return invMissile;

    }


    /**
     * this so that I can sent allInvList to invMiss in or der to create new
     * missile at a random location.
     * 
     * @return allInvaders
     */
    public List<Invader> getInvList(int rowNum) {
        if (rowNum == 1) {
            return topInv;
        }
        if (rowNum == 2 || rowNum == 3) {
            return midInv;
        }
        if (rowNum == 4 || rowNum == 5) {
            return botInv;
        }
        return allInvaders;
    }


    /**
     * removes the invader that has been shot from the list to try to keep the
     * list live
     * 
     * @param idx
     * @return
     */
    public List<Invader> rmvIdx(List<Integer> idx) {

        for (int i = 0; i < allInvaders.size(); i++) {
            if (i < idx.size() && i == idx.get(i)) {
                allInvaders.remove(i);

            }

        }

        return allInvaders;
    }


    /**
     * Get the current value of ingame.
     * @return The value of ingame for this object.
     */
    public boolean isIngame() {
        return ingame;
    }


    
    /**
     * Set the value of ingame for this object.
     * @param ingame The new value for ingame.
     */
    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }
    
    public void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Algerian", Font.ITALIC, 64);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.GREEN);
        g.setFont(small);

        // change later
        g.drawString(msg, 80, 210);
    }

}
