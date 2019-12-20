package THUgame.subwindows;

import THUgame.windows.WinBase;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import java.awt.Font;

/* 
 * 社团活动，第二学期第二周
 * 
 * --DIALOG--
 * 
 * update:20191214
 * via：卢宇芳
 * 女生节和筹备活动
 * 
 **/

public class WinClubActivityFive extends WinBase{
	

		public String text="";//对话内容
		
		/*************************************************************	
		 *
		 *【内部的事件响应类】
		 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
		 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
		 * 所有必要实现的接口都实现了。
		 * 
		 *************************************************************/
		static private class ClubActivityListener extends BaseMouseListener{
			static public DataPack dataPackage;
			static public EventManager mainGame;
			private JFrame frame;
			private JButton button;
			private int mode;
			
			public ClubActivityListener(int i){
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
				/*		START OF YOUR CODE		*/
				
				if(safeGuardCount==0) {
					safeGuardCount++;
					if(mode==0) {
						dataPackage.choiceA="Next";
					}else if (mode==1) {
						dataPackage.choiceA="Back";
					} else if(mode==2) {
						dataPackage.choiceB="GoSing";
					} else if (mode==3) {
						dataPackage.choiceB="NoSing";
					} else if (mode==4) {
						dataPackage.choiceB="GoInvite";
					} else if(mode==5) {
						dataPackage.choiceB="NoInvite";
					}
					/*		END OF YOUR CODE		*/
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
					EventManager.dataPackage=dataPackage;
					synchronized(mainGame) {
						mainGame.notify();
					}
				}	
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				
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
	public WinClubActivityFive(EventManager mainGame,JFrame frame) {
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
		backgroundPanel.setBackground(Color.GRAY);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		backgroundPanel.setOpaque(false);
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivityFive.jpeg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
		JPanel backgroundImage2=new ImagePanel("imgsrc//WinJoinClub/ClubActivityFive2.gif",0, 0, 1080, 720);	
		backgroundImage2.setOpaque(false);
		backgroundImage2.setBounds(0, 0, 1080, 720);
		
		JPanel backgroundImage3=new ImagePanel("imgsrc//WinJoinClub/ClubActivityFive3.gif",0, 0, 1080, 720);	
		backgroundImage3.setOpaque(false);
		backgroundImage3.setBounds(0, 0, 1080, 720);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBounds(67,390,965,255);
		dialogPanel.setOpaque(false);
		dialogPanel.setLayout(null);
		
		JPanel dialogText = new JPanel();
		dialogText.setBounds(0, 0, 938, 250);
		dialogText.setOpaque(false);
		dialogText.setLayout(null);
		
		JPanel dialogImage = new ImagePanel("imgsrc//WinJoinClub/DialogOfClub.png",0, 0, 965, 255);	

		dialogImage.setBounds(0, 0, 965, 255);
		dialogImage.setOpaque(false);//注意要设成透明的
		
		JLabel textSpeaker = new JLabel();
		textSpeaker.setHorizontalAlignment(SwingConstants.CENTER);
		textSpeaker.setText("<html>独白</html>");
		textSpeaker.setOpaque(false);
		textSpeaker.setFont(new Font("印品黑体", Font.PLAIN, 19));
		textSpeaker.setBounds(0, 0, 133, 48);
		
		
		JLabel textThis = new JLabel();
		textThis.setVerticalAlignment(SwingConstants.CENTER);
		textThis.setHorizontalAlignment(SwingConstants.LEFT);
		textThis.setText("<html>这样会自动换行</html>");
		textThis.setOpaque(false);
		textThis.setFont(new Font("印品黑体", Font.PLAIN, 19));
		textThis.setBounds(10, 60, 938, 180);	

		dialogText.add(textSpeaker);
		dialogText.add(textThis);
		dialogPanel.add(dialogText);
		dialogPanel.add(dialogImage);
		
		/*************************************************************	
		 * 【镶属性和时间】
		 *************************************************************/

		JPanel panel = new JPanel();
		panel.setBounds(20, 20, 197, 160);
		backgroundPanel.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setLayout(null);
		
		JLabel timeText = new JLabel("当前时间为："+String.valueOf(dataPackage.time)+" 时");
		timeText.setBounds(6, 40, 172, 16);
		panel.add(timeText);
		
		JLabel dateText = new JLabel("今天是：第"+String.valueOf(dataPackage.term)+"学期"+String.valueOf(dataPackage.week)+"周"+String.valueOf(dataPackage.date)+"日");
		dateText.setBounds(6, 15, 172, 16);
		panel.add(dateText);
		
		JLabel label_energy = new JLabel("体力:");
		label_energy.setBounds(10, 80, 92, 16);
		panel.add(label_energy);
		
		JLabel label_EQ = new JLabel("情商:");
		label_EQ.setBounds(10, 105, 84, 16);
		panel.add(label_EQ);
		
		JLabel label_process = new JLabel("社团进度:");
		label_process.setBounds(10, 130, 92, 16);
		panel.add(label_process);
		
		JLabel label_happy = new JLabel("心情:");
		label_happy.setBounds(100, 80, 92, 16);
		panel.add(label_happy);
		
		JLabel label_Art = new JLabel("才艺:");
		label_Art.setBounds(100, 105, 92, 16);
		panel.add(label_Art);
		
		JLabel label_lucky = new JLabel("幸运值:");
		label_lucky.setBounds(100, 130, 84, 16);
		panel.add(label_lucky);
		
		//下一个选项按钮
		JPanel panelNext = new JPanel();
		panelNext.setLayout(null);
		panelNext.setOpaque(false);
		panelNext.setBounds(900, 560, 50, 45);
		panelNext.setLayout(null);
	
		JPanel nextImage = new ImagePanel("imgsrc//WinJoinClub/Next.png",0, 0, 50, 45);	
		nextImage.setOpaque(false);
		nextImage.setBounds(0, 0, 50, 45);
		nextImage.setOpaque(false);//注意要设成透明的
		
		JButton nextButton = new JButton();	
		nextButton.setBorderPainted(false);
		nextButton.setFont(new Font("印品黑体", Font.PLAIN, 19));
		nextButton.setForeground(Color.BLACK);
		nextButton.setBounds(0, 0, 50, 45);
		nextButton.setContentAreaFilled(false);
		nextButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		//另一个学生的panel
		JPanel PanelTheOne = new JPanel();
		PanelTheOne.setOpaque(false);
		PanelTheOne.setBounds(120, 140, 300, 300);
		PanelTheOne.setLayout(null);
		//自己的panel
		JPanel PanelYou = new JPanel();
		PanelYou.setOpaque(false);
		PanelYou.setBounds(720, 140, 300, 300);
		PanelYou.setLayout(null);
		//女生panel
		JPanel PanelGirl = new JPanel();
		PanelGirl.setOpaque(false);
		PanelGirl.setBounds(500, 170, 200, 310);
		PanelGirl.setLayout(null);
		//男生panel
		JPanel PanelBoy = new JPanel();
		PanelBoy.setOpaque(false);
		PanelBoy.setBounds(380, 130, 200, 355);
		PanelBoy.setLayout(null);
		
		//女生图
		JPanel ImageGirl1 = new ImagePanel("imgsrc//WinJoinClub/ImageGirl.png",0, 0, 300, 300);
		ImageGirl1.setOpaque(false);
		ImageGirl1.setBounds(0, 0, 300, 300);
		ImageGirl1.setOpaque(false);//注意要设成透明的
		ImageGirl1.setVisible(false);
		
		JPanel ImageGirl2 = new ImagePanel("imgsrc//WinJoinClub/GirlSing.png",0, 0, 200, 310);
		ImageGirl2.setOpaque(false);
		ImageGirl2.setBounds(0, 0, 200, 310);
		ImageGirl2.setOpaque(false);//注意要设成透明的
		ImageGirl2.setVisible(false);
		//男生图
		JPanel ImageBoy1 = new ImagePanel("imgsrc//WinJoinClub/ImageBoy.png",0, 0, 300, 300);
		ImageBoy1.setOpaque(false);
		ImageBoy1.setBounds(0, 0, 300, 300);
		ImageBoy1.setOpaque(false);//注意要设成透明的
		ImageBoy1.setVisible(false);

		JPanel ImageBoy2 = new ImagePanel("imgsrc//WinJoinClub/ImageBoyTwo.png",0, 0, 231, 300);
		ImageBoy2.setOpaque(false);
		ImageBoy2.setBounds(0, 0, 231, 300);
		ImageBoy2.setOpaque(false);//注意要设成透明的
		ImageBoy2.setVisible(false);
		
		JPanel ImageBoy3 = new ImagePanel("imgsrc//WinJoinClub/BoySing.png",0, 0, 200, 355);
		ImageBoy3.setOpaque(false);
		ImageBoy3.setBounds(0, 0, 200, 355);
		ImageBoy3.setOpaque(false);//注意要设成透明的
		ImageBoy3.setVisible(false);
		
		//吃瓜群众panel
		JPanel PanelOther = new JPanel();
		PanelOther.setOpaque(false);
		PanelOther.setBounds(120, 140, 300, 300);
		PanelOther.setLayout(null);
		
		//吃瓜群众图
		JPanel ImageOther = new ImagePanel("imgsrc//WinJoinClub/ImageYou.png",0, 0, 300, 300);
		ImageOther.setOpaque(false);
		ImageOther.setBounds(0, 0, 300, 300);
		ImageOther.setOpaque(false);//注意要设成透明的
		ImageOther.setVisible(false);

		
		//女生节横幅panel
		JPanel PanelGirlsDay = new JPanel();
		PanelGirlsDay.setOpaque(false);
		PanelGirlsDay.setBounds(100, 50, 1000, 300);
		PanelGirlsDay.setLayout(null);
		//女生节横幅图
		JPanel ImageGirlsBanner = new ImagePanel("imgsrc//WinJoinClub/GirlBanner.jpeg",0, 0, 1000, 300);
		ImageGirlsBanner.setOpaque(false);
		ImageGirlsBanner.setBounds(0, 0, 1000, 300);
		ImageGirlsBanner.setOpaque(false);//注意要设成透明的
		ImageGirlsBanner.setVisible(false);
		
		//女生节贺卡panel
		JPanel PanelGirlsCard = new JPanel();
		PanelGirlsCard.setOpaque(false);
		PanelGirlsCard.setBounds(400, 10, 272, 424);
		PanelGirlsCard.setLayout(null);
		//女生节贺卡图
		JPanel ImageGirlsCard = new ImagePanel("imgsrc//WinJoinClub/GirlsCard.png",0, 0, 272, 424);
		ImageGirlsCard.setOpaque(false);
		ImageGirlsCard.setBounds(0, 0, 272, 424);
		ImageGirlsCard.setOpaque(false);//注意要设成透明的
		ImageGirlsCard.setVisible(false);
				
		//离开 唱歌 不唱歌 邀请 不邀请 按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 100, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		JButton goSing = new JButton("要唱歌");
		goSing.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		goSing.setBounds(800, 150, 120, 50);
		backgroundPanel.add(goSing);
		goSing.setEnabled(false);
		goSing.setVisible(false);
		
		JButton noSing = new JButton("不唱歌");
		noSing.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		noSing.setBounds(800, 200, 120, 50);
		backgroundPanel.add(noSing);
		noSing.setEnabled(false);
		noSing.setVisible(false);
		
		JButton goInvite = new JButton("邀请TA");
		goInvite.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		goInvite.setBounds(800, 150, 120, 50);
		backgroundPanel.add(goInvite);
		goInvite.setEnabled(false);
		goInvite.setVisible(false);
		
		JButton noInvite = new JButton("不邀请");
		noInvite.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		noInvite.setBounds(800, 200, 120, 50);
		backgroundPanel.add(noInvite);
		noInvite.setEnabled(false);
		noInvite.setVisible(false);
		
		int speaker = -1; //0代表自己，1代表对方，2代表吃瓜群众，3表示同时出现，4表示自己唱歌，5表示对方唱歌
		String text="";  //对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>今天有两项活动：女生节和筹备学期末的国庆专场演出！</html>";
			break;
		case 1:
			if (dataPackage.ClubCharacter==4) {
				text="<html>你已经是队长啦，每次活动都需要你来组织哦～</html>";
			} else 
				text="<html>队长出差了，把组织这次活动的任务交给你啦～</html>";
			break;
		case 2:
			if (dataPackage.sex.equals("girl")) {
				text="<html>去年给男孩子们过了男生节，今年可以好好享受他们准备的礼物和心意啦～</html>";
			} else {
				text="<html>去年享受男生节，这次要好好给女神们过节啦～</html>";
			}
			break;
		case 3:
			speaker = 0;
			text="<html>咳咳，同学们，今天我们最重要的任务就是先给女孩子们过节！</html>";
			break;
		case 4:
			text="<html>首先，来看看我们诚意满满的横幅～</html>";
			ImageGirlsBanner.setVisible(true);
			break;
		case 5:
			text="<html>假如我是一朵雪花，你就是我的方向。</html>";
			ImageGirlsBanner.setVisible(true);
			break;
		case 6:
			speaker = 1;
			if (dataPackage.sex.equals("girl")) {
				text="<html>横幅来自徐志摩的《雪花的快乐》，希望你们一直快乐，也希望可以一直一起表演《雪花的快乐》呀！</html>";
			} else {
				text="<html>这是我最喜欢的诗，徐志摩的《雪花的快乐》，真是用心啦，谢谢你们！</html>";
			}
			break;
		case 7:
			speaker = 0;
			text="<html>下面还有哦，请欣赏更加有诚意的贺卡～</html>";
			ImageGirlsCard.setVisible(true);
			break;
		case 8:
			speaker = 0;
			text="<html>祝所有的女神们都节日快乐，温婉如初！</html>";
			ImageGirlsCard.setVisible(true);
			break;
		case 9:
			ImageGirlsCard.setVisible(true);
			text="<html>哦这是什么意思嘛，我们已经没有原来温婉了？</html>";
			if (dataPackage.sex.equals("girl")) speaker = 0;	
			else speaker = 1;
			break;
		case 10:
			if (dataPackage.sex.equals("girl")) speaker = 1;	
			else speaker = 0;
			text="<html>啊啊啊...不是这个意思啦，只是表达我们美好的祝福嘛！你最会开玩笑了嘻嘻～</html>";
			break;
		case 11:
			text="<html>大家都看着你们俩笑了起来～会议室里一片愉悦轻松的氛围！</html>";
			break;
		case 12:
			speaker = 2;
			text="<html>唱一个！唱一个！吃瓜群众开始起哄让你们俩献唱一首～</html>";
			break;
		case 13:
			text = "<html>现在可不能扫了大家的兴哦，即使你在大家面前有点害羞，要不要唱歌呢？</html>";
			goSing.setEnabled(true);
			goSing.setVisible(true);
			noSing.setEnabled(true);
			noSing.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
			break;
		case 14:
			speaker = 0;
			text = "<html>那就献丑啦，唱一首歌，送给各位小仙女们和大家啦！";
			text+="<br>社团进度+10，情商+2，艺术属性+3。</html>";
			break;
		case 15:
			speaker = 2;
			text = "<html>怎么能只有你自己唱呢，你们俩唱才有意思呀！一起唱！一起唱！</html>";
			break;
		case 16:
			text="<html>你要不要邀请TA一起唱呢？大家都很期待哦～</html>";
			goInvite.setEnabled(true);
			goInvite.setVisible(true);
			noInvite.setEnabled(true);
			noInvite.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
			break;
		case 17:
			speaker = 3;
			text = "<html>你邀请了TA一起唱歌~你们俩唱了莫文蔚的《慢慢喜欢你》。";
			text+="<br>社团进度+5，情商+2，幸福感+5，幸运值+5，消耗能量20。</html>";
			break;
		case 18:
			speaker = 4;
			text = "<html>慢慢的陪你慢慢的老去</html>";
			break;
		case 19:
			speaker = 5;
			text="<html>因为慢慢是最好的原因......</html>";
			break;
		case 20:
			speaker = 3;
			text="<html>谢谢大家！也希望大家在忙碌的生活中可以慢下来，好好享受时光呢～</html>";
			break;
		case 21:
			speaker = 2;
			text="<html>太棒了！好喜欢你们的歌声！</html>";
			break;
		case 22:
			text="<html>无论去年是没有告白还是告白失败，但今天，你发现TA就是慢慢喜欢的你！</html>";
			break;
		case 23:
			speaker = 3;
			text="<html>恭喜你解锁稀有成就————谈恋爱了！幸福感爆棚+10！</html>";
			break;
		case 24:
			speaker = 3;
			text="<html>谢谢大家，也感谢这个温暖的大家庭～再次祝各位小仙女节日快乐！</html>";
			break;
		case 25:
			speaker = 3;
			text="<html>你看着TA，更加确定TA就是慢慢喜欢的你～</html>";
			break;
		case 26:
			speaker = 0;
			text="<html>这位同学比较害羞，大家就不要为难TA了哦，一首《雪花的快乐》送给大家～</html>";
			text+="<br>情商-2，能量消耗15。</html>";
			break;
		case 27:
			speaker = 4;
			text="<html>等着她来花园里探望——飞扬，飞扬，飞扬——</html>";
			break;
		case 28:
			speaker = 4;
			text="<html>啊，她身上有朱砂梅的清香！</html>";
			break;
		case 29:
			speaker = 1;
			text="<html>你唱歌很好听哦～没想到唱情歌也这么厉害呀！</html>";
			break;
		case 30:
			speaker = 0;
			text="<html>emmm不要嘲笑我了嘛，以后可以多多一起唱歌（脸红）</html>";
			break;
		case 31:
			speaker = 0;
			text="<html>对不起同学们哦，我今天有点不舒服，改天一定给大家唱歌！";
			text+="<br>社团进度+5，情商-2，消耗能量10。</html>";
			break;
		case 32:
			speaker = 1;
			text="<html>以后不要这么害羞啦，要勇敢一点哦，其实你唱歌很好听的呢～</html>";
			break;
		case 33:
			speaker = 0;
			text="<html>是吗？谢谢你的鼓励～我从小就比较害羞，不敢在大家面前表演。</html>";
			break;
		case 34:
			speaker = 1;
			text="<html>没关系哒，多练习就好！下次我可以陪你一起唱歌哦～</html>";
			break;
		case 35:
			speaker = 0;
			text="<html>太谢谢你啦！嗯嗯下次跟你一起唱，我就不会那么害羞啦～</html>";
			break;
		case 36:
			speaker = 0;
			text="<html>咳咳好啦，接下来我们来讨论一下正事。本学期末我们要举办一次大型的专场演出，希望同学们积极报名参与</html>";
			break;
		case 37:
			speaker = 0;
			text="<html>今天活动结束得晚就不训练啦，下周要加训哦～大家一起加油，我们把大学期间最后一次活动办好！</html>";
			break;
		case 38:
			speaker = 1;
			text="<html>嗯嗯我们一起加油！</html>";
			break;
		case 39:
			speaker = 2;
			text="<html>想到专场演出，我就迫不及待了呢！</html>";
			break;
		case 40:
			speaker = 0;
			text="<html>好啦今天辛苦大家了！时候不早了，大家回去好好休息哦～</html>";
			break;
		case 41:
			text="<html>今天的活动就到这里啦，点击离开返回～</html>";
			backButton.setEnabled(true);
			backButton.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
			break;
		}
		
		switch(speaker) {
		case 0: //自己
			if (dataPackage.sex.equals("girl")) ImageGirl1.setVisible(true);
			else {
				if ((dataPackage.count>=10&&dataPackage.count<=14)||(dataPackage.count>=29&&dataPackage.count<=35)) {
					ImageBoy1.setVisible(true);
				} else ImageBoy2.setVisible(true);
			}
			textSpeaker.setText("<html>我</html>");
			break;
		case 1: //对方
			if (dataPackage.sex.equals("girl")) {
				if ((dataPackage.count>=10&&dataPackage.count<=14)||(dataPackage.count>=29&&dataPackage.count<=35)) {
					ImageBoy1.setVisible(true);
				} else ImageBoy2.setVisible(true);
			}
			else ImageGirl1.setVisible(true);
			textSpeaker.setText("<html>TA</html>");
			break;
		case 2: //吃瓜群众
			ImageOther.setVisible(true);
			textSpeaker.setText("<html>吃瓜群众</html>");
			break;
		case 3:
			ImageGirl2.setVisible(true);
			ImageBoy3.setVisible(true);
			break;
		case 4:
			if (dataPackage.sex.equals("girl")) ImageGirl2.setVisible(true);
			else ImageBoy3.setVisible(true);
			break;
		case 5:
			if (dataPackage.sex.equals("girl")) ImageBoy3.setVisible(true);
			else ImageGirl2.setVisible(true);
			break;
		}
		
		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		if (dataPackage.sex.equals("girl")) {
			PanelYou.add(ImageGirl1);
			if ((dataPackage.count>=10&&dataPackage.count<=14)||(dataPackage.count>=29&&dataPackage.count<=35)) {
				PanelTheOne.add(ImageBoy1);
			} else PanelTheOne.add(ImageBoy2);
			
		} else {
			if ((dataPackage.count>=10&&dataPackage.count<=14)||(dataPackage.count>=29&&dataPackage.count<=35)) {
				PanelYou.add(ImageBoy1);
			} else PanelYou.add(ImageBoy2);
			PanelTheOne.add(ImageGirl1);
		}
		PanelOther.add(ImageOther);
		PanelGirlsDay.add(ImageGirlsBanner);
		PanelGirlsCard.add(ImageGirlsCard);
		PanelGirl.add(ImageGirl2);
		PanelBoy.add(ImageBoy3);
		
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelTheOne);
		backgroundPanel.add(PanelOther);
		backgroundPanel.add(PanelYou);
		backgroundPanel.add(PanelGirl);
		backgroundPanel.add(PanelBoy);
		backgroundPanel.add(PanelGirlsCard);
		backgroundPanel.add(PanelGirlsDay);
		if (dataPackage.count==23||(dataPackage.count>=17 && dataPackage.count<=19)) {
			backgroundPanel.add(backgroundImage2);
		} else if (dataPackage.count>=26 && dataPackage.count<=28) {
			backgroundPanel.add(backgroundImage3);
		} else backgroundPanel.add(backgroundImage);
		
		ClubActivityListener.dataPackage=dataPackage;
		ClubActivityListener.mainGame=mainGame;
		
		/*		START OF YOUR CODE		*/
		/*****************************************************************				
		 * 【细节设定】
		 * 在用插件绘制完界面之后进行界面内数值设定
		 * 利用数据包进行显示控件的输出的设置
		 *****************************************************************/
		/*		START OF YOUR CODE		*/
		
		label_energy.setText("体力: "+dataPackage.characterEnergy);
		label_EQ.setText("情商 :"+dataPackage.characterEQ);
		label_process.setText("社团进度: "+dataPackage.inClubPorcess);
		label_happy.setText("心情: "+dataPackage.characterHappiness);
		label_Art.setText("才艺: "+dataPackage.characterArt);
		label_lucky.setText("幸运值: "+dataPackage.characterlucky);
		
		/*****			
		 * 重要，事件监听器一定要把mainGame传入
		 ****/
		ClubActivityListener clicknext=new ClubActivityListener(0);//设置鼠标监听器，发生0号事件
		ClubActivityListener clickback = new ClubActivityListener(1); //发生1号事件，返回MAP
		ClubActivityListener clickgoSing = new ClubActivityListener(2); //发生2号事件，去唱歌
		ClubActivityListener clicknoSing = new ClubActivityListener(3); //发生3号事件，不唱歌
		ClubActivityListener clickgoInvite = new ClubActivityListener(4); //发生4号事件，去邀请
		ClubActivityListener clicknoInvite = new ClubActivityListener(5); //发生5号事件，不邀请
		
		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		clickgoSing.setButton(goSing);
		clicknoSing.setButton(noSing);
		clickgoInvite.setButton(goInvite);
		clicknoInvite.setButton(noInvite);
		
		nextButton.addMouseListener(clicknext);//0号事件
		backButton.addMouseListener(clickback);
		goSing.addMouseListener(clickgoSing);
		noSing.addMouseListener(clicknoSing);
		goInvite.addMouseListener(clickgoInvite);
		noInvite.addMouseListener(clicknoInvite);

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


