package THUgame.subwindows;

import THUgame.windows.WinBase;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import java.awt.Font;

/* 
 * 科研第一次组会事件
 * 
 * --DIALOG--
 * 
 * update:20191103
 * via：黄天翼
 * 更新：新建此事件
 * 
 * 
 **/


/*************************************************************	
 *简介
 * 基于其它模板改写，与多次多重选择事件模板一起使用
 * 
 *************************************************************/

public class WinResearchBegin extends WinBase{
	

		
		
		/*************************************************************	
		 *
		 *【内部的事件响应类】
		 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
		 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
		 * 所有必要实现的接口都实现了。
		 * 
		 *************************************************************/
		static private class ResearchBeginListener extends BaseMouseListener{
			static public DataPack dataPackage;
			static public EventManager mainGame;
			private JFrame frame;
			private JButton button;
			private int mode;
			
			public ResearchBeginListener(int i){
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
						dataPackage.choiceA="Yes";
					}
					else if(mode==2) {
						dataPackage.choiceA="No";
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
	public WinResearchBegin(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImageOne=new ImagePanel("imgsrc//WinResearchGroupmeeting/Notice.png",0, 0, 1080, 720);	
		backgroundImageOne.setOpaque(false);
		backgroundImageOne.setBounds(0, 0, 1080, 720);


		JPanel backgroundImageTwo=new ImagePanel("imgsrc//WinResearchGroupmeeting/Information.png",0, 0, 1080, 720);	
		backgroundImageTwo.setOpaque(false);
		backgroundImageTwo.setBounds(0, 0, 1080, 720);

		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBounds(67,390,965,255);
		dialogPanel.setOpaque(false);
		dialogPanel.setLayout(null);
		
		
		JPanel dialogText = new JPanel();
		dialogText.setBounds(0, 0, 938, 250);
		dialogText.setOpaque(false);
		dialogText.setLayout(null);
		
		
		JPanel dialogImage = new ImagePanel("imgsrc//WinResearchGroupmeeting/DialogOfResearch.png",0, 0, 965, 255);	

		dialogImage.setBounds(0, 0, 965, 255);
		dialogImage.setOpaque(false);//注意要设成透明的
		
		JLabel textSpeaker = new JLabel();
		textSpeaker.setHorizontalAlignment(SwingConstants.CENTER);
		textSpeaker.setText("<html>人名</html>");
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
		
		//Yes选项按钮
		JPanel panelYes = new JPanel();
		panelYes.setLayout(null);
		panelYes.setOpaque(false);
		panelYes.setBounds(500, 560, 110, 56);
		panelYes.setLayout(null);
		
		JButton buttonYes= new JButton();	
		buttonYes.setBorderPainted(false);
		buttonYes.setFont(new Font("印品黑体", Font.PLAIN, 19));
		buttonYes.setForeground(Color.BLACK);
		buttonYes.setBounds(0, 0, 110, 56);
		buttonYes.setContentAreaFilled(false);
		buttonYes.setHorizontalAlignment(SwingConstants.CENTER);
		buttonYes.setEnabled(false);
		buttonYes.setVisible(false);
		setIcon("/imgsrc/WinResearchGroupmeeting/Yes.png",buttonYes);
		setSelectedIcon("/imgsrc/WinResearchGroupmeeting/YesSelected.png",buttonYes);
		
		//No选项按钮
		JPanel panelNo = new JPanel();
		panelNo.setLayout(null);
		panelNo.setOpaque(false);
		panelNo.setBounds(700, 560, 110, 56);
		panelNo.setLayout(null);
		
		JButton buttonNo= new JButton();	
		buttonNo.setBorderPainted(false);
		buttonNo.setFont(new Font("印品黑体", Font.PLAIN, 19));
		buttonNo.setForeground(Color.BLACK);
		buttonNo.setBounds(0, 0, 110, 56);
		buttonNo.setContentAreaFilled(false);
		buttonNo.setHorizontalAlignment(SwingConstants.CENTER);
		buttonNo.setEnabled(false);
		buttonNo.setVisible(false);
		setIcon("/imgsrc/WinResearchGroupmeeting/No.png",buttonNo);
		setSelectedIcon("/imgsrc/WinResearchGroupmeeting/NoSelected.png",buttonNo);
		
		JPanel PanelTeacher = new JPanel();
		PanelTeacher.setOpaque(false);
		PanelTeacher.setBounds(100, 140, 290, 420);
		PanelTeacher.setLayout(null);
		
		JPanel teacherImage = new ImagePanel("imgsrc//WinResearchGroupmeeting/Teacher.png",0, 0, 290, 420);
		teacherImage.setBounds(0, 0, 290, 420);
		teacherImage.setOpaque(false);//注意要设成透明的T
		
		
		PanelTeacher.add(teacherImage);
		
		JPanel PanelStudentOther = new JPanel();
		PanelStudentOther.setOpaque(false);
		PanelStudentOther.setBounds(700, 140, 270, 355);
		PanelStudentOther.setLayout(null);
		
		JPanel studentOtherImage = new ImagePanel("imgsrc//WinResearchGroupmeeting/StudentOther.png",0, 0, 270, 355);
		studentOtherImage.setBounds(0, 0, 270, 355);
		studentOtherImage.setOpaque(false);//注意要设成透明的

		PanelStudentOther.add(studentOtherImage);
		
		JPanel PanelStudentYou = new JPanel();
		PanelStudentYou.setOpaque(false);
		PanelStudentYou.setBounds(700, 140, 270, 355);
		PanelStudentYou.setLayout(null);
		
		JPanel studentYouImage = new ImagePanel("imgsrc//WinResearchGroupmeeting/StudentYou.png",0, 0, 270, 355);
		studentYouImage.setBounds(0, 0, 270, 355);
		studentYouImage.setOpaque(false);//注意要设成透明的
		studentYouImage.setVisible(false);
		PanelStudentYou.add(studentYouImage);
		
		int speaker=4;//0代表老师，1/2/3分别代表同学A、B、C、4代表你
		String text="";//对话内容
		
		switch(dataPackage.count) {
		case 0:
			text="<html>噢，SRT开始报名了呀。</html>";
			break;
		case 1:
			text="<html>一直听说这本科生科研训练似乎有点意思，要不要去看看呢？</html>";
			break;
		case 2:
			text="<html>是否要前往info页面查看课题情况?</html>";
			buttonNo.setEnabled(true);
			buttonNo.setVisible(true);
			buttonYes.setEnabled(true);
			buttonYes.setVisible(true);
			nextButton.setEnabled(false);
			nextImage.setVisible(false);
			break;
		case 3:
			text="<html>去看看有没有感兴趣的课题。</html>";
			break;
		case 4:
			text="<html>算了，我对这方面不感兴趣。</html>";
			break;
		case 5:
			text="<html>唔……</html>";
			break;
		case 6:
			text="<html>我觉得我的能力不足以完成这些课题......</html>";
			break;
		case 7:
			text="<html>“反应堆蒙卡计算程序研究”这个题目好像不错，是否要报名呢？</html>";
			break;
		case 8:
			text="<html>是否报名SRT项目？</html>";
			buttonNo.setEnabled(true);
			buttonNo.setVisible(true);
			buttonYes.setEnabled(true);
			buttonYes.setVisible(true);
			nextButton.setEnabled(false);
			nextImage.setVisible(false);
			break;
		case 9:
			text="<html>报个名试试看吧！</html>";
			break;
		case 10:
			text="<html>呃，还是算了，我觉得我不太行，或许下次吧...</html>";
			break;
		}
		
		textThis.setText(text);
		switch(speaker) {
		case 0:
			studentYouImage.setVisible(false);
			studentOtherImage.setVisible(false);
			teacherImage.setVisible(true);
			textSpeaker.setText("<html>老师</html>");
			break;
		case 1:
			studentYouImage.setVisible(false);
			studentOtherImage.setVisible(true);
			teacherImage.setVisible(false);
			textSpeaker.setText("<html>同学A</html>");
			break;
		case 2:
			studentYouImage.setVisible(false);
			studentOtherImage.setVisible(true);
			teacherImage.setVisible(false);
			textSpeaker.setText("<html>同学B</html>");
			break;
		case 3:
			studentYouImage.setVisible(false);
			studentOtherImage.setVisible(true);
			teacherImage.setVisible(false);
			textSpeaker.setText("<html>同学C</html>");
			break;
		case 4:
			studentYouImage.setVisible(true);
			studentOtherImage.setVisible(false);
			teacherImage.setVisible(false);
			textSpeaker.setText("<html>你</html>");
			break;
		}
		
		
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		panelYes.add(buttonYes);
		panelNo.add(buttonNo);
		backgroundPanel.add(panelYes);
		backgroundPanel.add(panelNo);
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(PanelTeacher);
		backgroundPanel.add(PanelStudentOther);
		backgroundPanel.add(PanelStudentYou);
		if(dataPackage.count<5) {
			backgroundPanel.add(backgroundImageOne);
		}
		else {
			backgroundPanel.add(backgroundImageTwo);
		}
		
		ResearchBeginListener.dataPackage=dataPackage;
		ResearchBeginListener.mainGame=mainGame;
		
		/*		START OF YOUR CODE		*/
		/*****			
		 * 重要，事件监听器一定要把mainGame传入
		 ****/
		ResearchBeginListener clicknext=new ResearchBeginListener(0);//设置鼠标监听器，发生0号事件
		clicknext.setButton(nextButton);
		nextButton.addMouseListener(clicknext);//0号事件是 a 被点击

		ResearchBeginListener clickyes=new ResearchBeginListener(1);//设置鼠标监听器，发生0号事件
		clickyes.setButton(buttonYes);
		buttonYes.addMouseListener(clickyes);//0号事件是 a 被点击
		
		ResearchBeginListener clickno=new ResearchBeginListener(2);//设置鼠标监听器，发生0号事件
		clickno.setButton(buttonNo);
		buttonNo.addMouseListener(clickno);//0号事件是 a 被点击

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


