package THUgame.main;

import javax.swing.*;

import THUgame.datapack.DataPack;
import THUgame.windows.WinBase;
import THUgame.windows.WinChoice;
import THUgame.windows.WinHome;
import THUgame.windows.WinInDom;
import THUgame.windows.WinMap;
import THUgame.windows.WinMorningClass;
import THUgame.windows.WinNoonClass;
import THUgame.windows.WinBackground;
import THUgame.windows.WinWelcome;
import THUgame.windows.WinOrganization;

/*
 * template version 1.1
 * 窗口管理线程
 * update:20190928 19:30
 * */

public class WindowManager extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private EventManager mainGame;
	private WinBase generator;
	private JFrame mainFrame;
	static public DataPack dataPackage;
    public void set(EventManager mainGame){
    	this.mainGame=mainGame;
    	
    }
	public WindowManager(){
		
		this.mainFrame = new JFrame();
		mainFrame.setBounds(0, 0, 1080, 720);
		mainFrame.setResizable(false);

		/******************************************************************				
		 * 为分支事件窗口注册数据包
		 * 所有需要用到数据包的事件窗口都需要注册
		 * 数据包在所有类中都是静态（共享）的
		 * 这样设计是为了使用数据包进行初始化
		 ******************************************************************/
		/*		START OF YOUR CODE		*/
		WinInDom.dataPackage=dataPackage;
		WinMap.dataPackage=dataPackage;
		WinMorningClass.dataPackage=dataPackage;
		WinNoonClass.dataPackage=dataPackage;
		WinChoice.dataPackage=dataPackage;
		WinHome.dataPackage = dataPackage;
		WinBackground.dataPackage=dataPackage;
		WinWelcome.dataPackage=dataPackage;
		/*		END OF YOUR CODE		*/
	}
	
	public void repaint(){
		/******************************************************************		
		 * 使用ID选择触发的分支事件
		 * 只需要将「游戏对象」和「窗口对象」传进构造函数即可
		 * 前提：对应的分支对象类按照模板编写
		 ******************************************************************/
		/*		START OF YOUR CODE		*/
		switch(dataPackage.ID) {
			case -1:
				generator = new WinHome(mainGame,mainFrame);
				break;
			case 0:
				generator = new WinInDom(mainGame,mainFrame);
				break;
			case 1:
				generator = new WinMorningClass(mainGame,mainFrame);
				break;	
			case 2:
				generator = new WinNoonClass(mainGame,mainFrame);
				break;	
			case 3:
				generator = new WinMap(mainGame,mainFrame);
				break;
			case 30000:
				generator = new WinChoice(mainGame,mainFrame);
				break;
			case 30001:
				generator = new WinBackground(mainGame,mainFrame);
				break;
			case 30002:
				generator = new WinWelcome(mainGame,mainFrame);
				break;
			case 20001:
				generator = new WinOrganization(mainGame, mainFrame);
				break;
		}
		/*		END OF YOUR CODE		*/
	}
}
