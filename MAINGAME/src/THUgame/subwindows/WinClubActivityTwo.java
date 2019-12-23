package THUgame.subwindows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

/* 
 * 社团第二次活动，日常培训
 * 
 * --DIALOG--
 * 
 * update:20191124
 * via：卢宇芳
 * 更新：新建此事件
 * 需要的工作：
 * 1. go to hospital是否能切换背景
 * 2. 人物和对话加入，需要美工和加倍帮助
 * 3. 如何设置在某一事件完成前，其他按钮不出现
 * 
 **/


/*************************************************************	
 *简介
 * 基于其它模板改写，与多次多重选择事件模板一起使用
 * 
 *************************************************************/

public class WinClubActivityTwo extends WinBase{
	

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
			@Override
			public void setFrame(JFrame frame) {
				this.frame=frame;
			}
			@Override
			public void setGame(EventManager mainGame) {
				ClubActivityListener.mainGame=mainGame;
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
						dataPackage.choiceA="ExtraTrain";
					} else if (mode==3) {
						dataPackage.choiceA="ToHospital";
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
	public WinClubActivityTwo(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivityTwo.jpeg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
		JPanel ImageHospital=new ImagePanel("imgsrc//WinJoinClub/hospital.jpg",0, 0, 1080, 720);	
		ImageHospital.setOpaque(false);
		ImageHospital.setBounds(0, 0, 1080, 720);
		
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
		
		//歌词按钮
		JPanel PanelLyrics = new JPanel();
		PanelLyrics.setOpaque(false);
		PanelLyrics.setBounds(365, 40, 350, 562);
		PanelLyrics.setLayout(null);
		
		JPanel ImageLyrics = new ImagePanel("imgsrc//WinJoinClub/lyrics.png",0, 0, 350, 562);
		ImageLyrics.setOpaque(false);
		ImageLyrics.setBounds(0, 0, 350, 562);
		ImageLyrics.setOpaque(false);//注意要设成透明的
		ImageLyrics.setVisible(false);
		
		//女生
		JPanel PanelGirl = new JPanel();
		PanelGirl.setOpaque(false);
		PanelGirl.setBounds(100, 140, 300, 300);
		PanelGirl.setLayout(null);
		
		JPanel ImageGirl = new ImagePanel("imgsrc//WinJoinClub/ImageGirl.png",0, 0, 300, 300);
		ImageGirl.setOpaque(false);
		ImageGirl.setBounds(0, 0, 300, 300);
		ImageGirl.setOpaque(false);//注意要设成透明的
		ImageGirl.setVisible(false);
		
		//男生
		JPanel PanelBoy = new JPanel();
		PanelBoy.setOpaque(false);
		PanelBoy.setBounds(100, 140, 300, 300);
		PanelBoy.setLayout(null);
		
		JPanel ImageBoy = new ImagePanel("imgsrc//WinJoinClub/ImageBoy.png",0, 0, 300, 300);
		ImageBoy.setOpaque(false);
		ImageBoy.setBounds(0, 0, 300, 300);
		ImageBoy.setOpaque(false);//注意要设成透明的
		ImageBoy.setVisible(false);
		
		//离开 加训 去医院按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 150, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		JButton joinClubExtraTrain = new JButton("加训");
		joinClubExtraTrain.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		joinClubExtraTrain.setBounds(800, 200, 80, 50);
		backgroundPanel.add(joinClubExtraTrain);
		joinClubExtraTrain.setEnabled(false);
		joinClubExtraTrain.setVisible(false);
		
		JButton gotoHospital = new JButton("去医院");
		gotoHospital.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		gotoHospital.setBounds(800, 250, 100, 50);
		backgroundPanel.add(gotoHospital);
		gotoHospital.setEnabled(false);
		gotoHospital.setVisible(false);
		
		String text="";//对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>上周开了第一次例会，和很多同学们都逐渐熟悉了！</html>";
			break;
		case 1:
			text="<html>今天要开始第一次日常培训了！</html>";
			break;
		case 2:
			if(dataPackage.ClubCharacter==1) {
				text="<html>第一次培训，需要和学校部门沟通借好场地，合唱队的培训在蒙民伟音乐厅。</html>";
			}else if(dataPackage.ClubCharacter==2) {
				text="<html>今天需要统计大家出勤情况，并为大家准备物资：饮用水，歌词和金嗓子等。</html>";
			}else if(dataPackage.ClubCharacter==3) {
				text="<html>第一次培训，要为每一个队员都拍美美的照片，并制作推送，当然自己的也不能少哦。</html>";
			}
			break;
		case 3:
			text="<html>准备好了所有的前期工作，体力值有了一些消耗，下面就要开始正式的培训了。</html>";
			break;
		case 4:
			text="<html>今年是祖国的70岁生日，每个人耳边都飘荡着《我和我的祖国》，就培训这首歌吧！</html>";
			break;
		case 5:
			text="<html>如果你训练刻苦，表现很好，还会被选上国庆当天参加专项活动呢！</html>";
			break;
		case 6:
			text="<html>我和我的祖国，一刻也不能分割......</html>";
			break;
		case 7:
			text="<html>不知不觉2小时已经过去了，社团进度+10，体力值-15，艺术值+5.</html>";
			break;
		case 8:
			text="<html>今天的常规培训结束了，但清华的同学怎么能只有常规训练呢！接下来还有加训～</html>";
			break;
		case 9:
			text="<html>但是今天由于训练时间比较久，有位同学嗓子不舒服，需要去医院。</html>";
			break;
		case 10:
			if (dataPackage.sex.equals("girl")) {
				text="<html>这位同学是个很帅气的男孩子，上次例会时他好像很关注你呢～</html>";
			} else {
				text="<html>这位同学是个很可爱和善良的妹子，上次例会时他坐在你旁边，不时往你这边看呢～</html>";
			}
			break;
		case 11:
			text="<html>如果选择加训，在合唱队的成长会加速，说不定很快就能成为社长了！</html>";
			break;
		case 12:
			text+="<html>当然，一天结束后训练很累了，可以会宿舍休息或者自习，毕竟学业压力也很重。</html>";
			break;
		case 13:
			text="<html>这时候，你选择陪同学去医院？还是加训？还是回去休息呢？点击对应的按钮哦～</html>";
			joinClubExtraTrain.setEnabled(true);
			joinClubExtraTrain.setVisible(true);
			gotoHospital.setEnabled(true);
			gotoHospital.setVisible(true);
			break;
		case 14:
			text="<html>你选择了加训，可见你对艺术和祖国的热爱！";
			text += "<br>加训后消耗了体力和健康，但社团进度加速了，很快就可以当选社长！</html>";
			break;
		case 15:
			text="<html>时间又过去了2小时，体力值-10，健康值-2，社团进度+10，艺术值+2</html>";
			break;
		case 16:
			text="<html>你选择了陪朋友去医院，医生检查了你朋友身体无大碍，好好休息下就好啦！</html>";
			break;
		case 17:
			text="<html>你朋友非常感谢你，虽然ta嗓子不舒服，但你们有很多共同话题，整个过程都很开心。</html>";
			break;
		case 18:
			text="<html>时间又过去了2小时，体力值-10，情商+10，幸运值+5，幸福感+10</html>";
			break;
		case 19:
			text="<html>今天有关合唱队的故事就到这里啦！点击离开返回～</html>";
			break;
			}
		
		if (dataPackage.count==13||dataPackage.count==19) {
			backButton.setEnabled(true);
			backButton.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
		}
		if ((dataPackage.count>=4 && dataPackage.count<=6)||dataPackage.count==14) {
			ImageLyrics.setVisible(true);
		}
		if ((dataPackage.count>=16 && dataPackage.count<=17)||dataPackage.count==10) {
			if (dataPackage.sex.equals("girl")) ImageBoy.setVisible(true);
			else ImageGirl.setVisible(true);
		} 
		
		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		PanelGirl.add(ImageGirl);
		PanelBoy.add(ImageBoy);
		PanelLyrics.add(ImageLyrics);
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelGirl);
		backgroundPanel.add(PanelBoy);
		backgroundPanel.add(PanelLyrics);
		if (dataPackage.count>=16 && dataPackage.count<=18) 
			backgroundPanel.add(ImageHospital);
		else 
			backgroundPanel.add(backgroundImage);

		ClubActivityListener.dataPackage=dataPackage;
		ClubActivityListener.mainGame=mainGame;
		
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
		ClubActivityListener clickClubExtraTrain = new ClubActivityListener(2); //发生2号事件，加训
		ClubActivityListener clickgotoHospital = new ClubActivityListener(3); //发生3号事件，陪朋友去医院
		
		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		clickClubExtraTrain.setButton(joinClubExtraTrain);
		clickgotoHospital.setButton(gotoHospital);
		
		nextButton.addMouseListener(clicknext);//0号事件
		backButton.addMouseListener(clickback);
		joinClubExtraTrain.addMouseListener(clickClubExtraTrain);
		gotoHospital.addMouseListener(clickgotoHospital);



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


