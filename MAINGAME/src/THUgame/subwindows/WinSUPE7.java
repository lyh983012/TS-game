package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

/*
 * 体育部第三次活动 * 
 * 
 *  ---- LOG ----
 *  update:20191216
 *  via：余冬杰
 *  对话流程：
 *  	0-1-2-3-4-5-6-7-8-11-12-13-15
 * 						 \   / \   /
 * 					  	  10-   14-
 * */


public class WinSUPE7 extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 * 
	 *************************************************************/
	static JRadioButton A1;
	static JRadioButton B1;
	static JRadioButton C1;

	
	static JRadioButton A2;
	static JRadioButton B2;
	static JRadioButton C2;
//	static JRadioButton D2;
	static JRadioButton A3;
	static JRadioButton B3;
	static JRadioButton C3;
	
	static private class demoMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		
		
		public demoMouseListener(int i){
			this.mode=i;
		}
		
		@Override
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
			}else if(mode ==1){
				dataPackage.stateA = "yes";
			}else if(mode ==2){
				dataPackage.stateA = "no";
			}else if(mode ==3){
				if (dataPackage.count==11) {
					int choose11 = 0;
					int choose12 = 0;
					int choose13 = 0;
					int choose21 = 0;
					int choose22 = 0;
					int choose23 = 0;
					int choose31 = 0;
					int choose32 = 0;
					int choose33 = 0;
					// 第一题
					if (A1.isSelected()) {
						choose11 = 1;
					}
					if (B1.isSelected()) {
						choose12 = 1;
					}
					if (C1.isSelected()) {
						choose13 = 1;
					}
					// 第二题
					if (A2.isSelected()) {
						choose21 = 1;
					}
					if (B2.isSelected()) {
						choose22 = 1;
					}
					if (C2.isSelected()) {
						choose23 = 1;
					}
					// 第三题
					if (A3.isSelected()) {
						choose31 = 1;
					}
					if (B3.isSelected()) {
						choose32 = 1;
					}
					if (C3.isSelected()) {
						choose33 = 1;
					}

					if(choose11+choose12+choose13+choose21+choose22+choose23+choose31+choose32+choose33 != 3) {
						JOptionPane.showMessageDialog(null, "似乎漏选了一个题", "oops",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					
					if(choose11+choose12+choose13+choose21+choose22+choose23+choose31+choose32+choose33 == 3) {
						if(choose11 == 1) {
							dataPackage.stateA = "1";
						}else {
							dataPackage.stateA = "0";
						}
						if (choose22 == 1) {
							dataPackage.stateA = ""+(Integer.valueOf(dataPackage.stateA)+1);
						}
					}
				}
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
	public WinSUPE7(EventManager mainGame,JFrame frame) {
		
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
			
			int speaker=0;    //说话人0-我 1-GM	
			String text="";       //说话内容
		System.out.println(dataPackage.count);
		
		switch(dataPackage.count) {
		case 0:
			text = "<html>咦，这是哪？我不是来的东操吗？</html>";
			break;
		case 1:
			speaker = 1;
			text = "<html>嘿嘿嘿你好！</html>";
			break;
		case 2:
			text = "<html>你是谁？</html>";
			break;
		case 3:
			speaker = 1;
			text = "<html>我就是游戏的开发者，那你能给我磕个头吗？</html>";
			break;
		case 4:
			text = "<html>？？？</html>";
			break;
		case 5:
			speaker = 1;
			text =  "<html>开玩笑的哈！马上就是男足决赛了，我有几个问题想要问你，答对的数目越多，你们系获胜的概率就会越大，你觉得如何？</html>";
			break;
		case 6:
			text = "<html>输赢可不是你这种玄学就能决定的！</html>";
			break;
		case 7:
			speaker = 1;
			text = "<html>爱信不信咯。</html>";
			break;
		case 10:
			speaker = 1;
			text =  "<html>不错不错。相信自己，上吧。</html>";
			break;
		case 12:
			speaker = 3;
			text =  "<html>体育部的工作贯彻赛前赛中赛后，但是无论如何，一旦比赛开始，体育部只能安心地做一位观众，为选手们加油。"+
					"但是这并不会抹杀体育部的意义，服务组织的目的，原本就是让选手们能更加心无旁骛地比赛，仅此而已。最重要的是拼搏带给人的激情，"+
					"和体育带给人的勇气。——开发者按（体育部老狗的感受，夹带私货.txt）</html>";
			break;
		case 13:
			speaker = 3;
			text =  "<html>从两度落后2球到绝平，球队向所有人展示了不屈的意志。但是倒在12码点前，让所有人都难以接受。哨响时，"+
					"围在队长身边的大家心里的苦涩无需多言。也许青春就是这样，略带些遗憾吧。" + 
					"<br>【体育部贡献+5，心情-5】</html>";
			break;
		case 14:
			speaker = 3;
			text =  "<html>这是一个东山再起的故事，在绝对的实力面前，不留遗憾。3:1干净利落的胜利。向赛场上的英雄们致敬，也向赛场下的英雄们致敬！" + 
					"<br>【体育部贡献+5，心情+3】</html>";
			break;
		case 15:
			speaker = 3;
			text =  "<html>一年的马杯征程结束了，如愿以偿获得了大马杯。捧着奖杯的你和同伴一起合影，纪念这过去的一年。每一年都有新鲜血液加入每个组织，"+
					"每个组织都在为自己的目标奋斗着，这就是清华的社工故事……故事仍将继续。<br>社工支线结束。</html>";
			break;
		}
		
		
		//<html><font style=\"color:blue\">庚敬</font><font style=\"color:red\">煊</font></html>"
		switch (speaker) {
		case 0:	
			dialogName.setText("<html>我</html>");
			break;
		case 1:
			dialogName.setText("<html>GM</html>");
			break;
		default:
			dialogName.setText("<html></html>");
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

		if (dataPackage.count == 8 || dataPackage.count == 11) {
			nextButton.setVisible(false);
		}
		
		/*************************************************************	
		* 【选择】 
		*  	count=8 触发
		*  
		*************************************************************/
		JPanel choose_Pack = new JPanel();
		choose_Pack.setLayout(null);
		choose_Pack.setOpaque(false);//注意要设成透明的
		choose_Pack.setBounds(221, 136, 512, 354);
		choose_Pack.setVisible(false);
		
		JPanel choose_Panel = new JPanel();
		choose_Panel.setBounds(0, 0, 512, 354);
		choose_Panel.setOpaque(false);//注意要设成透明的
		choose_Panel.setLayout(null);
		
			JPanel choose = new ImagePanel("imgsrc//WinOrganization/smallDialog.png",0, 0, 512, 354);
			choose.setBounds(0, 0, 512, 354);
			choose.setLayout(null);
				JLabel textLabel = new JLabel();
				textLabel.setHorizontalAlignment(SwingConstants.CENTER);
				textLabel.setText("<html>是否回答GM的问题？</html>");
				textLabel.setFont(new Font("华文细黑", Font.BOLD, 25));
				textLabel.setBounds(81, 51, 270,77);
				
				JButton yes = new JButton();
				yes.setBounds(137, 138, 150, 50);
				yes.setBorderPainted(false);
				yes.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/acceptUp.png", yes);
				setSelectedIcon("/imgsrc/WinOrganization/acceptDown.png", yes);
				choose_Panel.add(yes);
				
				JButton nope = new JButton();
				nope.setBounds(137, 208, 150, 50);
				nope.setBorderPainted(false);
				nope.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/refuseUp.png", nope);
				setSelectedIcon("/imgsrc/WinOrganization/refuseDown.png", nope);
				choose_Panel.add(nope);
				
			choose_Panel.add(textLabel);
			choose_Pack.add(choose_Panel);
			choose_Pack.add(choose);
			backgroundPanel.add(choose_Pack);
		
		if (dataPackage.count == 8) {
			choose_Pack.setVisible(true);
		}else {
			choose_Pack.setVisible(false);
		}
		
		/*************************************************************	
		* 【题目】 
		*  	count=11 触发
		*  
		*************************************************************/
		JPanel problem_Pack = new JPanel();
		problem_Pack.setLayout(null);
		problem_Pack.setOpaque(false);//注意要设成透明的
		problem_Pack.setBounds(221, 136, 512, 354);
		problem_Pack.setVisible(false);
		
		JPanel problem_Panel = new JPanel();
		problem_Panel.setBounds(0, 0, 512, 354);
		problem_Panel.setOpaque(false);//注意要设成透明的
		problem_Panel.setLayout(null);
		
			JPanel problem = new ImagePanel("imgsrc//WinOrganization/question.png",0, 0, 512, 354);
			problem.setBounds(0, 0, 512, 354);
			problem.setLayout(null);
			
			ButtonGroup problem_1_Group = new ButtonGroup();
			A1 = new JRadioButton();
			A1.setBounds(50, 120, 30, 30);
			A1.setOpaque(false);//注意要设成透明的
			A1.setSelected(false);
			problem_1_Group.add(A1);
			problem_Panel.add(A1);
			
			B1 = new JRadioButton();
			B1.setBounds(215, 120, 30, 30);
			B1.setOpaque(false);//注意要设成透明的
			problem_1_Group.add(B1);
			problem_Panel.add(B1);
			
			C1 = new JRadioButton();
			C1.setBounds(387, 120, 30, 30);
			C1.setOpaque(false);//注意要设成透明的
			C1.setSelected(false);
			problem_1_Group.add(C1);
			problem_Panel.add(C1);
			
			ButtonGroup problem_2_Group = new ButtonGroup();
				A2 = new JRadioButton();
				A2.setBounds(50, 188, 30, 30);
				A2.setOpaque(false);//注意要设成透明的
				A2.setSelected(false);
				problem_Panel.add(A2);
				problem_2_Group.add(A2);
				
				B2 = new JRadioButton();
				B2.setBounds(215, 188, 30, 30);
				B2.setOpaque(false);//注意要设成透明的
				problem_Panel.add(B2);
				problem_2_Group.add(B2);
				
				C2 = new JRadioButton();
				C2.setBounds(387, 188, 30, 30);
				C2.setOpaque(false);//注意要设成透明的
				C2.setSelected(false);
				problem_Panel.add(C2);
				problem_2_Group.add(C2);
				
			ButtonGroup problem_3_Group = new ButtonGroup();
				A3 = new JRadioButton();
				A3.setBounds(50, 260, 30, 30);
				A3.setOpaque(false);//注意要设成透明的
				A3.setSelected(false);
				problem_Panel.add(A3);
				problem_3_Group.add(A3);
				
				B3 = new JRadioButton();
				B3.setBounds(215, 260, 30, 30);
				B3.setOpaque(false);//注意要设成透明的
				problem_Panel.add(B3);
				problem_3_Group.add(B3);
				
				C3 = new JRadioButton();
				C3.setBounds(387, 260, 30, 30);
				C3.setOpaque(false);//注意要设成透明的
				C3.setSelected(false);
				problem_Panel.add(C3);
				problem_3_Group.add(C3);
				
				JButton submit = new JButton();
				submit.setBounds(181, 305, 120, 40);
				submit.setBorderPainted(false);
				submit.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/buttonUp.png", submit);
				setSelectedIcon("/imgsrc/WinOrganization/buttonDown.png", submit);
				problem_Panel.add(submit);
				
			problem_Pack.add(problem_Panel);
			problem_Pack.add(problem);
			backgroundPanel.add(problem_Pack);
			
			if (dataPackage.count == 11) {
				problem_Pack.setVisible(true);
			}else {
				problem_Pack.setVisible(false);
			}
		
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
		
		JPanel Background=new ImagePanel("imgsrc//WinOrganization/white.png",0, 0, 1080, 720);
		if (dataPackage.count >= 0 && dataPackage.count <= 11) {
			Background = new ImagePanel("imgsrc//WinOrganization/white.png",0, 0, 1080, 720);
		}else if(dataPackage.count == 12) {
			Background = new ImagePanel("imgsrc//WinOrganization/normal.png",0, 0, 1080, 720);
		}else if(dataPackage.count == 13) {
			Background = new ImagePanel("imgsrc//WinOrganization/lose.jpg",0, 0, 1080, 720);
		}else if(dataPackage.count == 14) {
			Background = new ImagePanel("imgsrc//WinOrganization/win.png",0, 0, 1080, 720);
		}else if(dataPackage.count == 15) {
			Background = new ImagePanel("imgsrc//WinOrganization/dinner.png",0, 0, 1080, 720);
		}
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
		
		demoMouseListener clickYes=new demoMouseListener(1);//设置鼠标监听器，发生1号事件
		clickYes.setButton(yes);
		yes.addMouseListener(clickYes);//1号事件是 确认规则按钮 被点击
		
		demoMouseListener clickNo=new demoMouseListener(2);//设置鼠标监听器，发生1号事件
		clickNo.setButton(nope);
		nope.addMouseListener(clickNo);//02号事件是 题目1提交按钮被点击
		
		demoMouseListener clickSubmit=new demoMouseListener(3);//设置鼠标监听器，发生03号事件
		clickSubmit.setButton(submit);
		submit.addMouseListener(clickSubmit);//03号事件是 题目2提交按钮 被点击

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