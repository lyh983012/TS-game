package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

/*
 * 第一次活动-2 后勤分支 * 
 * 
 *  ---- LOG ----
 *  update:20191214
 *  via：余冬杰
 *  对话流程
 *  	购买物资且准时：1-2-10-11-12-13-14-15-16-17-18
 *  	购买物资但迟到：3-4-5-6-7-8-9
 *      没买物资但准时：19-20-21-2-10-11-12-13-14-15-16-17-18
 * 		没买物资且迟到：19-20-22-4-5-6-7-8-9
 * stateA-分支方向
 * stateB-分支方向判断的flag
 * */


public class WinSUPE22 extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 * 
	 *************************************************************/
	static JRadioButton studentUnion;
	static JRadioButton hobbyClub;
	static JRadioButton STA;
	
	static private class demoMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		
		
		public demoMouseListener(int i){
			this.mode=i;
		}
		
		public void setFrame(JFrame frame) {
			this.frame=frame;
		}
		
		public void setButton(JButton button) {
			this.button=button;
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
			if(mode==0) {
				dataPackage.choiceA = "clickNext";
			}else if(mode ==3){
				
			}else if(mode ==4){
				
			}

			/*		END OF YOUR CODE		*/
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
			EventManager.dataPackage=dataPackage;
			synchronized(mainGame) {
				mainGame.notify();
			}
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
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
	 * 	
	 * 【构造函数】
	 * 		不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 * 
	 *************************************************************/
	public WinSUPE22(EventManager mainGame,JFrame frame) {
		
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		/*************************************************************	
		 *【背景镶板】
		 * 		所有的组件都在里面，两个按钮直接用插件拖进去的
		 * 		这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性 
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		
		if (dataPackage.stateB.equals("")){
			/*
			 * 确定物资的购买与迟到情况
			 */
			if (dataPackage.SUPEstate.charAt(1) == '3') {
				if (dataPackage.time == 21) {   // 购买物资且准时
					dataPackage.stateA = "1";
				}else {                         // 购买物资但迟到
					dataPackage.stateA = "2";
					dataPackage.characterEQ -= 3;
				}
			}else {
				if (dataPackage.time == 21) {   // 没买物资但准时
					dataPackage.stateA = "3";
					dataPackage.characterEQ -= 3;
				}else {                         // 没买物资且迟到
					dataPackage.stateA = "4";
					dataPackage.characterEQ -= 5;
				}
			}
			
			
			switch (dataPackage.stateA) {
			case "1":
				dataPackage.count = 1;
				break;
			case "2":
				dataPackage.count = 3;
				break;
			case "3":
				dataPackage.count = 19;
				break;
			case "4":
				dataPackage.count = 19;
				break;
			}
			
			dataPackage.stateB = "flag";
		}
		
		
		/*************************************************************	
		 * 【镶对话框】
		 * 		建立一个带背景的Panel的流程设setBounds(x, y, 宽, 高);
		 *  	1.建一个Panel	
		 * 		2.Panel里建两个subPanel，全部都设成setBounds(0, 0, 宽, 高);
		 * 		3.底下的用imagePanel工具类放图片，上面的放控件
		 * 		4.设置两个Panel为透明这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		JPanel dialogPack = new JPanel();
		dialogPack.setBounds(66, 475, 689, 189);
		dialogPack.setOpaque(false);//注意要设成透明的
		dialogPack.setLayout(null);
		
			JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
			dialogPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	//第2个subPanel，放图
																								//(0, 0, 宽, 高);
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);		//注意要设成透明的
			dialogPanel.setLayout(null);
			
			JLabel dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogPanel.add(dialogName);
					
			JLabel dialogContent = new JLabel();
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(27, 42, 632, 137);
			dialogPanel.add(dialogContent);
			
			int speaker=0;    //说话人0-庚敬煊 1-独白 2-章昭焕 3-汪赫谦
			String text="";       //说话内容
		System.out.println(dataPackage.count);
		switch(dataPackage.count) {
		case 1:
			speaker = 0;
			text = "<html>嘿你好，辛苦你买物资啦。现在我们只要在这里陪队员按照计划训练就好。整个训练大概需要2小时，我们聊聊天就好。</html>";
			break;
		case 2:
			speaker = 1;
			text = "<html>好的。诶师傅当初为什么你加入体育部呀？</html>";
			break;
		case 3:
			speaker = 0;
			text = "<html>嘿你好，辛苦你买物资啦。现在我们只要在这里陪队员按照计划训练就好。你晚到了一会，训练过程还有1小时就结束啦。我们聊聊天就结束了。</html>";
			break;
		case 4:
			speaker = 1;
			text = "<html>好的。诶师傅当初为什么你加入体育部呀。</html>";
			break;
		case 5:
			speaker = 0;
			text = "<html>我平时体育也不错，就想着多参加点体育活动。后来也是被上一届部长拉进部门的。现在发现体育部做的事情跟运动本身关系不是特别大。</html>";
			break;
		case 6:
			speaker = 1;
			text = "<html>差别在哪里呢？</html>";
			break;
		case 7:
			speaker = 0;			
			text = "<html>体育部是一个服务组织。我们需要把运动员的赛前赛后考虑周到，比如训练、物资、比赛记录和赛后的推送。但是比赛本身，我们除了给运动员加油外能做的不是特别多。专业的人做专业的事情嘛。</html>";
			break;
		case 8:
			speaker = 0;	
			text = "<html>啊哈时候不早啦，各位今天的训练就到这里，辛苦各位运动员。<font style=\"color:red\">"+dataPackage.name+"</font>你也早点回去吧！</html>";
			break;
		case 9:
			speaker = 1;	
			text = "<html>回去的路上对学长的话若有所思，感到体育部的工作虽然零碎辛苦，但是也很重要。【体育部贡献值提升，心情提升】</html>";
			break;
		case 10:
			speaker = 0;	
			text = "<html>我平时体育也不错，就想着多参加点体育活动。后来也是被上一届部长拉进部门的。现在发现体育部做的事情跟运动本身关系不是特别大。</html>";
			break;
		case 11:
			speaker = 1;	
			text = "<html>差别在哪里呢？</html>";
			break;
		case 12:
			speaker = 0;	
			text = "<html>体育部是一个服务组织。我们需要把运动员的赛前赛后考虑周到，比如训练、物资、比赛记录和赛后的推送。但是比赛本身，我们除了给运动员加油外能做的不是特别多。专业的人做专业的事情嘛。</html>";
			break;
		case 13:
			speaker = 1;	
			text = "<html>若有所思地点了点头。</html>";
			break;
		case 14:
			speaker = 0;	
			text = "<html>有时候别人也挺不理解的吧，为什么要在社工上投入很多？不过我觉得，运动员在奋战的时候能感受到对院系的体育氛围的归属感，我就非常满意了。</html>";
			break;
		case 15:
			speaker = 1;	
			text = "<html>其实生活就像一个个小圈子吧，大家都可能对自己圈外的事物有一些不理解。</html>";
			break;
		case 16:
			speaker = 0;	
			text = "<html>没错。</html>";
			break;
		case 17:
			speaker = 0;	
			text = "<html>啊哈时候不早啦，各位今天的训练就到这里，辛苦各位运动员。<font style=\"color:red\">"+dataPackage.name+"</font>你也早点回去吧！</html>";
			break;
		case 18:
			speaker = 1;	
			text = "<html>回去的路上对章师傅学长的话若有所思，感到体育部的工作虽然零碎辛苦，但是也很重要。<br>【体育部贡献值提升，心情提升】</html>";
			break;
		case 19:
			speaker = 0;	
			text = "<html>嘿嘿，忘记了吧。幸好我买了物资，下次要记得啊！</html>";
			break;
		case 20:
			speaker = 1;	
			text = "<html>抱歉抱歉师傅，我下次注意。</html>";
			break;
		case 21:
			speaker = 0;	
			text = "<html>现在我们只要在这里陪队员按照计划训练就好。整个训练大概需要2小时，我们聊聊天就好。</html>";
			break;
		case 22:
			speaker = 0;	
			text = "<html>现在我们只要在这里陪队员按照计划训练就好。你晚到了一会，整个训练大概还有1小时，我们聊聊天就好。</html>";
			break;
		}
		
		//<html><font style=\"color:blue\">庚敬</font><font style=\"color:red\">煊</font></html>"
		switch (speaker) {
		case 0:
			dialogName.setText("<html>章昭焕</html>");
			break;
		case 1:
			dialogName.setText("<html>我</html>");
			break;
		}
		dialogContent.setText(text);
		dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
		
		/*************************************************************	
		* 【下一步按钮】 
		*  	特殊步骤下才会触发
		*  	具体用法见MorninigClass窗口
		*************************************************************/

		JButton nextButton = new JButton();
		nextButton.setBounds(597, 113, 52, 48);
		dialogPanel.add(nextButton);
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		setIcon("/imgsrc/WinOrganization/next.png", nextButton);
		setSelectedIcon("/imgsrc/WinOrganization/nextPressed.png", nextButton);
			
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);

//		if (dataPackage.count == 5) {              //触发聘书，不显示next，通过×交互  
//			nextButton.setVisible(false);
//		}
		
		
		/*************************************************************	
		 * 【镶时钟】
		 * 		不需要修改
		 * 		简而言之就是显示一个Table
		 *************************************************************/

		JPanel timePack = new JPanel();
		timePack.setLayout(null);
		timePack.setOpaque(false);//注意要设成透明的
		timePack.setBounds(66, 32, 195, 90);
		
			JPanel timePanel = new JPanel();
			timePanel.setBounds(0, 0, 195, 90);
			JPanel timeBackgoundPanel = new ImagePanel("imgsrc//taili.png",0, 0, 195, 90);	
			timeBackgoundPanel.setBounds(0, 0, 195, 90);
			
			timeBackgoundPanel.setOpaque(false);//注意要设成透明的
			timePanel.setOpaque(false);//注意要设成透明的
			timePanel.setLayout(null);
			
			JLabel timeText = new JLabel("当前时间为："+String.valueOf(dataPackage.time)+" 时");
			timeText.setBounds(6, 60, 172, 16);
			timePanel.add(timeText);
			
			JLabel dateText = new JLabel("今天是：第"+String.valueOf(dataPackage.term)+"学期"+String.valueOf(dataPackage.week)+"周"+String.valueOf(dataPackage.date)+"日");
			dateText.setBounds(6, 35, 172, 16);
			timePanel.add(dateText);
			
		timePack.add(timePanel);
		timePack.add(timeBackgoundPanel);
		backgroundPanel.add(timePack);
		
		
		/*************************************************************	
		 * 镶待办事项 这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		JPanel todoList = new JPanel();
		todoList.setLayout(null);
		todoList.setOpaque(false);	
		todoList.setBounds(752, 35, 263, 189);
			
			JLabel label = new JLabel("待办事项");
			label.setForeground(Color.WHITE);
			label.setBounds(20, 25, 100, 18);
			todoList.add(label);
			label.setFont(new Font("STFangsong", Font.PLAIN, 16));
				
			JLabel label2 = new JLabel("1.上午课:"+dataPackage.todayMorningClass);
			label2.setForeground(Color.WHITE);
			label2.setBounds(20, 55, 200, 18);
			todoList.add(label2);
			label2.setFont(new Font("STFangsong", Font.PLAIN, 16));
				
			JLabel label3 = new JLabel("2.下午课:"+dataPackage.todayAfternoonClass);
			label3.setForeground(Color.WHITE);
			label3.setBounds(20, 85, 200, 18);
			todoList.add(label3);
			label3.setFont(new Font("STFangsong", Font.PLAIN, 16));
				
			JLabel label4 = new JLabel("3.");
			label4.setForeground(Color.WHITE);
			label4.setBounds(20, 115, 100, 18);
			todoList.add(label4);
			label4.setFont(new Font("STFangsong", Font.PLAIN, 16));
			
			JPanel dbsxBackgruond = new ImagePanel("imgsrc//todoList.png",0, 0, 263, 189);
			dbsxBackgruond.setOpaque(false);	
			dbsxBackgruond.setBounds(0, 0, 263, 189);
		
		todoList.add(dbsxBackgruond);
		dbsxBackgruond.setLayout(null);
		backgroundPanel.add(todoList);
		
		
		/*************************************************************	
		 * 【放背景图】
		 * 		最后放。
		 *************************************************************/
		
		JPanel Background=new ImagePanel("imgsrc//WinOrganization/CBuilding.jpg",0, 0, 1080, 720);
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		demoMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		demoMouseListener.mainGame=mainGame;
		
		demoMouseListener clickNext=new demoMouseListener(0);//设置鼠标监听器，发生0号事件
		clickNext.setButton(nextButton);
		nextButton.addMouseListener(clickNext);//0号事件是 下一步按钮 被点击

		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 【恢复显示】
		 * 必须存在的四行代码！！！！
		 ******************************************************************/
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}