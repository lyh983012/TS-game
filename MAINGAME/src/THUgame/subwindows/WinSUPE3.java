package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

/*
 * 第二次例会 
 * 
 *  ---- LOG ----
 *  update:20191214
 *  via：余冬杰
 *  对话流程:
 *      0-1-2-3-6-7-8-9-10-11
 * 			 \
 * 			  4-5-7-8-9-10-11
 **/


public class WinSUPE3 extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 * 
	 *************************************************************/

	
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
				dataPackage.SUPEmentor = 1;  // 选择汪赫谦-设计线
			}else if(mode ==2){
				dataPackage.SUPEmentor = 2;  // 选择章昭焕-后勤线
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
	public WinSUPE3(EventManager mainGame,JFrame frame) {
		
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
			
			int speaker=0;    //说话人0-庚敬煊 1-独白 2-章昭焕 3-汪赫谦
			String text="";       //说话内容
			
		System.out.println(dataPackage.count);
		switch(dataPackage.count) {
		case 0:
			text = "<html>各位，大家晚上好呀！</html>";
			break;
		case 1:
			speaker = 2;
			text = "<html>好！</html>";
			break;
		case 2:
			speaker = 0;
			text = "<html>这学期的马杯工作进行的差不多啦，我们系的成果不错！</html>";
			break;
		case 3:
			speaker = 0;
			text = "<html>首先是男篮打进了4强，而且上周宣传做的推送反响很好，恭喜汪赫谦和<font style=\"color:red\">"+dataPackage.name+"</font>！"+
				   "<br>【收到了夸奖，心情好起来了+5】</html>";
			break;
		case 4:
			speaker = 0;
			text = "<html>首先是男篮打进了4强，而且上周宣传做的推送反响很好，恭喜汪赫谦和康孟博！</html>";
			break;
		case 5:
			speaker = 0;
			text = "<html>另外，键绳运动会我们总分并列第一。但是遗憾的是我们的第一名数目不够，所以无法拿到冠军奖杯。有点可惜。"+
				   "<br>【心中非常遗憾，心情-3】</html>";
			break;
		case 6:
			speaker = 0;
			text = "<html>另外，键绳运动会我们总分并列第一。但是遗憾的是我们的第一名数目不够，所以无法拿到冠军奖杯。有点可惜。"+
				   "<br>【同伴负责的项目，心里不是滋味，心情-2】</html>";
			break;
		case 7:
			speaker = 0;			
			text = "<html>另外，还有个好消息：男足小组赛我们头名出线了，晋级了下赛季的淘汰赛。"+
				   "<br>【心情再次回复了一些+1】</html>";
			break;
		case 8:
			speaker = 0;	
			text = "<html>这学期我们工作顺利，马杯总分上小有领先。下学期，我们老伙计们就退役了，就要靠年轻的大一各位了！</html>";
			break;
		case 9:
			speaker = 0;	
			text = "<html>相信大家跟着师傅，已经稍微熟悉了一些体育部的工作。大家要加油啊！下学期"+
				   "<font style=\"color:red\">第一周周二晚上10点</font>，大家需要继续参加例会，地点仍然是517A，主题是体育部部长选举。</html>";
			break;
		case 10:
			speaker = 0;	
			text = "<html>大家最近就好好学习，准备期末考试，加油！今天的例会就到这里。</html>";
			break;
		case 11:
			speaker = 1;	
			text = "<html>走出517A，心情复杂。</html>";
			break;
		}

		
		//<html><font style=\"color:blue\">庚敬</font><font style=\"color:red\">煊</font></html>"
		switch (speaker) {
		case 0:
			dialogName.setText("<html>庚敬煊</html>");
			break;
		case 1:
			dialogName.setText("<html>我</html>");
			break;
		case 2:
			dialogName.setText("<html>所有人</html>");
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