package THUgame.tool;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/* 【用于贴图的类的模板】
 * 
 * --DIALOG--
 * version 1.0
 * via 林逸晗
 * update:20191016 00:59
 * 更新：实现了它
/*************************************************************	
 * 用于贴图的类，继承了JPanel（镶板）
 * 这是一个工具类，具体用法见例程
 * 用法：
 * 		JPanel 背景 = new ImagePanel(路径,0,0,图片宽度,图片高度); （支持缩放）
 * 		背景.setBounds(0,0,图片宽度,图片高度); (如果要完全填充，图片高度和宽度 和 添加进入的镶板一致即可）
 * 		Panel.add(背景);
 *************************************************************/

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String pathOfImage;
	private int  x1;
	private int  y1;
	private int  width;
	private int  height;
	
	public ImagePanel(String pathOfImage,int x1,int y1,int width,int height){
		this.pathOfImage=pathOfImage;
		this.x1=0;
		this.y1=0;
		this.width=width;
		this.height=height;
	}

	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource(pathOfImage));
		g.drawImage(icon.getImage(), x1, y1, width, height, this);
	}
}
