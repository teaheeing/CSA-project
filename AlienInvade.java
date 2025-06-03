import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.ImageObserver;

public class AlienInvade implements KeyListener {
    private static final int LOGICAL_WIDTH = 50;
    private static final int LOGICAL_HEIGHT = 100;

    Player player = new Player(LOGICAL_WIDTH / 2, 10);
    private int time = 0;
    private int score = 0;
    private JFrame frame;
    private JLabel hpLabel;
    private JLabel scoreLabel;
    ArrayList<Alien> Aliens = new ArrayList<>();
    ArrayList<Bullet> Bullets = new ArrayList<>();
    private long lastShootTime = 0;
    private boolean gameOver = false;

    public Alien generateAlien() {
        int x = (int)(Math.random() * (LOGICAL_WIDTH - 10));
        int y = LOGICAL_HEIGHT - 10;
        return new Alien(x, y, 1);
    }

    public void draw(Graphics g, int width, int height) {
        ImageObserver observer = (ImageObserver) frame.getContentPane();
        int px = (int)(player.getX() / (double)LOGICAL_WIDTH * width);
        int py = (int)((LOGICAL_HEIGHT - player.getY() - player.getHeight()) / (double)LOGICAL_HEIGHT * height);
        int pw = (int)(player.getWidth() / (double)LOGICAL_WIDTH * width);
        int ph = (int)(player.getHeight() / (double)LOGICAL_HEIGHT * height);
        g.drawImage(new ImageIcon("soldier.png").getImage(), px, py, pw, ph, observer);

        for (Alien alien : Aliens) {
            int ax = (int)(alien.getX() / (double)LOGICAL_WIDTH * width);
            int ay = (int)((LOGICAL_HEIGHT - alien.getY() - alien.getHeight()) / (double)LOGICAL_HEIGHT * height);
            int aw = (int)(alien.getWidth() / (double)LOGICAL_WIDTH * width);
            int ah = (int)(alien.getHeight() / (double)LOGICAL_HEIGHT * height);
            g.drawImage(new ImageIcon("alien.png").getImage(), ax, ay, aw, ah, observer);
        }

        for (Bullet bullet : Bullets) {
            int bx = (int)(bullet.getX() / (double)LOGICAL_WIDTH * width);
            int by = (int)((LOGICAL_HEIGHT - bullet.getY() - bullet.getHeight()) / (double)LOGICAL_HEIGHT * height);
            int bw = (int)(bullet.getWidth() / (double)LOGICAL_WIDTH * width);
            int bh = (int)(bullet.getHeight() / (double)LOGICAL_HEIGHT * height);
            g.drawImage(new ImageIcon("bullet.png").getImage(), bx, by, bw, bh, observer);
        }

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER", width/2 - 100, height/2);
        }
    }

    public AlienInvade() {
        frame = new JFrame("Alien Invade");
        frame.setSize(600, 600);
        frame.setLocation(50, 100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setLayout(null);
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        hpLabel = new JLabel("HP = " + player.getHp());
        hpLabel.setBounds(10, 10, 150, 20);
        hpLabel.setForeground(Color.WHITE);
        panel.add(hpLabel);

        scoreLabel = new JLabel("Score = " + score);
        scoreLabel.setBounds(10, 40, 150, 20);
        scoreLabel.setForeground(Color.WHITE);
        panel.add(scoreLabel);

        frame.setContentPane(panel);
        frame.setVisible(true);
        panel.addKeyListener(this);

        Timer timer = new Timer(50, e -> {
            if (gameOver) return;

            time++;
            int spawnRate = (time < 60) ? 40 : (time < 120) ? 20 : 10;
            if (time % spawnRate == 0) Aliens.add(generateAlien());

            for (int i = 0; i < Bullets.size(); i++) {
                Bullet bullet = Bullets.get(i);
                if (bullet.getY() >= LOGICAL_HEIGHT) {
                    Bullets.remove(i);
                    i--;
                } else {
                    bullet.updateY(bullet.getY() + bullet.getSpeed());
                }
            }

            for (int j = 0; j < Bullets.size(); j++) {
                Bullet bullet = Bullets.get(j);
                for (int i = 0; i < Aliens.size(); i++) {
                    Alien alien = Aliens.get(i);
                    if (bullet.getX() + bullet.getWidth() > alien.getX() &&
                        bullet.getX() < alien.getX() + alien.getWidth() &&
                        bullet.getY() + bullet.getHeight() > alien.getY() &&
                        bullet.getY() < alien.getY() + alien.getHeight()) {
                        Bullets.remove(j);
                        j--;
                        Aliens.remove(i);
                        i--;
                        score++;
                        break;
                    }
                }
            }

            if (time % 10 == 0) {
                for (int i = 0; i < Aliens.size(); i++) {
                    Alien alien = Aliens.get(i);
                    alien.updateY(alien.getY() - alien.getSpeed());
                    if (alien.getY() <= 0) {
                        Aliens.remove(i);
                        i--;
                        player.updateHp(player.getHp() - 1);
                    }
                }
            }

            if (player.getHp() <= 0) gameOver = true;

            hpLabel.setText("HP = " + player.getHp());
            scoreLabel.setText("Score = " + score);
            panel.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        new AlienInvade();
    }

    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image bg = new ImageIcon("background.gif").getImage();
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            draw(g, getWidth(), getHeight());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) return;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            if (player.getX() + player.getWidth() < LOGICAL_WIDTH)
                player.updateX(player.getX() + 2);
        } else if (key == KeyEvent.VK_LEFT) {
            if (player.getX() > 0)
                player.updateX(player.getX() - 2);
        } else if (key == KeyEvent.VK_SPACE) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShootTime >= 200) {
                Bullets.add(new Bullet(player.getX() + player.getWidth()/2 - 1, player.getY() + player.getHeight()));
                lastShootTime = currentTime;
            }
        }
        ((GamePanel)frame.getContentPane()).repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
