package THUgame.windows;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import javax.swing.SwingConstants;
/* 
 * 显示游戏的文字背景
 * 
 * --DIALOG--
 * update：20191018 16:30
 * via 林逸晗
 * 更新；解决了按钮的问题，更新了GUI
 * 
 * version 1.0
 * via 黄天翼
 * update:20191018 00:59
 * 
 **/


public class WinBackground extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	static private class backgroundMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public backgroundMouseListener(int i){
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
	public WinBackground(EventManager mainGame,JFrame frame) {
		
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinBackground/Welcome.png",0, 0, 1080, 720);
		backgroundImage.setBounds(0, 0, 1080, 720);
		backgroundImage.setOpaque(false);
		backgroundImage.setLayout(null);
		/*
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setBounds(850,550,150,50);
		button.setText("确定");
		//JPanel panelbutton = new JPanel();
		//panelbutton.setLayout(null);
		//panelbutton.setOpaque(false);
		//panelbutton.setBounds(850,550,70,70);
		//panelbutton.setLayout(null);
		//JLabel Text = new JLabel("确定");
		//Text.setFont(new Font("印品黑体", Font.PLAIN, 16));
		//Text.setForeground(Color.BLACK);
		//Text.setBounds(0, 0, 70,70);
		//Text.setHorizontalAlignment(SwingConstants.CENTER);
		 */
		
		JButton button= new JButton();	
		button.setBorderPainted(false);
		button.setFont(new Font("印品黑体", Font.PLAIN, 19));
		button.setForeground(Color.BLACK);
		button.setBounds(850, 550, 80, 80);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		
		setIcon("/imgsrc/WinBackground/ca.png",button);
		setSelectedIcon("/imgsrc/WinBackground/cb.png",button);
		
		//panelbutton.add(Text);

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
			textLabel.setText("<html>9102年，我们的主角____（玩家设定），一个来自华国安徽南京的__（男／女）生，因为天天玩一款叫做MineCraft的游戏，连挂30学分，从北方大学退学。前往该省最顶尖的中学复读。拿到全省第五的高考成绩，毅然决然选择了隔壁，因此当地报纸授其以“北大得不到的学生”的荣誉称号。</html>");
			break;
		case 1:
			textLabel.setText("<html>进入华清大学的____认识到了自己天天玩MineCraft是不对的，1.1472版本里的各种feature他也厌倦了。痛改前非的同时，ta也同时也卸载了电脑里的Dota30、CSOL200、看门狗981、COD4～9(后面的呢？不好玩没下载)、刺客信条豪华版全DLC以及无主之地1、2、3、4、5，怪物猎人还有红色警戒3和星际2也惨遭移除，同时也不忘把存有黑魂和只狼的硬盘清空、把Switch带着其塞尔达一起低价甩卖，只带了一个装有扫雷的电脑来到了我们的华清大学……</html>");
			break;
		case 2:
			textLabel.setText("<html>带着珍贵的录取通知书，你来到了华清大学。<br> 这里是国内高中生梦想中的圣地之一，与“隔壁”北方大学共称为两大顶尖高校。<br>告别了过往的传奇经历，你的大学生活即将开始...");
			break;
		}
		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		backgroundMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		backgroundMouseListener.mainGame=mainGame;
		
		backgroundMouseListener click=new backgroundMouseListener(0);//设置鼠标监听器，发生0号事件

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