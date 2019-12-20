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
 * 社团第一次组会
 * 
 * --DIALOG--
 * 
 * update:20191124
 * via：卢宇芳
 * 更新：新建此事件
 * 
 * 
 **/


/*************************************************************	
 *简介
 * 基于其它模板改写，与多次多重选择事件模板一起使用
 * 
 *************************************************************/

public class WinClubActivityOne extends WinBase{
	

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
						dataPackage.choiceA="External";
					} else if (mode==3) {
						dataPackage.choiceA="Inline";
					} else if (mode==4) {
						dataPackage.choiceA="Advertise";
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
	public WinClubActivityOne(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinJoinClub/ClubActivityOne.png",0, 0, 1080, 720);	
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
		

		//Next选项按钮
		JPanel panelNext = new JPanel();
		panelNext.setLayout(null);
		panelNext.setOpaque(false);
		panelNext.setBounds(900, 560, 50, 45);
		panelNext.setLayout(null);
	
		JPanel nextImage = new ImagePanel("imgsrc//WinResearchGroupmeeting/Next.png",0, 0, 50, 45);	
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
		
		JButton backButton = new JButton("离开");
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		backButton.setBounds(800, 50, 80, 50);
		backgroundPanel.add(backButton);
		backButton.setEnabled(false);
		backButton.setVisible(false);
		
		JButton joinClubExtrenal = new JButton("外联部");
		joinClubExtrenal.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		joinClubExtrenal.setBounds(800, 100, 100, 50);
		backgroundPanel.add(joinClubExtrenal);
		joinClubExtrenal.setEnabled(false);
		joinClubExtrenal.setVisible(false);
		
		JButton joinClubInline = new JButton("内联部");
		joinClubInline.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		joinClubInline.setBounds(800, 150, 100, 50);
		backgroundPanel.add(joinClubInline);
		joinClubInline.setEnabled(false);
		joinClubInline.setVisible(false);
		
		JButton joinClubAdvertise = new JButton("宣传部");
		joinClubAdvertise.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		joinClubAdvertise.setBounds(800, 200, 100, 50);
		backgroundPanel.add(joinClubAdvertise);
		joinClubAdvertise.setEnabled(false);
		joinClubAdvertise.setVisible(false);
		
		
		String text="";//对话内容

		switch(dataPackage.count) {
		case 0:
			text="<html>上周加入了合唱队，今天要去开第一次例会啦！</html>";
			break;
		case 1:
			text="<html>你选择了一个很不错的社团，这里的老师和同学们都人美心善，能歌善舞！</html>";
			break;
		case 2:
			text="<html>合唱队的活动有很多，比如例会、生日会、男生节、集体演出等等。</html>";
			break;
		case 3:
			text="<html>开例会是社团沟通交流工作和感情建设的重要环节！虽然它占用了一定时间。</html>";
			break;
		case 4:
			text="<html>今天是部门的第一次会议，听说有很多部门，外联部、内联部、宣传部，要选择一个加入!</html>";
			break;
		case 5:
			text="<html>那么，要选择哪个部门呢？请点击对应的按钮哦！</html>";
			joinClubExtrenal.setEnabled(true);
			joinClubExtrenal.setVisible(true);
			joinClubInline.setEnabled(true);
			joinClubInline.setVisible(true);
			joinClubAdvertise.setEnabled(true);
			joinClubAdvertise.setVisible(true);
			break;
		case 6:
			text="<html>我大胆发言，能歌善舞，十分活跃，和社友们很快打成一片，加入外联部！";
			text += "<br><br>外联部之后负责的工作主要有拉赞助，借训练场地，演出场地等。</html>";
			break;
		case 7:
			text="<html>我认真负责但不善言谈，但朋友熟悉后很受欢迎，加入内联部！";
			text += "<br><br>由于内联要负责很多事务性的工作，会后我留下来整理了部员门的通讯录。</html>";
			break;
		case 8:
			text="<html>我拍得了照片，写得了文案，做得了海报，背得起大锅，加入宣传部！";
			text += "<br><br>宣传的工作主要有训练时为队员们拍照片，做推送，做演出海报等。</html>";
			break;
		case 9:
			text="<html>时间过去了2小时，体力值-10，情商+3，幸运值+5，社团进度+10</html>";
			break;
		case 10:
			text="<html>时间过去了4小时，体力值-10，情商+3，社团进度+15</html>";
			break;
		case 11:
			text="<html>时间过去了2小时，体力值-10，情商+2，艺术属性+3，社团进度+10</html>";
			break;
		case 12:
			text="<html>今天的例会就到此结束啦，无论加入了什么部门，都是很好的锻炼机会！";
			text+="<br><br>之后要努力工作哦！相信会给大学留下一段美好的回忆～</html>";
			break;
			}

		if (dataPackage.count==5 || dataPackage.count==12) {
			backButton.setEnabled(true);
			backButton.setVisible(true);
			nextButton.setEnabled(false);
			nextButton.setVisible(false);
			nextImage.setVisible(false);
		}
		
		textThis.setText(text);	
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
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
		ClubActivityListener clickClubExternal = new ClubActivityListener(2); //发生2号事件，加入外联部
		ClubActivityListener clickClubInline = new ClubActivityListener(3); //发生3号事件，加入内联部
		ClubActivityListener clickClubAdvertise = new ClubActivityListener(4); //发生4号事件，加入宣传部
		
		clicknext.setButton(nextButton);
		clickback.setButton(backButton);
		clickClubExternal.setButton(joinClubExtrenal);
		clickClubInline.setButton(joinClubInline);
		clickClubAdvertise.setButton(joinClubAdvertise);
		
		nextButton.addMouseListener(clicknext);//0号事件
		backButton.addMouseListener(clickback);
		joinClubExtrenal.addMouseListener(clickClubExternal);
		joinClubInline.addMouseListener(clickClubInline);
		joinClubAdvertise.addMouseListener(clickClubAdvertise);
		

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


