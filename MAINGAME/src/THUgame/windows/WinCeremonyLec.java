package THUgame.windows;
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
 * 科研第八次组会事件
 * 
 * --DIALOG--
 * 
 * update:20191108
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

public class WinCeremonyLec extends WinBase{

		static private class ResearchGroupmeetingEightListener extends BaseMouseListener{
			static public DataPack dataPackage;
			static public EventManager mainGame;
			private JFrame frame;
			private JButton button;
			private int mode;
			
			public ResearchGroupmeetingEightListener(int i){
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
				ResearchGroupmeetingEightListener.mainGame=mainGame;
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
	public WinCeremonyLec(EventManager mainGame,JFrame frame) {
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinCeremony/lecture.jpg",0, 0, 1080, 720);	
		backgroundImage.setOpaque(false);
		backgroundImage.setBounds(0, 0, 1080, 720);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBounds(67,440,965,255);
		dialogPanel.setOpaque(false);
		dialogPanel.setLayout(null);
		
		JPanel dialogText = new JPanel();
		dialogText.setBounds(0, 0, 938, 250);
		dialogText.setOpaque(false);
		dialogText.setLayout(null);
		
		
		JPanel dialogImage = new ImagePanel("imgsrc//WinCeremony/DialogOfResearch.png",0, 0, 965, 255);
		dialogImage.setBounds(0, 0, 965, 255);
		dialogImage.setOpaque(false);//注意要设成透明的
		
		JLabel textSpeaker = new JLabel();
		textSpeaker.setHorizontalAlignment(SwingConstants.CENTER);
		textSpeaker.setText(dataPackage.name);
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

		//A选项按钮
		JPanel panelNext = new JPanel();
		panelNext.setLayout(null);
		panelNext.setOpaque(false);
		panelNext.setBounds(900, 560, 50, 45);
		panelNext.setLayout(null);
	
		JPanel nextImage = new ImagePanel("imgsrc//WinCeremony/Next.png",0, 0, 50, 45);	
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
		
		JLabel PPT = new JLabel();
		PPT.setBounds(590,125,350,400);
		PPT.setFont(new Font("印品黑体", Font.PLAIN, 19));
		backgroundPanel.add(PPT);
		String text="";//对话内容
		switch(dataPackage.count) {
			case 0:
				text="<html>各位老师，同学，大家好。今天被邀请来我们的毕业典礼，我感到万分荣幸……</html>";
				PPT.setText("<html>优秀毕业生代表<br>"
						+ "新雅书院9102级学生"+dataPackage.name+"<br>"
								+ "学分绩:3.88 院系内第二<br>"
									+"曾获国家奖学金、学业优秀奖学金<br>"
									+ "社工优秀奖学金、科技创新奖学金<br>"
									+ "曾任新雅书院学生会主席<br>"
									+ "曾任新雅书院科协副主席<br>"
									+ "十佳优秀社团协会会长"
									+ "十佳志愿者</html>");
				break;
			case 1:
				text="<html>虽历磨难，但从未放弃……</html>";
				PPT.setText("<html>我的微积分作业要比别人多花三四倍的时间，<br>" + 
						"竞选班长不成功，<br>" + 
						"报名实践支队长也失败了，<br>" + 
						"仰卧起坐100分只拿到了20分……<br>" + 
						"“无论如何，不许退缩，<br>" + 
						"不许不努力，决不许放弃。<br>" + 
						"我在日记中写下这句话。<br><br>（      ————注：来自2019年本科生毕业典礼 张薇）</html>");
				break;
			case 2:
				text="<html>我都准备好啦！</html>";
				PPT.setText("<html>同学们，海阔天空的人生画卷正在你们面前展开，"
						+ "无限光明的未来正在呼唤你们勇敢前行。“惟希望也，故进取”"
						+ "“惟进取也，故日新”。身处开放的时代，我希望你们始终遵从内"
						+ "心的选择，永葆进取的精神和创新的激情，在包容和尊重中不断获"
						+ "取人生的力量。“乘风好去，长空万里，直下看山河”。我相信你们一"
						+ "定能够融入这个伟大的时代，以开放精神点亮人生，成就自我，引领未来！</html>");
				break;
		}
		textThis.setText(text);
		panelNext.add(nextButton);
		panelNext.add(nextImage);
		backgroundPanel.add(panelNext);
		backgroundPanel.add(dialogPanel);
		backgroundPanel.add(backgroundImage);
		ResearchGroupmeetingEightListener.dataPackage=dataPackage;
		ResearchGroupmeetingEightListener.mainGame=mainGame;
		ResearchGroupmeetingEightListener clicknext=new ResearchGroupmeetingEightListener(0);//设置鼠标监听器，发生0号事件
		clicknext.setButton(nextButton);
		nextButton.addMouseListener(clicknext);//0号事件是 a 被点击


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


