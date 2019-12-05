package THUgame.windows;

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
 * 多次多重选择可视化模板
 * 
 * --DIALOG--
 * 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * update:20191018 16:20
 * via 林逸晗
 * 更新：解决了按钮的动态显示的问题，基本可以算最终版本
 * 
 * version 1.0
 * via 黄天翼、江世航
 * update:20191018 20:28
 * 
 **/


/*************************************************************	
 *简介
 * 基于其它模板改写，与多次多重选择事件模板一起使用
 * 
 *************************************************************/

public class WinChoice extends WinBase{
	

		
		
		/*************************************************************	
		 *
		 *【内部的事件响应类】
		 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
		 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
		 * 所有必要实现的接口都实现了。
		 * 
		 *************************************************************/
		static private class WinChoiceMouseListener extends BaseMouseListener{
			static public DataPack dataPackage;
			static public EventManager mainGame;
			private JFrame frame;
			private JButton button;
			private int mode;
			
			public WinChoiceMouseListener(int i){
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
						dataPackage.choiceA="A";
					}else if(mode==1){
						dataPackage.choiceA="B";
					}else if(mode==2){
						dataPackage.choiceA="C";
					}else if(mode==3){
						dataPackage.choiceA="D";
					}else if(mode==4){
						dataPackage.choiceA="E";
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
	public WinChoice(EventManager mainGame,JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinBackground/Welcome.png",0, 0, 1080, 720);
		backgroundImage.setBounds(0, 0, 1080, 720);
		backgroundImage.setOpaque(false);
		backgroundImage.setLayout(null);
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
		
		JPanel questionPanel = new JPanel();
		questionPanel.setBounds(145,30,800,280);
		questionPanel.setOpaque(false);
		questionPanel.setLayout(null);
		
		JPanel questionText = new JPanel();
		questionText.setBounds(0, 0, 800, 280);
		questionText.setOpaque(false);
		questionText.setLayout(null);
		
		
		JPanel questionImage = new ImagePanel("imgsrc//WinChoice/Question.png",0, 0, 800, 280);	
		questionImage.setOpaque(false);
		questionImage.setBounds(0, 0, 800, 280);
		questionImage.setOpaque(false);//注意要设成透明的
		
		JLabel textLast = new JLabel();
		textLast.setHorizontalAlignment(SwingConstants.CENTER);
		textLast.setText("<html>这样会自动换行</html>");
		textLast.setOpaque(false);
		textLast.setFont(new Font("印品黑体", Font.BOLD, 19));
		textLast.setBounds(0, 5, 800, 60);
		
		
		JLabel textThis = new JLabel();
		textThis.setVerticalAlignment(SwingConstants.CENTER);
		textThis.setHorizontalAlignment(SwingConstants.CENTER);
		textThis.setText("<html>这样会自动换行</html>");
		textThis.setOpaque(false);
		textThis.setFont(new Font("印品黑体", Font.BOLD, 19));
		textThis.setBounds(10, 80, 780, 180);
		

		questionText.add(textLast);
		questionText.add(textThis);	
		questionPanel.add(questionText);
		questionPanel.add(questionImage);
		backgroundPanel.add(questionPanel);
		//A选项按钮
		JPanel panelA = new JPanel();
		panelA.setLayout(null);
		panelA.setOpaque(false);
		panelA.setBounds(145, 320, 810, 60);
		panelA.setLayout(null);
	
		JLabel choiceAText = new JLabel("E");
		choiceAText.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceAText.setForeground(Color.BLACK);
		choiceAText.setBounds(0, 0, 805, 60);
		choiceAText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton choiceAButton= new JButton();	
		choiceAButton.setBorderPainted(false);
		choiceAButton.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceAButton.setForeground(Color.BLACK);
		choiceAButton.setBounds(0, 0, 805, 60);
		choiceAButton.setContentAreaFilled(false);
		choiceAButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelA.add(choiceAText);
		panelA.add(choiceAButton);
		backgroundPanel.add(panelA);
		
		//B选项按钮
		JPanel panelB = new JPanel();
		panelB.setLayout(null);
		panelB.setOpaque(false);
		panelB.setBounds(145, 390, 810, 60);
		panelB.setLayout(null);
	
		JLabel choiceBText = new JLabel("E");
		choiceBText.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceBText.setForeground(Color.BLACK);
		choiceBText.setBounds(0, 0, 805, 60);
		choiceBText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton choiceBButton= new JButton();	
		choiceBButton.setBorderPainted(false);
		choiceBButton.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceBButton.setForeground(Color.BLACK);
		choiceBButton.setBounds(0, 0, 805, 60);
		choiceBButton.setContentAreaFilled(false);
		choiceBButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelB.add(choiceBText);
		panelB.add(choiceBButton);
		backgroundPanel.add(panelB);
		
		//C选项按钮
		JPanel panelC = new JPanel();
		panelC.setLayout(null);
		panelC.setOpaque(false);
		panelC.setBounds(145, 460, 810, 60);
		panelC.setLayout(null);
	
		JLabel choiceCText = new JLabel("E");
		choiceCText.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceCText.setForeground(Color.BLACK);
		choiceCText.setBounds(0, 0, 805, 60);
		choiceCText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton choiceCButton= new JButton();	
		choiceCButton.setBorderPainted(false);
		choiceCButton.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceCButton.setForeground(Color.BLACK);
		choiceCButton.setBounds(0, 0, 805, 60);
		choiceCButton.setContentAreaFilled(false);
		choiceCButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelC.add(choiceCText);
		panelC.add(choiceCButton);
		backgroundPanel.add(panelC);
		
		
		//E选项按钮
		JPanel panelD = new JPanel();
		panelD.setLayout(null);
		panelD.setOpaque(false);
		panelD.setBounds(145, 530, 810, 60);
		panelD.setLayout(null);
	
		JLabel choiceDText = new JLabel("E");
		choiceDText.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceDText.setForeground(Color.BLACK);
		choiceDText.setBounds(0, 0, 805, 60);
		choiceDText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton choiceDButton= new JButton();	
		choiceDButton.setBorderPainted(false);
		choiceDButton.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceDButton.setForeground(Color.BLACK);
		choiceDButton.setBounds(0, 0, 805, 60);
		choiceDButton.setContentAreaFilled(false);
		choiceDButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelD.add(choiceDText);
		panelD.add(choiceDButton);
		backgroundPanel.add(panelD);
		
		//E选项按钮
		JPanel panelE = new JPanel();
		panelE.setLayout(null);
		panelE.setOpaque(false);
		panelE.setBounds(145, 600, 810, 60);
		panelE.setLayout(null);
		
		JLabel choiceEText = new JLabel("E");
		choiceEText.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceEText.setForeground(Color.BLACK);
		choiceEText.setBounds(0, 0, 805, 60);
		choiceEText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton choiceEButton= new JButton();	
		choiceEButton.setBorderPainted(false);
		choiceEButton.setFont(new Font("印品黑体", Font.BOLD, 19));
		choiceEButton.setForeground(Color.BLACK);
		choiceEButton.setBounds(0, 0, 805, 60);
		choiceEButton.setContentAreaFilled(false);
		choiceEButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelE.add(choiceEText);
		panelE.add(choiceEButton);
		backgroundPanel.add(panelE);
		
		setIcon("/imgsrc/WinChoice/ChoiceText.png",choiceAButton);
		setSelectedIcon("/imgsrc/WinChoice/ChoiceText2.png",choiceAButton);
		setIcon("/imgsrc/WinChoice/ChoiceText.png",choiceBButton);
		setSelectedIcon("/imgsrc/WinChoice/ChoiceText2.png",choiceBButton);
		setIcon("/imgsrc/WinChoice/ChoiceText.png",choiceCButton);
		setSelectedIcon("/imgsrc/WinChoice/ChoiceText2.png",choiceCButton);
		setIcon("/imgsrc/WinChoice/ChoiceText.png",choiceDButton);
		setSelectedIcon("/imgsrc/WinChoice/ChoiceText2.png",choiceDButton);
		setIcon("/imgsrc/WinChoice/ChoiceText.png",choiceEButton);
		setSelectedIcon("/imgsrc/WinChoice/ChoiceText2.png",choiceEButton);
		
		//设置上方的文字，主要是对前一个回答的响应
		switch(dataPackage.count) {
		case 0:
			textLast.setText("在此之前，请你说说你是怎样一个人吧~");
			break;
		case 1:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("健康生活，从我做起！");
				break;
			case "B":
				textLast.setText("略有些肝呢...");
				break;
			case "C":
				textLast.setText("我头发呢？？刚刚还在这里的！");
				break;	
			}
			break;
		case 2:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("华清的月色很美哦~");
				break;
			case "B":
				textLast.setText("有机会一定要见识一下。");
				break;
			}
			break;
		case 3:
			textLast.setText("开始怀念起那些奋斗的日子了...");
			break;	
		case 4:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("要深造吗？算了吧。");
				break;
			case "B":
				textLast.setText("读个硕士挺好的。");
				break;
			case "C":
				textLast.setText("我要读博！");
				break;	
			}
			break;
		case 5:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("生命在于运动！");
				break;
			case "B":
				textLast.setText("游戏是我的最爱！");
				break;
			case "C":
				textLast.setText("可以做些有意思的实验啦！");
				break;	
			case "D":
				textLast.setText("赠人玫瑰，手有余香。");
				break;	
			}
			break;
		case 6:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("做不完啦");
				break;
			case "B":
				textLast.setText("可恶！这把队友太菜了！");
				break;
			case "C":
				textLast.setText("床在召唤我...呜呜...");
				break;	
			case "D":
				textLast.setText("什么时候才开完啊...");
				break;	
			}
			break;
		case 7:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("夜深人静最适合读论文啦~");
				break;
			case "B":
				textLast.setText("再赢一把我就睡觉！");
				break;
			case "C":
				textLast.setText("困困困");
				break;	
			case "D":
				textLast.setText("还有几题！尽管来！");
				break;	
			}
			break;
		case 8:
			switch(dataPackage.choiceA) {
			case "A":
				textLast.setText("急着用呢，看看店里怎么说。");
				break;
			case "B":
				textLast.setText("唉，这电脑也该换了");
				break;
			case "C":
				textLast.setText("既然会修，那就自己修吧。");
				break;	
			}
			break;
		case 9:
			textLast.setText("清华这么好，来报这里吧~");
			break;
		}
		switch(dataPackage.count) {//设置问题和选项文字
		case 0:
			panelD.setVisible(false);
			panelE.setVisible(false);
			textThis.setText("你在高中的时候睡觉时间大致是？");
			choiceAText.setText("12点之前");
			choiceBText.setText("0am-1am");
			choiceCText.setText("1点以后");
			break;
		case 1:
			panelC.setVisible(false);
			panelD.setVisible(false);
			panelE.setVisible(false);
			textThis.setText("你是否见过凌晨4点（除了拉练）的北京？");
			choiceAText.setText("是");
			choiceBText.setText("否");
			break;
		case 2:
			panelE.setVisible(false);
			textThis.setText("你最自豪于高中取得的哪方面成就？");
			choiceAText.setText("高考成绩");
			choiceBText.setText("社工志愿");
			choiceCText.setText("竞赛科创");
			choiceDText.setText("体育体质");
			break;
		case 3:
			panelD.setVisible(false);
			panelE.setVisible(false);
			textThis.setText("你对本科阶段结束后的打算？");
			choiceAText.setText("创业或工作");
			choiceBText.setText("读硕");
			choiceCText.setText("读博");
			break;
		case 4:
			panelE.setVisible(false);
			textThis.setText("你最喜欢以下哪个场景？");
			choiceAText.setText("紫操奔跑");
			choiceBText.setText("游戏界面");
			choiceCText.setText("实验室设备");
			choiceDText.setText("志愿者");
			break;
		case 5:
			panelE.setVisible(false);
			textThis.setText("哪一种场景让你很痛苦？");
			choiceAText.setText("作业堆积");
			choiceBText.setText("游戏掉段");
			choiceCText.setText("凌晨2点的实验室");
			choiceDText.setText("开会");
			break;
		case 6:
			panelE.setVisible(false);
			textThis.setText("熄灯之后，你更愿意做什么？");
			choiceAText.setText("读论文");
			choiceBText.setText("上分");
			choiceCText.setText("马上睡觉");
			choiceDText.setText("做没做完的作业");
			break;
		case 7:
			panelD.setVisible(false);
			panelE.setVisible(false);
			textThis.setText("买了两年多的电脑出了问题，你会？");
			choiceAText.setText("赶紧送去店里修好");
			choiceBText.setText("重新购买是最方便的选择");
			choiceCText.setText("自己买零件修修补补");
			break;
		case 8:
			textThis.setText("回母校招生，你会如何宣传清华？");
			choiceAText.setText("清华大学学术水平高，科研经费多");
			choiceBText.setText("清华毕业生收入高");
			choiceCText.setText("清华平台广，可以为社会贡献自己的力量");
			choiceDText.setText("与应届生一起玩主机游戏");
			choiceEText.setText("清华大学体育健将多，学生体质好");
			break;
		case 9:
			panelE.setVisible(false);
			textThis.setText("你对大学生活的期望？");
			choiceAText.setText("专心学习，GPA第一！");
			choiceBText.setText("老师说上了大学就可以随便玩游戏啦！");
			choiceCText.setText("搞科研，发paper！");
			choiceDText.setText("听说社团和学生组织很有意思！");
		}
		
		WinChoiceMouseListener.dataPackage=dataPackage;
		WinChoiceMouseListener.mainGame=mainGame;
		
		/*		START OF YOUR CODE		*/
		/*****			
		 * 重要，事件监听器一定要把mainGame传入
		 ****/
		WinChoiceMouseListener clicka=new WinChoiceMouseListener(0);//设置鼠标监听器，发生0号事件
		WinChoiceMouseListener clickb=new WinChoiceMouseListener(1);//设置鼠标监听器，发生1号事件
		WinChoiceMouseListener clickc=new WinChoiceMouseListener(2);//设置鼠标监听器，发生2号事件
		WinChoiceMouseListener clickd=new WinChoiceMouseListener(3);//设置鼠标监听器，发生3号事件
		WinChoiceMouseListener clicke =new WinChoiceMouseListener(4);//设置鼠标监听器，发生4号事件
		clicka.setButton(choiceAButton);
		clickb.setButton(choiceAButton);
		clickc.setButton(choiceAButton);
		clickd.setButton(choiceAButton);
		clicke.setButton(choiceEButton);
		choiceAButton.addMouseListener(clicka);//0号事件是 a 被点击
		choiceBButton.addMouseListener(clickb);//1号事件是 b 被点击
		choiceCButton.addMouseListener(clickc);//2号事件是 c 被点击
		choiceDButton.addMouseListener(clickd);//3号事件是 d 被点击
		choiceEButton.addMouseListener(clicke);//4号事件是 e 被点击

		

		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 恢复显示，按照流程做就不会消失，之后四行不允许更改
		 ******************************************************************/ 
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().add(backgroundImage);
		frame.getContentPane().repaint();
		frame.setVisible(true);
	}
}


