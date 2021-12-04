import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class SpaceInvadersFrame
    extends JFrame {

    private boolean       left, right, up, down;
    private Missile       missile;
    private Base          base;

    private InvaderTop    topRow;
    private InvaderMid    midRow;
    private InvaderMid    midRow2;
    private InvaderBottom bottomRow;
    private InvaderBottom bottomRow2;

    private Panel         panel = new Panel();
    private int           width;
    private int           height;

    private static String IMG_NAME1 = "img_invadertopA.gif";
    private static String IMG_NAME2 = "img_invadermiddleA.gif";
    private static String IMG_NAME3 = "img_invaderbottomA.gif";

    private List<Invader> topInv;
    private List<Invader> botInv;
    private List<Invader> midInv;
    private List<Invader> allInvaders;

    
    
    public SpaceInvadersFrame() {

        // Title
        super("Space Invaders dummy");
    
        JPanel p = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D)g;
                base.draw(g2);
                topRow.draw(g2);
                midRow.draw(g2);
                midRow2.draw(g2);
                bottomRow.draw(g2);
                bottomRow2.draw(g2);

                if (missile != null) {
                    missile.draw(g2);

                }
            }
        };
        p.setBackground(Color.BLACK);
        p.setFocusable(true);
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

        Timer timer = new Timer(50, e -> {
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
            repaint();

        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });
        timer.start();

        // Add menu bar of options on top
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // implementing options within menu bar
        // Game Option
        JMenu game = new JMenu("Game");
        menubar.add(game);
        // Options under "Game" --------- need to change null
        JMenuItem newGame = game.add("New Game");
        newGame.addActionListener(null);
        JMenuItem pause = game.add("Pause");
        pause.addActionListener(null);
        JMenuItem resume = game.add("Resume");
        resume.addActionListener(null);
        JMenuItem quit = game.add("Quit");
        quit.addActionListener(e -> closeConfirmDialog());

        // Help option
        JMenu help = new JMenu("Help");
        menubar.add(help);
        // Option under "Help" --------- need to change null
        JMenuItem about = help.add("About...");
        about.addActionListener(e -> infoPopUp());

        System.out.println(bottomRow.getX() + ", " + bottomRow.getY());

        setBackground(Color.BLACK);
        setSize(500, 450);
        // to disable resizing
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }




    public void infoPopUp() {
        JOptionPane.showMessageDialog(
            null,
            "SpaceInvaders by:" + "\n" + "Arooj Khan \n" + "Alessi Reiter");
    }


    // Copied from class - closes game is "yes" is selected
    public void closeConfirmDialog() {
        int result =
            JOptionPane.showConfirmDialog(SpaceInvadersFrame.this, "Quitting?");
        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
    }


    public static void main(String[] args) {
        JFrame f = new SpaceInvadersFrame();
        f.setVisible(true);
    }

}
