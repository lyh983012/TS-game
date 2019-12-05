package THUgame.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

/*
 * 【地图界面】
 * 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 *  update:20191028 01:07 
 *  via：林逸晗
 *  更新：绘制地图转换以及过场动画
 * 
 **/


public class WinMap extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 * 
	 *************************************************************/
	static private class demoMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		static public JFrame frame;
		private JButton button;
		private int mode;
		Timer timer;
		
		public demoMouseListener(int i){
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
			if(safeGuardCount==0) {
				safeGuardCount++;
				if(mode==0) {
					dataPackage.choiceA="clickbackToDom";	//点按钮0（睡觉按钮）返回sleep
				}else if(mode ==1){
					dataPackage.choiceA="clickGoToClassAfternoon";//点按钮1（自习按钮）返回selfstudy
				}else if(mode ==2){
					dataPackage.choiceA="clickGoToClassMorning";//点按钮2（上课按钮）返回gotoclass
				}else if(mode ==3){
					dataPackage.choiceA="clickGoToSTA";//点按钮3（科协按钮）返回gotoclass
				}else if(mode ==4){
					dataPackage.choiceA="clickGoToExam";//点按钮4（考试按钮）返回gotoclass
				}else if(mode ==5){
					int K=(dataPackage.term-3)*4+dataPackage.week;
					dataPackage.choiceA="clickGoToLab"+K;//点按钮4（考试按钮）返回gotoclass
				}					
				timer=new Timer(200,new ActionListener()
			    	{
		    			int count=0;
						@Override
						public void actionPerformed(ActionEvent e) {
							count+=1;
							frame.getContentPane().removeAll();
							JPanel bc = new ImagePanel("imgsrc//WinMap//"+(count%4+1)+".png",0, 0, 1080, 720);	
							bc.setBounds(0, 0, 1080, 720);
							frame.getContentPane().add(bc);
							frame.getContentPane().repaint();
							if(count==10) {
								timer.stop();
								dataPackage.eventFinished=true;
								synchronized(mainGame) {
									mainGame.notify();
								}
							}
						}
			    	});
		    	timer.start();
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
	 * 	
	 * 【构造函数】
	 * 		不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 * 
	 *************************************************************/
	public WinMap(EventManager mainGame,JFrame frame) {
		
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
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		/*************************************************************	
		 *【按钮】
		 *		按钮设置流程：
		 *		1.创建按钮
		 *		2.取消默认的边框
		 *		3.设置坐标和大小
		 *		4.设置一下两种状态的图片，调用的是虚基类里的接口
		 *		5.把按钮加入panel里
		 *************************************************************/
		JButton backToDom = new JButton();
		backToDom.setBorderPainted(false);
		backToDom.setContentAreaFilled(false);
		backToDom.setBounds(425, 50, 75, 50);
		setIcon("/imgsrc/WinMap/BackToDom.png",backToDom);
		setSelectedIcon("/imgsrc/WinMap/BackToDom_Press.png",backToDom);
		backgroundPanel.add(backToDom);
		
		JButton goToExam = new JButton();
		goToExam.setBorderPainted(false);
		goToExam.setContentAreaFilled(false);
		goToExam.setBounds(425, 400, 75, 50);
		setIcon("/imgsrc/WinMap/GoToExam.png",goToExam);
		setSelectedIcon("/imgsrc/WinMap/GoToExam_Press.png",goToExam);
		if(dataPackage.date==6 && dataPackage.week==4 && dataPackage.time>=8 && dataPackage.time<=18) {
			backgroundPanel.add(goToExam);
		}
		
		JButton GoToClassMorning = new JButton();
		GoToClassMorning.setBorderPainted(false);
		GoToClassMorning.setBounds(600, 350, 75, 50);
		GoToClassMorning.setContentAreaFilled(false);
		setIcon("/imgsrc/WinMap/GoToClassMorning.png",GoToClassMorning);
		setSelectedIcon("/imgsrc/WinMap/GoToClassMorning_Press.png",GoToClassMorning);
		if(!dataPackage.todayMorningClass.equals("----") && dataPackage.time>=8 && dataPackage.time<=10) {
			backgroundPanel.add(GoToClassMorning);
		}
		JButton GoToClassAfternoon = new JButton();
		GoToClassAfternoon.setContentAreaFilled(false);
		GoToClassAfternoon.setBorderPainted(false);
		GoToClassAfternoon.setBounds(280, 200, 75, 50);
		setIcon("/imgsrc/WinMap/GoToClassNoon.png",GoToClassAfternoon);
		setSelectedIcon("/imgsrc/WinMap/GoToClassNoon_Press.png",GoToClassAfternoon);
		if(!dataPackage.todayAfternoonClass.equals("----") && dataPackage.time>=13 && dataPackage.time<=15) {
			backgroundPanel.add(GoToClassAfternoon);
		}
		
		JButton GoToSTA = new JButton();
		GoToSTA.setContentAreaFilled(false);
		GoToSTA.setBorderPainted(false);
		GoToSTA.setBounds(720, 640, 75, 50);
		setIcon("/imgsrc/WinMap/GoToSTA.png",GoToSTA);
		setSelectedIcon("/imgsrc/WinMap/GoToSTA_Press.png",GoToSTA);
		if(dataPackage.time>=8 && dataPackage.time<=18 && dataPackage.week<=3 && (dataPackage.date==2||dataPackage.date==5)) {
			backgroundPanel.add(GoToSTA);
		}
		
		JButton GoToLab = new JButton();
		GoToLab.setContentAreaFilled(false);
		GoToLab.setBorderPainted(false);
		GoToLab.setBounds(860, 320, 75, 50);
		setIcon("/imgsrc/WinMap/GoToLab.png",GoToLab);
		setSelectedIcon("/imgsrc/WinMap/GoToLab_Press.png",GoToLab);
		if(dataPackage.joinResearch && dataPackage.date==4 && dataPackage.time<17 && 12<=dataPackage.time) {
			backgroundPanel.add(GoToLab);
		}
		/*************************************************************	
		 * 【镶时钟】
		 * 		不需要修改
		 * 		简而言之就是显示一个Table
		 *************************************************************/
		
		
		JPanel timePack = new JPanel();
		timePack.setLayout(null);
		timePack.setOpaque(false);//注意要设成透明的
		timePack.setBounds(66, 32, 195, 90);
		
			JPanel timePanel = new JPanel();
			timePanel.setBounds(0, 0, 195, 90);
			JPanel timeBackgoundPanel = new ImagePanel("imgsrc//taili.png",0, 0, 195, 90);	
			timeBackgoundPanel.setBounds(0, 0, 195, 90);
			
			timeBackgoundPanel.setOpaque(false);//注意要设成透明的
			timePanel.setOpaque(false);//注意要设成透明的
			timePanel.setLayout(null);
			
			JLabel timeText = new JLabel("当前时间为："+String.valueOf(dataPackage.time)+" 时");
			timeText.setBounds(6, 60, 172, 16);
			timePanel.add(timeText);
			
			JLabel dateText = new JLabel("今天是：第"+String.valueOf(dataPackage.term)+"学期"+String.valueOf(dataPackage.week)+"周"+String.valueOf(dataPackage.date)+"日");
			dateText.setBounds(6, 35, 172, 16);
			timePanel.add(dateText);
			
		timePack.add(timePanel);
		timePack.add(timeBackgoundPanel);
		backgroundPanel.add(timePack);
			

		/*************************************************************	
		 * 镶待办事项 这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		JPanel todoList = new JPanel();
		todoList.setLayout(null);
		todoList.setOpaque(false);	
		todoList.setBounds(752, 35, 263, 189);
			
			JLabel label = new JLabel("待办事项");
			label.setForeground(Color.WHITE);
			label.setBounds(20, 25, 100, 18);
			todoList.add(label);
			label.setFont(new Font("STFangsong", Font.PLAIN, 16));
				
			JLabel label2 = new JLabel("1.上午课:"+dataPackage.todayMorningClass);
			label2.setForeground(Color.WHITE);
			label2.setBounds(20, 55, 200, 18);
			todoList.add(label2);
			label2.setFont(new Font("STFangsong", Font.PLAIN, 16));
				
			JLabel label3 = new JLabel("2.下午课:"+dataPackage.todayAfternoonClass);
			label3.setForeground(Color.WHITE);
			label3.setBounds(20, 85, 200, 18);
			todoList.add(label3);
			label3.setFont(new Font("STFangsong", Font.PLAIN, 16));
				
			JLabel label4 = new JLabel("3.");
			label4.setForeground(Color.WHITE);
			label4.setBounds(20, 115, 100, 18);
			todoList.add(label4);
			label4.setFont(new Font("STFangsong", Font.PLAIN, 16));
			
			JPanel dbsxBackgruond = new ImagePanel("imgsrc//todoList.png",0, 0, 263, 189);
			dbsxBackgruond.setOpaque(false);	
			dbsxBackgruond.setBounds(0, 0, 263, 189);
		
		todoList.add(dbsxBackgruond);
		dbsxBackgruond.setLayout(null);
		backgroundPanel.add(todoList);
		/*************************************************************	
		 * 【放背景图】
		 * 		最后放。
		 *************************************************************/
		
		JPanel Background=new ImagePanel("imgsrc//WinMap/map.png",0, 0, 1080, 720);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		/*****************************************************************				
		 * 
		 * 【细节设定】
		 * 在用插件绘制完界面之后进行界面内数值设定
		 * 利用数据包进行显示控件的输出的设置
		 * 
		 *****************************************************************/
		/*		START OF YOUR CODE		*/

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		demoMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		demoMouseListener.mainGame=mainGame;
		demoMouseListener.frame=frame;
		demoMouseListener clickbackToDom=new demoMouseListener(0);//设置鼠标监听器，发生0号事件
		demoMouseListener clickGoToClassAfternoon=new demoMouseListener(1);//设置鼠标监听器，发生1号事件
		demoMouseListener clickGoToClassMorning=new demoMouseListener(2);//设置鼠标监听器，发生2号事件
		demoMouseListener clickGoToSTA=new demoMouseListener(3);//设置鼠标监听器，发生2号事件
		demoMouseListener clickGoToExam=new demoMouseListener(4);//设置鼠标监听器，发生2号事件
		demoMouseListener clickGoToLab=new demoMouseListener(5);//设置鼠标监听器，发生2号事件

		clickbackToDom.setButton(backToDom);
		clickGoToClassAfternoon.setButton(GoToClassAfternoon);
		clickGoToClassMorning.setButton(GoToClassMorning);
		clickGoToSTA.setButton(GoToSTA);
		clickGoToExam.setButton(goToExam);
		clickGoToLab.setButton(GoToLab);
		
		backToDom.addMouseListener(clickbackToDom);//0号事件是 睡觉按钮 被点击
		GoToClassAfternoon.addMouseListener(clickGoToClassAfternoon);//1号事件是 去自习按钮 被点击
		GoToClassMorning.addMouseListener(clickGoToClassMorning);
		GoToSTA.addMouseListener(clickGoToSTA);
		goToExam.addMouseListener(clickGoToExam);
		GoToLab.addMouseListener(clickGoToLab);
		/*		  END OF YOUR CODE		*/
    	    	
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