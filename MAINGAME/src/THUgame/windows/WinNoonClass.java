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
import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

import javax.swing.JTextField;


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
	 * 
	 *************************************************************/

public class WinNoonClass extends WinBase{
		private JTextField dialogName;
		private JTextField dialogContent;
	
	
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
			EventManager.dataPackage=dataPackage;
			synchronized(mainGame) {
				mainGame.notify();
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
	public WinNoonClass(EventManager mainGame,JFrame frame) {

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
		 * 小事件
		 *************************************************************/
		JPanel EventPanel = new JPanel();//将来可以用它来放临时小事件
		EventPanel.setBackground(new Color(255, 255, 204));
		EventPanel.setBounds(254, 134, 536, 398);
		backgroundPanel.add(EventPanel);
		EventPanel.setLayout(null);
		EventPanel.setVisible(dataPackage.trigSubEvent);
		
		JButton btnNewButton_3 = new JButton("不学了，回宿舍");
		btnNewButton_3.setBounds(185, 242, 190, 47);
		EventPanel.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("已经下课了！");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(226, 107, 220, 68);
		EventPanel.add(lblNewLabel_1);
		EventPanel.setVisible(dataPackage.trigSubEvent);
		
		/*************************************************************	
		 * 基本按钮
		 *************************************************************/
		JButton btnNewButton = new JButton();
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(824, 545, 155, 55);
		setIcon("/imgsrc/WinNoonClass/answerUn.png",btnNewButton);
		setSelectedIcon("/imgsrc/WinNoonClass/answer.png",btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(824, 478, 155, 55);
		setIcon("/imgsrc/WinNoonClass/askUn.png",btnNewButton_1);
		setSelectedIcon("/imgsrc/WinNoonClass/ask.png",btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(824, 609, 155, 55);
		setIcon("/imgsrc/WinNoonClass/nextUn.png",btnNewButton);
		setSelectedIcon("/imgsrc/WinNoonClass/next.png",btnNewButton);
		
		if(!dataPackage.trigSubEvent){				//仅在未触发事件时添加按钮
			backgroundPanel.add(btnNewButton);	
			backgroundPanel.add(btnNewButton_1);
			backgroundPanel.add(btnNewButton_2);
		}
		/************************************************************	
		 * 镶属性 这一部分按照流程做的话就会自然消失的
		 *************************************************************/

		JPanel panel = new JPanel();
		panel.setBounds(64, 184, 197, 267);
		backgroundPanel.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setLayout(null);
		
		JLabel StudentIDLable = new JLabel("学号");
		StudentIDLable.setFont(new Font("STFangsong", Font.PLAIN, 13));
		StudentIDLable.setBounds(26, 78, 32, 16);
		panel.add(StudentIDLable);
		
		JTextPane nameShow = new JTextPane();
		nameShow.setEditable(false);
		nameShow.setBounds(86, 42, 62, 16);
		panel.add(nameShow);
		
		JLabel nameLable = new JLabel("姓名");
		nameLable.setFont(new Font("STFangsong", Font.PLAIN, 13));
		nameLable.setBounds(26, 42, 32, 24);
		panel.add(nameLable);
		
		JTextPane IDshow = new JTextPane();
		IDshow.setEditable(false);
		IDshow.setBounds(86, 78, 62, 16);
		panel.add(IDshow);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBounds(60, 119, 124, 20);
		panel.add(healthBar);
		
		JProgressBar IQBar = new JProgressBar();
		IQBar.setBounds(60, 139, 124, 20);
		panel.add(IQBar);
		
		JProgressBar StrongBar = new JProgressBar();
		StrongBar.setBounds(60, 165, 124, 20);
		panel.add(StrongBar);
		
		JProgressBar happyBar = new JProgressBar();
		happyBar.setBounds(60, 187, 124, 20);
		panel.add(happyBar);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(60, 211, 124, 20);
		panel.add(progressBar);
		
		JLabel labelIQ = new JLabel("智力值");
		labelIQ.setBounds(6, 142, 52, 16);
		panel.add(labelIQ);
		
		JLabel labelstrong = new JLabel("体力值");
		labelstrong.setBounds(6, 166, 52, 16);
		panel.add(labelstrong);
		
		JLabel labelshealth = new JLabel("健康值");
		labelshealth.setBounds(6, 118, 52, 16);
		panel.add(labelshealth);
		
		JLabel label_happy = new JLabel("心   情");
		label_happy.setBounds(6, 189, 52, 16);
		panel.add(label_happy);
		
		JLabel label_social = new JLabel("社交力");
		label_social.setBounds(6, 213, 52, 16);
		panel.add(label_social);
		
		JPanel sxBackground = new ImagePanel("imgsrc//shuxing.jpg",0, 0, 197, 267);
		sxBackground.setBounds(0, 0, 197, 267);
		sxBackground.setOpaque(false);
		panel.add(sxBackground);
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
			
			JLabel dateText = new JLabel("当前日期为：第"+String.valueOf(dataPackage.term)+"学期"+String.valueOf(dataPackage.date)+"日");
			dateText.setBounds(6, 35, 172, 16);
			timePanel.add(dateText);
			
		timePack.add(timePanel);
		timePack.add(timeBackgoundPanel);
		backgroundPanel.add(timePack);
		/*************************************************************	
		 * 镶课程表 这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(752, 35, 263, 160);
		backgroundPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("课程表");
		lblNewLabel.setBounds(6, 6, 61, 16);
		panel_1.add(lblNewLabel);
		
		JPanel kcbBackground = new ImagePanel("imgsrc//kcb.jpg",0, 0, 263, 160);
		kcbBackground.setBounds(0, 0, 263, 160);
		panel_1.add(kcbBackground);
		
		/*************************************************************	
		 * 镶待办事项 这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		JPanel todoList = new JPanel();
		todoList.setLayout(null);
		todoList.setBounds(752, 248, 263, 189);
			
				JLabel label = new JLabel("待办事项");
				label.setForeground(Color.WHITE);
				label.setBounds(20, 25, 100, 18);
				todoList.add(label);
				label.setFont(new Font("STFangsong", Font.PLAIN, 18));
			
			JPanel dbsxBackgruond = new ImagePanel("imgsrc//todoList.png",0, 0, 263, 189);
			dbsxBackgruond.setBounds(0, 0, 263, 189);
			todoList.add(dbsxBackgruond);
			dbsxBackgruond.setLayout(null);
		backgroundPanel.add(todoList);
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
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(15, 42, 677, 141);
			dialogPanel.add(dialogContent);
			
			if (!dataPackage.notification.equals(""))//设置对话内容
				dialogContent.setText(dataPackage.notification);
			else
				dialogContent.setText("下午，上课了……");//设置默认对话内容
		
		dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);
		
		/*************************************************************	
		 * 【放背景图】
		 *************************************************************/
		JPanel Background=new ImagePanel("imgsrc//WinNoonClass/noon.jpg",0, 0, 1080, 720);
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
		IDshow.setText(dataPackage.name);//显示名字
		nameShow.setText(dataPackage.studentID);//显示学号
		
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

		/*		END OF YOUR CODE		*
		 * 	-
		 */
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

