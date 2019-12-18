package THUgame.FinalBird.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GamePanel extends JPanel implements Runnable {

    private Game game;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);


        g2D.setColor(Color.BLACK);

        if (!game.started) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.drawString(Integer.toString(game.score), 10, 465);
        }

        if (game.gameover) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
        }
    }

    public void run() {
    	JPanel EventPanel = new JPanel();
        JTextPane txtpnshi = new JTextPane();
        EventPanel.setBounds(0, 0, 50, 50);
		//txtpnshi.setBounds(170, 100, 220, 100);
		txtpnshi.setOpaque(false);
		txtpnshi.setEditable(false);
		txtpnshi.setText("这一次的作业稍微有些难度，需要花点心思来做。规则：尽快从迷宫的一头走向另一头");
		txtpnshi.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		EventPanel.add(txtpnshi);
		this.add(EventPanel);
		update();
		boolean start = false;
		
        try {
            while (start) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
