package THUgame.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.border.LineBorder;

import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/*
 * 
 * update:20191114
 * via：林逸晗
 * 更新：加入读存档
 * 
 * */


public class WinSaveAndLoad extends WinBase{
	
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
			if(file==null) {
				dataPackage.choiceA=""; 
    			synchronized(mainGame) {
    				mainGame.notify();
    			}
    			return;
			}else{
            	String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            	if(suffix.equals("thu")) {
            		dataPackage.choiceA=file.getPath();
        			synchronized(mainGame) {
        				mainGame.notify();
        			}
            	}else{
            		System.out.println(suffix);
            		JOptionPane.showMessageDialog(null, "文件格式不对？", "oops",JOptionPane.WARNING_MESSAGE);  
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
	public WinSaveAndLoad(EventManager mainGame,JFrame frame)  {
		
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
		
		JButton button= new JButton();	
		button.setBorderPainted(false);
		button.setFont(new Font("印品黑体", Font.PLAIN, 19));
		button.setForeground(Color.BLACK);
		button.setBounds(850, 550, 80, 80);
		button.setContentAreaFilled(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		
		setIcon("/imgsrc/WinBackground/ca.png",button);
		setSelectedIcon("/imgsrc/WinBackground/cb.png",button);
		
		JLabel title = new JLabel("请选择一个合法的thu后缀名的存档文件:");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		title.setBounds(360, 200, 500, 30);
		backgroundPanel.add(title);
		
		lblNewLabel = new JLabel("（点击箭头返回）你选择了: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(340, 268, 500, 30);
		backgroundPanel.add(lblNewLabel);
		
		JButton open=new JButton("选择存档");  
		open.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		open.setBounds(420, 320, 300, 100);  
		open.setVisible(true);   
        open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                JFileChooser jfc=new JFileChooser();  
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  
                jfc.setCurrentDirectory(new File(new File(this.getClass().getResource("").getPath()).getParentFile().getParentFile().getParentFile().getPath()));
                jfc.showDialog(new JLabel(), "选择");  
                file=jfc.getSelectedFile();
                if(file==null)
                	return;
                lblNewLabel.setText("（点击箭头开始）你选择了："+file.getName());
            }  
        });  
        
		JButton close=new JButton("取消选择");  
		close.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		close.setBounds(420, 450, 300, 100);  
		close.setVisible(true);   
        close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                file=null;
                lblNewLabel.setText("（点击箭头返回）你选择了：  ");
            }  
        });  
        backgroundPanel.add(open);  
		backgroundPanel.add(close);  
		backgroundPanel.add(button);  
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

    

