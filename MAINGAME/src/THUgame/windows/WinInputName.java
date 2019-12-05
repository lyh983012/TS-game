package THUgame.windows;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
/* 
/*
 * 
 * update:20191114
 * via：林逸晗
 * 更新：加入用户的信息输入
 * 
 * */



public class WinInputName extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
	static JFormattedTextField formattedTextField;
	static JRadioButton boy;
	static JRadioButton girl;
	static JRadioButton other;

	static private class backgroundMouseListener extends BaseMouseListener{
		static public DataPack dataPackage;
		static public EventManager mainGame;
		private JFrame frame;
		private JButton button;
		private int mode;
		
		public backgroundMouseListener(int i){
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
			/*		END OF YOUR CODE		*/
			//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥要刷新事件这部分一定要加¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
			if(safeGuardCount==0) {
				String name=formattedTextField.getText();
				int isBoy=0;
				if(boy.isSelected())
					isBoy=1; 
				int isGirl=0;
				if(girl.isSelected())
					isGirl=1; 
				int isOthers=0;
				if(other.isSelected())
					isOthers=1;
				
				if(isBoy+isGirl+isOthers>1) {
					JOptionPane.showMessageDialog(null, "你的性别太复杂了", "oops",JOptionPane.WARNING_MESSAGE);  
					return;
				}
				if(isBoy+isGirl+isOthers==0) {
					JOptionPane.showMessageDialog(null, "还是要选一个选项哦", "oops",JOptionPane.WARNING_MESSAGE);  
					return;
				}
				if(name.length()>5 || name.length()<1) {
					JOptionPane.showMessageDialog(null, "名字字数有问题哦", "oops",JOptionPane.WARNING_MESSAGE);  
					return;
				}
				if(isBoy+isGirl+isOthers==1) {
					safeGuardCount++;
					dataPackage.name=name;
					if(isBoy==1)
						dataPackage.sex="boy";
					if(isGirl==1)
						dataPackage.sex="girl";
					if(isOthers==1)
						dataPackage.sex="others";
					synchronized(mainGame) {
						mainGame.notify();
					}
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
	public WinInputName(EventManager mainGame,JFrame frame) {
		
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
		
		JPanel backgroundImage=new ImagePanel("imgsrc//WinBackground/Welcome.png",0, 0, 1080, 720);
		backgroundImage.setBounds(0, 0, 1080, 720);
		backgroundImage.setOpaque(false);
		backgroundImage.setLayout(null);
		
		JLabel namelabel = new JLabel("在这输入你的名字：");
		namelabel.setFont(new Font("STIXGeneral", Font.BOLD, 20));
		namelabel.setBounds(300, 200, 200, 50);
		backgroundPanel.add(namelabel);
		
		JLabel sexlabel = new JLabel("选择你的性别：");
		sexlabel.setFont(new Font("STIXGeneral", Font.BOLD, 20));
		sexlabel.setBounds(300, 350, 200, 50);
		backgroundPanel.add(sexlabel);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		formattedTextField.setText("建议在4个字符以内");
		formattedTextField.setBounds(500, 200, 183, 50);
		backgroundPanel.add(formattedTextField);
		
	    boy = new JRadioButton("男生");
		boy.setFont(new Font("STIXGeneral", Font.BOLD, 20));
		boy.setBounds(500, 300, 141, 50);
		backgroundPanel.add(boy);
		
		girl = new JRadioButton("女生");
		girl.setFont(new Font("STIXGeneral", Font.BOLD, 20));
		girl.setBounds(500, 350, 141, 50);
		backgroundPanel.add(girl);
		
		other = new JRadioButton("其他");
		other.setFont(new Font("STIXGeneral", Font.BOLD, 20));
		other.setBounds(500, 400, 141, 50);
		backgroundPanel.add(other);
		
		JButton button= new JButton();	
		button.setBorderPainted(false);
		button.setFont(new Font("印品黑体", Font.BOLD, 19));
		button.setForeground(Color.BLACK);
		button.setBounds(850, 550, 80, 80);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		
		setIcon("/imgsrc/WinBackground/ca.png",button);
		setSelectedIcon("/imgsrc/WinBackground/cb.png",button);
		
		backgroundPanel.add(button);
		backgroundPanel.add(backgroundImage);

		/*********************************************			
		 * 【鼠标动作的设置】
		 ********************************************/
		backgroundMouseListener.dataPackage=dataPackage;//数据包注册，不需要改
		backgroundMouseListener.mainGame=mainGame;
		backgroundMouseListener click=new backgroundMouseListener(0);//设置鼠标监听器，发生0号事件

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
		frame.getContentPane().repaint();
    	frame.setVisible(true);
    	//¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥这部分不允许改¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥
	}
}