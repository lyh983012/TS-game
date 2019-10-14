package THUgame.windows;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
<<<<<<< HEAD
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase.BaseMouseListener;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
=======
import javax.swing.JButton;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
>>>>>>> linyh_test


/*
 * template version 1.0
 * 多次多重选择可视化模板
 * update:20191009 20:28
 * 基于其它模板改写，与多次多重选择事件模板一起使用

 * 
 * 	推荐看WinInDom.java的注释
 * 
 **/
	/*************************************************************	
	 *
	 * 推荐直接复制粘贴pureDmoe使用，直接使用JFrame生成的窗口不太符合我们的需求
	 * 界面的实现可以参考这个文件
	 * 这个文件是可运行的，可以直接使用它进行原型开发
	 * 推荐看WinInD.java的注释
	 * 
	 *************************************************************/



public class WinHome extends WinBase{
		
		
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
				}//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				
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
	public WinHome(EventManager mainGame,JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 * 最后放一下背景
		 *************************************************************/
<<<<<<< HEAD
		JPanel backgroundPanel=new ImagePanel("imgsrc//WinHome/home.jpg",0, 0, 1080, 720);
		
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
=======
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		backgroundPanel.setOpaque(false);
		
		JPanel backgroundPanel1=new JPanel();
		backgroundPanel1.setBorder(null);
		JPanel backgroundPanel2=new ImagePanel("imgsrc//WinHome/home.jpg",0, 0, 1080, 720);
		backgroundPanel1.setBounds(0, 0, 1080, 720);
		backgroundPanel2.setBounds(0, 0, 1080, 720);
		backgroundPanel1.setLayout(null);
		backgroundPanel2.setLayout(null);
		backgroundPanel1.setOpaque(false);
		backgroundPanel2.setOpaque(false);
>>>>>>> linyh_test
		
		JPanel panela = new JPanel();
		panela.setBackground(Color.LIGHT_GRAY);
		panela.setBounds(146, 485, 194, 60);
<<<<<<< HEAD
		backgroundPanel.add(panela);
=======
		backgroundPanel1.add(panela);
>>>>>>> linyh_test
		panela.setLayout(null);
		
		JButton choicea = new JButton("A");
		choicea.setBounds(24, 5, 141, 49);
		panela.add(choicea);
		
		JPanel panelb = new JPanel();
		panelb.setBackground(Color.LIGHT_GRAY);
		panelb.setBounds(462, 485, 194, 60);
<<<<<<< HEAD
		backgroundPanel.add(panelb);
=======
		backgroundPanel1.add(panelb);
>>>>>>> linyh_test
		panelb.setLayout(null);
		
		JButton choiceb = new JButton("B");
		choiceb.setBounds(24, 5, 141, 49);
		panelb.add(choiceb);
		
		JPanel panelc = new JPanel();
		panelc.setBackground(Color.LIGHT_GRAY);
		panelc.setBounds(762, 485, 194, 60);
<<<<<<< HEAD
		backgroundPanel.add(panelc);
=======
		backgroundPanel1.add(panelc);
>>>>>>> linyh_test
		panelc.setLayout(null);
		
		JButton choicec = new JButton("C");
		choicec.setBounds(24, 5, 141, 49);
		panelc.add(choicec);
		
		JPanel paneld = new JPanel();
		paneld.setBackground(Color.LIGHT_GRAY);
		paneld.setBounds(146, 592, 194, 60);
<<<<<<< HEAD
		backgroundPanel.add(paneld);
=======
		backgroundPanel1.add(paneld);
>>>>>>> linyh_test
		paneld.setLayout(null);
		
		JButton choiced = new JButton("D");
		choiced.setBounds(24, 5, 141, 49);
		paneld.add(choiced);
		
		JPanel panele = new JPanel();
		panele.setBackground(Color.LIGHT_GRAY);
		panele.setBounds(762, 592, 194, 60);
<<<<<<< HEAD
		backgroundPanel.add(panele);
=======
		backgroundPanel1.add(panele);
>>>>>>> linyh_test
		panele.setLayout(null);
		
		JButton choicee = new JButton("E");
		choicee.setBounds(24, 5, 141, 49);
		panele.add(choicee);
		

		paneld.setVisible(false);
		panele.setVisible(false);
		choicea.setText("新游戏");
		choiceb.setText("继续游戏");
		choicec.setText("帮助");


		
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
		
		clicka.setButton(choicea);
		clickb.setButton(choiceb);
		clickc.setButton(choicec);
		clickd.setButton(choiced);
		clicke.setButton(choicee);
		
		choicea.addMouseListener(clicka);//0号事件是 a 被点击
    	choiceb.addMouseListener(clickb);//1号事件是 b 被点击
    	choicec.addMouseListener(clickc);//2号事件是 c 被点击
    	choiced.addMouseListener(clickd);//3号事件是 d 被点击
    	choicee.addMouseListener(clicke);//4号事件是 e 被点击
		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 恢复显示，按照流程做就不会消失，之后四行不允许更改
		 ******************************************************************/
		
		
<<<<<<< HEAD

=======
    	backgroundPanel.add(backgroundPanel1);
    	backgroundPanel.add(backgroundPanel2);
>>>>>>> linyh_test
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
		frame.setVisible(true);
	}

}
