package template.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import template.datapack.DataPack;
import template.listener.demoMouseListener;
import template.main.EventManager;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.border.LineBorder;

/*
 * template version 1.1
 * 可视化界面模板【纯净版】
 * 可以直接开发，
 * update:20190928 23:49
 * 
 **/
	/*************************************************************	
	 *
	 * 推荐直接复制粘贴使用，直接使用JFrame生成的窗口不太符合我们的需求
	 * 流程：
	 * 	1.新建一个JFrame project
	 * 	2.把源代码都删了，把这个程序复制进去
	 * 	3.改类名和构造函数名
	 *  4.在Design中把所有组建都删掉
	 *  5.添加自己的组件，添加自己的动作
	 *  
	 *  第四步完成后会生成pureDemo.java这样的文件，就可以开始你的创作啦～
	 * 
	 *************************************************************/


public class PureDemo{

	public EventManager mainGame;
	static public DataPack dataPackage;//静态成员属性，好处在于没有对象的时候就可以传入
	
	public PureDemo(EventManager mainGame,JFrame frame) {
		/*************************************************************	
		 *
		 * 不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
		 * 好处：不然窗口就会关掉在开起来，很讨厌
		 * 坏处：不好写
		 * 目前问题：还是会闪，第一句如果注释掉就不会闪，但是组建可能加载不出来
		 * 这一部分不要动（按照流程做的话就不会变）
		 * 
		 *************************************************************/
		frame.setVisible(false);
		frame.getContentPane().removeAll();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		
		
		
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(backgroundPanel);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		
		demoMouseListener.dataPackage=dataPackage;
		
		
		/*		START OF YOUR CODE		*/
		demoMouseListener clickgotoclass=new demoMouseListener(0);//设置鼠标监听器，发生0号事件
		demoMouseListener clicksleep=new demoMouseListener(1);//设置鼠标监听器，发生1号事件
		/*****************************************************************				
		 * 重要，事件监听器一定要把mainGame传入
		 ******************************************************************/
		clickgotoclass.setGame(mainGame);
		clicksleep.setGame(mainGame);
		/*		END OF YOUR CODE		*/
    	
    	/*****************************************************************				
		 * 恢复显示，按照流程做就不会消失
		 ******************************************************************/
    	frame.setVisible(true);
	}
}


