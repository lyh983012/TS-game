package THUgame.windows;

import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;


/*
 * template version 1.2
 * 可视化界面模板的基类
 * update:20190930 18:30
 * 
 **/

/*************************************************************	
*【简要介绍，这个类不需要改】
*所有显示层的基类，是虚基类
*	想要实现的是内部的鼠标响应类
*		统一了分支事件绘图的结构
* 
*************************************************************/
	abstract public class WinBase {
		public EventManager mainGame;
		static public DataPack dataPackage;
		
		static protected abstract class BaseMouseListener implements MouseListener{
			private EventManager mainGame;
			private JFrame frame;		
			public void setFrame(JFrame frame) {
				this.frame=frame;
			}
			public void setGame(EventManager mainGame) {
				this.mainGame=mainGame;
			}
	}
		public void setIcon(String file,JButton com){ 
	        ImageIcon ico=new ImageIcon(getClass().getResource(file)); 
	        Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),ico.getImage().SCALE_DEFAULT);
	        ico=new ImageIcon(temp); 
	        com.setIcon(ico); 
	    } 
		
		public void setSelectedIcon(String file,JButton com){ 
	        ImageIcon ico=new ImageIcon(getClass().getResource(file)); 
	        Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),ico.getImage().SCALE_DEFAULT);
	        ico=new ImageIcon(temp); 
	        com.setSelectedIcon(ico); 
	    } 
		
}
