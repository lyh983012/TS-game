package THUgame.subwindows;

import THUgame.windows.WinBase;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
 * 社团Term1，Week4活动：培训和男生节聚会
 * 
 * --DIALOG--
 * 
 * update:20191214
 * via：卢宇芳
 * 
 **/


public class WinClubActivityThree extends WinBase{
	

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
						dataPackage.choiceA="BoysDay";
					} else if (mode==3) {
						dataPackage.choiceB="BeBrave";
					} else if (mode==4) {
						dataPackage.choiceB="IamAfraid";
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
	public WinClubActivityThree(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivityThree.jpeg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
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
		
		JPanel ImageGirl2 = new ImagePanel("imgsrc//WinJoinClub/ImageGirl.png",0, 0, 300, 300);
		ImageGirl2.setOpaque(false);
		ImageGirl2.setBounds(0, 0, 300, 300);
		ImageGirl2.setOpaque(false);//注意要设成透明的
		ImageGirl2.setVisible(false);
		
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
		
		JPanel ImageBoy2 = new ImagePanel("imgsrc//WinJoinClub/ImageBoy.png",0, 0, 300, 300);
		ImageBoy2.setOpaque(false);
		ImageBoy2.setBounds(0, 0, 300, 300);
		ImageBoy2.setOpaque(false);//注意要设成透明的
		ImageBoy2.setVisible(false);
		
		//主人公
		JPanel PanelYou = new JPanel();
		PanelYou.setOpaque(false);
		PanelYou.setBounds(700, 140, 300, 300);
		PanelYou.setLayout(null);
		
		JPanel ImageYou = new ImagePanel("imgsrc//WinJoinClub/ImageYou.png",0, 0, 300, 300);
		ImageYou.setOpaque(false);
		ImageYou.setBounds(0, 0, 300, 300);
		ImageYou.setOpaque(false);//注意要设成透明的
		ImageYou.setVisible(false);
		
		//离开 男生节 告白 不去按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 150, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		JButton joinClubBoysDay = new JButton("男生节");
		joinClubBoysDay.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		joinClubBoysDay.setBounds(800, 200, 100, 50);
		backgroundPanel.add(joinClubBoysDay);
		joinClubBoysDay.setEnabled(false);
		joinClubBoysDay.setVisible(false);
		
		JButton beBrave = new JButton("去告白");
		beBrave.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		beBrave.setBounds(800, 150, 100, 50);
		backgroundPanel.add(beBrave);
		beBrave.setEnabled(false);
		beBrave.setVisible(false);
		
		JButton iamAfraid = new JButton("不敢去");
		iamAfraid.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		iamAfraid.setBounds(800, 200, 100, 50);
		backgroundPanel.add(iamAfraid);
		iamAfraid.setEnabled(false);
		iamAfraid.setVisible(false);
		
		int speaker = -1; //0代表自己，1代表对方，3表示同时出现
		String text="";  //对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>今天的工作有两项：日常训练和训练后为男孩子们过节！</html>";
			break;
		case 1:
			text="<html>每学期的第四周要选择一天为男生节哦，这是清华的传统！</html>";
			break;
		case 2:
			text="<html>每年第二个学期的第四周是女生节活动，如果你是女生就期待下学期的节日吧！</html>";
			break;
		case 3:
			if(dataPackage.ClubCharacter==1 && dataPackage.sex.equals("girl")) {
				text="<html>今天外联没什么特别的工作呢，要和女生们一起给男生过节！</html>";
			}else if(dataPackage.ClubCharacter==1 && dataPackage.sex.equals("boy")) {
				text="<html>今天外联没什么特别的工作呢，只需要好好享受女孩子们的礼物和祝福！</html>";
			}else if(dataPackage.ClubCharacter==1 && dataPackage.sex.equals("others")) {
				text="<html>虽然你性别特殊，但清华有很强的包容性，和男生们一起享受节日就好啦！</html>";
			}else if(dataPackage.ClubCharacter==2) {
				text="<html>内联今天的活动比较重哦，需要提前订蛋糕，还要构思男生节横幅，布置场地。</html>";
			}else if(dataPackage.ClubCharacter==3) {
				text="<html>今天需要用相机记录下诸多美好的瞬间，多拍男孩子的表情包，多拍女孩子的美照！</html>";
			}
			break;
		case 4:
			text="<html>当然，第一项任务还是要好好完成日常培训，下学期就要去合唱专场演出了！</html>";
			break;
		case 5:
			text="<html>今天我们首先要回顾一下《我和我的祖国》！</html>";
			ImageLyrics.setVisible(true);
			break;
		case 6:
			text="<html>不知不觉3小时已经过去了，社团进度+10，体力值-15，艺术值+5</html>";
			break;
		case 7:
			text="<html>今天的常规培训结束了，由于要过男生节，就不加训啦～</html>";
			break;
		case 8:
			text="<html>但是由于训练时间比较久，你感觉有点疲惫。可以选择回宿舍休息或者和小伙伴们过节～</html>";
			break;
		case 9:
			if (dataPackage.sex.equals("girl")) {
				text="<html>上次那位嗓子痛的男孩子恢复的很好，他似乎很希望你留下来～";
				speaker = 1;
			} else if (dataPackage.sex.equals("boy")){
				text="<html>上次那位嗓子痛的女孩子恢复的很好，她似乎很希望你留下来～";
				speaker = 1;
			} else {
				text="<html>如果留下来的话，可以和同学们聊聊天，深入交流感情～";
			}
			break;
		case 10:
			text="<html>这时候，你选择牺牲休息时间留下来过节？还是回去休息呢？</html>";
			joinClubBoysDay.setEnabled(true);
			joinClubBoysDay.setVisible(true);
			backButton.setEnabled(true);
			backButton.setVisible(true);
			break;
		case 11:
			text="<html>你选择了留下来过男生节！</html>";
			break;
		case 12:
			if (dataPackage.sex.equals("boy")||dataPackage.sex.equals("others")) {
				text = "<html>你享受着女孩们们精心准备的礼物、蛋糕和横幅，心情十分快乐！</html>";
			}else if (dataPackage.sex.equals("girl")) {
				text = "<html>你为男孩子们准备了蛋糕，礼物，还有横幅！</html>";
			}
			break;
		case 13:
			text = "<html>只见横幅上写着：阿妹的歌儿传四方，阿哥那个幸福日日长！</html>";
			break;
		case 14:
			text = "<html>不知不觉中......时间又过去了2小时，体力值-10，社团进度+5，幸福感+5</html>";
			break;
		case 15:
			speaker = 1;
			if (dataPackage.sex.equals("girl")) {
				text = "<html>之前那个男孩子一直在关注你，你是否要鼓起勇气向他走过去呢？</html>";
			}else {
				text = "<html>那个女孩子一直在关注你，还为你特地准备了一份礼物，你是否要鼓起勇气向她走过去呢？</html>";
			}
			beBrave.setEnabled(true);
			beBrave.setVisible(true);
			iamAfraid.setEnabled(true);
			iamAfraid.setVisible(true);
			break;
		case 16:
			speaker = 0;
			text="<html>你走向了ta，迈出了勇敢的一步！</html>";
			break;
		case 17:
			speaker = 0;
			text="<html>原来你们俩一直都在互相关注，你对ta轻声说，今晚月色好美～</html>";
			break;
		case 18:
			speaker = 3;
			text="<html>恭喜你解锁稀有成就——谈恋爱了！幸福感爆棚+10</html>";
			break;
		case 19:
			speaker = 1;
			text="<html>很遗憾，女生对你说，你是个好人......";
			text+="<br><br>虽然没有解锁谈恋爱成就，幸福感-10，但这次失败下次成功机会就会提高哦，幸运值+2</html>";
			break;
		case 20:
			text="<html>很多事情都是需要勇气的呢！</html>";
			break;
		case 21:
			text="<html>虽然没有成功走出那一步，幸福感-5，但之后活动还很多，还有机会呢！</html>";
			break;
		case 22:
			text="<html>这就是今天活动的所有内容了，这学期所有的合唱团活动也结束啦！点击离开返回～</html>";
			backButton.setEnabled(true);
			backButton.setVisible(true);
			break;
		}
		if (dataPackage.count==10||dataPackage.count==15||dataPackage.count==22) {
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
		}
		
		switch(speaker) {
		case 0:
			if (dataPackage.sex.equals("girl")) ImageGirl2.setVisible(true);
			else ImageBoy2.setVisible(true);
			break;
		case 1:
			if (dataPackage.sex.equals("girl")) ImageBoy.setVisible(true);
			else ImageGirl.setVisible(true);
			break;
		case 3:
			ImageGirl.setVisible(true);
			ImageBoy.setVisible(true);
			break;
		}
		
		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		PanelLyrics.add(ImageLyrics);
		PanelGirl.add(ImageGirl);
		PanelBoy.add(ImageBoy);
		if (dataPackage.sex.equals("girl")) PanelYou.add(ImageGirl2);
		else PanelYou.add(ImageBoy2);
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelGirl);
		backgroundPanel.add(PanelBoy);
		backgroundPanel.add(PanelYou);
		backgroundPanel.add(PanelLyrics);
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
		ClubActivityListener clickClubBoysDay = new ClubActivityListener(2); //发生2号事件，过男生节
		ClubActivityListener clickbeBrave = new ClubActivityListener(3); //发生3号事件，去告白
		ClubActivityListener clickiamAfraid = new ClubActivityListener(4); //发生3号事件，不敢去
		
		clicknext.setButton(nextButton);
		nextButton.addMouseListener(clicknext);//0号事件
		clickback.setButton(backButton);
		clickClubBoysDay.setButton(joinClubBoysDay);
		clickbeBrave.setButton(beBrave);
		clickiamAfraid.setButton(iamAfraid);
		
		backButton.addMouseListener(clickback);
		joinClubBoysDay.addMouseListener(clickClubBoysDay);
		beBrave.addMouseListener(clickbeBrave);
		iamAfraid.addMouseListener(clickiamAfraid);

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


