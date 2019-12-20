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
 * 【宿舍界面】
 * 
 * 体育部第二次活动 * 
 * 
 *  ---- LOG ----
 *  update:20191215
 *  via：余冬杰
 *  
 *  
 **/


public class WinSUPE5 extends WinBase{
	
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
	static JRadioButton D1;
	static JRadioButton E1;
	static JRadioButton F1;
	static JRadioButton G1;
	static JRadioButton H1;
	
	static JRadioButton A2;
	static JRadioButton B2;
	static JRadioButton C2;
	static JRadioButton D2;
	static JRadioButton E2;
	static JRadioButton F2;
	static JRadioButton G2;
	
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
			}else if(mode ==1){
				dataPackage.choiceA = "confirmRule";
			}else if(mode ==2){
				// 开始结算分数
				// 1-3  2-6  3-1  4-4  5-2  6-5
				if (safeGuardCount == 0 && dataPackage.count==4) {
					int choose1 = 0;
					int choose2 = 0;
					int choose3 = 0;
					int choose4 = 0;
					int choose5 = 0;
					int choose6 = 0;
					int choose7 = 0;
					int choose8 = 0;
					if (A1.isSelected()) {
						choose1 = 1;
					}
					if (B1.isSelected()) {
						choose2 = 1;
					}
					if (C1.isSelected()) {
						choose3 = 1;
					}
					if (D1.isSelected()) {
						choose4 = 1;
					}
					if (E1.isSelected()) {
						choose5 = 1;
					}
					if (F1.isSelected()) {
						choose6 = 1;
					}
					if (G1.isSelected()) {
						choose7 = 1;
					}
					if (H1.isSelected()) {
						choose8 = 1;
					}
					if(choose1+choose2+choose3+choose4+choose5+choose6+choose7+choose8==0) {
						JOptionPane.showMessageDialog(null, "还是要选一个选项哦", "oops",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					if(choose1+choose2+choose3+choose4+choose5+choose6+choose7+choose8==1) {
						safeGuardCount++;
						if(choose4==1) {
							dataPackage.stateA = "1";
						}else {
							dataPackage.stateA = "0";
						}	
					}
				}
			}else if(mode ==3){
				if (dataPackage.count==6) {
					int choose1 = 0;
					int choose2 = 0;
					int choose3 = 0;
					int choose4 = 0;
					int choose5 = 0;
					int choose6 = 0;
					int choose7 = 0;
					if (A2.isSelected()) {
						choose1 = 1;
					}
					if (B2.isSelected()) {
						choose2 = 1;
					}
					if (C2.isSelected()) {
						choose3 = 1;
					}
					if (D2.isSelected()) {
						choose4 = 1;
					}
					if (E2.isSelected()) {
						choose5 = 1;
					}
					if (F2.isSelected()) {
						choose6 = 1;
					}
					if (G2.isSelected()) {
						choose7 = 1;
					}

					if(choose1+choose2+choose3+choose4+choose5+choose6+choose7==0) {
						JOptionPane.showMessageDialog(null, "还是要选一个选项哦", "oops",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					if(choose1+choose2+choose3+choose4+choose5+choose6+choose7==1) {
						if(choose6==1) {
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
	public WinSUPE5(EventManager mainGame,JFrame frame) {
		
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
			
			int speaker=0;    //说话人0-王山 1-我 2-大家
			if (dataPackage.SUPEchair == 1) {
				speaker = 1;
			}
			
			String text="";       //说话内容
		System.out.println(dataPackage.count);
		
		switch(dataPackage.count) {
		case 0:
			text = "<html>大家周末还要来干活，辛苦啦！</html>";
			break;
		case 1:
			speaker = 2;
			text = "<html>还好还好~</html>";
			break;
		case 2:
			text = "<html>今天我们需要安排一下校马的物资。主要有两个任务：" +
				   "一个是把有些物资袋空缺的东西放进去；另一个是把学校体育部分" + 
				   "发物资里面混进去的放错类型的东西挑出来，那么大家就开始吧！</html>";
			break;
		case 3:
			text = "<html></html>";
			break;
		case 4:
			text = "<html></html>";
			break;
		case 5:
			text =  "<html></html>";
			break;
		case 6:
			text = "<html></html>";
			break;
		case 7:
			text = "<html>（看起来还没睡醒的样子，分错了一些）</html>";
			break;
		case 8:
			text = "<html>辛苦大家周末还来体育部干活了！快回去休息吧！<br>【体育部贡献值+3，心情+2】</html>";
			break;
		case 9:
			text = "<html>辛苦各位啦！大家快回去休息吧<br>【体育部贡献值+5，心情+3】</html>";
			break;
		case 10:	
			text = "<html>下次的例会是</font><font style=\"color:red\">第二周周二晚上10点</font>！记得准时到场哦！</html>";
			break;
		}
		
		
		//<html><font style=\"color:blue\">庚敬</font><font style=\"color:red\">煊</font></html>"
		switch (speaker) {
		case 0:	
			dialogName.setText("<html>王山</html>");
			break;
		case 1:
			dialogName.setText("<html>我</html>");
			break;
		case 2:
			dialogName.setText("<html>大家</html>");
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

		if (dataPackage.count >= 3 && dataPackage.count <= 6) {  //触发选图，不显示next，通过确认交互
			nextButton.setVisible(false);
		}
		
		/*************************************************************	
		* 【第一题】 
		*  	count=4 触发
		*  
		*************************************************************/
		JPanel problem_1_Pack = new JPanel();
		problem_1_Pack.setLayout(null);
		problem_1_Pack.setOpaque(false);//注意要设成透明的
		problem_1_Pack.setBounds(221, 136, 512, 354);
		problem_1_Pack.setVisible(false);
		
		JPanel problem_1_Panel = new JPanel();
		problem_1_Panel.setBounds(0, 0, 512, 354);
		problem_1_Panel.setOpaque(false);//注意要设成透明的
		problem_1_Panel.setLayout(null);
		
			JPanel problem_1 = new ImagePanel("imgsrc//WinOrganization/problem_1.png",0, 0, 512, 354);
			problem_1.setBounds(0, 0, 512, 354);
			problem_1.setLayout(null);
			
			ButtonGroup problem_1_Group = new ButtonGroup();
				A1 = new JRadioButton();
				A1.setBounds(280, 177, 30, 30);
				A1.setOpaque(false);//注意要设成透明的
				A1.setSelected(false);
				problem_1_Group.add(A1);
				problem_1_Panel.add(A1);
				
				B1 = new JRadioButton();
				B1.setBounds(338, 177, 30, 30);
				B1.setOpaque(false);//注意要设成透明的
				problem_1_Group.add(B1);
				problem_1_Panel.add(B1);
				
				C1 = new JRadioButton();
				C1.setBounds(396, 177, 30, 30);
				C1.setOpaque(false);//注意要设成透明的
				C1.setSelected(false);
				problem_1_Group.add(C1);
				problem_1_Panel.add(C1);
				
				D1 = new JRadioButton();
				D1.setBounds(454, 177, 30, 30);
				D1.setOpaque(false);//注意要设成透明的
				problem_1_Group.add(D1);
				problem_1_Panel.add(D1);
				
				E1 = new JRadioButton();
				E1.setBounds(280, 284, 30, 30);
				E1.setOpaque(false);//注意要设成透明的
				E1.setSelected(false);
				problem_1_Group.add(E1);
				problem_1_Panel.add(E1);
				
				F1 = new JRadioButton();
				F1.setBounds(338, 284, 30, 30);
				F1.setOpaque(false);//注意要设成透明的
				problem_1_Group.add(F1);
				problem_1_Panel.add(F1);
				
				G1 = new JRadioButton();
				G1.setBounds(396, 284, 30, 30);
				G1.setOpaque(false);//注意要设成透明的
				G1.setSelected(false);
				problem_1_Group.add(G1);
				problem_1_Panel.add(G1);
				
				H1 = new JRadioButton();
				H1.setBounds(454, 284, 30, 30);
				H1.setOpaque(false);//注意要设成透明的
				problem_1_Group.add(H1);
				problem_1_Panel.add(H1);
				
				JButton submit = new JButton();
				submit.setBounds(200, 313, 90, 30);
				submit.setBorderPainted(false);
				submit.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/buttonUp.png", submit);
				setSelectedIcon("/imgsrc/WinOrganization/buttonDown.png", submit);
				problem_1_Panel.add(submit);
				
			problem_1_Pack.add(problem_1_Panel);
			problem_1_Pack.add(problem_1);
			backgroundPanel.add(problem_1_Pack);
		
		if (dataPackage.count == 4) {
			problem_1_Pack.setVisible(true);
		}else {
			problem_1_Pack.setVisible(false);
		}
		
		/*************************************************************	
		* 【第二题】 
		*  	count=6 触发
		*  
		*************************************************************/
		JPanel problem_2_Pack = new JPanel();
		problem_2_Pack.setLayout(null);
		problem_2_Pack.setOpaque(false);//注意要设成透明的
		problem_2_Pack.setBounds(221, 136, 512, 354);
		problem_2_Pack.setVisible(false);
		
		JPanel problem_2_Panel = new JPanel();
		problem_2_Panel.setBounds(0, 0, 512, 354);
		problem_2_Panel.setOpaque(false);//注意要设成透明的
		problem_2_Panel.setLayout(null);
		
			JPanel problem_2 = new ImagePanel("imgsrc//WinOrganization/problem_2.png",0, 0, 512, 354);
			problem_2.setBounds(0, 0, 512, 354);
			problem_2.setLayout(null);
			ButtonGroup problem_2_Group = new ButtonGroup();
				A2 = new JRadioButton();
				A2.setBounds(95, 172, 30, 30);
				A2.setOpaque(false);//注意要设成透明的
				A2.setSelected(false);
				problem_2_Panel.add(A2);
				problem_2_Group.add(A2);
				
				B2 = new JRadioButton();
				B2.setBounds(215, 172, 30, 30);
				B2.setOpaque(false);//注意要设成透明的
				problem_2_Panel.add(B2);
				problem_2_Group.add(B2);
				
				C2 = new JRadioButton();
				C2.setBounds(322, 172, 30, 30);
				C2.setOpaque(false);//注意要设成透明的
				C2.setSelected(false);
				problem_2_Panel.add(C2);
				problem_2_Group.add(C2);
				
				D2 = new JRadioButton();
				D2.setBounds(435, 172, 30, 30);
				D2.setOpaque(false);//注意要设成透明的
				problem_2_Panel.add(D2);
				problem_2_Group.add(D2);
				
				E2 = new JRadioButton();
				E2.setBounds(147, 278, 30, 30);
				E2.setOpaque(false);//注意要设成透明的
				E2.setSelected(false);
				problem_2_Panel.add(E2);
				problem_2_Group.add(E2);
				
				F2 = new JRadioButton();
				F2.setBounds(266, 278, 30, 30);
				F2.setOpaque(false);//注意要设成透明的
				problem_2_Panel.add(F2);
				problem_2_Group.add(F2);
				
				G2 = new JRadioButton();
				G2.setBounds(377, 278, 30, 30);
				G2.setOpaque(false);//注意要设成透明的
				G2.setSelected(false);
				problem_2_Panel.add(G2);
				problem_2_Group.add(G2);
				
				JButton submit_2 = new JButton();
				submit_2.setBounds(200, 313, 90, 30);
				submit_2.setBorderPainted(false);
				submit_2.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/buttonUp.png", submit_2);
				setSelectedIcon("/imgsrc/WinOrganization/buttonDown.png", submit_2);
				problem_2_Panel.add(submit_2);
				
			problem_2_Pack.add(problem_2_Panel);
			problem_2_Pack.add(problem_2);
			backgroundPanel.add(problem_2_Pack);
		
		if (dataPackage.count == 6) {
			problem_2_Pack.setVisible(true);
		}else {
			problem_2_Pack.setVisible(false);
		}
		
		/*************************************************************	
		* 【规则解释】 
		*  	count=3 && 5 触发
		*  
		*************************************************************/
		JPanel choosePack = new JPanel();
		choosePack.setLayout(null);
		choosePack.setOpaque(false);//注意要设成透明的
		choosePack.setBounds(221, 136, 512, 354);
		choosePack.setVisible(false);
		
			JPanel choosePanel = new JPanel();
			choosePanel.setBounds(0, 0, 512, 354);
			choosePanel.setOpaque(false);//注意要设成透明的
			choosePanel.setLayout(null);
			
			JPanel chooseBackground = new ImagePanel("imgsrc//WinOrganization/ruleExplain.png",0, 0, 512, 354);
			chooseBackground.setBounds(0, 0, 512, 354);
			chooseBackground.setLayout(null);
			
			JLabel textTitle = new JLabel();
			textTitle.setVerticalAlignment(SwingConstants.CENTER);
			textTitle.setHorizontalAlignment(SwingConstants.CENTER);
			textTitle.setOpaque(false);
			textTitle.setFont(new Font("仿宋", Font.BOLD, 30));
			textTitle.setBounds(10, 73, 492, 200);
			choosePanel.add(textTitle);
			
			JButton confirm = new JButton();
			confirm.setBounds(193, 283, 120, 40);
			confirm.setBorderPainted(false);
			confirm.setContentAreaFilled(false);
			setIcon("/imgsrc/WinOrganization/buttonUp.png", confirm);
			setSelectedIcon("/imgsrc/WinOrganization/buttonDown.png", confirm);
			choosePanel.add(confirm);
			
			choosePack.add(choosePanel);
			choosePack.add(chooseBackground);
		backgroundPanel.add(choosePack);     //触发聘书，不显示next，通过×交互
		
		if (dataPackage.count == 3) {
			textTitle.setText("<html>根据现有的物资组合规律，从备选的8个物资图片中选择正确的物资。加油，奥利给！</html>");
			choosePack.setVisible(true);
		}else if (dataPackage.count == 5){
			textTitle.setText("<html>从下列物资中选择编号类型不同的物资。加油，奥利给！</html>");
			choosePack.setVisible(true);
		}else {
			choosePack.setVisible(false);
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
		
		JPanel Background=new ImagePanel("imgsrc//WinOrganization/SUPEbackground.png",0, 0, 1080, 720);
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
		
		demoMouseListener clickConfirm=new demoMouseListener(1);//设置鼠标监听器，发生1号事件
		clickConfirm.setButton(confirm);
		confirm.addMouseListener(clickConfirm);//1号事件是 确认规则按钮 被点击
		
		demoMouseListener clickSubmit=new demoMouseListener(2);//设置鼠标监听器，发生1号事件
		clickSubmit.setButton(submit);
		submit.addMouseListener(clickSubmit);//02号事件是 题目1提交按钮被点击
		
		demoMouseListener clickSubmit_2=new demoMouseListener(3);//设置鼠标监听器，发生03号事件
		clickSubmit_2.setButton(submit_2);
		submit_2.addMouseListener(clickSubmit_2);//03号事件是 题目2提交按钮 被点击

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