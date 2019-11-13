package THUgame.tool;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GifPanel extends JLabel implements Runnable {

    public Image image;
    private int framecount;
    private int count=0;
    private boolean unlimitedrepeat=false;
    // 用以刷新paint函数
    Thread refreshThread;

    public GifPanel(String pathOfImage,int x1,int y1,int width,int height,int framecount) {
    	this.setBounds(x1, y1, width,height);
    	Image image = Toolkit.getDefaultToolkit().createImage(GifPanel.class.getResource(pathOfImage));
    	this.framecount=framecount;
    	if (this.framecount==-1)
    		unlimitedrepeat=true;
        this.image = image;
        refreshThread = new Thread(this);
        refreshThread.start();
    }
    
    public GifPanel(String pathOfImage,int x1,int y1,int width,int height) {
    	this.setBounds(x1, y1, width,height);
    	Image image = Toolkit.getDefaultToolkit().createImage(GifPanel.class.getResource(pathOfImage));
    	this.framecount=-1;
    	unlimitedrepeat=true;
        this.image = image;
        refreshThread = new Thread(this);
        refreshThread.start();
    }

	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graph = (Graphics2D) g;
        if (image != null) {
            // 全屏描绘图片
            graph.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image
                    .getWidth(null), image.getHeight(null), null);
        }
    }

    /**
     * 隔100毫秒刷新一次
     */
    public void run() {
        while (this.count<this.framecount || unlimitedrepeat) {
            this.repaint();// 这里调用了Paint
            try {
                Thread.sleep(200);// 休眠100毫秒
                this.count+=1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}