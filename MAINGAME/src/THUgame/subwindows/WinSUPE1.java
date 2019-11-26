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
 * --DIALOG--
 *  update:20191123
 *  via：余冬杰  
 *  
 *  update:20191029
 *  via：余冬杰
 *  更新：
 *  TODO:
 *      1.
 *  TODO LIST in line:
 *  
 **/


public class WinSUPE1 extends WinBase{
	
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
	public WinSUPE1(EventManager mainGame,JFrame frame) {
		
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
			text = "<html>各位体育部的新生力量和老面孔大家新学期好呀！</html>";
			break;
		case 1:
			speaker = 4;
			text = "<html>吼！</html>";
			break;
		case 2:
			speaker = 0;
			text = "<html>新一年的马杯征程就要开始了~这半个学期我们旧成员要做好交接的工作，让新人熟悉工作，学期结束后放心大胆的交给他们！</html>";
			break;
		case 3:
			speaker = 0;
			text = "<html>体育部目前采用师徒制，每一位新成员都要选择一个学长/学姐作为师傅，协助他/她工作了解内容。</html>";
			break;
		case 4:
			speaker = 1;
			text = "<html>……看着其他同学决定</html>";
			break;
		case 5:
			speaker = 0;
			text = "<html><font style=\"color:red\">"+dataPackage.name+"</font>该你作出决定啦</html>";
			break;
		case 6:
			speaker = 1;
			String name = "";
			if (dataPackage.SUPEmentor==1) {
				name = "汪赫谦";
			}else if (dataPackage.SUPEmentor==2){
				name = "章昭焕";
			}	
			text = "<html>最终选择了<font style=\"color:red\">"+name+"</font>作为自己的师傅</html>";
			break;
		case 7:
			speaker = 3;			
			text = "<html>你眼光真不赖233333选我做师傅，下周我们应该会有第一件事，做当天男篮比赛的推送。<font style=\"color:red\">"+"下周四9点"+
					"</font>我们<font style=\"color:red\">"+"517A"+"</font>见，做好准备哦。</html>";
			break;
		case 8:
			speaker = 2;	
			text = "<html>谢谢你信任我做师傅！键绳运动会快到了，<font style=\"color:red\">"+"下周四晚8点C楼"+
					"</font>会有训练，到时候得麻烦你7点提前去<font style=\"color:red\">"+"黑猫超市"+"</font>购买好物资，辛苦啦！</html>";
			break;
		case 9:
			speaker = 0;	
			text = "<html>好的，今天的例会就到这里。我们下周四同一时间见！</html>";
			break;
		}
		
		
		
		//<html><font style=\"color:blue\">庚敬</font><font style=\"color:red\">煊</font></html>"
		switch (speaker) {
		case 0:
			dialogName.setText("<html>庚敬煊</html>");
			break;
		case 1:
			dialogName.setText("<html>独白</html>");
			break;
		case 2:
			dialogName.setText("<html>章昭焕</html>");
			break;
		case 3:
			dialogName.setText("<html>汪赫谦</html>");
			break;
		case 4:
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

		if (dataPackage.count == 5) {              //触发聘书，不显示next，通过×交互  
			nextButton.setVisible(false);
		}
		
		
		/*************************************************************	
		* 【选择师傅】 
		*  	count=5触发
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
			
			JPanel chooseBackground = new ImagePanel("imgsrc//WinOrganization/chooseBG.png",0, 0, 512, 354);
			chooseBackground.setBounds(0, 0, 512, 354);
			chooseBackground.setLayout(null);
			
				// 标题
				JLabel textTitle = new JLabel();
				textTitle.setVerticalAlignment(SwingConstants.CENTER);
				textTitle.setHorizontalAlignment(SwingConstants.CENTER);
				textTitle.setText("<html>你选择哪位师傅呢？</html>");
				textTitle.setOpaque(false);
				textTitle.setFont(new Font("华文黑体", Font.BOLD, 30));
				textTitle.setBounds(10, 0, 502, 50);
				choosePanel.add(textTitle);
				
				// 汪赫谦区
				JPanel masterWang = new JPanel();
				masterWang.setBounds(40, 60, 200, 220);
				masterWang.setOpaque(false);//注意要设成透明的
				masterWang.setLayout(null);
				choosePanel.add(masterWang);
					JLabel wangContent = new JLabel();
					wangContent.setVerticalAlignment(SwingConstants.TOP);
					wangContent.setOpaque(false);
					wangContent.setFont(new Font("印品黑体", Font.PLAIN, 15));
					wangContent.setBounds(10, 150, 190, 70);
					wangContent.setText("<html>喜欢说骚话<br>想象力丰富<br>主要负责推送和海报的制作</html>");
					masterWang.add(wangContent);
				
					JPanel imageWang = new ImagePanel("imgsrc//WinOrganization/whq.png",0, 0, 132, 220);
					imageWang.setBounds(34, 0, 132, 220);
					imageWang.setLayout(null);
					masterWang.add(imageWang);
					
					
				
				//章昭焕区
				JPanel masterZhang = new JPanel();
				masterZhang.setBounds(272, 60, 200, 220);
				masterZhang.setOpaque(false);//注意要设成透明的
				masterZhang.setLayout(null);
				choosePanel.add(masterZhang);
					JLabel zhangContent = new JLabel();
					zhangContent.setVerticalAlignment(SwingConstants.TOP);
					//zhangContent.setHorizontalAlignment(SwingConstants.CENTER);
					zhangContent.setOpaque(false);
					zhangContent.setFont(new Font("印品黑体", Font.PLAIN, 15));
					zhangContent.setBounds(0, 150, 190, 70);
					zhangContent.setText("<html>认真负责<br>但不善言谈<br>主要负责后勤的事务</html>");
					masterZhang.add(zhangContent);
					
					JPanel imageZhang = new ImagePanel("imgsrc//WinOrganization/zzh.png",0, 0, 132, 220);
					imageZhang.setBounds(34, 0, 132, 220);
					imageZhang.setLayout(null);
					masterZhang.add(imageZhang);
					
				
				// 选择按钮
				JButton WButton = new JButton();
				WButton.setBounds(85, 290, 120, 40);
				WButton.setBorderPainted(false);
				WButton.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/wang.png", WButton);
				setSelectedIcon("/imgsrc/WinOrganization/wangPressed.png", WButton);
				choosePanel.add(WButton);
				
				JButton ZButton = new JButton();
				ZButton.setBounds(317, 290, 120, 40);
				ZButton.setBorderPainted(false);
				ZButton.setContentAreaFilled(false);
				setIcon("/imgsrc/WinOrganization/zhang.png", ZButton);
				setSelectedIcon("/imgsrc/WinOrganization/zhangPressed.png", ZButton);
				choosePanel.add(ZButton);
				
				demoMouseListener clickWang=new demoMouseListener(1);//设置鼠标监听器，发生1号事件——选择汪赫谦
				clickWang.setButton(WButton);
				WButton.addMouseListener(clickWang);
				
				demoMouseListener clickZhang=new demoMouseListener(2);//设置鼠标监听器，发生2号事件——选择章昭焕
				clickZhang.setButton(ZButton);
				ZButton.addMouseListener(clickZhang);
				
				choosePack.add(choosePanel);
				choosePack.add(chooseBackground);
		backgroundPanel.add(choosePack);     //触发聘书，不显示next，通过×交互
		
		if (dataPackage.count == 5) {
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