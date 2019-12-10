package THUgame.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.LineBorder;

import THUgame.Game.ShootGame;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;


/*【早课界面】
 * 
 * --DIALOG--
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * update:20191028 01:07
 * via：林逸晗
 * 更新：更改了界面UI，使之适配MAP
 * 
 * update:20191018 01:07
 * via：林逸晗
 * 更新：加入了游戏
 * 
 **/


public class WinMorningClass extends WinBase{
	
	static JPanel todolistPanel;
	static boolean showToDoList=false;
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	static private class InClassMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public InClassMouseListener(int i){
			this.mode=i;
		}
		public void setButton(JButton button) {
			this.button=button;
		}
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
			if(mode ==8){
				if(showToDoList) {
					showToDoList=false;
					ImageIcon ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/seemore1.png")); 
			        ico.getImage();
					Image temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setIcon(ico); 
			        ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/seemore2.png")); 
			        ico.getImage();
					temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setPressedIcon(ico); 
				}else {
					showToDoList=true;
					ImageIcon ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/pack1.png")); 
			        ico.getImage();
					Image temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setIcon(ico); 
			        ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/pack2.png")); 
			        ico.getImage();
					temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setPressedIcon(ico); 
				}
				todolistPanel.setVisible(showToDoList);//TODO:不知道为什么收不起来
				return;
			}
			
			/*		START OF YOUR CODE		*/
			if(safeGuardCount==0) {
				safeGuardCount++;
				if(mode==0) {
					dataPackage.choiceA="answer";
				}else if(mode==1){
					dataPackage.choiceA="ask";
				}else if(mode==2){
					dataPackage.choiceA="next";
				}else if(mode==3){
					dataPackage.choiceA="back";
				}
				/*		END OF YOUR CODE		*/
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				synchronized(mainGame) {
					mainGame.notify();
				}
			}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
			
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
	public WinMorningClass(EventManager mainGame,JFrame frame) {

		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 *************************************************************/

		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		
		/*************************************************************	
		 * 【小事件，这里分成两类，一类是结束提示，一类是游戏】
		 * 	游戏比较特殊，相当于执行了按钮的事件后附加了一个事件
		 * 		用之前，把mainGame和datapackage传给游戏
		 * 		这样游戏才可以从内部控制外部窗口的刷新	
		 * 		使用游戏的人不需要关注游戏内部的实现，只需要传参数进去就可以；
		 * 
		 *************************************************************/
		JPanel exitPanel = new JPanel();//将来可以用它来放临时小事件
		exitPanel.setOpaque(false);
		exitPanel.setBounds(253, 129, 536, 398);
		exitPanel.setLayout(null);
		
		JPanel upperlevel = new JPanel();
		upperlevel.setOpaque(false);
		upperlevel.setBounds(0, 0, 531, 363);
		upperlevel.setLayout(null);
		
		JPanel background = new ImagePanel("imgsrc//对话框.png",0, 0, 531, 363);
		background.setOpaque(false);
		background.setBounds(0, 0, 531, 363);
		background.setLayout(null);
	
			JLabel lblNewLabel_1 = new JLabel("已经下课了！");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(200, 107, 220, 68);
			
			JButton btnNewButton_3 = new JButton("离开教室……");
			btnNewButton_3.setBounds(165, 242, 190, 47);
			
		upperlevel.add(lblNewLabel_1);
		upperlevel.add(btnNewButton_3);	
		exitPanel.add(upperlevel);
		exitPanel.add(background);
			
		ShootGame.mainGame=mainGame;//注意这里！不然没办法结束游戏！
		ShootGame.dataPackage=dataPackage;//注意这里！不然没办法结束游戏！
		JPanel gamePack = new ShootGame(254, 134);//将来可以用它来放临时小事件	
		gamePack.setBounds(254, 134, 536, 398);
		gamePack.setLayout(null);
		gamePack.setOpaque(false);//注意要设成透明的
		
		if (dataPackage.trigSubEvent) {
			if(dataPackage.time==12) {
				backgroundPanel.add(exitPanel);
			}else  {
				backgroundPanel.add(gamePack);
			}
		}
		
		/*************************************************************	
		 * 【基本按钮】
		 *************************************************************/
		JButton btnNewButton = new JButton();
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(824, 545, 155, 55);
		setIcon("/imgsrc/WinMorningClass/answerUn.png",btnNewButton);
		setSelectedIcon("/imgsrc/WinMorningClass/answer.png",btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(824, 478, 155, 55);
		setIcon("/imgsrc/WinMorningClass/askUn.png",btnNewButton_1);
		setSelectedIcon("/imgsrc/WinMorningClass/ask.png",btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(824, 609, 155, 55);
		setIcon("/imgsrc/WinMorningClass/nextUn.png",btnNewButton_2);
		setSelectedIcon("/imgsrc/WinMorningClass/next.png",btnNewButton_2);
		
		if(!dataPackage.trigSubEvent){				//仅在未触发事件时添加按钮
			backgroundPanel.add(btnNewButton);	
			backgroundPanel.add(btnNewButton_1);
			backgroundPanel.add(btnNewButton_2);
		}
		/************************************************************	
		 * 【镶属性】
		 *  这一部分按照流程做的话就会自然消失的
		 *************************************************************/

		JPanel panel = new JPanel();
		panel.setBounds(64, 140, 197, 290);
		backgroundPanel.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setLayout(null);
		
		JLabel StudentIDLable = new JLabel("学号");
		StudentIDLable.setHorizontalAlignment(SwingConstants.CENTER);
		StudentIDLable.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		StudentIDLable.setBounds(26, 78, 32, 16);
		
		JTextPane nameShow = new JTextPane();
		nameShow.setEditable(false);
		nameShow.setBounds(84, 42, 73, 20);
		panel.add(nameShow);
		
		JLabel nameLable = new JLabel("姓名");
		nameLable.setHorizontalAlignment(SwingConstants.CENTER);
		nameLable.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		nameLable.setBounds(26, 42, 32, 24);
		panel.add(nameLable);
		
		JTextPane IDshow = new JTextPane();
		IDshow.setEditable(false);
		IDshow.setBounds(84, 76, 73, 20);
		panel.add(IDshow);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBounds(70, 119, 114, 20);
		panel.add(healthBar);
		
		JProgressBar Bar_progress = new JProgressBar();
		Bar_progress.setBounds(70, 139, 114, 20);
		panel.add(Bar_progress);
		
		JProgressBar Bar_Energy = new JProgressBar();
		Bar_Energy.setBounds(70, 165, 114, 20);
		panel.add(Bar_Energy);
		
		JProgressBar Bar_happiness = new JProgressBar();
		Bar_happiness.setBounds(70, 187, 114, 20);
		panel.add(Bar_happiness);
		
		JLabel label_workProgress = new JLabel("学习进度");
		label_workProgress.setBounds(10, 142, 52, 16);
		panel.add(label_workProgress);
		
		JLabel label_Energy = new JLabel("体力值");
		label_Energy.setBounds(10, 166, 52, 16);
		panel.add(label_Energy);
		
		JLabel label_health = new JLabel("健康值");
		label_health.setBounds(10, 118, 52, 16);
		panel.add(label_health);
		
		JLabel label_happy = new JLabel("心   情");
		label_happy.setBounds(10, 189, 52, 16);
		panel.add(label_happy);
		
		JLabel label_social = new JLabel("社交能力:");
		label_social.setBounds(10, 219, 92, 16);
		panel.add(label_social);
		
		
		JLabel label_Art = new JLabel("才艺能力:");
		label_Art.setBounds(10, 245, 92, 16);
		panel.add(label_Art);
		
		JLabel label_IQ = new JLabel("智商:");
		label_IQ.setBounds(100, 219, 84, 16);
		panel.add(label_IQ);
		
		JLabel label_lucky = new JLabel("幸运值:");
		label_lucky.setBounds(100, 245, 84, 16);
		panel.add(label_lucky);
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

		todolistPanel = new JPanel();//2.跟转场相关的小事件在这里触发
		todolistPanel.setBounds(360, 120, 215, 337);
		backgroundPanel.add(todolistPanel);
		todolistPanel.setVisible(showToDoList);
		todolistPanel.setOpaque(false);//注意要设成透明的
		todolistPanel.setLayout(null);
			
			JPanel todolistPanelText = new JPanel();//2.跟转场相关的小事件在这里触发
			todolistPanelText.setBounds(0, 0, 215, 337);
			todolistPanel.add(todolistPanelText);
			todolistPanelText.setLayout(null);
			todolistPanelText.setOpaque(false);//注意要设成透明的
			
			JLabel todolistExtra = new JLabel();
			todolistExtra.setForeground(Color.BLACK);
			todolistExtra.setBounds(40, 25, 150, 300);
			todolistPanelText.add(todolistExtra);
			todolistExtra.setFont(new Font("STFangsong", Font.PLAIN, 16));
			todolistExtra.setText(dataPackage.stateE);
			
			JPanel todolistPanelBackground =  new ImagePanel("imgsrc//Windom//dbsx.png",0, 0,215, 337);//2.跟转场相关的小事件在这里触发
			todolistPanelBackground.setBounds(0, 0, 215, 337);
			todolistPanel.add(todolistPanelBackground);
			todolistPanelBackground.setLayout(null);
			todolistPanelBackground.setOpaque(false);//注意要设成透明的
			
		backgroundPanel.add(todolistPanel);
			
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
			label4.setBounds(20, 115, 20, 20);
			todoList.add(label4);
			label4.setFont(new Font("STFangsong", Font.PLAIN, 16));
			
			JButton readToDoList = new JButton("查看更多");
			readToDoList.setBorderPainted(false);
			readToDoList.setContentAreaFilled(false);
			readToDoList.setBounds(37, 113, 80, 27);
			setIcon("/imgsrc/Windom/seemore1.png",readToDoList);
			setSelectedIcon("/imgsrc/Windom/seemore2.png",readToDoList);
			todoList.add(readToDoList);
			backgroundPanel.add(todoList);
			
			JPanel dbsxBackgruond = new ImagePanel("imgsrc//todoList.png",0, 0, 263, 189);
			dbsxBackgruond.setOpaque(false);	
			dbsxBackgruond.setBounds(0, 0, 263, 189);
		
		todoList.add(dbsxBackgruond);
		dbsxBackgruond.setLayout(null);
		
		InClassMouseListener clickToDoList = new InClassMouseListener(8);//设置鼠标监听器，发生4号事件
		clickToDoList.setButton(readToDoList);
		readToDoList.addMouseListener(clickToDoList);//7号事件是 出发考试 被点击
		
		/*************************************************************	
		 * 【镶对话框】
		 * 		建立一个带背景的Panel的流程设setBounds(x, y, 宽, 高);
		 *  	1.建一个Panel	
		 * 		2.Panel里建两个subPanel，全部都设成setBounds(0, 0, 宽, 高);
		 * 		3.底下的用imagePanel工具类放图片，上面的放控件
		 * 		4.设置两个Panel为透明这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		JPanel dialogPack = new JPanel();
		dialogPack.setBounds(66, 475, 689, 189);
		dialogPack.setOpaque(false);//注意要设成透明的
		dialogPack.setLayout(null);
		
			JPanel dialogPanel = new JPanel();
			dialogPanel.setBounds(0, 0, 689, 189);
			
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	
														//因为图片会遮住控件，所以另外加一个图层放背景
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setLayout(null);
			
			JLabel dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogPanel.add(dialogName);
			dialogName.setText("独白");
			
			JLabel dialogContent = new JLabel();
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(15, 42, 677, 141);
			dialogPanel.add(dialogContent);
			if (!dataPackage.notification.equals(""))//设置对话内容
				dialogContent.setText(dataPackage.notification);
			else
				dialogContent.setText("啊，早课……");//设置默认对话内容
		
		dialogPack.add(dialogPanel);
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);
		
		/*************************************************************	
		 * 【放背景图】
		 *************************************************************/
		JPanel Background=new ImagePanel("imgsrc//WinMorningClass/class.jpg",0, 0, 1080, 720);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		
		/*****************************************************************				
		 * 
		 * 1.在用插件绘制完界面之后进行界面内数值设定
		 * 利用数据包进行显示控件的输出的设置
		 * 
		 * 2.添加监听器以响应某些动作
		 * 监听器有模板类，见本类的开头的那个内部类
		 * 
		 *****************************************************************/
		/*		START OF YOUR CODE		*/
		Bar_progress.setValue(dataPackage.studyProgress);
		Bar_Energy.setValue(dataPackage.characterEnergy);
		Bar_happiness.setValue(dataPackage.characterHappiness);
		healthBar.setValue(dataPackage.characterHealth);//进度条设置进度
		Bar_progress.setStringPainted(true);
		Bar_Energy.setStringPainted(true);//开启进度条显示字
		Bar_happiness.setStringPainted(true);
		healthBar.setStringPainted(true);
		Bar_progress.setString(String.format("%d",dataPackage.studyProgress));
		Bar_Energy.setString(String.format("%d",dataPackage.characterEnergy));
		Bar_happiness.setString(String.format("%d",dataPackage.characterHappiness));
		healthBar.setString(String.format("%d",dataPackage.characterHealth));//进度条显示字
		IDshow.setText(dataPackage.studentID);//显示学号
		nameShow.setText(dataPackage.name);//显示名字
		
		label_social.setText("社交能力:"+dataPackage.characterEQ);
		label_Art.setText("才艺能力:"+dataPackage.characterArt);
		label_IQ.setText("智商:"+dataPackage.characterIQ);
		label_lucky.setText("幸运值:"+dataPackage.characterlucky);

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		InClassMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		InClassMouseListener.mainGame=mainGame;
		
		InClassMouseListener clickask=new InClassMouseListener(0);//设置鼠标监听器，发生0号事件
		InClassMouseListener clickanswer=new InClassMouseListener(1);//设置鼠标监听器，发生1号事件
		InClassMouseListener clicknext=new InClassMouseListener(2);//设置鼠标监听器，发生2号事件
		InClassMouseListener clickback=new InClassMouseListener(3);//设置鼠标监听器，发生3号事件
		
		btnNewButton_1.addMouseListener(clickanswer);//0号事件是 回答按钮 被点击
    	btnNewButton.addMouseListener(clickask);//1号事件是 提问按钮 被点击
		btnNewButton_2.addMouseListener(clicknext);//2号事件是 下一题按钮 被点击
		btnNewButton_3.addMouseListener(clickback);//3号事件是 回宿舍按钮 被点击

			
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

