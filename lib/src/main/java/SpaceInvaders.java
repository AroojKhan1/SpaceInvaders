import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.Rectangle;

/**
 * creates jPannel
 * 
 * @author Arooj Alessi
 * @version 12/2/21
 */
@SuppressWarnings("serial")
public class SpaceInvaders
    extends JFrame {

    private boolean        left, right, up, down;

    private Base           base;
    private Missile        missile;

    private InvaderTop     topRow;
    private InvaderMid     midRow;
    private InvaderMid     midRow2;
    private InvaderBottom  bottomRow;
    private InvaderBottom  bottomRow2;
    private MysteryShip    mysteryShip = new MysteryShip(50, 50);
    private InvaderMissile invMissile;

    private PopInvader     popInv;

    private boolean        temp;
    private List<Invader>  allInv   = new ArrayList<>();

    private Panel          panel;

    private Timer          timer;
    
    public int mysteryShipLocation = 2;

    public SpaceInvaders() {

        super("Space Invaders");

        panel = new Panel();
        allInv = panel.createInvader();
        Random rand = new Random();

        List<InvaderMissile> invMissileList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Invader randInv = allInv.get(rand.nextInt(allInv.size()));
            invMissileList.add(
                new InvaderMissile((int)randInv.getX(), (int)randInv.getY()));
        }

        JPanel p = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D)g;

                // this shows the score in top right
                String str = "Score " + panel.getUserScore();
                Font font = new Font("Algerian", Font.ITALIC, 20);
                FontMetrics fm = g2.getFontMetrics();
                int width = fm.stringWidth(str);
                g2.setFont(font);
                g2.drawString(str, 435 - width, LEFT_ALIGNMENT + 30);

                base.draw(g2);

                for (int i = 0; i < allInv.size(); i++) {
                    if (allInv.get(i).getVisible()) {
                        allInv.get(i).draw(g2);
                        
                    }
                }

              Random randShip = new Random();

              if(mysteryShip.isVisible()) {
                  mysteryShip.draw(g2);
                  mysteryShip.setX(mysteryShip.getX() + 5);

                  System.out.println("mysteryShip.getX: " + mysteryShip.getX());
                  if(mysteryShip.getX() > 500) {
                      System.out.println("set to false");
                      mysteryShip.setVisible(false);
                  }
              }
              

              if (!mysteryShip.isVisible() && randShip.nextInt(100) == 5) {
                  System.out.println("Ship created...");
                  mysteryShip.setVisible(true);
                  mysteryShip.draw(g2);
                  mysteryShip.setX(2);
                  mysteryShip.setY(50);
              }

                
                for (InvaderMissile missile : invMissileList) {

                    if (missile.getY() > 500 && allInv.size() > 0) {

                        System.out.println("Out of bounds...");
                        Invader randInv =
                            allInv.get(rand.nextInt(allInv.size()));
                        missile.setX(randInv.getX());
                        missile.setY(randInv.getY());
                        missile.fireSound();
                    }

                }
                panel.invaderShoots(invMissileList, base);

                for (InvaderMissile missile : invMissileList) {

                    if (missile != null) {
                        if (missile.getVisible()) {
                            missile.draw(g2);
                        }
                    }
                }

                if (missile != null) {
                    if (missile.getVisible()) {
                        missile.draw(g2);
                    }
                }
                
                if(!panel.isIngame() || !panel.checkCollision()) {
                    panel.drawGameOver(g);
                }
            }
        };

        p.setBackground(Color.BLACK);
        p.setFocusable(true);
        p.setForeground(Color.GREEN);
        p.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;

                    case KeyEvent.VK_SPACE:
                        if (missile == null) {
                            missile = base.getMissile();
                            panel.setMissile(missile);
                        }
                }
            }


            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                }
            }
        });
        add(p);

        base = new Base(225, 373);
        topRow = new InvaderTop(70, 80);
        midRow = new InvaderMid(70, 105);
        midRow2 = new InvaderMid(70, 130);
        bottomRow = new InvaderBottom(70, 155);
        bottomRow2 = new InvaderBottom(70, 180);

        timer = new Timer(50, new GameCycle());
        timer.start();


        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                closeConfirmDialog();
                timer.stop();
            }
        });
        timer.start();
        System.out.println("after timer......");

        // Add menu bar of options on top
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // implementing options within menu bar
        // Game Option
        JMenu game = new JMenu("Game");
        menubar.add(game);
        // Options under "Game" --------- need to change null
        JMenuItem newGame = game.add("New Game");
        newGame.addActionListener(e -> newGameDialog());
        JMenuItem pause = game.add("Pause");
        pause.addActionListener(e -> timer.stop());
        JMenuItem resume = game.add("Resume");
        resume.addActionListener(e -> timer.start());
        JMenuItem quit = game.add("Quit");
        quit.addActionListener(e -> closeConfirmDialog());

        // Help option
        JMenu help = new JMenu("Help");
        menubar.add(help);
        // Option under "Help" --------- need to change null
        JMenuItem about = help.add("About...");
        about.addActionListener(e -> infoPopUp());

        setBackground(Color.BLACK);
        setSize(500, 450);
        // to disable resizing
        // -----------------------------------------------------------UNCOMMENT
        // NEXT LINE!!
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private class GameCycle
        implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        
        if (left)
            base.move(Drawable.Direction.LEFT);
        if (right)
            base.move(Drawable.Direction.RIGHT);
        if (missile != null) {
            if (missile.isVisible()) {
                missile.move(Drawable.Direction.UP);
            }
            else {
                missile = null;
            }
        }

        panel.updateInvaders();
        repaint();
        if (!panel.isIngame()) {
            System.out.println("Missile Hit!!!");
            timer.stop();
        }

        if (!panel.checkCollision()) {
            System.out.println("Invaders Collided!!!");
            timer.stop();
        }

    }


    // incomplete
    public void newGameDialog() {
        int result = JOptionPane
            .showConfirmDialog(SpaceInvaders.this, "Start a new game?");
        if (result == JOptionPane.YES_OPTION) {
            SpaceInvaders spcInv = new SpaceInvaders();
// Panel pan = new Panel();
// pan.createInvader();
            spcInv.repaint();

        }
    }


    // help - about pop up w/ info.
    public void infoPopUp() {
        JOptionPane.showMessageDialog(
            null,
            "SpaceInvaders by:" + "\n" + "Arooj Khan &\n" + "Alessi Reiter");
    }


    // Closes game is "yes" is selected
    public void closeConfirmDialog() {
        int result =
            JOptionPane.showConfirmDialog(SpaceInvaders.this, "Quitting?");
        if (result == JOptionPane.YES_OPTION) {
            dispose();

        } else {
            
        }
    }


    public static void main(String[] args) {
        JFrame f = new SpaceInvaders();
        f.setVisible(true);
    }
}
