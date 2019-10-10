package template.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import template.datapack.DataPack;
import template.main.EventManager;
import template.tool.ImagePanel;
import java.awt.Color;


/*
 * template version 1.2
 * 可视化界面模板
 * update:20190930 18:30
 * 跟新：解决了界面闪烁的问题
 * 		跟新了一些类的结构和注释的问题
 * 		注释中：//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥***¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
 * 			表示最重要的事情
 * 		蓝色的部分则是表示对代码块的解释
 * 		细节用单句注释阐述
 * 		推荐收起后再看代码
 * 
 * 	推荐看WinInDom.java的注释
 * 
 **/
	/*************************************************************	
	 *
	 * 推荐直接复制粘贴pureDmoe使用，直接使用JFrame生成的窗口不太符合我们的需求
	 * 界面的实现可以参考这个文件
	 * 这个文件是可运行的，可以直接使用它进行原型开发
	 * 推荐看WinInD.java的注释
	 * 
	 *************************************************************/

public class WinPureDemo extends WinBase{
	
	
	/*************************************************************	
	 *
	 *【事件响应类】
	 * 
	 *************************************************************/
	static class puredemoMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		private EventManager mainGame;
		private JFrame frame;

		public void setFrame(JFrame frame) {
			this.frame=frame;
		}
		public void setGame(EventManager mainGame) {
			this.mainGame=mainGame;
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
			/*		START OF YOUR CODE		*/
			/*		END OF YOUR CODE		*/
			
			//例子：如果这个事件结束之后需要重新刷新，那么需要加这三行
			EventManager.dataPackage=dataPackage;
			synchronized(mainGame) {
				mainGame.notify();
			}
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
	
	
	/*************************************************************	
	 * 【构造函数】
	 * 不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 *************************************************************/
	public WinPureDemo(EventManager mainGame,JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 * 最后放一下背景
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);

		JPanel Background=new ImagePanel("imgsrc//dom.jpg",0, 0, 1080, 720);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		
		puredemoMouseListener.dataPackage=dataPackage;
		
		/*		START OF YOUR CODE		*/
		/*****			
		 * 重要，事件监听器一定要把mainGame传入
		 ****/
		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 恢复显示，按照流程做就不会消失，之后四行不允许更改
		 ******************************************************************/
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
		frame.setVisible(true);
	}
}


