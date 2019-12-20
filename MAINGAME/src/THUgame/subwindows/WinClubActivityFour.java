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
 * 社团活动，第二学期第一周
 * 
 * --DIALOG--
 * 
 * update:20191214
 * via：卢宇芳
 * 竞选合唱队队长
 * 
 **/

public class WinClubActivityFour extends WinBase{
	

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
						dataPackage.choiceB="GoElection";
					} else if (mode==3) {
						dataPackage.choiceB="NoElection";
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
	public WinClubActivityFour(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivityFour.jpeg",0, 0, 1080, 720);	
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
		
		//另一个学生的panel
		JPanel PanelTheOne = new JPanel();
		PanelTheOne.setOpaque(false);
		PanelTheOne.setBounds(120, 140, 300, 300);
		PanelTheOne.setLayout(null);
		//自己的panel
		JPanel PanelYou = new JPanel();
		PanelYou.setOpaque(false);
		PanelYou.setBounds(770, 140, 300, 300);
		PanelYou.setLayout(null);
		//女生图
		JPanel ImageGirl = new ImagePanel("imgsrc//WinJoinClub/ImageGirl.png",0, 0, 300, 300);
		ImageGirl.setOpaque(false);
		ImageGirl.setBounds(0, 0, 300, 300);
		ImageGirl.setOpaque(false);//注意要设成透明的
		ImageGirl.setVisible(false);
		//男生图
		JPanel ImageBoy = new ImagePanel("imgsrc//WinJoinClub/ImageBoy.png",0, 0, 300, 300);
		ImageBoy.setOpaque(false);
		ImageBoy.setBounds(0, 0, 300, 300);
		ImageBoy.setOpaque(false);//注意要设成透明的
		ImageBoy.setVisible(false);
		//男生图2
		JPanel ImageBoy2 = new ImagePanel("imgsrc//WinJoinClub/ImageBoyTwo.png",0, 0, 231, 300);
		ImageBoy2.setOpaque(false);
		ImageBoy2.setBounds(0, 0, 231, 300);
		ImageBoy2.setOpaque(false);//注意要设成透明的
		ImageBoy2.setVisible(false);
		
		//队长的panel
		JPanel PanelCaptain = new JPanel();
		PanelCaptain.setOpaque(false);
		PanelCaptain.setBounds(120, 140, 300, 300);
		PanelCaptain.setLayout(null);
		//队长图
		JPanel ImageCaptain = new ImagePanel("imgsrc//WinJoinClub/ImageYou.png",0, 0, 300, 300);
		ImageCaptain.setOpaque(false);
		ImageCaptain.setBounds(0, 0, 300, 300);
		ImageCaptain.setOpaque(false);//注意要设成透明的
		ImageCaptain.setVisible(false);
		
		//成为队长的证书panel
		JPanel PanelCertify = new JPanel();
		PanelCertify.setOpaque(false);
		PanelCertify.setBounds(300, 50, 542, 378);
		PanelCertify.setLayout(null);
		//证书图
		JPanel ImageCertify = new ImagePanel("imgsrc//WinJoinClub/CaptainCertify.png",0, 0, 542, 378);
		ImageCertify.setOpaque(false);
		ImageCertify.setBounds(0, 0, 542, 378);
		ImageCertify.setOpaque(false);//注意要设成透明的
		ImageCertify.setVisible(false);
		
		//离开 竞选  不去竞选按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 200, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		JButton goElection = new JButton("去竞选");
		goElection.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		goElection.setBounds(800, 150, 120, 50);
		backgroundPanel.add(goElection);
		goElection.setEnabled(false);
		goElection.setVisible(false);
		
		JButton noElection = new JButton("不竞选");
		noElection.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		noElection.setBounds(800, 200, 120, 50);
		backgroundPanel.add(noElection);
		noElection.setEnabled(false);
		noElection.setVisible(false);
		
		int speaker = -1; //0代表自己，1代表对方，2代表队长，3表示同时出现
		String text="";  //对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>又来到了熟悉的合唱队！这是第一次例会，新的学期希望在这里能收获更多哦！</html>";
			break;
		case 1:
			text="<html>经过一学期的努力，你的工作得到了大家的认可～你也给大家留下了好印象～</html>";
			break;
		case 2:
			text="<html>首先，队长要组织开会啦！</html>";
			break;
		case 3:
			speaker = 2;
			text="<html>同学们，我们又见面啦！感谢大家继续留在合唱队的大家庭！</html>";
			break;
		case 4:
			speaker = 2;
			text="<html>我这学期由于自身原因要退休啦，需要新的小伙伴来带领大家继续前进！</html>";
			break;
		case 5:
			speaker = 2;
			text="<html>希望大家积极竞选队长！</html>";
			break;
		case 6:
			speaker = 1;
			text="<html>我觉得你可以当选哎，大家好像都很喜欢你哦，要不要考虑一下？</html>";
			break;
		case 7:
			speaker = 1;
			text="<html>如果你去选，我肯定会支持你哦！</html>";
			break;
		case 8:
			text="<html>你的社团贡献进度已经有";
			text+=dataPackage.inClubPorcess;
			text+="了！社团进度大于40就很可能竞选成功哦！</html>";
			break;
		case 9:
			text="<html>如果竞选成功，你可以更多为同学们服务，但占用的时间和经历也会很多，学习进度可能会很慢哦！</html>";
			break;
		case 10:
			text="<html>这时候，你要不要选择竞选呢？<br>社团进度+5。</html>";
			goElection.setEnabled(true);
			goElection.setVisible(true);
			noElection.setEnabled(true);
			noElection.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
			break;
		case 11:
			text="<html>你选择了去竞选！需要发表竞选宣言～</html>";
			break;
		case 12:
			speaker = 0;
			text="<html>大家好，我是";
			text+=dataPackage.name;
			text+="。非常幸运今天可以竞选合唱队的队长！</html>";
			break;
		case 13:
			speaker = 0;
			text = "<html>如果我能当队长，一定会继续做好工作，为大家服务，建设好我们的大家庭！</html>";
			break;
		case 14:
			text = "<html>恭喜你获得了大家的支持！成功当选了合唱队的队长！";
			text+="<br>社团进度+15，情商+3，心情值+5，艺术属性+3。</html>";
			ImageCertify.setVisible(true);
			break;
		case 15:
			speaker = 2;
			text = "<html>你当选队长我很放心呀！希望以后能做好工作哦～这样我就放心了～</html>";
			break;
		case 16:
			speaker = 1;
			text="<html>恭喜你呀！以后就要叫你队长了呢～很优秀哦！</html>";
			break;
		case 17:
			text = "<html>很遗憾，你并没有当选队长...可能是上学期工作还不够哦～但通过竞选也有很多收获。";
			text+="<br>社团进度+10，情商+2，心情值-5。</html>";
			break;
		case 18:
			speaker = 2;
			text = "<html>虽然没有当选队长，但你上学期表现很棒哦！不要灰心，以后我们一起努力！</html>";
			break;
		case 19:
			speaker = 1;
			text="<html>没关系哦～我还是很支持你哒～之后还能一起举办很多活动呢！</html>";
			break;
		case 20:
			speaker = 0;
			text="<html>谢谢关心！没关系我会再接再厉的～在队里还是能和大家一起玩耍呢～</html>";
			break;
		case 21:
			speaker = 0;
			text="<html>谢谢嘿嘿！不要那么客气啦～我们之后一起努力做好工作！</html>";
			break;
		case 22:
			speaker = 0;
			text="<html>哎对了！我们下周就要女生节了～还记得去年男生节，大家都玩的很开心呢～</html>";
			break;
		case 23:
			speaker = 0;
			text="<html>今年女生节要不要和我一起准备呢？</html>";
			break;
		case 24:
			speaker = 1;
			text="<html>好呀！我们会后一起商量一下好啦！</html>";
			break;
		case 25:
			speaker = 0;
			text="<html>嗯嗯，有你的支持相信下周的活动一定很顺利～</html>";
			break;
		case 26:
			text="<html>你没有选择去竞选，可以理解，因为第二学期各种事也越来越多了～但有一点小失落";
			text+="<br>社团进度+5，情商值-2。</html>";
			break;
		case 27:
			text="<html>但你没关系啦，社团活动重在参与、享受和体验～</html>";
			break;
		case 28:
			text="<html>今天的活动就到这里啦！点击离开返回～</html>";
			backButton.setEnabled(true);
			backButton.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
			break;
		}
		
		switch(speaker) {
		case 0: //自己
			if (dataPackage.sex.equals("girl")) ImageGirl.setVisible(true);
			else ImageBoy2.setVisible(true);
			textSpeaker.setText("<html>我</html>");
			break;
		case 1: //别人
			if (dataPackage.sex.equals("girl")) ImageBoy.setVisible(true);
			else ImageGirl.setVisible(true);
			textSpeaker.setText("<html>TA</html>");
			break;
		case 2: //队长
			ImageCaptain.setVisible(true);
			textSpeaker.setText("<html>队  长</html>");
			break;
		}
		
		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		if (dataPackage.sex.equals("girl")) {
			PanelYou.add(ImageGirl);
			PanelTheOne.add(ImageBoy);
		} else {
			PanelYou.add(ImageBoy2);
			PanelTheOne.add(ImageGirl);
		}
		PanelCaptain.add(ImageCaptain);
		PanelCertify.add(ImageCertify);
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelTheOne);
		backgroundPanel.add(PanelYou);
		backgroundPanel.add(PanelCaptain);
		backgroundPanel.add(PanelCertify);
		backgroundPanel.add(backgroundImage);
		
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
		ClubActivityListener clickgoElection = new ClubActivityListener(2); //发生2号事件，去竞选
		ClubActivityListener clicknoElection = new ClubActivityListener(3); //发生3号事件，不去竞选
		
		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		clickgoElection.setButton(goElection);
		clicknoElection.setButton(noElection);
		
		nextButton.addMouseListener(clicknext);//0号事件
		backButton.addMouseListener(clickback);
		goElection.addMouseListener(clickgoElection);
		noElection.addMouseListener(clicknoElection);


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


