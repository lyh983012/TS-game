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
 * 社团第一周
 * 
 * --DIALOG--
 * 
 * update:20191215
 * via：卢宇芳
 * 社团招新，加入合唱队
 * 
 * 
 **/


/*************************************************************	
 *简介
 * 基于其它模板改写，与多次多重选择事件模板一起使用
 * 
 *************************************************************/

public class WinClubRecirument extends WinBase{
	
		public String text="";//对话内容
		
		/*************************************************************	
		 *
		 *【内部的事件响应类】
		 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
		 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
		 * 所有必要实现的接口都实现了。
		 * 
		 *************************************************************/
		static private class ResearchGroupmeetingOneListener extends BaseMouseListener{
			static public DataPack dataPackage;
			static public EventManager mainGame;
			private JFrame frame;
			private JButton button;
			private int mode;
			
			public ResearchGroupmeetingOneListener(int i){
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
					}
					else if(mode==1) {
						dataPackage.choiceA="back";
					}
					else if(mode==2) {
						dataPackage.choiceA="JoinClub";
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
	public WinClubRecirument(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubRecuirtBackground.jpeg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
		JPanel dialogText = new JPanel();
		dialogText.setBounds(0, 0, 938, 250);
		dialogText.setOpaque(false);
		dialogText.setLayout(null);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBounds(67,390,965,255);
		dialogPanel.setOpaque(false);
		dialogPanel.setLayout(null);
		
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
		
		//海报panel
		JPanel PanelPoster = new JPanel();
		PanelPoster.setOpaque(false);
		PanelPoster.setBounds(250, 0, 325, 459);
		PanelPoster.setLayout(null);
		
		//招新海报
		JPanel ImagePoster = new ImagePanel("imgsrc//WinJoinClub/RecuirtPoster.png",0, 0, 325, 459);
		ImagePoster.setOpaque(false);
		ImagePoster.setBounds(0, 0, 325, 459);
		ImagePoster.setOpaque(false);//注意要设成透明的
		ImagePoster.setVisible(false);
		
		//自己的panel
		JPanel PanelYou = new JPanel();
		PanelYou.setOpaque(false);
		PanelYou.setBounds(720, 140, 300, 300);
		PanelYou.setLayout(null);
		
		//女生图
		JPanel ImageGirl1 = new ImagePanel("imgsrc//WinJoinClub/ImageGirl.png",0, 0, 300, 300);
		ImageGirl1.setOpaque(false);
		ImageGirl1.setBounds(0, 0, 300, 300);
		ImageGirl1.setOpaque(false);//注意要设成透明的
		ImageGirl1.setVisible(false);
		
		//男生图
		JPanel ImageBoy1 = new ImagePanel("imgsrc//WinJoinClub/ImageBoy.png",0, 0, 300, 300);
		ImageBoy1.setOpaque(false);
		ImageBoy1.setBounds(0, 0, 300, 300);
		ImageBoy1.setOpaque(false);//注意要设成透明的
		ImageBoy1.setVisible(false);
		
		//离开按钮
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 100, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		//加入社团选项按钮

		JButton joinClubButton = new JButton("加入合唱队");
		joinClubButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		joinClubButton.setBounds(800, 150, 130, 50);
		backgroundPanel.add(joinClubButton);
		joinClubButton.setEnabled(false);
		joinClubButton.setVisible(false);

		String text="";//对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>大学一定要加入一个社团，来康康有什么好玩的社团呢！</html>";
			break;
		case 1:
			text="<html>清华大学每年都有“百团大战”的招新传统，这么多的社团看得你眼花缭乱......</html>";
			if (dataPackage.sex.equals("girl")) ImageGirl1.setVisible(true);
			else ImageBoy1.setVisible(true);
			break;
		case 2:
			text="<html>突然，耳边传来优美的歌声和漂亮的海报，原来是清华大学合唱队！</html>";
			ImagePoster.setVisible(true);
			break;
		case 3:
			text="<html>清华的合唱队很受同学们欢迎哦，在去年的社团评审中被评为十佳社团呢！</html>";
			ImagePoster.setVisible(true);
			break;
		case 4:
			text="<html>加入合唱队不仅能提高自身能力，丰富大学生活，还能遇到很多优秀的小伙伴呢！</html>";
			joinClubButton.setEnabled(true);
			joinClubButton.setVisible(true);
			backButton.setEnabled(true);
			backButton.setVisible(true);
			nextButton.setVisible(false);
			nextButton.setEnabled(false);
			nextImage.setVisible(false);
			break;
		case 5:
			text="<html>加入了合唱队，可以和志同道合的小伙伴愉快玩耍了！下周周四第一次例会和部门迎新，要记得参加哦";
			text+="<br><br>时间过去了2小时，社团进度+10，心情值+2，艺术属性+2</html>";
			backButton.setEnabled(true);
			backButton.setVisible(true);
			nextButton.setVisible(false);
			nextButton.setEnabled(false);
			nextImage.setVisible(false);
			break;
		}
	
		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		if (dataPackage.sex.equals("girl")) PanelYou.add(ImageGirl1);
		else PanelYou.add(ImageBoy1);
		PanelPoster.add(ImagePoster);
		
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelYou);
		backgroundPanel.add(PanelPoster);
		backgroundPanel.add(backgroundImage);
		
		ResearchGroupmeetingOneListener.dataPackage=dataPackage;
		ResearchGroupmeetingOneListener.mainGame=mainGame;
		
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
		ResearchGroupmeetingOneListener clicknext=new ResearchGroupmeetingOneListener(0);//设置鼠标监听器，发生0号事件
		ResearchGroupmeetingOneListener clickback = new ResearchGroupmeetingOneListener(1); //发生1号事件，返回MAP
		ResearchGroupmeetingOneListener clickJoinClub = new ResearchGroupmeetingOneListener(2); //发生2号事件，加入社团
		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		clickJoinClub.setButton(joinClubButton);
		nextButton.addMouseListener(clicknext);//0号事件是 a被点击
		backButton.addMouseListener(clickback);
		joinClubButton.addMouseListener(clickJoinClub);
		

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


