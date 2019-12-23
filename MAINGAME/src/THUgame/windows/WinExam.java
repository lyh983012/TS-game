package THUgame.windows;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.LineBorder;
import THUgame.Game.ShootGame;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.main.WindowManager;
import THUgame.tool.ImagePanel;
import javax.swing.JTextField;
import THUgame.finalbird.ExamPanel;
import THUgame.finalbird.Keyboard;


/*【下午课界面】
 * 
 * --DIALOG--
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * update:20191028 01:07
 * via：林逸晗
 * 更新：更改了界面UI，使之适配MAP
 * 
 * update:20191018 01:07
 * via：林逸晗
 * 更新：加入了游戏
 * 
 **/


public class WinExam extends WinBase{
		private JTextField dialogName;
		private JTextField dialogContent;
	    public static int WIDTH = 1080;
	    public static int HEIGHT = 720;
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	/*public static void main(String[] args) {
		DataPack dataPackage=new DataPack("test");
	    EventManager.dataPackage=dataPackage;
	    WindowManager.dataPackage=dataPackage;
	    EventManager mainGame;
		WindowManager mainGUI;
	    mainGame = new EventManager();
	    mainGUI = new WindowManager();
	    mainGame.set(mainGUI);
		mainGUI.set(mainGame);
		JFrame mainFrame=new JFrame();
		mainFrame.setBounds(0, 0, 1080, 720);
		mainFrame.setResizable(false);
		WinExam news= new WinExam(mainGame,mainFrame);
	}
	*/
	static private class InClassMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public InClassMouseListener(int i){
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
			if(mode ==8){
				if(showToDoList) {
					showToDoList=false;
					ImageIcon ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/seemore1.png")); 
			        ico.getImage();
					Image temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setIcon(ico); 
			        ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/seemore2.png")); 
			        ico.getImage();
					temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setPressedIcon(ico); 
				}else {
					showToDoList=true;
					ImageIcon ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/pack1.png")); 
			        ico.getImage();
					Image temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setIcon(ico); 
			        ico=new ImageIcon(getClass().getResource("/imgsrc/Windom/pack2.png")); 
			        ico.getImage();
					temp=ico.getImage().getScaledInstance(button.getWidth(),button.getHeight(),Image.SCALE_DEFAULT);
			        ico=new ImageIcon(temp); 
			        button.setPressedIcon(ico); 
				}
				todolistPanel.setVisible(showToDoList);//TODO:不知道为什么收不起来
				return;
			}
			
			if(safeGuardCount==0) {
				safeGuardCount++;
				if(mode==0) {
					dataPackage.choiceA="answer";
				}else if(mode==1){
					dataPackage.choiceA="ask";
				}else if(mode==2){
					dataPackage.choiceA="next";
				}else if(mode==3){
					dataPackage.choiceA="back";
				}
				/*		END OF YOUR CODE		*/
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
				EventManager.dataPackage=dataPackage;
				synchronized(mainGame) {
					mainGame.notify();
				}
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
	public WinExam(EventManager mainGame,JFrame frame) {

		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.mainGame=mainGame;
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 *************************************************************/
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
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
		 * 【镶对话框】
		 * 		建立一个带背景的Panel的流程设setBounds(x, y, 宽, 高);
		 *  	1.建一个Panel	
		 * 		2.Panel里建两个subPanel，全部都设成setBounds(0, 0, 宽, 高);
		 * 		3.底下的用imagePanel工具类放图片，上面的放控件
		 * 		4.设置两个Panel为透明这一部分按照流程做的话就会自然消失的
		 *************************************************************/
		
		/*************************************************************	
		 * 小事件
		 *************************************************************/

		//dialogContent.setText(dataPackage.notification);
	    Keyboard keyboard = Keyboard.getInstance();
	    ExamPanel gamePanel = new ExamPanel(mainGame,dataPackage);
	    gamePanel.addKeyListener(keyboard);
	    gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
	    backgroundPanel.add(gamePanel);
	    
		/*****************************************************************				
		 * 
		 * 1.在用插件绘制完界面之后进行界面内数值设定
		 * 利用数据包进行显示控件的输出的设置
		 * 
		 * 2.添加监听器以响应某些动作
		 * 监听器有模板类，见本类的开头的那个内部类
		 * 
		 *****************************************************************/
		
		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		InClassMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		InClassMouseListener.mainGame=mainGame;
		

		/*		END OF YOUR CODE		*
		 * 	-
		 */
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


