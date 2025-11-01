import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Logic logic;
    int width = 360;
    int height = 640;

    // constructor
    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        setFocusable(true);
        addKeyListener(logic);

        // label skor ke panel
        JLabel scoreLabel = logic.getScoreLabel();
        scoreLabel.setBounds(10, 10, 200, 30);
        add(scoreLabel);
        setLayout(null);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Player player = logic.getPlayer();

        //gambar burung
        if (player != null) {
            g.drawImage(
                    player.getImage(),
                    player.getPosX(),
                    player.getPosY(),
                    player.getWidth(),
                    player.getHeight(),
                    null
            );
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(
                        pipe.getImage(),
                        pipe.getPosX(),
                        pipe.getPosY(),
                        pipe.getWidth(),
                        pipe.getHeight(),
                        null
                );
            }
        }
        // teks “Game Over” di layar
        if (logic.getIsGameOver()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times new roman", Font.BOLD, 28));
            g.drawString("GAME OVER!", 90, 250);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Tekan R untuk restart", 80, 290);
        }
    }
}
