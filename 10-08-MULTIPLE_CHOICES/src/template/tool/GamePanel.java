package template.tool;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**********
 * 
 * @author lyh
 * 工具类 用来作为小游戏绘制的动画的窗格
 *
 */


public class GamePanel extends JPanel implements MouseListener,MouseMotionListener{

    private JButton btnNewButton;

    public GamePanel(int delay) {

        Timer timer = new Timer(delay, new TimerListener());
        timer.start();
        addMouseListener(this);   
        addMouseMotionListener(this); 
        
        this.btnNewButton = new JButton("H");
		this.btnNewButton.setBounds(240, 266, 99, 45);
		this.add(btnNewButton);
		this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
    	//在这里执行每一次重新绘制的逻辑
        super.paintComponent(g);
    }

    class TimerListener implements ActionListener {
        /** Handle ActionEvent */
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		this.btnNewButton.setBounds(e.getX(),e.getY(), 99, 45);
		Random r = new Random();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}