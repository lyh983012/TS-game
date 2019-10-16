package THUgame.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
/*
 * template version 1.3
 * 可视化界面模板
 * 
 * update:20190930 18:30
 * 更新：解决了界面闪烁的问题
 * 		跟新了一些类的结构和注释的问题
 * 		注释中：//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥***¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
 * 			表示最重要的事情
 * 		蓝色的部分则是表示对代码块的解释
 * 		细节用单句注释阐述
 * 		推荐收起后再看代码
 * 		推荐看WinInDom.java的注释
 * 
 * update:20191006 18:30
 * 		加入了按钮的可见／不可见
 * 		加入了时钟（简陋）
 * 		鼠标事件响应不需要再写set game了，使用方法和dataPack的传递一样
 * 
 * update:20191010 18:30
 * 		加入对话框
 * 		加入对话的逻辑
 * 
 * update:20191010 18:30
 * 		加入了UI及使用方法
 * 			对于镶板的背景，流程为：
 * 				1.建一个Panel	
 * 				2.Panel里建两个subPanel
 * 				3.底下的用imagePanel工具类放图片，上面的放控件
 * 				4.设置两个Panel为透明
 * 			对于按钮的背景，流程为
 * 				1.创建按钮
 * 				2.取消默认的边框	
 * 				3.设置坐标和大小
 * 				4.设置一下两种状态的图片，调用的是虚基类里的接口
 * 				5.把按钮加入panel里
 * 		更新了按钮的UI和对话框的UI
 * 			设置UI的方法可以看本类中【按钮】和【对话框】的部分
 * 
 * update:20191014 18:30
 * 		加入了游戏界面（在morning class的窗口里）
 * 		游戏panel直接添加就可以运行，但是需要注册一下数据包和控制进程（maingame）具体用法见morning
 * 		游戏panel在tool里有，在Game里有一个可运行的例程
 **/


public class WinWelcome extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	static private class welcomeMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public welcomeMouseListener(int i){
			this.mode=i;
		}
		
		public void setFrame(JFrame frame) {
			this.frame=frame;
		}
		
		public void setButton(JButton button) {
			this.button=button;
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
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
			EventManager.dataPackage=dataPackage;
			synchronized(mainGame) {
				mainGame.notify();
			}
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
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
	 * 	
	 * 【构造函数】
	 * 		不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 * 
	 *************************************************************/
	public WinWelcome(EventManager mainGame,JFrame frame) {
		
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		/*************************************************************	
		 *【背景镶板】
		 * 		所有的组件都在里面，两个按钮直接用插件拖进去的
		 * 		这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性 
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setOpaque(false);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		
		JPanel backgroundImage=new ImagePanel("imgsrc//eb.png",0, 0, 1080, 720);
		backgroundImage.setBounds(0, 0, 1080, 720);
		backgroundImage.setOpaque(false);
		backgroundImage.setLayout(null);
		
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setBounds(850,550,150,50);
		button.setText("确定");
		
		JLabel textLabel = new JLabel();
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setText("<html>这样会自动换行</html>");
		textLabel.setOpaque(false);
		textLabel.setFont(new Font("印品黑体", Font.PLAIN, 30));
		textLabel.setBounds(80, 60, 948, 460);
		
		backgroundPanel.add(button);
		backgroundPanel.add(textLabel);
		backgroundPanel.add(backgroundImage);
		
		switch(dataPackage.count) {
		case 0:
			String text = "<html>你曾被称为学霸，因学习成绩优异受到同学仰慕。<br>";
			if(dataPackage.nightOwl)
				text = text + "你有熬夜的习惯，并且熬夜对第二天精力影响不太大。<br>";
			if(dataPackage.scientificMad)
				text = text + "你学术能力强，对大学阶段的科研有所憧憬。<br>";
			if(dataPackage.sportsMan)
				text = text + "你热爱运动，体质过人。<br>";
			if(dataPackage.eSportsPlayer)
				text = text + "你热爱游戏，喜欢用游戏来放松。<br>";
			if(dataPackage.nextWinner)
				text = text + "你有很强的社工热情和相当的社工经验。<br>";
			if(dataPackage.computerExpert)
				text = text + "你会自己装机和修理电脑。<br>";
			if(dataPackage.richPerson)
				text = text + "你家庭背景良好，财富观念先进。<br>";
			text = text + "在这里，你会遇到丰富多彩的事件、各种各样的同学、做出不同的选择。<br>在短短几个学期之后，你会走向何方？";
			textLabel.setText(text);
			break;
		case 1:
			textLabel.setFont(new Font("印品黑体", Font.PLAIN, 50));
			textLabel.setText("那么...");
			break;
		case 2:
			textLabel.setFont(new Font("印品黑体", Font.PLAIN, 50));
			textLabel.setText("大学生活，我来啦！");
			break;
		}
		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		welcomeMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		welcomeMouseListener.mainGame=mainGame;
		
		welcomeMouseListener click=new welcomeMouseListener(0);//设置鼠标监听器，发生0号事件

		click.setButton(button);

    	button.addMouseListener(click);//0号事件是 睡觉按钮 被点击

		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 【恢复显示】
		 * 必须存在的四行代码！！！！
		 ******************************************************************/
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}