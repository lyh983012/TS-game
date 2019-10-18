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
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

/*
 * 【宿舍界面】
 * 
 * --DIALOG--
 * update:20191018 01:07
 * via：余冬杰
 * 更新：加入了打呼噜的子事件
 * 
 * update:20191014 18:30
 * via：林逸晗
 * 更新：加入了游戏界面（在morning class的窗口里）
 * 		游戏panel直接添加就可以运行，但是需要注册一下数据包和控制进程（maingame）具体用法见morning
 * 		游戏panel在tool里有，在Game里有一个可运行的例程，现已更新完第一个game
 * 
 * update:20191010 18:30
 * via：林逸晗
 * 更新：加入了UI及使用方法
 * 		更新了按钮的UI和对话框的UI，设置UI的方法可以看本类中【按钮】和【对话框】的部分
 * 
 * update:20191010 18:30
 * via：林逸晗
 * 更新：加入对话框
 * 		加入对话的逻辑
 * 
 * update:20191006 18:30
 * via：林逸晗
 * 更新：加入了按钮的可见／不可见
 * 		加入了时钟（简陋）
 * 		鼠标事件响应不需要再写set game了，使用方法和dataPack的传递一样
 * 
 * update:20190930 18:30
 * via：林逸晗
 * 更新：解决了界面闪烁的问题
 * 		跟新了一些类的结构和注释的问题
 * 		注释中：//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥***¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥表示最重要的事情
 * 		蓝色的部分则是表示对代码块的解释
 * 		细节用单句注释阐述
 * 		推荐收起后再看代码
 * 		推荐看WinInDom.java的注释
 * 
 **/


public class WinIndom extends WinBase{
	
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
			if(mode==0) {
				dataPackage.choiceA="sleep";	//点按钮0（睡觉按钮）返回sleep
			}else if(mode ==1){
				dataPackage.choiceA="selfstudy";//点按钮1（自习按钮）返回selfstudy
			}else if(mode ==2){
				dataPackage.choiceA="gotoclass";//点按钮2（上课按钮）返回gotoclass
			}else if(mode ==3){
				dataPackage.choiceA="wakehimup";//点按钮3（唤醒按钮）返回wakehimup
			}else if(mode ==4){
				dataPackage.choiceA="stayup";//点按钮4（待着按钮）返回stayup
			}
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
	public WinIndom(EventManager mainGame,JFrame frame) {
		
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
		JButton sleepButton = new JButton();
		sleepButton.setBorderPainted(false);
		sleepButton.setBounds(819, 544, 150, 50);
		sleepButton.setContentAreaFilled(false);
		setIcon("/imgsrc/Windom/sleep.png",sleepButton);
		setSelectedIcon("/imgsrc/Windom/sleepUn.png",sleepButton);
		backgroundPanel.add(sleepButton);

		JButton selfstudyButton = new JButton();
		selfstudyButton.setBorderPainted(false);
		selfstudyButton.setBounds(819, 477, 150, 50);
		setIcon("/imgsrc/Windom/study.png",selfstudyButton);
		setSelectedIcon("/imgsrc/Windom/studyUn.png",selfstudyButton);
		backgroundPanel.add(selfstudyButton);
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(819, 611, 150, 50);
		if (dataPackage.stateB.equals("classtime")) {	//高级应用：仅仅在符合“classtime”状态的时候显示这个按钮
			if(dataPackage.stateA.equals("上早上课")){   //高级应用：图标也对应有不同
				if(!dataPackage.todayMorningClass.equals("----")) {
					setIcon("/imgsrc/Windom/Morning.png",btnNewButton_2);
					setSelectedIcon("/imgsrc/Windom/MorningUn.png",btnNewButton_2);
					backgroundPanel.add(btnNewButton_2);
				}
			}else {
				if(!dataPackage.todayAfternoonClass.equals("----")) {
					setIcon("/imgsrc/Windom/afternoon.png",btnNewButton_2);
					setSelectedIcon("/imgsrc/Windom/afternoonUn.png",btnNewButton_2);
					backgroundPanel.add(btnNewButton_2);
				}
			}
		}
		/*************************************************************	
		 * 【小事件】 
		 *  	这一部分需要用dataPackage.trigSubEvent决定是否绘制
		 *  	具体用法见MorninigClass窗口
		 *************************************************************/
		JPanel EventPanel = new JPanel();	
		EventPanel.setBackground(new Color(255, 255, 204));
		EventPanel.setBounds(254, 129, 531, 363);
		backgroundPanel.add(EventPanel);
		EventPanel.setLayout(null);

		if (dataPackage.trigSubEvent){ // 触发子事件，小事情可见。。
			EventPanel.setVisible(true);
			sleepButton.setVisible(false);
			selfstudyButton.setVisible(false);
			btnNewButton_2.setVisible(false);
		}else {
			EventPanel.setVisible(false); // 未触发子事件，取消小事件，恢复睡觉按钮
		}
		
		JLabel label_1 = new JLabel("你被舍友的呼噜吵醒了，睡眠质量大跌");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_1.setBounds(95, 130, 388, 16);
		EventPanel.add(label_1);
		
		JLabel label_2 = new JLabel("健康下降、心情下降、社交力下降、体力下降");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_2.setBounds(95, 173, 388, 16);
		EventPanel.add(label_2);
		
		JButton wakeButton = new JButton();
		wakeButton.setBorderPainted(true);//waiting foe GUI//waiting foe GUI//waiting foe GUI//waiting foe GUI//waiting foe GUI
		wakeButton.setBounds(95, 250, 120, 50);
		wakeButton.setText("叫醒舍友");
		
		JButton stayButton = new JButton();
		stayButton.setBorderPainted(true);//waiting foe GUI//waiting foe GUI//waiting foe GUI//waiting foe GUI//waiting foe GUI
		stayButton.setBounds(300, 250, 120, 50);
		stayButton.setText("保持沉默");
		
		EventPanel.add(wakeButton);
		EventPanel.add(stayButton);
		
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
		 * 【镶属性】
		 *************************************************************/
		
		JPanel sxPanel = new JPanel();
		sxPanel.setBounds(64, 184, 197, 267);
		backgroundPanel.add(sxPanel);
		sxPanel.setLayout(null);
		
		JLabel StudentIDLable = new JLabel("学号");
		StudentIDLable.setFont(new Font("STFangsong", Font.PLAIN, 13));
		StudentIDLable.setBounds(26, 82, 32, 16);
		sxPanel.add(StudentIDLable);
		
		JTextPane nameShow = new JTextPane();
		nameShow.setEditable(false);
		nameShow.setBounds(70, 42, 78, 24);
		sxPanel.add(nameShow);
		
		JLabel nameLable = new JLabel("姓名");
		nameLable.setFont(new Font("STFangsong", Font.PLAIN, 13));
		nameLable.setBounds(26, 42, 32, 24);
		sxPanel.add(nameLable);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBounds(60, 119, 124, 20);
		sxPanel.add(healthBar);
		
		JProgressBar IQBar = new JProgressBar();
		IQBar.setBounds(60, 139, 124, 20);
		sxPanel.add(IQBar);
		
		JProgressBar StrongBar = new JProgressBar();
		StrongBar.setBounds(60, 165, 124, 20);
		sxPanel.add(StrongBar);
		
		JProgressBar happyBar = new JProgressBar();
		happyBar.setBounds(60, 187, 124, 20);
		sxPanel.add(happyBar);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(60, 211, 124, 20);
		sxPanel.add(progressBar);
		
		JLabel labelIQ = new JLabel("智力值");
		labelIQ.setBounds(6, 142, 52, 16);
		sxPanel.add(labelIQ);
		
		JLabel labelstrong = new JLabel("体力值");
		labelstrong.setBounds(6, 166, 52, 16);
		sxPanel.add(labelstrong);
		
		JLabel labelshealth = new JLabel("健康值");
		labelshealth.setBounds(6, 118, 52, 16);
		sxPanel.add(labelshealth);
		
		JLabel label_happy = new JLabel("心   情");
		label_happy.setBounds(6, 189, 52, 16);
		sxPanel.add(label_happy);
		
		JLabel label_social = new JLabel("社交力");
		label_social.setBounds(6, 213, 52, 16);
		sxPanel.add(label_social);
		
		JTextPane IDshow = new JTextPane();
		IDshow.setBounds(70, 78, 78, 24);
		sxPanel.add(IDshow);
		IDshow.setEditable(false);
		IDshow.setText(dataPackage.name);

		
		JPanel sxBackground = new ImagePanel("imgsrc//shuxing.jpg",0, 0, 197, 267);
		sxBackground.setBounds(0, 0, 197, 267);
		sxPanel.add(sxBackground);
		sxBackground.setOpaque(false);
		sxBackground.setLayout(null);
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
		
			JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
			dialogPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	//第2个subPanel，放图
																								//(0, 0, 宽, 高);
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);		//注意要设成透明的
			dialogPanel.setLayout(null);
			
			JLabel dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogPanel.add(dialogName);
			dialogName.setText("独白");
			
			JLabel dialogContent = new JLabel();
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(15, 42, 677, 141);
			dialogPanel.add(dialogContent);
			
			if (!dataPackage.notification.equals(""))//设置对话内容
				dialogContent.setText(dataPackage.notification);
			else
				dialogContent.setText("回到了宿舍～");//设置默认对话内容
		
		dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);
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
		
		JPanel Background=new ImagePanel("imgsrc//Windom/dom3.jpg",0, 0, 1080, 720);
		if(dataPackage.choiceA.equals("sleep")) {
			Background=new ImagePanel("imgsrc//Windom/dom2.jpg",0, 0, 1080, 720);
		}else if(dataPackage.choiceA.equals("selfstudy")) {
			Background=new ImagePanel("imgsrc//Windom/dom1.jpg",0, 0, 1080, 720);
		}
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

		
		IQBar.setValue(dataPackage.characterIQ);
		progressBar.setValue(dataPackage.characterEQ);
		StrongBar.setValue(dataPackage.characterStrength);
		happyBar.setValue(dataPackage.characterHappiness);
		healthBar.setValue(dataPackage.characterHealth);//进度条设置进度
		IQBar.setStringPainted(true);
		progressBar.setStringPainted(true);
		StrongBar.setStringPainted(true);//开启进度条显示字
		happyBar.setStringPainted(true);
		healthBar.setStringPainted(true);
		IQBar.setString(String.format("%d",dataPackage.characterIQ));
		progressBar.setString(String.format("%d",dataPackage.characterEQ));
		StrongBar.setString(String.format("%d",dataPackage.characterEnergy));
		happyBar.setString(String.format("%d",dataPackage.characterHappiness));
		healthBar.setString(String.format("%d",dataPackage.characterHealth));//进度条显示字
		nameShow.setText(dataPackage.studentID);//显示学号
		

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		demoMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		demoMouseListener.mainGame=mainGame;
		
		demoMouseListener clicksleep=new demoMouseListener(0);//设置鼠标监听器，发生0号事件
		demoMouseListener clickselfstudy=new demoMouseListener(1);//设置鼠标监听器，发生1号事件
		demoMouseListener clickgotoclass=new demoMouseListener(2);//设置鼠标监听器，发生2号事件
		demoMouseListener clickwake=new demoMouseListener(3);//设置鼠标监听器，发生3号事件
		demoMouseListener clickstay=new demoMouseListener(4);//设置鼠标监听器，发生4号事件

		clicksleep.setButton(sleepButton);
		clickselfstudy.setButton(selfstudyButton);
		clickgotoclass.setButton(btnNewButton_2);
		clickwake.setButton(wakeButton);
		clickstay.setButton(stayButton);
		
    	sleepButton.addMouseListener(clicksleep);//0号事件是 睡觉按钮 被点击
		selfstudyButton.addMouseListener(clickselfstudy);//1号事件是 去自习按钮 被点击
		btnNewButton_2.addMouseListener(clickgotoclass);//2号事件是 去上课按钮 被点击
		wakeButton.addMouseListener(clickwake);//3号事件是 叫醒舍友 被点击
		stayButton.addMouseListener(clickstay);//4号事件是 按兵不动 被点击
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