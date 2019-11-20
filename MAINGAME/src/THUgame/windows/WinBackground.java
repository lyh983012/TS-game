package THUgame.windows;
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
import javax.swing.SwingConstants;
/* 
 * 显示游戏的文字背景
 * 
 * --DIALOG--
 * update：20191018 16:30
 * via 林逸晗
 * 更新；解决了按钮的问题，更新了GUI
 * 
 * version 1.0
 * via 黄天翼
 * update:20191018 00:59
 * 
 **/


public class WinBackground extends WinBase{
	
	/*************************************************************	
	 *
	 *【内部的事件响应类】
	 * 		这里只写了鼠标的动作，如果之后熟悉了可以加入键盘的操作
	 * 		写成类内部的类是为了防止别的分支事件访问到它，所以可以乱命名
	 * 		所有必要实现的接口都实现了。
	 * 
	 *************************************************************/
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
	public WinBackground(EventManager mainGame,JFrame frame) {
		
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
		
		//panelbutton.add(Text);

		JLabel textLabel = new JLabel();
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setText("<html>这样会自动换行</html>");
		textLabel.setOpaque(false);
		textLabel.setFont(new Font("印品黑体", Font.PLAIN, 30));
		textLabel.setBounds(80, 60, 948, 460);
		
		backgroundPanel.add(button);
		backgroundPanel.add(textLabel);
		backgroundPanel.add(backgroundImage);
		
		switch(dataPackage.count) {
		case 0:
			String sex;
			if(dataPackage.sex.equals("others"))
				sex="***(性别保密)";
			else
				sex=dataPackage.sex;
			textLabel.setText("<html>9102年，曾经的学习之神"+dataPackage.name+"，一个来自华国安徽南京的"+sex+"，因为天天玩一款叫做MineCraft的游戏，连挂30学分，从北方大学退学。前往该省最顶尖的中学复读。拿到全省第五的高考成绩，毅然决然选择了上个学校的隔壁，因此当地报纸授其以“北大得不到的学生”的荣誉称号。</html>");
			break;
		case 1:
			textLabel.setText("<html>带着珍贵的录取通知书，你来到了华清大学。<br> 这里是国内高中生梦想中的圣地之一，与“隔壁”北方大学共称为两大顶尖高校。<br>告别了过往的传奇经历，你的大学生活即将开始...");
			break;
		case 2:
			String dom;
			if(dataPackage.sex.equals("others"))
				dom="因为你对自己的性别认知十分特殊，无奈之下，学校根据你的生理性别把你分配到了一个男生宿舍。出人意料的，你的舍友都开心地接纳了你。但这是不是好事呢？";
			else if(dataPackage.sex=="girl")
				dom="尽管你是个女生，但是这所学校为了争当世界一流高校，竟然在你入学前一年拆掉了女生宿舍进行重建！虽然大家已经习惯了男女混宿，但你为了和舍友能够正常相处，还是决定女扮男装进入了宿舍。";
			else 
				dom="作为一个男生，你的同性在这所学校占据了66.66%的相对多数，因此你也顺利地找到了很多的同性好朋友。怀揣着激动的心情，你来到了宿舍。";
			textLabel.setText("<html>"+dom+"</html>");
			break;

		}
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