package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;

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
 * 【宿舍界面】
 * 
 * 体育部第二次活动——宣传线 * 
 * 
 *  ---- LOG ----
 *  update:20191203
 *  via：余冬杰
 *  
 **/


public class WinSUPE21 extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 * 
	 *************************************************************/
	static JRadioButton img1;
	static JRadioButton img2;
	static JRadioButton img3;
	static JRadioButton img4;
	static JRadioButton img5;
	static JRadioButton img6;
	
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
				dataPackage.choiceA = "chooseImg";
			}else if(mode ==2){
				// 开始结算分数
				// 1-3  2-6  3-1  4-4  5-2  6-5
				if (safeGuardCount == 0 && dataPackage.count==8) {
					int choose1 = 0;
					int choose2 = 0;
					int choose3 = 0;
					int choose4 = 0;
					int choose5 = 0;
					int choose6 = 0;
					int point = 0;  // 总得分
					if (img1.isSelected()) {
						choose1 = 1;
						point += 3;
					}
					if (img2.isSelected()) {
						choose2 = 1;
						point += 6;
					}
					if (img3.isSelected()) {
						choose3 = 1;
						point += 1;
					}
					if (img4.isSelected()) {
						choose4 = 1;
						point += 4;
					}
					if (img5.isSelected()) {
						choose5 = 1;
						point += 2;
					}
					if (img6.isSelected()) {
						choose6 = 1;
						point += 5;
					}
					if( choose1+choose2+choose3+choose4+choose5+choose6<3) {
						JOptionPane.showMessageDialog(null, "没选满3张图片噢", "oops",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					if( choose1+choose2+choose3+choose4+choose5+choose6>3) {
						JOptionPane.showMessageDialog(null, "图片选的太多啦", "oops",JOptionPane.WARNING_MESSAGE);  
						return;
					}
					if(choose1+choose2+choose3+choose4+choose5+choose6 == 3) {
						if (dataPackage.stateA.equals("")) {
							dataPackage.stateA = "1";
						}else {
							dataPackage.stateA = "" + (Integer.valueOf(dataPackage.stateA)+1); // 记录选取次数
						}
						System.out.println(dataPackage.stateA+"!");
						if(point >= 10) {
							dataPackage.stateB = "Success";
							safeGuardCount++;
						}else if(10 - point > 1) {  // 分数差的比较多
							dataPackage.stateB = "farFromSuccess";
							if (Integer.valueOf(dataPackage.stateA) >= 12) {
								dataPackage.stateB = "justStop";
							}
						}else if(10 - point == 1) { // 分数很接近了
							dataPackage.stateB = "nearlySuccess";
							if (Integer.valueOf(dataPackage.stateA) >= 12) {
								dataPackage.stateB = "justStop";
							}
						} 
					}
				}
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
	public WinSUPE21(EventManager mainGame,JFrame frame) {
		
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
		dataPackage.trigonceSA=false;
		
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
		case 0:
			text = "<html>哈，<font style=\"color:red\">"+dataPackage.name+"</font>你来啦！</html>";
			break;
		case 2:
			speaker = 1;
			text = "<html>汪师傅你好！</html>";
			break;
		case 3:
			speaker = 0;
			text = "<html>嘿嘿，我们开始工作吧。今天主要是把中午男篮比赛的图片筛选一下用于推送素材，"+
				   "我们需要从6张图中选取3张。<font style=\"color:red\">不同的图片代表不同的得分，</font>"+
				   "只有满足得分要求才可以开始做推送噢。</html>";
			break;
		case 4:
			speaker = 1;
			text = "<html>抱歉抱歉学长，我迟到了......<br>社交值-2</html>";
			break;
		case 5:
			speaker = 0;
			text =  "<html>哈哈没事没事，我们尽快开始。今天主要是把中午男篮比赛的图片筛选一下用于推送素材，"+
					"我们需要从6张图中选取3张。<font style=\"color:red\">不同的图片代表不同的得分，</font>"+
					"只有满足得分要求才可以开始做推送噢。</html>";
			break;
		case 6:
			speaker = 1;
			text = "<html>明白了，我们开始吧！</html>";
			break;
		case 8:
			speaker = 0;
			switch (dataPackage.stateB) {
			case "nearlySuccess":
				text = "<html>再选择一下呢？感觉效果很接近了</html>";
				break;
			case "farFromSuccess":
				text = "<html>看起来效果不是很好~</html>";
				break;
			case "":
				text = "<html>开始选择吧~</html>";
				break;
			}
			break;
		case 9:
			speaker = 0;	
			text = "<html>嗯，看起来效果不错！你很有做推送的天赋嘛2333333！今天就到这里，辛苦你啦，记得下周二晚上10点的例会~</html>";
			break;
		case 10:
			speaker = 0;	
			text = "<html>嗯，调到了不错的效果！今天也不早啦，辛苦！记得下周二晚上10点的例会~</html>";
			break;
		case 11:
			speaker = 0;	
			text = "<html>嗯！虽然试了好多次，但是最终的效果很好！设计这事情的确需要灵感和勤勉。辛苦啦，快回去睡觉吧~记得下周二晚上10点的例会~</html>";
			break;
		case 12:
			speaker = 1;	
			text = "<html>谢谢师傅，我先撤啦！再见~ <br>SUPE贡献值+4，心情+5，体力减少了一部分</html>";
			break;
		case 13:
			speaker = 0;	
			text = "<html>今天也不早啦，你先回去休息吧！我再来调整一下推送的排版~记得下周二晚上10点的例会~</html>";
			break;
		case 14:
			speaker = 1;
			text = "<html>谢谢师傅，那我先回去了，拜拜！<br>SUPE贡献值+2，心情+1，体力-8</html>";
			break;
		}
		
		
		//<html><font style=\"color:blue\">庚敬</font><font style=\"color:red\">煊</font></html>"
		switch (speaker) {
		case 0:	
			dialogName.setText("<html>汪赫谦</html>");
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

		if (dataPackage.count == 7 || dataPackage.count == 8) {  //触发选图，不显示next，通过确认交互
			nextButton.setVisible(false);
		}
		
		/*************************************************************	
		* 【选取图片】 
		*  	count=8 触发
		*  
		*************************************************************/
		JPanel chooseImgPack = new JPanel();
		chooseImgPack.setLayout(null);
		chooseImgPack.setOpaque(false);//注意要设成透明的
		chooseImgPack.setBounds(221, 136, 512, 354);
		chooseImgPack.setVisible(false);
		JPanel chooseImgPanel = new JPanel();
		chooseImgPanel.setBounds(0, 0, 512, 354);
		chooseImgPanel.setOpaque(false);//注意要设成透明的
		chooseImgPanel.setLayout(null);
		
			JPanel chooseImgBackground = new ImagePanel("imgsrc//WinOrganization/chooseImg.png",0, 0, 512, 354);
			chooseImgBackground.setBounds(0, 0, 512, 354);
			chooseImgBackground.setLayout(null);
			
				img1 = new JRadioButton();
				img1.setBounds(150, 101, 30, 30);
				img1.setOpaque(false);//注意要设成透明的
				img1.setSelected(true);
				chooseImgPanel.add(img1);
				
				img2 = new JRadioButton();
				img2.setBounds(303, 101, 30, 30);
				img2.setOpaque(false);//注意要设成透明的
				chooseImgPanel.add(img2);
				
				img3 = new JRadioButton();
				img3.setBounds(456, 101, 30, 30);
				img3.setOpaque(false);//注意要设成透明的
				img3.setSelected(true);
				chooseImgPanel.add(img3);
				
				img4 = new JRadioButton();
				img4.setBounds(150, 234, 30, 30);
				img4.setOpaque(false);//注意要设成透明的
				chooseImgPanel.add(img4);
				
				img5 = new JRadioButton();
				img5.setBounds(303, 234, 30, 30);
				img5.setOpaque(false);//注意要设成透明的
				img5.setSelected(true);
				chooseImgPanel.add(img5);
				
				img6 = new JRadioButton();
				img6.setBounds(456, 234, 30, 30);
				img6.setOpaque(false);//注意要设成透明的
				chooseImgPanel.add(img6);
				
				JButton submit = new JButton();
				submit.setBounds(193, 305, 120, 40);
				submit.setBorderPainted(false);
				submit.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/buttonUp.png", submit);
				setSelectedIcon("/imgsrc/WinOrganization/buttonDown.png", submit);
				chooseImgPanel.add(submit);
				
			chooseImgPack.add(chooseImgPanel);
			chooseImgPack.add(chooseImgBackground);
			backgroundPanel.add(chooseImgPack);
		
		if (dataPackage.count == 8) {
			chooseImgPack.setVisible(true);
		}
		
		/*************************************************************	
		* 【规则解释】 
		*  	count=7 触发
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
			textTitle.setText("<html>从6张给定的图片中选择3张，汪师傅会对你选择的图片给出评价。如果他不够满意的话，需要重新选择。机会不限，但是时间流逝与选择次数有关。加油，奥利给！</html>");
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
		
		if (dataPackage.count == 7) {
			choosePack.setVisible(true);
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
		
		demoMouseListener clickConfirm=new demoMouseListener(1);//设置鼠标监听器，发生0号事件
		clickConfirm.setButton(confirm);
		confirm.addMouseListener(clickConfirm);//0号事件是 下一步按钮 被点击
		
		demoMouseListener clickSubmit=new demoMouseListener(2);//设置鼠标监听器，发生0号事件
		clickSubmit.setButton(submit);
		submit.addMouseListener(clickSubmit);//0号事件是 下一步按钮 被点击

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