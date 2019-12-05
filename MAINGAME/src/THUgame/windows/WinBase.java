package THUgame.windows;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;

/* 
 * 可视化界面模板的基类
 * 
 * --DIALOG--
 * 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * version 1.3
 * via 林逸晗
 * update:20191018 01:00 轻量化
 * 
 * version 1.2
 * via 林逸晗
 * update:20190930 18:30
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
		static protected JPanel todolistPanel;
		static protected boolean showToDoList=false;
		
		static protected abstract class BaseMouseListener implements MouseListener{
			private EventManager mainGame;
			private JFrame frame;	
			protected int safeGuardCount=0;
			public void setFrame(JFrame frame) {
				this.frame=frame;
			}
			public void setGame(EventManager mainGame) {
				this.mainGame=mainGame;
			}
	}
		public void setIcon(String file,JButton com){ 
	        ImageIcon ico=new ImageIcon(getClass().getResource(file)); 
	        ico.getImage();
			Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),Image.SCALE_DEFAULT);
	        ico=new ImageIcon(temp); 
	        com.setIcon(ico); 
	    } 
		
		public void setSelectedIcon(String file,JButton com){ 
	        ImageIcon ico=new ImageIcon(getClass().getResource(file)); 
	        ico.getImage();
			Image temp=ico.getImage().getScaledInstance(com.getWidth(),com.getHeight(),Image.SCALE_DEFAULT);
	        ico=new ImageIcon(temp); 
	        com.setPressedIcon(ico); 
	    } 
		
		
}
