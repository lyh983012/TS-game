package THUgame.subwindows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import THUgame.windows.WinBase;

import javax.swing.SwingConstants;



public class WinEnd2050_1 extends WinBase{
	
	static File file;
	static JLabel lblNewLabel;
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	static private class welcomeMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public welcomeMouseListener(int i){
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
    		synchronized(mainGame) {
    			mainGame.notify();
    		}
    		return;
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
	public WinEnd2050_1(EventManager mainGame,JFrame frame)  {
		
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
		backgroundPanel.setOpaque(false);
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);

		JPanel backgroundImage=new ImagePanel("imgsrc//WinEnd2050/Sci1.png",0, 0, 1080, 720);
		JButton button= new JButton("翻看");	
		button.setBorderPainted(false);
		button.setFont(new Font("印品黑体", Font.PLAIN, 19));
		button.setForeground(Color.BLACK);
		button.setBounds(570, 380, 80, 80);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		setIcon("/imgsrc/WinSTA/2.png",button);
		setSelectedIcon("/imgsrc/WinSTA/2p.png",button);

		JPanel dialogPack = new JPanel();
		dialogPack.setBounds(20, 500, 689, 189);
		dialogPack.setOpaque(false);//注意要设成透明的
		dialogPack.setLayout(null);
		
			JPanel dialogPanel = new JPanel();//第1个subPanel，放控件
			dialogPanel.setBounds(0, 0, 700, 189);//(0, 0, 宽, 高);
			
			JPanel dialogBackgoundPanel = new ImagePanel("imgsrc//Dialog.png",0, 0, 689, 189);	//第2个subPanel，放图																	//(0, 0, 宽, 高);
			dialogBackgoundPanel.setBounds(0, 0, 689, 189);//(0, 0, 宽, 高);
			dialogBackgoundPanel.setOpaque(false);//注意要设成透明的
			dialogPanel.setOpaque(false);		//注意要设成透明的
			dialogPanel.setLayout(null);
			
			JLabel dialogName = new JLabel();
			dialogName.setBounds(17, 6, 89, 40);
			dialogName.setText("独白");
			
			JLabel dialogContent = new JLabel();
			dialogName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			dialogContent.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			dialogContent.setBounds(44, 42, 650, 141);

			JButton nextDialog = new JButton("");
			nextDialog.setBounds(600, 120, 40, 40);
			nextDialog.setBorderPainted(false);
			nextDialog.setContentAreaFilled(false);
			nextDialog.setHorizontalAlignment(SwingConstants.CENTER);
			setIcon("/imgsrc/WinSTA/ca.png",nextDialog);
			setSelectedIcon("/imgsrc/WinSTA/cb.png",nextDialog);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(60, 160, 300, 315);
		lblNewLabel_1.setFont(new Font("Songti SC", Font.PLAIN, 15));
		backgroundPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Songti TC", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(400, 160, 500, 250);
		backgroundPanel.add(lblNewLabel_2);

		dialogPanel.add(dialogName);
		dialogPanel.add(dialogContent);
		dialogPack.add(dialogPanel);		//注意：先放的在上层，所以先放带控件的
		dialogPack.add(dialogBackgoundPanel);
		backgroundPanel.add(dialogPack);
		if(dataPackage.count==1) {
			backgroundImage=new ImagePanel("imgsrc//WinEnd2050/Sci2.png",0, 0, 1080, 720);
			lblNewLabel_1.setText(dataPackage.stateA);
			lblNewLabel_2.setText(dataPackage.stateB);
			dialogContent.setText("<html>咦……原来我又上报纸了!<br><br> >>>>>游戏结束，恭喜你达成结局：【共和国的工程师】<<<<<<</html>");
		}
		else {
			backgroundPanel.add(button);
			dialogContent.setText("<html>啊，又是美好的一天……让我看看今天的报纸</html>");
		}
		backgroundImage.setBounds(0, 0, 1080, 720);
		backgroundImage.setOpaque(false);
		backgroundImage.setLayout(null);
		

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		welcomeMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		welcomeMouseListener.mainGame=mainGame;
		welcomeMouseListener click=new welcomeMouseListener(0);//设置鼠标监听器，发生0号事件
		click.setButton(button);
    	button.addMouseListener(click);//0号事件是 睡觉按钮 被点击

		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 【恢复显示】
		 * 必须存在的四行代码！！！！
		 ******************************************************************/
		//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().add(backgroundImage);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}

    

