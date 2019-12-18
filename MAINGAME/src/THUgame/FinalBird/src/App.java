package THUgame.FinalBird.src;

import javax.swing.*;

public class App {

    public static int WIDTH = 500;
    public static int HEIGHT = 600;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        //frame.setBounds(254, 134, 536, 398);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);
    }
}
