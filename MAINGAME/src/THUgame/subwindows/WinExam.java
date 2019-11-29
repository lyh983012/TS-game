package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.tool.GifPanel;
import THUgame.windows.WinBase;

/*
 * 【宿舍界面】
 * 
 * --DIALOG--
 * 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * update:20191029 
 * via：林逸晗
 * 更新：科协分支的主体架构实现
 * 
 **/

public class WinExam extends WinBase{
	static private JLabel dialogContent;
	static private JLabel dialogName;
	
	static private class demoMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
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
	
	public WinExam(EventManager mainGame,JFrame frame) {
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		/*************************************************************	
		 * 【镶对话框】
		 *************************************************************/
		JPanel dialogPack = new JPanel();
		dialogPack.setBounds(340, 475, 689, 189);
		dialogPack.setOpaque(false);//注意要设成透明的
		dialogPack.setLayout(null);
			
			JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
			dialogPanel.setBounds(0, 0, 700, 189);//(0, 0, 宽, 高);
				
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	//第2个subPanel，放图																	//(0, 0, 宽, 高);
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);		//注意要设成透明的
			dialogPanel.setLayout(null);
			
			dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogName.setText("独白");
				
			dialogContent = new JLabel();
			dialogName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(44, 42, 650, 141);
			if (!dataPackage.notification.equals(""))//设置对话内容
				dialogContent.setText(dataPackage.notification);
			
			JButton nextDialog = new JButton("");
			nextDialog.setBounds(600, 120, 40, 40);
			nextDialog.setBorderPainted(false);
			nextDialog.setContentAreaFilled(false);
			nextDialog.setHorizontalAlignment(SwingConstants.CENTER);
			setIcon("/imgsrc/WinSTA/ca.png",nextDialog);
			setSelectedIcon("/imgsrc/WinSTA/cb.png",nextDialog);
		
			if(dataPackage.stateA.equals("")) {
				dialogPanel.add(nextDialog);
				dialogName.setText("管理员");
				dialogContent.setText("<html>hello～好久没看到你了啊</html>");//设置默认对话内
			}
			dialogPanel.add(dialogName);
			dialogPanel.add(dialogContent);
			dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
			dialogPack.add(dialogBackgoundPanel);
			backgroundPanel.add(dialogPack);
			/*************************************************************	
			 *【按钮】
			 *************************************************************/
			JButton use3DPrinterButton = new JButton();
			use3DPrinterButton.setBorderPainted(false);
			use3DPrinterButton.setBounds(120, 500, 80, 80);
			use3DPrinterButton.setContentAreaFilled(false);
			setIcon("/imgsrc/WinSTA/2.png",use3DPrinterButton);
			setSelectedIcon("/imgsrc/WinSTA/2p.png",use3DPrinterButton);
			
	
			JButton useLaserCutterButton = new JButton();
			useLaserCutterButton.setBorderPainted(false);
			useLaserCutterButton.setBounds(240, 440, 80, 80);
			useLaserCutterButton.setContentAreaFilled(false);
			setIcon("/imgsrc/WinSTA/1.png",useLaserCutterButton);
			setSelectedIcon("/imgsrc/WinSTA/1p.png",useLaserCutterButton);
			
			
			JButton useToolButton = new JButton();
			useToolButton.setBorderPainted(false);
			useToolButton.setBounds(360, 250, 80, 80);
			useToolButton.setContentAreaFilled(false);
			setIcon("/imgsrc/WinSTA/3.png",useToolButton);
			setSelectedIcon("/imgsrc/WinSTA/3p.png",useToolButton);
			
			
			JButton backButton = new JButton("离开");
			backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			backButton.setBounds(150, 600, 80, 50);
			if(dataPackage.stateA.equals("")) {
				backgroundPanel.add(use3DPrinterButton);
				backgroundPanel.add(useLaserCutterButton);
				backgroundPanel.add(useToolButton);
				
			}
			backgroundPanel.add(backButton);
			/*************************************************************	
			 * 【镶时钟】
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
			 * 【镶属性】
			 *************************************************************/
	
			JPanel panel = new JPanel();
			panel.setBounds(520, 40, 197, 290);
			backgroundPanel.add(panel);
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
			panel.setLayout(null);
			
			JLabel StudentIDLable = new JLabel("学号");
			StudentIDLable.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			StudentIDLable.setBounds(26, 78, 32, 16);
			panel.add(StudentIDLable);
			
			JTextPane nameShow = new JTextPane();
			nameShow.setEditable(false);
			nameShow.setBounds(84, 42, 73, 20);
			panel.add(nameShow);
			
			JLabel nameLable = new JLabel("姓名");
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
			 *************************************************************/
			if(dataPackage.stateB.equals("gif")) {
				GifPanel Background=new GifPanel("/imgsrc/WinSTA/"+dataPackage.stateA,0, 0, 1080, 720, 20);
				Background.setBounds(0, 0, 1080, 720);
				backgroundPanel.add(Background);
				Background.setLayout(null);
			}else {
				JPanel Background=null;
				if (dataPackage.stateA.equals("")){
					Background=new ImagePanel("imgsrc//WinSTA//2501.jpeg",0, 0, 1080, 720);
				}else {
					Background=new ImagePanel("imgsrc//WinSTA//"+dataPackage.stateA,0, 0, 1080, 720);
				}
				Background.setBounds(0, 0, 1080, 720);
				backgroundPanel.add(Background);
				Background.setLayout(null);
			}
			/*****************************************************************				
			 * 【细节设定】
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
			demoMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
			demoMouseListener.mainGame=mainGame;
			
			demoMouseListener click3D=new demoMouseListener(0);//设置鼠标监听器，发生0号事件
			demoMouseListener clicklaser=new demoMouseListener(1);//设置鼠标监听器，发生1号事件
			demoMouseListener clickTool=new demoMouseListener(2);//设置鼠标监听器，发生2号事件
			demoMouseListener clickBack=new demoMouseListener(3);//设置鼠标监听器，发生3号事件
			demoMouseListener clickNext=new demoMouseListener(4);//设置鼠标监听器，发生4号事件
			
			clickNext.setButton(nextDialog);
			click3D.setButton(use3DPrinterButton);
			clicklaser.setButton(useLaserCutterButton);
			clickTool.setButton(useToolButton);
			clickBack.setButton(backButton);
			
			nextDialog.addMouseListener(clickNext);
			use3DPrinterButton.addMouseListener(click3D);//0号事件是 睡觉按钮 被点击
			useLaserCutterButton.addMouseListener(clicklaser);//1号事件是 去自习按钮 被点击
			useToolButton.addMouseListener(clickTool);//2号事件是 去上课按钮 被点击
			backButton.addMouseListener(clickBack);//2号事件是 去上课按钮 被点击
	
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}