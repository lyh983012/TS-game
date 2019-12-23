package THUgame.subwindows;

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
import javax.swing.border.LineBorder;

import THUgame.Game.ShootGame;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

import javax.swing.JTextField;
import javax.swing.SwingConstants;


/*【早课界面】
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


public class WinDabianClass extends WinBase{
		private JTextField dialogName;
		private JTextField dialogContent;
	
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
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
				if(mode==1) {
					dataPackage.count++;//点继续对话按钮,继续剧情   zwj
				}else if(mode==3){
					dataPackage.choiceA="back";
				}
				/*		END OF YOUR CODE		*/
				//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
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
	public WinDabianClass(EventManager mainGame,JFrame frame) {

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
		 * 【小事件，这里分成两类，一类是结束提示，一类是游戏】
		 * 	游戏比较特殊，相当于执行了按钮的事件后附加了一个事件
		 * 		用之前，把mainGame和datapackage传给游戏
		 * 		这样游戏才可以从内部控制外部窗口的刷新	
		 * 		使用游戏的人不需要关注游戏内部的实现，只需要传参数进去就可以；
		 * 
		 *************************************************************/
			
		ShootGame.mainGame=mainGame;//注意这里！不然没办法结束游戏！
		ShootGame.dataPackage=dataPackage;//注意这里！不然没办法结束游戏！
		JPanel gamePack = new ShootGame(254, 134);//将来可以用它来放临时小事件	
		gamePack.setBounds(254, 134, 536, 398);
		gamePack.setLayout(null);
		gamePack.setOpaque(false);//注意要设成透明的
		

		
		/*************************************************************	
		 * 【基本按钮】
		 *************************************************************/

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
		
			JPanel dialogPanel = new JPanel();
			dialogPanel.setBounds(0, 0, 689, 189);
			
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	
														//因为图片会遮住控件，所以另外加一个图层放背景
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setLayout(null);
			
			JButton backButton = new JButton();
			backButton.setBorderPainted(false);
			backButton.setBounds(800, 550, 150, 50);
			backButton.setContentAreaFilled(false);
			setIcon("/imgsrc/Challenge/back-up.png",backButton);
			setSelectedIcon("/imgsrc/Challenge/back-down.png",backButton);
			
			backgroundPanel.add(backButton);
			
			backButton.setVisible(false);
			
			
			JLabel dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogPanel.add(dialogName);
			
			if (!dataPackage.stateA.equals(""))//设置对话内容
				dialogName.setText(dataPackage.stateA);
			else
				dialogName.setText("独白");//设置默认对话内容
			
			JLabel dialogContent = new JLabel();
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(15, 42, 677, 141);
			dialogPanel.add(dialogContent);
			
			if (!dataPackage.notification.equals(""))//设置对话内容
				dialogContent.setText(dataPackage.notification);
			else
				dialogContent.setText("终于到了这一天，希望这一学期的努力能够让我取得一个好成绩吧");//设置默认对话内容
			
		
		dialogPack.add(dialogPanel);
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);
		
		JButton dialog_nextButton = new JButton();   //用于点击后继续对话//zwj
		dialog_nextButton.setIcon(new ImageIcon(WinDabianClass.class.getResource("/imgsrc/dialog_toNext.png")));
		dialog_nextButton.setBounds(0, 0, 689, 189);
		dialogPanel.add(dialog_nextButton);
		dialog_nextButton.setBorderPainted(false);
		dialog_nextButton.setContentAreaFilled(false);
		//setIcon("imgsrc//dialog_toNext.png",dialog_nextButton);
		dialog_nextButton.setVisible(true);
		
		if(dataPackage.count >= 7) {     
			backButton.setVisible(true);
			dialog_nextButton.setVisible(false);
		}
		
		/*************************************************************	
		 * 【放背景图】
		 *************************************************************/
		
		JPanel Background=new ImagePanel("imgsrc//Challenge/dabian2.png",0, 0, 1080, 720);
		if(dataPackage.count==4) {
			Background=new ImagePanel("imgsrc//Challenge/dabianxianchang.png",0, 0, 1080, 720);
		}
		Background.setBounds(0, 0, 1080, 720);
		backgroundPanel.add(Background);
		Background.setLayout(null);
		
		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		InClassMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		InClassMouseListener.mainGame=mainGame;
		
		InClassMouseListener clicknext=new InClassMouseListener(1);//设置鼠标监听器，发生1号事件
		InClassMouseListener clickback=new InClassMouseListener(3);//设置鼠标监听器，发生3号事件
		
		clickback.setButton(backButton);
		clicknext.setButton(dialog_nextButton);
		
		dialog_nextButton.addMouseListener(clicknext);//1号事件是 对话继续按钮 被点击	
		backButton.addMouseListener(clickback);//3号事件是 回宿舍按钮 被点击
			
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

