package THUgame.windows;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import THUgame.datapack.DataPack;
import THUgame.main.EventManager;
import THUgame.tool.ImagePanel;


/* 
 * 初始界面
 * 
 * --DIALOG--
 * 
 * update:20191030
 * via：林逸晗
 * 更新：加入safeGuardCount
 * 
 * version 1.0
 * via 黄天翼、江世航
 * update:20191018 01:03
 * 
 **/
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
		JPanel backgroundPanel=new JPanel();
		backgroundPanel.setBounds(0, 0, 1080, 720);
		backgroundPanel.setLayout(null);
		backgroundPanel.setOpaque(false);
		
		JPanel backgroundPanel1=new JPanel();
		backgroundPanel1.setBorder(null);
		JPanel backgroundPanel2=new ImagePanel("imgsrc//WinHome/home.png",0, 0, 1080, 720);
		backgroundPanel1.setBounds(0, 0, 1080, 720);
		backgroundPanel2.setBounds(0, 0, 1080, 720);
		backgroundPanel1.setLayout(null);
		backgroundPanel2.setLayout(null);
		backgroundPanel1.setOpaque(false);
		backgroundPanel2.setOpaque(false);
		
		JPanel panela = new JPanel();
		panela.setBackground(null);
		panela.setBounds(703, 279, 249, 87);
		panela.setOpaque(false);
		panela.setLayout(null);
		
		JButton choicea = new JButton("A");
		choicea.setBounds(0, 0, 249, 87);
		choicea.setBorderPainted(false);
		choicea.setOpaque(false);
		choicea.setLayout(null);
		choicea.setContentAreaFilled(false);
		panela.add(choicea);
		backgroundPanel1.add(panela);
		//
		JPanel panelb = new JPanel();
		panelb.setBackground(null);
		panelb.setBounds(639, 402, 377, 87);
		panelb.setOpaque(false);
		panelb.setLayout(null);
		
		JButton choiceb = new JButton("B");
		choiceb.setBounds(0, 0, 377, 87);
		choiceb.setBorderPainted(false);
		choiceb.setOpaque(false);
		choiceb.setLayout(null);
		choiceb.setContentAreaFilled(false);
		panelb.add(choiceb);
		backgroundPanel1.add(panelb);
		
		JPanel panelc = new JPanel();
		panelc.setBackground(null);
		panelc.setBounds(748, 524, 162, 87);
		panelc.setOpaque(false);
		panelc.setLayout(null);
		
		JButton choicec = new JButton("C");
		choicec.setBounds(0, 0, 162, 87);
		choicec.setBorderPainted(false);
		choicec.setOpaque(false);
		choicec.setLayout(null);
		choicec.setContentAreaFilled(false);
		panelc.add(choicec);
		backgroundPanel1.add(panelc);
		
		

		choicea.setText("");
		choiceb.setText("");
		choicec.setText("");


		
		WinChoiceMouseListener.dataPackage=dataPackage;
		WinChoiceMouseListener.mainGame=mainGame;
		
		/*		START OF YOUR CODE		*/
		/*****			
		 * 重要，事件监听器一定要把mainGame传入
		 ****/
		WinChoiceMouseListener clicka=new WinChoiceMouseListener(0);//设置鼠标监听器，发生0号事件
		WinChoiceMouseListener clickb=new WinChoiceMouseListener(1);//设置鼠标监听器，发生1号事件
		WinChoiceMouseListener clickc=new WinChoiceMouseListener(2);//设置鼠标监听器，发生2号事件
		
		clicka.setButton(choicea);
		clickb.setButton(choiceb);
		clickc.setButton(choicec);
		
		choicea.addMouseListener(clicka);//0号事件是 a 被点击
    	choiceb.addMouseListener(clickb);//1号事件是 b 被点击
    	choicec.addMouseListener(clickc);//2号事件是 c 被点击
		/*		END OF YOUR CODE		*/
    	    	
    	/*****************************************************************				
		 * 恢复显示，按照流程做就不会消失，之后四行不允许更改
		 ******************************************************************/
		
		
    	backgroundPanel.add(backgroundPanel1);
    	backgroundPanel.add(backgroundPanel2);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(backgroundPanel);
		frame.getContentPane().repaint();
		frame.setVisible(true);
	}

}
