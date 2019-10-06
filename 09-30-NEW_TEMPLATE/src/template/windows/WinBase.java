package template.windows;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import template.datapack.DataPack;
import template.main.EventManager;


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
}
