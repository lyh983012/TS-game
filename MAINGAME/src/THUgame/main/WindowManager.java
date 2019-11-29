package THUgame.main;

import javax.swing.*;

import THUgame.datapack.DataPack;
import THUgame.subwindows.WinEnd2050_1;
import THUgame.subwindows.WinEndAnimate;
import THUgame.subwindows.WinResearch;
import THUgame.subwindows.WinResearchBegin;
import THUgame.subwindows.WinSTA;
import THUgame.windows.WinBase;
import THUgame.windows.WinChoice;
import THUgame.windows.WinCourseRegistration;
import THUgame.windows.WinHome;
import THUgame.windows.WinInDom;
import THUgame.windows.WinInputName;
import THUgame.windows.WinMap;
import THUgame.windows.WinMorningClass;
import THUgame.windows.WinNoonClass;
import THUgame.windows.WinSaveAndLoad;
import THUgame.windows.WinBackground;
import THUgame.windows.WinWelcome;
import THUgame.subwindows.WinResearchGroupmeetingOne;
import THUgame.subwindows.WinResearchGroupmeetingTwo;
import THUgame.subwindows.WinResearchGroupmeetingThree;
import THUgame.subwindows.WinResearchGroupmeetingFour;
import THUgame.subwindows.WinResearchGroupmeetingFive;
import THUgame.subwindows.WinResearchGroupmeetingSix;
import THUgame.subwindows.WinResearchGroupmeetingSeven;
import THUgame.subwindows.WinResearchGroupmeetingEight;
import THUgame.subwindows.WinResearchMeetingNotice;
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
		WinSTA.dataPackage=dataPackage;
		WinInputName.dataPackage=dataPackage;
		WinSaveAndLoad.dataPackage=dataPackage;
		WinResearchBegin.dataPackage=dataPackage;
		WinResearchGroupmeetingOne.dataPackage=dataPackage;
		WinResearchGroupmeetingTwo.dataPackage=dataPackage;
		WinResearchGroupmeetingThree.dataPackage=dataPackage;
		WinResearchGroupmeetingFour.dataPackage=dataPackage;
		WinResearchGroupmeetingFive.dataPackage=dataPackage;
		WinResearchGroupmeetingSix.dataPackage=dataPackage;
		WinResearchGroupmeetingSeven.dataPackage=dataPackage;
		WinResearchGroupmeetingEight.dataPackage=dataPackage;
		WinCourseRegistration.dataPackage=dataPackage;
		WinEnd2050_1.dataPackage=dataPackage;
		WinEndAnimate.dataPackage=dataPackage;
		WinResearch.dataPackage=dataPackage;
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
			case 4:
				generator = new WinCourseRegistration(mainGame,mainFrame);
				break;
			case 20016:
				generator = new WinSTA(mainGame,mainFrame);
				break;
			case 21000:
				generator = new WinResearchBegin(mainGame,mainFrame);
				break;
			case 21001:
				generator = new WinResearchMeetingNotice(mainGame,mainFrame);
				break;
			case 21002:
				generator = new WinResearchGroupmeetingOne(mainGame,mainFrame);
				break;
			case 21003:
				generator = new WinResearchGroupmeetingTwo(mainGame,mainFrame);
				break;
			case 21004:
				generator = new WinResearchGroupmeetingThree(mainGame,mainFrame);
				break;
			case 21005:
				generator = new WinResearchGroupmeetingFour(mainGame,mainFrame);
				break;
			case 21006:
				generator = new WinResearchGroupmeetingFive(mainGame,mainFrame);
				break;
			case 21007:
				generator = new WinResearchGroupmeetingSix(mainGame,mainFrame);
				break;
			case 21008:
				generator = new WinResearchGroupmeetingSeven(mainGame,mainFrame);
				break;
			case 21009:
				generator = new WinResearchGroupmeetingEight(mainGame,mainFrame);
				break;
			case 21010:
				generator = new WinResearch(mainGame,mainFrame);
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
			case 30003:
				generator = new WinInputName(mainGame,mainFrame);
				break;
			case 30004:
				generator = new WinSaveAndLoad(mainGame,mainFrame);
				break;
			case 40000:
				generator= new WinEndAnimate(mainGame,mainFrame);
				break;
			case 40001:
				generator= new WinEnd2050_1(mainGame,mainFrame);
				break;
				
		}
		/*		END OF YOUR CODE		*/
	}
}
