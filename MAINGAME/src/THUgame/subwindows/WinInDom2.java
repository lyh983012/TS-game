package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import java.awt.Font;

import THUgame.Game.RememberGame;
import THUgame.Game.ShootGame;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

/*
 * 【宿舍界面】
 * 
 * --DIALOG--
 * 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * update:20191028 01:07
 * via：林逸晗
 * 更新：更改了界面UI，使之适配MAP，加入了游戏
 * 
 * update:20191018 01:07
 * via：余冬杰
 * 更新：加入了打呼噜的子事件
 * 
 * update:20191014 18:30
 * via：林逸晗
 * 更新：加入了游戏界面（在morning class的窗口里）
 * 		游戏panel直接添加就可以运行，但是需要注册一下数据包和控制进程（maingame）具体用法见morning
 * 		游戏panel在tool里有，在Game里有一个可运行的例程，现已更新完第一个game
 * 
 * update:20191010 18:30
 * via：林逸晗
 * 更新：加入了UI及使用方法
 * 		更新了按钮的UI和对话框的UI，设置UI的方法可以看本类中【按钮】和【对话框】的部分
 * 
 * update:20191010 18:30
 * via：林逸晗
 * 更新：加入对话框
 * 		加入对话的逻辑
 * 
 * update:20191006 18:30
 * via：林逸晗
 * 更新：加入了按钮的可见／不可见
 * 		加入了时钟（简陋）
 * 		鼠标事件响应不需要再写set game了，使用方法和dataPack的传递一样
 * 
 * update:20190930 18:30
 * via：林逸晗
 * 更新：解决了界面闪烁的问题
 * 		跟新了一些类的结构和注释的问题
 * 		注释中：//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥***¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥表示最重要的事情
 * 		蓝色的部分则是表示对代码块的解释
 * 		细节用单句注释阐述
 * 		推荐收起后再看代码
 * 		推荐看WinInDom.java的注释
 * 
 **/


public class WinInDom2 extends WinBase{
	
	static JTextField Chalnametext;
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
					//START OF YOUR CODE		
			if(safeGuardCount==0) {
				String name=Chalnametext.getText();
				safeGuardCount++;
				if(mode ==5){
					dataPackage.count++;//点按钮5（继续对话按钮）继续剧情   zwj
				}else if(mode ==6){
					dataPackage.count++;//点按钮6（确定报名按钮）返回对话
					dataPackage.stateA="sign up";
					dataPackage.joinChallengeCup=true;
				}else if(mode ==7){
					dataPackage.count=12;//点按钮7（取消报名按钮）进入放弃对话
					dataPackage.stateA="give up";
				}else if(mode ==8){
					if(name.length()>20 || name.length()<4) {
						JOptionPane.showMessageDialog(null, "名字字数有问题哦", "oops",JOptionPane.WARNING_MESSAGE);  
					}else{
					dataPackage.count++;//点按钮8（确定项目名按钮）
					dataPackage.ChalengeName=Chalnametext.getText();
					System.out.println("dataPackage.ChalengeName");
					System.out.println(dataPackage.ChalengeName);
					dataPackage.stateA="finish";
					}
				}else if(mode ==9){
					dataPackage.count=14;//点按钮9（确定）收到答辩提醒
				}
						//END OF YOUR CODE		
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				EventManager.dataPackage=dataPackage;
				synchronized(mainGame) {
					mainGame.notify();
				}
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
	public WinInDom2(EventManager mainGame,JFrame frame) {
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
		 * 【小事件】 
		 *  	这一部分需要用dataPackage.trigSubEvent决定是否绘制
		 *  	具体用法见MorninigClass窗口
		 *************************************************************/
		
		/*************************************************************	
		 * 【小事件】 
		 *  	下面是zwj创建的一系列挑战杯报名相关事件
		 *************************************************************/
		
		//JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	
		
/*		JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
		dialogPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
		dialogPanel.setOpaque(false);		//注意要设成透明的
		dialogPanel.setLayout(null);*/
	
		
		Chalnametext = new JTextField();
		Chalnametext.setText("4-20字");
		Chalnametext.setBounds(210, 162, 100, 40);
		Chalnametext.setColumns(10);
		//Chalnamefill.add(Chalnametext);
		
		JLabel cha_label_2 = new JLabel("给你的项目起个名字吧");
		cha_label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		cha_label_2.setBounds(185, 89, 194, 16);
		//Chalnamefill.add(cha_label_2);
		
		JButton chalnameButton = new JButton();
		chalnameButton.setBorderPainted(false);
		chalnameButton.setBounds(200, 250, 120, 50);
		chalnameButton.setContentAreaFilled(false);
		setIcon("/imgsrc/Challenge/yes.png",chalnameButton);
		setSelectedIcon("/imgsrc/Challenge/yes_press.png",chalnameButton);
		
		JPanel Chalnamefill = new JPanel();	     //填写项目名称的
		Chalnamefill.setBounds(254, 129, 531, 363);
		Chalnamefill.setOpaque(false);		//注意要设成透明的
		Chalnamefill.setLayout(null);
		backgroundPanel.add(Chalnamefill);

		Chalnamefill.add(Chalnametext);
		Chalnamefill.add(cha_label_2);
		Chalnamefill.add(chalnameButton);
		
		Chalnamefill.setVisible(false); // 未触发子事件
		
		//** *.* * *.* **//
		
		JPanel Chalsignup_1 = new JPanel();
		Chalsignup_1.setBounds(254, 129, 531, 363);
		Chalsignup_1.setOpaque(false);		//注意要设成透明的
		Chalsignup_1.setLayout(null);
		backgroundPanel.add(Chalsignup_1);
		
		JLabel cha_label_1 = new JLabel("是否要参加挑战杯？");
		cha_label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		cha_label_1.setBounds(192, 87, 157, 16);
		Chalsignup_1.add(cha_label_1);
		
		
		JButton chalyesButton = new JButton();
		chalyesButton.setBorderPainted(false);
		chalyesButton.setBounds(95, 250, 120, 50);
		chalyesButton.setContentAreaFilled(false);
		setIcon("/imgsrc/Challenge/canjia.png",chalyesButton);
		setSelectedIcon("/imgsrc/Challenge/canjia_press.png",chalyesButton);
		
		JButton chalnoButton = new JButton();
		chalnoButton.setBorderPainted(false);
		chalnoButton.setBounds(300, 250, 120, 50);
		chalnoButton.setContentAreaFilled(false);
		setIcon("/imgsrc/Challenge/bucanjia.png",chalnoButton);
		setSelectedIcon("/imgsrc/Challenge/bucanjia_press.png",chalnoButton);
		
	
		Chalsignup_1.add(chalyesButton);
		Chalsignup_1.add(chalnoButton);
		
		Chalsignup_1.setVisible(false);//测试
		
		//** *.* * *.* **//
		JLabel Notice = new JLabel("记得参加今晚20点的挑战杯答辩！");
		Notice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		Notice.setBounds(140, 89, 275, 16);
		//Chalnamefill.add(cha_label_2);
		
		JButton NoticeButton = new JButton();
		NoticeButton.setBorderPainted(false);
		NoticeButton.setBounds(200, 250, 120, 50);
		NoticeButton.setContentAreaFilled(false);
		setIcon("/imgsrc/Challenge/yes.png",NoticeButton);
		setSelectedIcon("/imgsrc/Challenge/yes_press.png",NoticeButton);
		
		JPanel NoticeWin = new JPanel();	     //填写项目名称的
		NoticeWin.setBounds(254, 129, 531, 363);
		NoticeWin.setOpaque(false);		//注意要设成透明的
		NoticeWin.setLayout(null);
		backgroundPanel.add(NoticeWin);

		NoticeWin.add(Notice);
		NoticeWin.add(NoticeButton);
		
		NoticeWin.setVisible(false); // 未触发子事件
		
		
		//** *.* * *.* **//
		
		/*************************************************************	
		 * 【小事件】 
		 *  	上面是zwj创建的一系列挑战杯报名相关事件
		 *************************************************************/
		
		RememberGame.mainGame=mainGame;//注意这里！不然没办法结束游戏！
		RememberGame.dataPackage=dataPackage;//注意这里！不然没办法结束游戏！

		/*************************************************************	
		 * 【镶时钟】
		 * 		不需要修改
		 * 		简而言之就是显示一个Table
		 *************************************************************/
		
		JPanel noticeBackgoundPanel = new ImagePanel("imgsrc//WinBackground/Welcome.png",0, 0, 531, 363);	//第2个subPanel，放图
		noticeBackgoundPanel.setBounds(254, 129, 531, 363);//(0, 0, 宽, 高);
		noticeBackgoundPanel.setOpaque(false);//注意要设成透明的
		backgroundPanel.add(noticeBackgoundPanel);
		noticeBackgoundPanel.setVisible(false);
		
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
		 * 【镶属性】
		 *************************************************************/

		JPanel panel = new JPanel();
		panel.setBounds(64, 140, 197, 290);
		backgroundPanel.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setLayout(null);
		
		JLabel StudentIDLable = new JLabel("学号");
		StudentIDLable.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		StudentIDLable.setBounds(26, 78, 32, 16);
		panel.add(StudentIDLable);
		
		JTextPane nameShow = new JTextPane();
		nameShow.setEditable(false);
		nameShow.setBounds(84, 42, 73, 20);
		panel.add(nameShow);
		
		JLabel nameLable = new JLabel("姓名");
		nameLable.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		nameLable.setBounds(26, 42, 32, 24);
		panel.add(nameLable);
		
		JTextPane IDshow = new JTextPane();
		IDshow.setEditable(false);
		IDshow.setBounds(84, 76, 73, 20);
		panel.add(IDshow);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBounds(70, 119, 114, 20);
		panel.add(healthBar);
		
		JProgressBar Bar_progress = new JProgressBar();
		Bar_progress.setBounds(70, 139, 114, 20);
		panel.add(Bar_progress);
		
		JProgressBar Bar_Energy = new JProgressBar();
		Bar_Energy.setBounds(70, 165, 114, 20);
		panel.add(Bar_Energy);
		
		JProgressBar Bar_happiness = new JProgressBar();
		Bar_happiness.setBounds(70, 187, 114, 20);
		panel.add(Bar_happiness);
		
		JLabel label_workProgress = new JLabel("学习进度");
		label_workProgress.setBounds(10, 142, 52, 16);
		panel.add(label_workProgress);
		
		JLabel label_Energy = new JLabel("体力值");
		label_Energy.setBounds(10, 166, 52, 16);
		panel.add(label_Energy);
		
		JLabel label_health = new JLabel("健康值");
		label_health.setBounds(10, 118, 52, 16);
		panel.add(label_health);
		
		JLabel label_happy = new JLabel("心   情");
		label_happy.setBounds(10, 189, 52, 16);
		panel.add(label_happy);
		
		JLabel label_social = new JLabel("社交能力:");
		label_social.setBounds(10, 219, 92, 16);
		panel.add(label_social);
		
		
		JLabel label_Art = new JLabel("才艺能力:");
		label_Art.setBounds(10, 245, 92, 16);
		panel.add(label_Art);
		
		JLabel label_IQ = new JLabel("智商:");
		label_IQ.setBounds(100, 219, 84, 16);
		panel.add(label_IQ);
		
		JLabel label_lucky = new JLabel("幸运值:");
		label_lucky.setBounds(100, 245, 84, 16);
		panel.add(label_lucky);
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
			
			if (!dataPackage.stateB.equals(""))//设置对话内容
				dialogName.setText(dataPackage.stateB);
			else
				dialogName.setText("独白");//设置默认对话内容
			
			JLabel dialogContent = new JLabel();
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(15, 42, 677, 141);
			dialogPanel.add(dialogContent);
		
		
		dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);
		
		
			JButton dialog_nextButton = new JButton();   //用于点击后继续对话//zwj
			dialog_nextButton.setIcon(new ImageIcon(WinInDom2.class.getResource("/imgsrc/dialog_toNext.png")));
			dialog_nextButton.setBounds(0, 0, 689, 189);
			dialogPanel.add(dialog_nextButton);
			dialog_nextButton.setBorderPainted(false);
			dialog_nextButton.setContentAreaFilled(false);
			dialog_nextButton.setVisible(true);

			if(dataPackage.count==5){
				Chalsignup_1.setVisible(true);
				noticeBackgoundPanel.setVisible(true);
				dialog_nextButton.setVisible(false);
			}else if(dataPackage.count==7){
				Chalnamefill.setVisible(true);
				noticeBackgoundPanel.setVisible(true);
				dialog_nextButton.setVisible(false);
			}else if(dataPackage.count==8){
				dataPackage.stateA="";
			}
			
			if( dataPackage.DabianNoticeEnable ) {
				dialogContent.setText("有点慌啊。");//设置提醒内容
				//System.out.println("判定成功");
			    noticeBackgoundPanel.setVisible(true);
			    NoticeWin.setVisible(true);
			    dialog_nextButton.setVisible(false);
			}
			else {
				//System.out.println("判定失败");
				//System.out.println(dataPackage.DabianNoticeEnable);
				if (!dataPackage.notification.equals(""))//设置对话内容
					dialogContent.setText(dataPackage.notification);
				else 
					dialogContent.setText("突然响起了敲门声，会是谁呢？");//设置默认对话内容
			}
			
	
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
		
		JPanel Background=new ImagePanel("imgsrc//Windom/dom3.jpg",0, 0, 1080, 720);
		if(dataPackage.choiceA.equals("sleep")) {
			Background=new ImagePanel("imgsrc//Windom/dom2.jpg",0, 0, 1080, 720);
		}else if(dataPackage.choiceA.equals("selfstudy")) {
			Background=new ImagePanel("imgsrc//Windom/dom1.jpg",0, 0, 1080, 720);
		}
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		
		
		
		
		/*****************************************************************				
		 * 
		 * 【细节设定】
		 * 在用插件绘制完界面之后进行界面内数值设定
		 * 利用数据包进行显示控件的输出的设置
		 * 
		 *****************************************************************/
		/*		START OF YOUR CODE		*/
		
		Bar_progress.setValue(dataPackage.studyProgress);
		Bar_Energy.setValue(dataPackage.characterEnergy);
		Bar_happiness.setValue(dataPackage.characterHappiness);
		healthBar.setValue(dataPackage.characterHealth);//进度条设置进度
		Bar_progress.setStringPainted(true);
		Bar_Energy.setStringPainted(true);//开启进度条显示字
		Bar_happiness.setStringPainted(true);
		healthBar.setStringPainted(true);
		Bar_progress.setString(String.format("%d",dataPackage.studyProgress));
		Bar_Energy.setString(String.format("%d",dataPackage.characterEnergy));
		Bar_happiness.setString(String.format("%d",dataPackage.characterHappiness));
		healthBar.setString(String.format("%d",dataPackage.characterHealth));//进度条显示字
		IDshow.setText(dataPackage.studentID);//显示学号
		nameShow.setText(dataPackage.name);//显示名字
		
		label_social.setText("社交能力:"+dataPackage.characterEQ);
		label_Art.setText("才艺能力:"+dataPackage.characterArt);
		label_IQ.setText("智商:"+dataPackage.characterIQ);
		label_lucky.setText("幸运值:"+dataPackage.characterlucky);

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		demoMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		demoMouseListener.mainGame=mainGame;
		
		

		demoMouseListener clicknext=new demoMouseListener(5);//设置鼠标监听器，发生5号事件   zwj
		demoMouseListener clickchayes=new demoMouseListener(6);//设置鼠标监听器，发生6号事件   zwj
		demoMouseListener clickchalno=new demoMouseListener(7);//设置鼠标监听器，发生7号事件   zwj
		demoMouseListener clickchalname=new demoMouseListener(8);
		demoMouseListener clicknotice=new demoMouseListener(9);
		
		clicknext.setButton(dialog_nextButton);
		clickchayes.setButton(chalyesButton);
		clickchalno.setButton(chalnoButton);
		clickchalname.setButton(chalnameButton);
		clicknotice.setButton(NoticeButton);
		
		dialog_nextButton.addMouseListener(clicknext);//5号事件是 对话继续按钮 被点击
		chalyesButton.addMouseListener(clickchayes);//6号事件是 叫醒舍友 被点击
		chalnoButton.addMouseListener(clickchalno);//7号事件是 叫醒舍友 被点击
		chalnameButton.addMouseListener(clickchalname);//8号事件是 叫醒舍友 被点击
		NoticeButton.addMouseListener(clicknotice);//9号事件是 叫醒舍友 被点击
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