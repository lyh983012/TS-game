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
 * 社团活动，第二学期第三周
 * 
 * --DIALOG--
 * 
 * update:20191214
 * via：卢宇芳
 * 培训
 * 
 **/

public class WinClubActivitySix extends WinBase{

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
	public WinClubActivitySix(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivitySix.jpeg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
		JPanel backgroundImage2=new ImagePanel("imgsrc//WinJoinClub/ClubActivitySix4.png",0, 0, 1080, 720);	
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

		JPanel ImageBoy2 = new ImagePanel("imgsrc//WinJoinClub/ImageBoyTwo.png",0, 0, 231, 300);
		ImageBoy2.setOpaque(false);
		ImageBoy2.setBounds(0, 0, 231, 300);
		ImageBoy2.setOpaque(false);//注意要设成透明的
		ImageBoy2.setVisible(false);
		
		//歌词panel
		JPanel PanelLyrics = new JPanel();
		PanelLyrics.setOpaque(false);
		PanelLyrics.setBounds(365, 40, 350, 562);
		PanelLyrics.setLayout(null);
		
		//我的祖国歌词
		JPanel ImageChinaLyrics = new ImagePanel("imgsrc//WinJoinClub/lyrics.png",0, 0, 350, 562);
		ImageChinaLyrics.setOpaque(false);
		ImageChinaLyrics.setBounds(0, 0, 350, 562);
		ImageChinaLyrics.setOpaque(false);//注意要设成透明的
		ImageChinaLyrics.setVisible(false);
		
		//黄河大合唱歌词
		JPanel ImageriverLyrics = new ImagePanel("imgsrc//WinJoinClub/RiverLyrics.jpg",0, 0, 350, 562);
		ImageriverLyrics.setOpaque(false);
		ImageriverLyrics.setBounds(0, 0, 350, 562);
		ImageriverLyrics.setOpaque(false);//注意要设成透明的
		ImageriverLyrics.setVisible(false);
		
		//零食panel
		JPanel PanelSnacks = new JPanel();
		PanelSnacks.setOpaque(false);
		PanelSnacks.setBounds(350, 140, 400, 276);
		PanelSnacks.setLayout(null);
		//零食图
		JPanel ImageSnacks = new ImagePanel("imgsrc//WinJoinClub/Snacks.png",0, 0, 400, 276);
		ImageSnacks.setOpaque(false);
		ImageSnacks.setBounds(0, 0, 400, 276);
		ImageSnacks.setOpaque(false);//注意要设成透明的
		ImageSnacks.setVisible(false);
				
		//离开按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 100, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		int speaker = -1; //0代表自己，1代表对方，2代表老师
		String text="";  //对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>今天最重要的事情，就是筹备国庆专场演出！</html>";
			break;
		case 1:
			if (dataPackage.ClubCharacter==4) {
				text="<html>你已经是队长啦，每次活动都需要你来组织哦～</html>";
			} else 
				text="<html>队长出差了，把组织这次活动的任务交给你啦～</html>";
			break;
		case 2:
			speaker = 0;
			text="<html>同学们，感谢大家来参加今天的培训！</html>";
			break;
		case 3:
			speaker = 0;
			text="<html>我们要为国庆专场演出好好做准备哦～</html>";
			break;
		case 4:
			speaker = 0;
			text="<html>今天主要培训两首歌，一首是我们之前一直练习的《我和我的祖国》，另一首是《黄河大合唱》</html>";
			break;
		case 5:
			speaker = 0;
			text="<html>培训后，我会简单布置下周的工作和需要大家筹备的内容～希望大家积极配合哦！</html>";
			break;
		case 6:
			speaker = 0;
			text="<html>今天我们非常有幸邀请到了经验丰富的谭老师来指挥大家训练！</html>";
			break;
		case 7:
			speaker = 2;
			text="<html>大家好，非常高高兴能和大家一起培训！话不读多说，我们就开始吧！</html>";
			break;
		case 8:
			speaker = 2;
			text="<html>第一首是《我和我的祖国》！</html>";
			break;
		case 9:
			text="<html>我和我的祖国，一刻也不能分割。</html>";
			ImageChinaLyrics.setVisible(true);
			break;
		case 10:
			text="<html>无论我走到哪里，都留着一首赞歌......</html>";
			ImageChinaLyrics.setVisible(true);
			break;
		case 11:
			speaker = 2;
			text="<html>好，下面是第二首歌————《保卫黄河》。</html>";
			break;
		case 12:
			text="<html>风在吼，马在叫，黄河在咆哮，黄河在咆哮～</html>";
			ImageriverLyrics.setVisible(true);
			break;
		case 13:
			text = "<html>唱到情深处，两首歌都激起了大家强烈的爱国热情和民族自豪感。";
			text+="<br><br>时间过去了3小时，社团进度+10，体力消耗20，艺术属性+5。</html>";
			break;
		case 14:
			speaker = 1;
			text = "<html>不忘国耻，我为祖国而自豪！一定会努力完成这场演出的！</html>";
			break;
		case 15:
			speaker = 0;
			text = "<html>嗯嗯加油，你这么棒一定可以的～我们一起把演出办好～</html>";
			break;
		case 16:
			speaker = 2;
			text="<html>看到你们这一代人如此有朝气和爱国热情，就像看到了当年的我呀！行勒，我放心啦！</html>";
			break;
		case 17:
			speaker = 0;
			text="<html>谢谢老师鼓励！老师今天指导我们辛苦啦～我们一定会更加刻苦训练的！</html>";
			break;
		case 18:
			speaker = 0;
			text = "<html>感谢大家的辛苦付出，训练了这么久一定很累了，由我们温暖的内联给大家发点零食花束嘻嘻～</html>";
			ImageSnacks.setVisible(true);
			break;
		case 19:
			speaker = 1;
			text = "<html>好贴心呀！</html>";
			ImageSnacks.setVisible(true);
			break;
		case 20:
			speaker = 1;
			text="<html>感谢你最近对我的关心和陪伴，和你在一起时真的很开心呢～</html>";
			break;
		case 21:
			speaker = 0;
			text="<html>是呀，和你在一起的时候感觉烦恼都消失了～</html>";
			break;
		case 22:
			if (dataPackage.sex.equals("girl")) speaker = 1;
			else speaker = 0;
			text="<html>哎，我陪你一起回去吧～</html>";
			break;
		case 23:
			if (dataPackage.sex.equals("girl")) speaker = 0;
			else speaker = 1;
			text="<html>好的呀，谢谢～我们走吧！</html>";
			break;
		case 24:
			speaker = 0;
			if (dataPackage.sex.equals("girl")) {
				text="<html>好啦，这就是我宿舍了！我们下周再见～</html>";
			} else
				text="<html>好啦，就送你到操场这里了！我们下周再见～</html>";
			break;
		case 25:
			speaker = 1;
			text="<html>嗯嗯！下周见！</html>";
			break;
		case 26:
			text="<html>和TA聊天：情商+2，幸运值+3，心情+5。";
			text += "<br><br>今天的培训和活动到此结束啦，点击离开返回。</html>";
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
				if (dataPackage.count<=14) {
					ImageBoy2.setVisible(true);
				} else ImageBoy1.setVisible(true);
			}
			textSpeaker.setText("<html>我</html>");
			break;
		case 1: //对方
			if (dataPackage.sex.equals("girl")) {
				if (dataPackage.count<=14) {
					ImageBoy2.setVisible(true);
				} else ImageBoy1.setVisible(true);
			}
			else ImageGirl1.setVisible(true);
			textSpeaker.setText("<html>TA</html>");
			break;
		case 2: //指导老师
			ImageCommand.setVisible(true);
			textSpeaker.setText("<html>指导老师</html>");
			break;
		}

		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		if (dataPackage.sex.equals("girl")) {
			PanelYou.add(ImageGirl1);
			PanelTheOne.add(ImageBoy1);
		} else {
			if (dataPackage.count<=14) PanelYou.add(ImageBoy2);
			else PanelYou.add(ImageBoy1);
			PanelTheOne.add(ImageGirl1);
		}
		if (dataPackage.count<=10) PanelLyrics.add(ImageChinaLyrics);
		else PanelLyrics.add(ImageriverLyrics);
		PanelCommand.add(ImageCommand);
		PanelSnacks.add(ImageSnacks);
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelLyrics);
		backgroundPanel.add(PanelTheOne);
		backgroundPanel.add(PanelCommand);
		backgroundPanel.add(PanelYou);
		backgroundPanel.add(PanelSnacks);
		if (dataPackage.count<=23) {
			backgroundPanel.add(backgroundImage);
		} else backgroundPanel.add(backgroundImage2);
		
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

		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		nextButton.addMouseListener(clicknext);//0号事件
		backButton.addMouseListener(clickback);
		
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


