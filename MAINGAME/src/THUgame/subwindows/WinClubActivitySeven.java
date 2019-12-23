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
 * 社团活动，第二学期第四周
 * 
 * --DIALOG--
 * 
 * update:20191214
 * via：卢宇芳
 * 最后一次专场演出活动
 * 
 **/

public class WinClubActivitySeven extends WinBase{

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
					}else if (mode==2) {
						dataPackage.choiceB="Collectivism";
					}else if (mode==3) {
						dataPackage.choiceB="Individualism";
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
	public WinClubActivitySeven(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivitySeven.jpeg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
		JPanel backgroundImage2=new ImagePanel("imgsrc//WinJoinClub/ClubActivitySeven2.jpeg",0, 0, 1080, 720);	
		backgroundImage2.setOpaque(false);
		backgroundImage2.setBounds(0, 0, 1080, 720);

		
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
		//指挥panel
		JPanel PanelCommand = new JPanel();
		PanelCommand.setOpaque(false);
		PanelCommand.setBounds(720, 140, 300, 300);
		PanelCommand.setLayout(null);
		
		//女生图
		JPanel ImageGirl1 = new ImagePanel("imgsrc//WinJoinClub/ImageGirl.png",0, 0, 300, 300);
		ImageGirl1.setOpaque(false);
		ImageGirl1.setBounds(0, 0, 300, 300);
		ImageGirl1.setOpaque(false);//注意要设成透明的
		ImageGirl1.setVisible(false);
		//老师图
		JPanel ImageCommand = new ImagePanel("imgsrc//WinJoinClub/ImageCommand.png",0, 0, 300, 300);
		ImageCommand.setOpaque(false);
		ImageCommand.setBounds(0, 0, 300, 300);
		ImageCommand.setOpaque(false);//注意要设成透明的
		ImageCommand.setVisible(false);
		//男生图
		JPanel ImageBoy1 = new ImagePanel("imgsrc//WinJoinClub/ImageBoy.png",0, 0, 300, 300);
		ImageBoy1.setOpaque(false);
		ImageBoy1.setBounds(0, 0, 300, 300);
		ImageBoy1.setOpaque(false);//注意要设成透明的
		ImageBoy1.setVisible(false);
		
		//合照panel
		JPanel PanelGroupPhoto = new JPanel();
		PanelGroupPhoto.setOpaque(false);
		PanelGroupPhoto.setBounds(400, 40, 600, 285);
		PanelGroupPhoto.setLayout(null);
		
		//合照图
		JPanel ImageGroupPhoto = new ImagePanel("imgsrc//WinJoinClub/GroupPhoto.png",0, 0, 600, 285);
		ImageGroupPhoto.setOpaque(false);
		ImageGroupPhoto.setBounds(0, 0, 600, 285);
		ImageGroupPhoto.setOpaque(false);//注意要设成透明的
		ImageGroupPhoto.setVisible(false);
		
		//合照panel
		JPanel PanelTwoPhoto = new JPanel();
		PanelTwoPhoto.setOpaque(false);
		PanelTwoPhoto.setBounds(350, 40, 350, 350);
		PanelTwoPhoto.setLayout(null);
		
		//两人合照图
		JPanel ImageTwoPhoto = new ImagePanel("imgsrc//WinJoinClub/TwoPhoto.jpeg",0, 0, 350, 350);
		ImageTwoPhoto.setOpaque(false);
		ImageTwoPhoto.setBounds(0, 0, 350, 350);
		ImageTwoPhoto.setOpaque(false);//注意要设成透明的
		ImageTwoPhoto.setVisible(false);
				
		//离开 个人荣誉 集体荣誉 按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 100, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		JButton CollectButton = new JButton("集体荣誉");
		CollectButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		CollectButton.setBounds(800, 150, 160, 50);
		backgroundPanel.add(CollectButton);
		CollectButton.setEnabled(false);
		CollectButton.setVisible(false);
		
		JButton IndiButton = new JButton("个人荣誉");
		IndiButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		IndiButton.setBounds(800, 200, 160, 50);
		backgroundPanel.add(IndiButton);
		IndiButton.setEnabled(false);
		IndiButton.setVisible(false);
		
		int speaker = -1; //0代表自己，1代表对方，2代表老师，3代表大家
		String text="";  //对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>今天最重要的事情，就是国庆专场演出！</html>";
			break;
		case 1:
			speaker = 0;
			text="<html>想想要为全校的师生甚至全国的人民演唱，真是非常激动呢！</html>";
			break;
		case 2:
			speaker = 1;
			text="<html>唉，我还有一点紧张呢，害怕自己发挥失误...</html>";
			break;
		case 3:
			speaker = 2;
			text="<html>同学们，一年一度重要的专场演出来啦！这次更加重要哦，因为我们要为祖国70周年献礼～</html>";
			break;
		case 4:
			speaker = 2;
			text="<html>大家各就各位，打起十二分精神，演出马上开始啦！</html>";
			break;
		case 5:
			text="<html>首先，大家一起唱了清华大学校歌！</html>";
			break;
		case 6:
			speaker = 0;
			text="<html>哇！全场的观众都跟着我们挥舞着国旗唱歌，好激动呢！</html>";
			break;
		case 7:
			speaker = 1;
			text="<html>是呀，能让观众享受我们的演唱，感觉之前所有的训练都值得了～</html>";
			break;
		case 8:
			text="<html>随着《难忘今宵》熟(出)悉(戏)的歌声，不知不觉已经过去了3小时";
			text+="<br><br>体力消耗20，才艺值+10，幸福感+5，社团进度+10</html>";
			break;
		case 9:
			text="<html>观众们都似乎意犹未尽，恋恋不舍，这次演出看来很成功呀！</html>";
			break;
		case 10:
			speaker = 0;
			text="<html>虽然很累，但看大家都很开心，在合唱团的成长也算圆满告一个段落了！</html>";
			break;
		case 11:
			speaker = 2;
			if (dataPackage.sex.equals("girl")) {
				text="<html>小姑娘，我在培训的时候仔细观察着你，觉得你很有唱歌的天赋呀！</html>";
			} else text="<html>小伙子，我在培训的时候仔细观察着你，觉得你很有唱歌的天赋呀！</html>";
			break;
		case 12:
			speaker = 2;
			text="<html>毕业了可以考虑加入北京市青年合唱团，以后一定是可塑之才！</html>";
			break;
		case 13:
			speaker = 0;
			text = "<html>谢谢老师的认可！我也很喜欢唱歌和合唱队的大家！毕业之后我会考虑的～</html>";
			break;
		case 14:
			speaker = 1;
			text = "<html>你毕业之后真的要去合唱团吗？</html>";
			break;
		case 15:
			speaker = 0;
			text = "<html>唔，我还没有考虑好...毕竟合唱只是我的业余爱好，还是要以学业为重呢...</html>";
			break;
		case 16:
			speaker = 1;
			text="<html>喜欢的和想做的事情就大胆去做，我会一直支持你的！</html>";
			break;
		case 17:
			speaker = 0;
			text="<html>谢谢你的鼓励哦！对了，你毕业之后打算去哪呢？</html>";
			break;
		case 18:
			speaker = 1;
			if (dataPackage.sex.equals("girl")) {
				text="<html>我呀，我很可能就继续留在清华了，这里的生活我很喜欢，还想在园子里多待几年！</html>";
			} else text="<html>我要去隔壁北大读书了，你如果留在北京我们也会离得很近哦～</html>";
			break;
		case 19:
			speaker = 0;
			text = "<html>我们一起拍一张合照留作纪念吧！</html>";
			break;
		case 20:
			speaker = 1;
			text="<html>好耶！这样以后看到照片就可以想起你哦！</html>";
			break;
		case 21:
			text="<html>这张照片真好，我会一直珍藏着的，你在心里想...</html>";
			ImageTwoPhoto.setVisible(true);
			break;
		case 22:
			text="<html>学期结束了，需要帮合唱队申请十佳社团荣誉，也需要给自己申请社团优秀个人金奖。</html>";
			break;
		case 23:
			text="<html>但由于时间有限，你只能申请一个。这时候，你选则集体荣誉还是个人荣誉的申请呢？</html>";
			CollectButton.setEnabled(true);
			CollectButton.setVisible(true);
			IndiButton.setEnabled(true);
			IndiButton.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
			break;
		case 24:
			speaker = 0;
			text="<html>跟合唱队的家人们感情这么深了，我的个人荣誉又算得了什么呢？大家好才是真的好！</html>";
			text+="<br><br>幸运值+5，情商+2，社团进度+5</html>";
			break;
		case 25:
			text="<html>你忙于为社团申请十佳，没能去参加庆祝祖国七十周年的合唱活动！</html>";
			break;
		case 26:
			text = "<html>现场的小伙伴们为你发来了一张图片，你们也成功被评选为年度十佳社团！</html>";
			ImageGroupPhoto.setVisible(true);
			break;
		case 27:
			speaker = 1;
			text="<html>大家都说，虽然你没有去现场，但每个人唱歌时心里都装着你呢！我也是～</html>";
			ImageGroupPhoto.setVisible(true);
			break;
		case 28:
			speaker = 0;
			text="<html>没有啦哈哈哈应该的，大家都这么棒！你们去演出了就是我去演出了！</html>";
			ImageGroupPhoto.setVisible(true);
			break;
		case 29:
			text="<html>你选择了为自己申请个人金奖，放弃了合唱队的荣誉申请...";
			text+="<br><br>幸运值-5，情商-2。</html>";
			break;
		case 30:
			speaker = 0;
			text="<html>因为忙于申请，你没能去参加庆祝祖国七十周年的合唱活动！</html>";
			break;
		case 31:
			speaker = 1;
			text="<html>虽然有时候个人的荣誉很重要，但我觉得放弃团体的申请不可取。</html>";
			break;
		case 32:
			speaker = 1;
			text="<html>既然你没有时间，只好我负责了全部的申请，我们成功获得了十佳社团称号。看照片～</html>";
			ImageGroupPhoto.setVisible(true);
			break;
		case 33:
			speaker = 0;
			text="<html>不好意思...这次是我的不对，我不该太看重个人的荣誉...</html>";
			break;
		case 34:
			speaker = 1;
			text="<html>没关系哦，大家也都理解你，不要太放在心上，反正我们最后拿到十佳啦！</html>";
			break;
		case 35:
			speaker = 0;
			text="<html>嗯嗯！你们准备材料一定很辛苦了！</html>";
			break;
		case 36:
			speaker = 0;
			text="<html>你看着大家的照片出了神，回想起在合唱队的点点滴滴...</html>";
			ImageGroupPhoto.setVisible(true);
			break;
		case 37:
			speaker = 3;
			text="<html>还有那个你一直特别关心的人...</html>";
			break;
		case 38:
			speaker = 0;
			text="<html>虽然不舍，但最终还要说告别呀！</html>";
			break;
		case 39:
			speaker = 0;
			text="<html>社团支线到此就全部结束啦，点击离开，接下来好好准备毕业吧～</html>";
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
			else ImageBoy1.setVisible(true);
			textSpeaker.setText("<html>我</html>");
			break;
		case 1: //对方
			if (dataPackage.sex.equals("girl")) ImageBoy1.setVisible(true);
			else ImageGirl1.setVisible(true);
			textSpeaker.setText("<html>TA</html>");
			break;
		case 2: //指导老师
			ImageCommand.setVisible(true);
			textSpeaker.setText("<html>指导老师</html>");
			break;
		case 3://两个人
			ImageBoy1.setVisible(true);
			ImageGirl1.setVisible(true);
			break;
		}

		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		if (dataPackage.sex.equals("girl")) {
			PanelYou.add(ImageGirl1);
			PanelTheOne.add(ImageBoy1);
		} else {
			PanelYou.add(ImageBoy1);
			PanelTheOne.add(ImageGirl1);
		}
		PanelGroupPhoto.add(ImageGroupPhoto);
		PanelTwoPhoto.add(ImageTwoPhoto);
		PanelCommand.add(ImageCommand);
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelTwoPhoto);
		backgroundPanel.add(PanelTheOne);
		backgroundPanel.add(PanelCommand);
		backgroundPanel.add(PanelYou);
		backgroundPanel.add(PanelGroupPhoto);
		if (dataPackage.count<=9 && dataPackage.count>=5) {
			backgroundPanel.add(backgroundImage2);
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
		ClubActivityListener clickgoCollect = new ClubActivityListener(2); //发生2号事件，集体荣誉
		ClubActivityListener clickgoIndividual = new ClubActivityListener(3); //发生3号事件，个人荣誉
		
		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		clickgoCollect.setButton(CollectButton);
		clickgoIndividual.setButton(IndiButton);
		
		nextButton.addMouseListener(clicknext);//0号事件
		backButton.addMouseListener(clickback);
		CollectButton.addMouseListener(clickgoCollect);
		IndiButton.addMouseListener(clickgoIndividual);
		
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


