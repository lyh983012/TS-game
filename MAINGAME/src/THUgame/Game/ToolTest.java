package THUgame.Game;

import javax.swing.JFrame;
import THUgame.tool.GifPanel;

import java.awt.EventQueue;


public class ToolTest{
	
	JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

					ToolTest window = new ToolTest();
					window.frame.setVisible(true);

			}
		});
	}

	/*************************************************************	
	 * 【构造函数】
	 * 不要新建JFrame窗口对象，而是把上层传进来的窗口对象里面的东西扔了，重新添加
	 *************************************************************/
	public ToolTest() {
		
		frame=new JFrame();
		frame.setBounds(0, 0,540,303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		/*************************************************************	
		 * 背景镶板，所有的组件都在里面
		 * 两个按钮直接用插件拖进去的
		 * 这一部分按照流程做的话就会自然消失的，推荐直接在可视化界面编辑属性
		 * 最后放一下背景
		 *************************************************************/
		GifPanel gifbox2 = new GifPanel("/imgsrc/test.gif",0,0,540,303,-1);
		frame.getContentPane().add(gifbox2);
		frame.getContentPane().repaint();
    	frame.setVisible(true);
		
	}
}

