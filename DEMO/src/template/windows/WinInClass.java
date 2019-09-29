package template.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import template.datapack.DataPack;
import template.main.EventManager;
import template.tool.ImagePanel;
import template.tool.demoMouseListener;

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
 * 可视化界面模板
 * update:20190928 23:15
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
public class WinInClass extends WinBase{

	public WinInClass(EventManager mainGame,JFrame frame) {
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
		JButton btnNewButton = new JButton("提问");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setBounds(413, 477, 148, 55);
		backgroundPanel.add(btnNewButton);

		
		JButton btnNewButton_2 = new JButton("下一题");
		btnNewButton_2.setBounds(562, 477, 155, 55);
		backgroundPanel.add(btnNewButton_2);
		

		JButton btnNewButton_1 = new JButton("回答问题");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton_1.setBounds(253, 477, 155, 55);
		backgroundPanel.add(btnNewButton_1);
		/*************************************************************	
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
		panel.add(sxBackground);
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
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		imagePanel.setLayout(null);
		imagePanel.setBounds(752, 248, 263, 189);
		backgroundPanel.add(imagePanel);
		
		JLabel label = new JLabel("待办事项");
		label.setBounds(6, 6, 61, 16);
		imagePanel.add(label);
		
		JPanel dbsxBackgruond = new ImagePanel("imgsrc//dbsx.jpg",0, 0, 263, 189);
		dbsxBackgruond.setBounds(0, 0, 263, 189);
		imagePanel.add(dbsxBackgruond);
	

		JPanel Background=new ImagePanel("imgsrc//class.jpg",0, 0, 1080, 720);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);

		InClassMouseListener.dataPackage=dataPackage;//数据包注册
		
		
		
		/*****************************************************************				
		 * 
		 * 在用插件绘制完界面之后进行界面内数值设定
		 * 利用数据包进行显示控件的输出的设置
		 * 
		 * 添加监听器以响应某些动作
		 * 监听器有模板类
		 * 
		 * 
		 * 这部分是手动写的，但是也会被自动删掉，可以参考一下就是了
		 * 
		 *****************************************************************/
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
		StrongBar.setString(String.format("%d",dataPackage.characterStrength));
		happyBar.setString(String.format("%d",dataPackage.characterHappiness));
		healthBar.setString(String.format("%d",dataPackage.characterHealth));//进度条显示字
		
		IDshow.setText(dataPackage.name);//显示名字
		nameShow.setText(dataPackage.studentID);//显示学号
		
		
		/*		START OF YOUR CODE		*/
		InClassMouseListener clickask=new InClassMouseListener(0);//设置鼠标监听器，发生0号事件
		InClassMouseListener clickanswer=new InClassMouseListener(1);//设置鼠标监听器，发生1号事件
		InClassMouseListener clicknext=new InClassMouseListener(2);//设置鼠标监听器，发生1号事件
		
		/*****			
		 * 重要，事件监听器一定要把mainGame传入
		 ****/
		clickask.setGame(mainGame);
		clickanswer.setGame(mainGame);
		clicknext.setGame(mainGame);
		btnNewButton_1.addMouseListener(clickanswer);//0号事件是 去按钮 被点击
    	btnNewButton.addMouseListener(clickask);//1号事件是 睡觉按钮 被点击
		btnNewButton_2.addMouseListener(clicknext);//2号事件是 去按钮 被点击
		/*		END OF YOUR CODE		*/
    	
    	/*****************************************************************				
		 * 恢复显示，按照流程做就不会消失
		 ******************************************************************/
    	frame.setVisible(true);
	}
}


class InClassMouseListener implements MouseListener{

	private EventManager mainGame;
	private JFrame frame;
	static public DataPack dataPackage;
	
	private int mode;
	public InClassMouseListener(int i){
		this.mode=i;
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
		/*		START OF YOUR CODE		*/
		
		
		if(mode==0) {
			dataPackage.choiceA="answer";
		}else if(mode==1){
			dataPackage.choiceA="ask";
		}else if(mode==2){
			dataPackage.choiceA="next";
		}
		/*		END OF YOUR CODE		*/
		
		
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
